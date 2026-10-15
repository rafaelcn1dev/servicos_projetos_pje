package br.jus.cnj.pje.criminal.services;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import com.lowagie.text.DocumentException;
import com.sun.istack.ByteArrayDataSource;

import br.jus.cnj.certificado.Signer.SignatureAlgorithm;
import br.jus.cnj.intercomunicacao.beans.Assinatura;
import br.jus.cnj.intercomunicacao.beans.AssuntoLocal;
import br.jus.cnj.intercomunicacao.beans.AssuntoProcessual;
import br.jus.cnj.intercomunicacao.beans.CabecalhoProcessual;
import br.jus.cnj.intercomunicacao.beans.CadastroIdentificador;
import br.jus.cnj.intercomunicacao.beans.Data;
import br.jus.cnj.intercomunicacao.beans.DataHora;
import br.jus.cnj.intercomunicacao.beans.DocumentoIdentificacao;
import br.jus.cnj.intercomunicacao.beans.DocumentoProcessual;
import br.jus.cnj.intercomunicacao.beans.Endereco;
import br.jus.cnj.intercomunicacao.beans.ManifestacaoProcessual;
import br.jus.cnj.intercomunicacao.beans.ModalidadeDocumentoIdentificador;
import br.jus.cnj.intercomunicacao.beans.ModalidadeGeneroPessoa;
import br.jus.cnj.intercomunicacao.beans.ModalidadePoloProcessual;
import br.jus.cnj.intercomunicacao.beans.NumeroUnico;
import br.jus.cnj.intercomunicacao.beans.OrgaoJulgador;
import br.jus.cnj.intercomunicacao.beans.Parametro;
import br.jus.cnj.intercomunicacao.beans.Parte;
import br.jus.cnj.intercomunicacao.beans.Pessoa;
import br.jus.cnj.intercomunicacao.beans.PoloProcessual;
import br.jus.cnj.intercomunicacao.beans.RequisicaoConsultaProcesso;
import br.jus.cnj.intercomunicacao.beans.RespostaConsultaProcesso;
import br.jus.cnj.intercomunicacao.beans.RespostaManifestacaoProcessual;
import br.jus.cnj.intercomunicacao.beans.TipoQualificacaoPessoa;
import br.jus.cnj.pje.criminal.enums.RegimePenaEnum;
import br.jus.cnj.pje.criminal.enums.TipoPenaExecucaoEnum;
import br.jus.cnj.pje.criminal.error.CriminalBusinessException;
import br.jus.cnj.pje.criminal.error.EntregaCertiadoPJEException;
import br.jus.cnj.pje.criminal.error.PjeEntityNotFoundException;
import br.jus.cnj.pje.criminal.model.beans.DecisaoInstanciaSuperiorBean;
import br.jus.cnj.pje.criminal.model.beans.DispositivoBean;
import br.jus.cnj.pje.criminal.model.beans.DocumentoIdentificacaoDTO;
import br.jus.cnj.pje.criminal.model.beans.DocumentoSeeuBean;
import br.jus.cnj.pje.criminal.model.beans.EventoCriminalBean;
import br.jus.cnj.pje.criminal.model.beans.FugaBean;
import br.jus.cnj.pje.criminal.model.beans.GuiaSeeuBean;
import br.jus.cnj.pje.criminal.model.beans.IncidenciaPenalBean;
import br.jus.cnj.pje.criminal.model.beans.PrisaoBean;
import br.jus.cnj.pje.criminal.model.beans.RemessaSeeuBean;
import br.jus.cnj.pje.criminal.model.beans.Resposta;
import br.jus.cnj.pje.criminal.model.beans.RespostaAssinatura;
import br.jus.cnj.pje.criminal.model.beans.RespostaAssinaturaBean;
import br.jus.cnj.pje.criminal.model.beans.RespostaSeeuBean;
import br.jus.cnj.pje.criminal.model.beans.ReuBean;
import br.jus.cnj.pje.criminal.model.beans.SentencaCondenatoriaProcessoBean;
import br.jus.cnj.pje.criminal.model.beans.SentencaPronunciaProcessoBean;
import br.jus.cnj.pje.criminal.model.beans.SolturaBean;
import br.jus.cnj.pje.criminal.model.entities.Dispositivo;
import br.jus.cnj.pje.criminal.model.entities.InformacaoCriminal;
import br.jus.cnj.pje.criminal.model.entities.Processo;
import br.jus.cnj.pje.criminal.model.entities.SeeuTribunal;
import br.jus.cnj.pje.criminal.repositories.SeeuTribunalRepository;
import br.jus.cnj.pje.criminal.seeu.extramni.ListarSentenciadoAtivoPorFiltro;
import br.jus.cnj.pje.criminal.seeu.extramni.ListarSentenciadoAtivoPorFiltroResponse;
import br.jus.cnj.pje.criminal.seeu.extramni2.TodasAreaDeVara;
import br.jus.cnj.pje.criminal.seeu.extramni2.TodasAreaDeVaraResponse;
import br.jus.cnj.pje.criminal.seeu.extramni2.TodasComarcas;
import br.jus.cnj.pje.criminal.seeu.extramni2.TodasComarcasResponse;
import br.jus.cnj.pje.criminal.util.ArquivoConversaoUtil;
import br.jus.cnj.pje.criminal.util.MniUtil;
import br.jus.cnj.pje.criminal.util.PjeLegacyClientUtil;
import br.jus.cnj.pje.criminal.util.StringUtil;
import br.jus.cnj.pje.pjecommons.utils.NumeroProcessoUtil;

@Service
public class SeeuService {
	
	private static final String PREFIXO_PARAGRAFO_UNICO = "PARAGRAFO";

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private SeeuTribunalRepository seeuTribunalRepository;
	
	@Autowired
	private MNIService mniService;
	
	@Autowired
	private SeeuExtraMNIService extraMniService;
	
	@Autowired
	private SeeuExtraMNI2Service extraMniService2;
	
	@Autowired
	private DispositivoService dispositivoService;

	@Value("${seeu.wsdl}")
	private String wsdlSeeu; 
	
	@Value("${assinador.url}")
	private String assinadorUrl;
	
	private final int CLASSE_EXECUCAO_PENA=386; //Execução de pena
	private final int ASSUNTO_EXECUCAO_PENA=7791; //Pena privativa de liberdade
	private final int ASSUNTO_EXECUCAO_PENA_PAI=7942; //Execução de penas e medidas alternativas
	private final String TIPO_DOCUMENTO_SEEU="1";
	
	private SimpleDateFormat sdf = new SimpleDateFormat("ddMMYYYY");
	private SimpleDateFormat sdfAno = new SimpleDateFormat("YYYY");
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	/**
	 * Retorna os dados do Tribunal que está se comunicando com o serviço criminal, a partir das informações
	 * da requisição. A classe SeeuTribunal guarda as informações relevantes do Tribunal para a integração
	 * com o SEEU. 
	 * @return Instância de SeeuTribunal, se encontrada na base de dados do serviço o registro correspondente.
	 * @throws CriminalBusinessException
	 */
	private SeeuTribunal getDadosTribunal() throws CriminalBusinessException {
		String idTribunal = null;
		String codigoTribunal = null;
		
		idTribunal = request.getHeader(PjeLegacyClientUtil.HEADER_PJE_LEGACY_APP);
		if(idTribunal != null) {
			String[] lista = idTribunal.split("-");
			codigoTribunal = NumeroProcessoUtil.jtrPorSiglaTribunal(lista[1].toUpperCase());
		}
		else {
			throw new CriminalBusinessException("Nao foi encontrada nenhuma sigla do PJe de origem. Sera que foi informado o header 'X-pje-legacy-app' na requisicao?");
		}
		
		SeeuTribunal dadosTribunal = seeuTribunalRepository.findByCodigoTribunal(codigoTribunal);
		if (dadosTribunal==null) {
			throw new PjeEntityNotFoundException(SeeuTribunal.class, codigoTribunal);
		}
		
		return dadosTribunal;
	}
	
	/**
	 * Retorn a senha diária que deve ser utilizada, conjuntamento com o login do Tribunal
	 * cadastrado no SEEU, para as entregas de manifestação processual àquele sistema, via MNI.
	 * Obs: código conforme manual em https://docs.seeu.pje.jus.br/tecnica/requisito-acesso.html
	 * @see https://docs.seeu.pje.jus.br/tecnica/requisito-acesso.html
	 * @return A senha gerada para o tribunal que está fazendo a requisição
	 * @throws CriminalBusinessException
	 */
	public String gerarSenha(SeeuTribunal dadosTribunal) {
		Integer codigoSistema = dadosTribunal.getCdSeeu();
		
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date dataAtual = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo")).getTime();
        String ddMM = sdf.format(dataAtual);

        String password = codigoSistema + ddMM;

        return DigestUtils.md5Hex(password);
	}
	
	/**
	 * Realiza uma entrega de manifestação processual via MNI ao SEEU.
	 * @param remessa Objeto contedo todas as informações necessárias para montar a entrega de manifestação processual ao SEEU.
	 * @param guiaHTML String contendo o HTML da guia de execução.
	 * @throws CriminalBusinessException 
	 */
	public RespostaSeeuBean entregaManifestacaoProcessulAoSEUU(RemessaSeeuBean remessa) throws CriminalBusinessException {
		
		SeeuTribunal dadosTribunal = getDadosTribunal();
		
		String idManifestante = dadosTribunal.getDsIdentificador();
		String senhaManifestante = this.gerarSenha(dadosTribunal);
		
		Processo processoCriminal 							 	= remessa.getProcessoCriminal();
		String orgaoJulgador 								 	= remessa.getOrgaoJulgador();
		Date dataDistribuicao 								 	= remessa.getDataDistribuicao();
		GuiaSeeuBean guia 									 	= remessa.getGuia();
		ReuBean reu 										 	= remessa.getReu();
		EventoCriminalBean recebimentoDenuncia				 	= remessa.getRecebimentoDenuncia();
		SentencaCondenatoriaProcessoBean sentencaCondenatoria	= remessa.getSentencaCondenatoria();
		List<FugaBean> fugas 								 	= remessa.getFugas();
		List<PrisaoBean> prisoes 							 	= remessa.getPrisoes();
		List<SolturaBean> solturas 							 	= remessa.getSolturas();
		List<DocumentoSeeuBean> documentos 					 	= remessa.getDocumentos();
		
		if (recebimentoDenuncia==null || sentencaCondenatoria==null) {
			throw new CriminalBusinessException("O recebimento da denúncia e a sentença condenatórias são eventos criminais obrigatórios");
		}
		if (sentencaCondenatoria!=null && (sentencaCondenatoria.getTipificacoes()==null || sentencaCondenatoria.getTipificacoes().size()==0)) {
			throw new CriminalBusinessException("Incidências penais não informadas na sentença condenatória");
		}
		if (guia==null) {
			throw new CriminalBusinessException("Dados da guia do SEEU não pode ser vazio");
		}
		if (reu==null) {
			throw new CriminalBusinessException("Réu não informado");
		}
		if (documentos==null) {
			throw new CriminalBusinessException("Lista de documentos não informada");
		}
		if (processoCriminal==null) {
			throw new CriminalBusinessException("Dados do processo criminal não informados");
		}
		if (orgaoJulgador==null) {
			throw new CriminalBusinessException("Órgão julgador não informado");
		}
		if (dataDistribuicao==null) {
			throw new CriminalBusinessException("Data de distribuição não informada");
		}
		
		int totalBytes = 0;
		
		RestTemplate restTemplate = new RestTemplate();
		
		DataHora dataHora = new DataHora();
		dataHora.setValue(new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()));
			
		OrgaoJulgador orgao = new OrgaoJulgador();
		orgao.setCodigoOrgao("");
		orgao.setNomeOrgao(orgaoJulgador);
		
		NumeroUnico numero = new NumeroUnico();
		numero.setValue(limpaNumeroProcesso(guia.getNumeroProcessoSeeu()));
		
		//O cabeçalho (dados básicos) da entrega ao SEEU precisa sempre ser um número com 20 zeros (não pergunte o motivo...)
		NumeroUnico numeroVazio = new NumeroUnico();
		numeroVazio.setValue(limpaNumeroProcesso(null));
		
		DataHora data = new DataHora();
		data.setValue(sdf.format(Calendar.getInstance().getTime()));
		
		String numeroProcessoCriminal = limpaNumeroProcesso(processoCriminal.getNrProcesso());
		
		String guiaHTML = MniUtil.geraGuiaExecucaoHTML(remessa, dadosTribunal);
		
		//Montar corpo do XML
		CabecalhoProcessual cabecalhoProcessual = new CabecalhoProcessual();
		cabecalhoProcessual.setClasseProcessual(CLASSE_EXECUCAO_PENA);
		cabecalhoProcessual.setCodigoLocalidade(guia.getAreaDeVara().getCodigo().toString());
		cabecalhoProcessual.setCompetencia(guia.getAreaDeVara().getCompetencia());
		cabecalhoProcessual.setDataAjuizamento(data);
		cabecalhoProcessual.setIntervencaoMP(false);
		cabecalhoProcessual.setNivelSigilo(0);
		cabecalhoProcessual.setNumero(numeroVazio);
		cabecalhoProcessual.setOrgaoJulgador(orgao);
		cabecalhoProcessual.setValorCausa(new Double(0));
		cabecalhoProcessual.getAssunto().add(getAssuntoExecucaoPena());
		cabecalhoProcessual.getPolo().add(getAutorExecucao(dadosTribunal));
		cabecalhoProcessual.getPolo().add(getReuExecucao(reu));
		
		//Montar manifestação em si
		ManifestacaoProcessual manifestacao = new ManifestacaoProcessual();
		manifestacao.setIdManifestante(idManifestante);
		manifestacao.setSenhaManifestante(senhaManifestante);
		manifestacao.setDadosBasicos(cabecalhoProcessual);
		manifestacao.setNumeroProcesso(guia.getNumeroProcessoSeeu()!=null ? numero : null);
		
		//Monta documento da guia
		DocumentoProcessual docGuia = new DocumentoProcessual();
		byte[] bytesPdfGuia = geraPdfGuiaExecucaoAssinado(restTemplate, guiaHTML);
		docGuia.setMimetype("application/pdf");
		docGuia.setDescricao("Guia de execucao.pdf.p7s");
		docGuia.setTipoDocumento(TIPO_DOCUMENTO_SEEU);
		docGuia.setDataHora(dataHora);
		docGuia.setNivelSigilo(0);
		docGuia.setMovimento(1);
		docGuia.setConteudo(convertToDataHandler(bytesPdfGuia));
		docGuia.getOutroParametro().add(montaParametro("NOME_ARQUIVO", docGuia.getDescricao()));
		docGuia.getOutroParametro().add(montaParametro("DESCRICAO_ARQUIVO", "Guia de execucao"));
		manifestacao.getDocumento().add(docGuia);
		
		//Cria chave única de origem que o SEEU exige
		Parametro parametroIdRef = new Parametro();
		parametroIdRef.setNome("ID_REF_EXTERNA");
		parametroIdRef.setValor(Base64.getEncoder().encodeToString(geraHash(bytesPdfGuia)));
		cabecalhoProcessual.getOutroParametro().add(parametroIdRef);
		
		//Busca os documentos a serem enviados
		int i=1;
		for (DocumentoSeeuBean documento: documentos) {
			i++;
			
			//Pega documento do legacy
			byte[] bytes = null;
			ResponseEntity<byte[]> response = restTemplate.getForEntity(documento.getLinkDownload(), byte[].class);
			if (response.getStatusCode()!=HttpStatus.OK) {
				throw new CriminalBusinessException("Não foi possível fazer download de '"+documento.getLinkDownload()+"'. Servidor retornou status code "+response.getStatusCodeValue());
			}
			
			//Converte para pdf, se necessário
			if (!documento.isBinario()) {
				try {
					bytes = ArquivoConversaoUtil.getPDFFromConteudoHTML(new String(response.getBody(), "ISO-8859-1")).toByteArray();
				}
				catch (UnsupportedEncodingException | DocumentException e) {
					e.printStackTrace();
					throw new CriminalBusinessException("Não foi possível converter documento HTML para PDF. Documento: "+documento.getDescricao());
				}
			}
			else {
				bytes = response.getBody();
			}
			
			//Assina o pdf no formato que o SEEU exige
			bytes = assinaPdfSeeu(restTemplate, bytes); 
			
			//Cria documento da manifestação processual
			DocumentoProcessual doc = new DocumentoProcessual();
			doc.setConteudo(convertToDataHandler(bytes));
			doc.setTipoDocumento(TIPO_DOCUMENTO_SEEU);
			doc.setMimetype("application/pdf");
			doc.setNivelSigilo(0);
			doc.setMovimento(i);
			doc.setDataHora(dataHora);
			doc.setDescricao(documento.getDescricao()+".pdf.p7s");
			doc.getOutroParametro().add(montaParametro("NOME_ARQUIVO", doc.getDescricao()));
			doc.getOutroParametro().add(montaParametro("DESCRICAO_ARQUIVO", documento.getDescricao()));
			manifestacao.getDocumento().add(doc);
			
			totalBytes += bytes.length; 
		}
		cabecalhoProcessual.setTamanhoProcesso(totalBytes);
		
		//Montar JSON das ACOES_PENAIS e EVENTOS que o SEEU espera em 'outrosParametros'
		List<Parametro> parametros = new ArrayList<Parametro>();
		parametros.add(retornaParametroAcaoPenal(remessa));
		if (prisoes!=null || fugas!=null || solturas!=null) {
			parametros.add(retornaParametroEventos(prisoes, fugas, solturas, numeroProcessoCriminal));
		}
		manifestacao.getParametros().addAll(parametros);
		
		//Fazer a comunicação MNI
		try {
			RespostaManifestacaoProcessual resposta = mniService.entregaManifestacao(manifestacao, wsdlSeeu);
			return new RespostaSeeuBean(resposta, guiaHTML, dadosTribunal);
		}
		catch (Exception e) {
			logger.error("Erro ao entregar ao SEEU a guia de execução.", e);
			throw new CriminalBusinessException(e.getMessage(), e);
		}
	}
	
	/**
	 * Monta JSON das ACOES_PENAIS que o SEEU espera no XML da entrega MNI no campo 'outrosParametros'.
	 * Vide: https://docs.seeu.pje.jus.br/tecnica/conhecimento.html
	 * @param remessa
	 * @return
	 * @throws CriminalBusinessException
	 */
	private Parametro retornaParametroAcaoPenal(RemessaSeeuBean remessa) throws CriminalBusinessException {
		
		Processo processoCriminal 							 	= remessa.getProcessoCriminal();
		String orgaoJulgador 								 	= remessa.getOrgaoJulgador();
		Date dataDistribuicao 								 	= remessa.getDataDistribuicao();
		GuiaSeeuBean guia 									 	= remessa.getGuia();
		EventoCriminalBean recebimentoDenuncia				 	= remessa.getRecebimentoDenuncia();
		SentencaCondenatoriaProcessoBean sentencaCondenatoria	= remessa.getSentencaCondenatoria();
		SentencaPronunciaProcessoBean sentencaPronuncia 	 	= remessa.getSentencaPronuncia();
		DecisaoInstanciaSuperiorBean decisaoInstanciaSuperior	= remessa.getDecisaoInstanciaSuperior();
		String observacao 									 	= remessa.getObservacao();
		
		String numeroProcessoCriminal = limpaNumeroProcesso(processoCriminal.getNrProcesso());
		
		StringBuilder acaoPenal = new StringBuilder();
		acaoPenal.append("[{");
		acaoPenal.append("'definitivaOuProvisoria':'"+(sentencaCondenatoria.getDataTransitoJulgado()==null ?"P":"D")+"',");
		try {
			acaoPenal.append("'anoProcessoCondenacao':'"+processoCriminal.getNrProcesso().substring(11, 15)+"',");
		}
		catch (StringIndexOutOfBoundsException e) {
			throw new CriminalBusinessException("Número único do processo criminal não está no formato NNNNNNN-DD.AAAA.JTR.OOOO");
		}
		acaoPenal.append("'numeroUnicoProcessoCriminal':'"+numeroProcessoCriminal+"',");
		acaoPenal.append("'varaOrigemCriminal':'"+orgaoJulgador.replaceAll("'", "")+"',");
		acaoPenal.append("'dataAutuacaoProcessoCriminal':'"+sdf.format(dataDistribuicao)+"',");
		acaoPenal.append("'dataInfracao':'"+sdf.format(processoCriminal.getDtLocalFato())+"',");
		acaoPenal.append("'dataRecebimentoDenuncia':'"+sdf.format(recebimentoDenuncia.getData())+"',");
		if (sentencaPronuncia!=null) {
			acaoPenal.append("'dataRecebimentoPronuncia':'"+sdf.format(sentencaPronuncia.getData())+"',");
		}
		acaoPenal.append("'reincidente':'"+(sentencaCondenatoria.getReincidente()!=null && sentencaCondenatoria.getReincidente()?"S":"N")+"',");
		if (sentencaCondenatoria.getDataTransitoJulgado()!=null) {
			acaoPenal.append("'dataTransitoJulgado':'"+sdf.format(sentencaCondenatoria.getDataTransitoJulgado())+"',");
		}
		if (sentencaCondenatoria.getDataTransitoAcusacao()!=null) {
			acaoPenal.append("'dataTransitoJulgadoAcusacao':'"+sdf.format(sentencaCondenatoria.getDataTransitoAcusacao())+"',");
		}
		acaoPenal.append("'dataSentenca':'"+sdf.format(sentencaCondenatoria.getDataSentenca())+"',");
		Dispositivo dispositivoPrincipal = pegaDispositivoPrincipal(sentencaCondenatoria.getTipificacoes());
		acaoPenal.append("'numeroLei':'"+dispositivoPrincipal.getNorma().getNumeroNorma()+"',");
		acaoPenal.append("'anoLei':'"+ sdfAno.format(dispositivoPrincipal.getNorma().getInicioVigencia())+"',");
		acaoPenal.append("'artigoLei':'"+dispositivoPrincipal.getArtigo()+"',");
		acaoPenal.append("'dataReferenciaManual':null,");
		acaoPenal.append("'aguardandoPrisao':'"+guia.getAguardandoPrisao()+"',");
		acaoPenal.append("'observacao':"+testIfNull(observacao)+",");
		acaoPenal.append("'listaPenaExecucao':[{");
		acaoPenal.append("'tipoPenaExecucao':"+testIfNull(TipoPenaExecucaoEnum.getPorCod(sentencaCondenatoria.getTipoPenaExecucao()).getCod() , "1")+",");
		acaoPenal.append("'anoPena':"+testIfNull(sentencaCondenatoria.getRecorrivelPrazoAnos(),"0")+",");
		acaoPenal.append("'mesPena':"+testIfNull(sentencaCondenatoria.getRecorrivelPrazoMeses(),"0")+",");
		acaoPenal.append("'diaPena':"+testIfNull(sentencaCondenatoria.getRecorrivelPrazoDias(),"0")+",");
		acaoPenal.append("'regimePena':'"+(sentencaCondenatoria.getRegime()==RegimePenaEnum.A ? "1" : (sentencaCondenatoria.getRegime()==RegimePenaEnum.S) ? "2" : "3")+"',");
		acaoPenal.append("'nomeJuiz':'"+sentencaCondenatoria.getJuizProlator()+"',");
		acaoPenal.append("'diasMulta':"+testIfNull(sentencaCondenatoria.getDiasMulta())+",");
		acaoPenal.append("'valorMulta':"+testIfNull(sentencaCondenatoria.getValorMulta())+",");
		acaoPenal.append("'pagouMulta':"+(sentencaCondenatoria.getPagouMulta()==null ? "null" : "'"+(sentencaCondenatoria.getPagouMulta()?"S":"N")+"'")+",");
		acaoPenal.append("'observacao':null,");
		acaoPenal.append("'tipoMedidaSeguranca':null,");
		acaoPenal.append("'listaItemPenaExecucao':[");
		for (IncidenciaPenalBean incidenciaPenal: sentencaCondenatoria.getTipificacoes()) {
			Dispositivo dispositivo = recuperarDispositivoPorId(incidenciaPenal.getDispositivo().getId());
			String lei = dispositivo.getNorma().getNumeroNorma().toString();
			String ano = sdfAno.format(dispositivo.getNorma().getInicioVigencia());
			String artigo = dispositivo.getArtigo();
			String paragrafo = converterEmParagrafoFormatoIdentificadorPena(dispositivo.getParagrafo());
			String inciso = dispositivo.getInciso();
			acaoPenal.append("{");
			acaoPenal.append("'numeroLei':'"+lei+"',");
			acaoPenal.append("'anoLei':'"+ano+"',");
			acaoPenal.append("'numeroArtigo':'"+artigo+"',");
			acaoPenal.append("'complementoArtigo':'',");
			acaoPenal.append("'identificadorPena':'"+lei+"."+ano+"."+artigo+"."+paragrafo+"."+inciso+"',");
			acaoPenal.append("'dataDelito':'"+sdf.format(processoCriminal.getDtLocalFato())+"',");
			acaoPenal.append("'anoPena':"+testIfNull(incidenciaPenal.getPena().getPrazoAnos(),"0")+",");
			acaoPenal.append("'mesPena':"+testIfNull(incidenciaPenal.getPena().getPrazoMeses(),"0")+",");
			acaoPenal.append("'diaPena':"+testIfNull(incidenciaPenal.getPena().getPrazoDias(),"0")+",");
			acaoPenal.append("'fracaoProgressaoRegime':1,"); // <- fração comum. O ideal seria ter esse dado no evento criminal, pois a VEP vai ter sempre que corrigir
			acaoPenal.append("'fracaoLivramentoCondicional':1,"); // <- fração comum. O ideal seria ter esse dado no evento criminal, pois a VEP vai ter sempre que corrigir
			if (incidenciaPenal.getPena().getDiasMulta()!=null && incidenciaPenal.getPena().getValorMulta()!=null) {
				acaoPenal.append("'diasMulta':'"+incidenciaPenal.getPena().getDiasMulta()+"',");
				acaoPenal.append("'valorMulta':'"+incidenciaPenal.getPena().getValorMulta()+"',");
				acaoPenal.append("'pagouMulta':"+(incidenciaPenal.getPena().getPagouMulta()==null ? "null" : "'"+(incidenciaPenal.getPena().getPagouMulta()?"S":"N")+"'")+",");		
			}
			acaoPenal.append("},");
		}
		acaoPenal.append("]}]}]");
		
		Parametro outroParametroAcoesPenais = new Parametro();
		outroParametroAcoesPenais.setNome("ACOES_PENAIS");
		outroParametroAcoesPenais.setValor(formataJsonParaFormatoXml(acaoPenal.toString()));
		
		return outroParametroAcoesPenais;
	}
	
	/**
	 * Monta JSON dos EVENTOS que o SEEU espera no XML da entrega MNI no campo 'outrosParametros'.
	 * Vide: https://docs.seeu.pje.jus.br/tecnica/conhecimento.html
	 * @return
	 */
	private Parametro retornaParametroEventos(List<PrisaoBean> prisoes, List<FugaBean> fugas, List<SolturaBean> solturas, String numeroProcessoCriminal) {
		StringBuilder eventos = new StringBuilder();
		
		eventos.append("[");
		if (prisoes!=null) {
			for (PrisaoBean prisao: prisoes) {
				eventos.append("{");
				eventos.append("'tipoEvento': 'P',");
				eventos.append("'dataEvento': '"+sdf.format(prisao.getDtPrisao())+"',");
				eventos.append("'tipoPrisao': '"+(prisao.getMotivoPrisao().getId()==1?"5":"2")+"',"); //<-Flagrante ou preventiva TODO: e os outros tipos de prisão?
				eventos.append("'motivoInterrupcao': null,");
				eventos.append("'observacao': null,");
				eventos.append("'numerosUnicosAcaoCondenatoria': ['"+numeroProcessoCriminal+"']");
				eventos.append("},");
			}
		}
		if (fugas!=null) {
			for (FugaBean fuga: fugas) {
				eventos.append("{");
				eventos.append("'tipoEvento': 'I',");
				eventos.append("'dataEvento': '"+sdf.format(fuga.getDtFuga())+"',");
				eventos.append("'tipoPrisao': null,");
				eventos.append("'motivoInterrupcao': '1',"); //<- fuga
				eventos.append("'observacao': null,");
				eventos.append("'numerosUnicosAcaoCondenatoria': null");
				eventos.append("},");
			}
		}
		if (solturas!=null) {
			for (SolturaBean soltura: solturas) {
				eventos.append("{");
				eventos.append("'tipoEvento': 'I',");
				eventos.append("'dataEvento': '"+sdf.format(soltura.getDtSoltura())+"',");
				eventos.append("'tipoPrisao': null,");
				eventos.append("'motivoInterrupcao': '17',"); //<- liberdade provisória
				eventos.append("'observacao': null,");
				eventos.append("'numerosUnicosAcaoCondenatoria': null");
				eventos.append("},");
			}
		}
		eventos.append("]");
		
		Parametro outroParametroEventos = new Parametro();
		outroParametroEventos.setNome("EVENTOS");
		outroParametroEventos.setValor(formataJsonParaFormatoXml(eventos.toString()));
		
		return outroParametroEventos;
	}

	private PoloProcessual getAutorExecucao(SeeuTribunal dadosTribunal) {
		PoloProcessual polo = new PoloProcessual();
		Parte parte = new Parte();
		Pessoa pessoa = new Pessoa();
		DocumentoIdentificacao documento = new DocumentoIdentificacao();
		Endereco endereco = new Endereco();
		CadastroIdentificador identificador = new CadastroIdentificador();
		
		documento.setTipoDocumento(ModalidadeDocumentoIdentificador.CMF);
		documento.setCodigoDocumento(dadosTribunal.getCnpj());
		pessoa.getDocumento().add(documento);
		
		endereco.setLogradouro("Não informado");
		endereco.setBairro("Não informado");
		endereco.setCidade(dadosTribunal.getDsMunicipio());
		endereco.setEstado(dadosTribunal.getDsEstado());
		endereco.setCep("Não informado");
		pessoa.getEndereco().add(endereco);
		
		pessoa.setNome(dadosTribunal.getDsParteAutoraSeeu());
		pessoa.setTipoPessoa(TipoQualificacaoPessoa.JURIDICA);
	    identificador.setValue(dadosTribunal.getCnpj());
		pessoa.setNumeroDocumentoPrincipal(identificador);
		parte.setPessoa(pessoa);
		
		polo.setPolo(ModalidadePoloProcessual.AT);
		polo.getParte().add(parte);
		
		return polo;
	}

	private PoloProcessual getReuExecucao(ReuBean reu) {
		PoloProcessual polo = new PoloProcessual();
		Parte parte = new Parte();
		Pessoa pessoa = new Pessoa();
	
		Endereco endereco = new Endereco();
		
		configurarDocumentosDeIdentificacao(reu, pessoa);
		
		if (reu.getDataNascimento()!=null) {
			Data dataNascimento = convertToDataMNI(reu.getDataNascimento());
			pessoa.setDataNascimento(dataNascimento);
		}
		
		endereco.setLogradouro(reu.getNomeLogradouro());
		endereco.setBairro(reu.getNomeBairro());
		endereco.setCidade(reu.getMunicipio());
		endereco.setEstado(reu.getEstado());
		endereco.setCep(reu.getNumeroCep().replaceAll("-", ""));
		endereco.setNumero(reu.getNumeroEndereco());
		endereco.setComplemento(reu.getComplemento());
		pessoa.getEndereco().add(endereco);
		
		pessoa.setNome(reu.getNomePessoa());
		pessoa.setNomeGenitora(reu.getNomeMae());
		pessoa.setSexo(ModalidadeGeneroPessoa.fromValue(reu.getSexo()));
		pessoa.setTipoPessoa(TipoQualificacaoPessoa.FISICA);
		pessoa.setDataObito(convertToDataMNI(reu.getDataObito()));
		pessoa.getOutroNome().addAll(reu.getOutrosNomes());
		parte.setPessoa(pessoa);
		
		polo.setPolo(ModalidadePoloProcessual.PA);
		polo.getParte().add(parte);
		
		return polo;
	}

	private Data convertToDataMNI(Date date) {
		if(date == null) {
			return null;
		}
		Data dataNascimento = new Data();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		dataNascimento.setValue(sdf.format(date));
		return dataNascimento;
	}

	private AssuntoProcessual getAssuntoExecucaoPena() {
		AssuntoProcessual assunto = new AssuntoProcessual();
		AssuntoLocal assuntoLoc = new AssuntoLocal();
		assunto.setCodigoNacional(ASSUNTO_EXECUCAO_PENA);
		assuntoLoc.setCodigoAssunto(ASSUNTO_EXECUCAO_PENA);
		assuntoLoc.setCodigoPaiNacional(ASSUNTO_EXECUCAO_PENA_PAI);
		assuntoLoc.setDescricao("Pena privativa de liberdade");
		assunto.setPrincipal(true);
		assunto.setAssuntoLocal(assuntoLoc);
		return assunto;
	}

	public RespostaManifestacaoProcessual entregaCertidaoAoPJe(RemessaSeeuBean remessa, RespostaSeeuBean resposta, String guiaHTML, SeeuTribunal dadosTribunal) throws EntregaCertiadoPJEException {
		try {
			DataHora dataHora = new DataHora();
			dataHora.setValue(new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()));
			
			NumeroUnico numeroUnico = new NumeroUnico();
			numeroUnico.setValue(limpaNumeroProcesso(remessa.getProcessoCriminal().getNrProcesso()));
			
			String html = MniUtil.geraCertidaoRemessaSeeuHTML(resposta, guiaHTML);
			
			ManifestacaoProcessual manifestacao = new ManifestacaoProcessual();
			manifestacao.setNumeroProcesso(numeroUnico);
			manifestacao.setIdManifestante(dadosTribunal.getDsLoginPjeMni());
			manifestacao.setSenhaManifestante(dadosTribunal.getDsSenhaPjeMni());
			
			DocumentoProcessual documento = new DocumentoProcessual();
			documento.setTipoDocumento(dadosTribunal.getDsCodigoTipoDocumentoPje());
			documento.setMimetype("text/html");
			documento.setNivelSigilo(0);
			documento.setDataHora(dataHora);
			documento.setConteudo(convertToDataHandler(html.getBytes("ISO-8859-1")));
			documento.getAssinatura().add(this.assinaPdfPje(html.getBytes(), dataHora));
			
			manifestacao.getDocumento().add(documento);
			
			return mniService.entregaManifestacao(manifestacao, dadosTribunal.getDsUrlPjeMni());
		}
		catch (Exception e) {
			logger.error("Erro ao entregar ao PJe a certidão de remessa do SEEU.", e);
			throw new EntregaCertiadoPJEException(e.getMessage(), e);
		}
	}
	
	public ListarSentenciadoAtivoPorFiltroResponse listarSentenciadoAtivoPorFiltro(String nomeSentenciado) throws CriminalBusinessException {
		
		SeeuTribunal dadosTribunal = getDadosTribunal();
		String idManifestante = dadosTribunal.getDsIdentificador();
		String senhaManifestante = this.gerarSenha(dadosTribunal);
		
		ListarSentenciadoAtivoPorFiltro request = new ListarSentenciadoAtivoPorFiltro();
		request.setUsuario(idManifestante);
		request.setSenha(senhaManifestante);
		request.setNome(nomeSentenciado);
		request.setPagina("1");
		
		return extraMniService.listarSentenciadoAtivoPorFiltro(request);
	}
	
	public TodasAreaDeVaraResponse listarAreasDeVara(Long idComarca) throws CriminalBusinessException {
		
		SeeuTribunal dadosTribunal = getDadosTribunal();
		String idManifestante = dadosTribunal.getDsIdentificador();
		String senhaManifestante = this.gerarSenha(dadosTribunal);
		
		TodasAreaDeVara request = new TodasAreaDeVara();
		request.setSistemaTribunal(idManifestante);
		request.setSystemPass(senhaManifestante);
		request.setCodigoTribunal(new Long(idManifestante.substring(1)));
		request.setCodigoComarca(idComarca);
		
		try {
			return extraMniService2.listarAreasDeVara(request);
		} catch (Exception e) {
			throw new CriminalBusinessException(e.getMessage(), e);
		}
	}
	
	public TodasComarcasResponse listarComarcas() throws CriminalBusinessException {
		
		SeeuTribunal dadosTribunal = getDadosTribunal();
		String idManifestante = dadosTribunal.getDsIdentificador();
		String senhaManifestante = this.gerarSenha(dadosTribunal);
		
		TodasComarcas request = new TodasComarcas();
		request.setSistemaTribunal(idManifestante);
		request.setSystemPass(senhaManifestante);
		request.setCodigoTribunal(new Long(idManifestante.substring(1)));
		
		try {
			return extraMniService2.listarComarcas(request);
		} catch (Exception e) {
			throw new CriminalBusinessException(e.getMessage(), e);
		}
	}
	
	/**
	 * Assina os bytes do documento informado com o certificado A1 do CNJ.
	 */
	private Assinatura assinaPdfPje(byte[] bytes, DataHora dataHora) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<RespostaAssinatura> respostaAssinatura = restTemplate.getForEntity(assinadorUrl + "/" + DigestUtils.md5Hex(bytes), RespostaAssinatura.class);
		
		if (respostaAssinatura.getStatusCode()!=HttpStatus.OK) {
			throw new CriminalBusinessException("Não foi possível reassinar o documento. Servidor retornou status code "+respostaAssinatura.getStatusCodeValue());
		}
		
		if (respostaAssinatura.getBody().getCode()!=200) {
			throw new CriminalBusinessException("Não foi possível reassinar o documento. Servidor retornou "+respostaAssinatura.getBody().getMessages().get(0));
		}
		
		Assinatura result = new Assinatura();
		byte[] assinatura = respostaAssinatura.getBody().getData().getAssinatura().getBytes();
		result.setAssinatura(new String(Base64.getEncoder().encode(assinatura)));
		result.setAlgoritmoHash(SignatureAlgorithm.MD5withRSA.name());
		result.setCadeiaCertificado(respostaAssinatura.getBody().getData().getCadeiaCertificado());
		result.setCodificacaoCertificado("PkiPath");
		result.setDataAssinatura(dataHora);
		
		return result;
	}

	/**
	 * Assina o PDF informado no formato envelopado que o SEEU exige (CAdES).
	 * @throws CriminalBusinessException
	 */
	private byte[] assinaPdfSeeu(RestTemplate restTemplate, byte[] bytes) throws CriminalBusinessException {
		ResponseEntity<RespostaAssinaturaBean> respostaAssinatura = restTemplate.postForEntity(assinadorUrl, bytes, RespostaAssinaturaBean.class);
		if (respostaAssinatura.getStatusCode()!=HttpStatus.OK) {
			throw new CriminalBusinessException("Não foi possível reassinar o documento. Servidor retornou status code "+respostaAssinatura.getStatusCodeValue());
		}
		if (respostaAssinatura.getBody().getCode()!=200) {
			throw new CriminalBusinessException("Não foi possível reassinar o documento. Servidor retornou "+respostaAssinatura.getBody().getMessages().get(0));
		}
		return Base64Utils.decodeFromString(respostaAssinatura.getBody().getData());
	}
	
	private byte[] geraPdfGuiaExecucaoAssinado(RestTemplate restTemplate, String html) throws CriminalBusinessException {
		byte[] bytesGuia = null;
		try {
			bytesGuia = ArquivoConversaoUtil.getPDFFromConteudoHTML(html).toByteArray();
		} catch (UnsupportedEncodingException | DocumentException e1) {
			e1.printStackTrace();
			throw new CriminalBusinessException("Erro ao tentar gerar o PDF da guia de execução - ArquivoConversaoUtil.getPDFFromConteudoHTML(guiaHTML)");
		}
		return assinaPdfSeeu(restTemplate, bytesGuia);
	}
	
	private Dispositivo pegaDispositivoPrincipal(List<IncidenciaPenalBean> tipificacoes) {
		DispositivoBean dispositivoBean = tipificacoes.get(0).getDispositivo();
		return recuperarDispositivoPorId(dispositivoBean.getId());

	}

	/**
	 * Gera hash para ser usado no preenchimento do campo ID_REF_EXTERNA
	 * @param valor
	 * @return
	 * @throws CriminalBusinessException
	 */
	private byte[] geraHash(byte[] valor) throws CriminalBusinessException {
		try {
			return MessageDigest.getInstance("SHA-1").digest(valor);
		}
		catch (NoSuchAlgorithmException e) {
			throw new CriminalBusinessException("Algoritmo SHA-1 não disponível para gerar HASH");
		}
	}
	
	private Parametro montaParametro(String nome, String valor) {
		Parametro parametro = new Parametro();
		parametro.setNome(nome);
		parametro.setValor(valor);
		return parametro;
	}
	
	private DataHandler convertToDataHandler(byte[] bytes) {
		return new DataHandler(new ByteArrayDataSource(bytes, "application/pdf"));
	}
	
	private String testIfNull(Object valor) {
		return valor!=null ? "'"+valor.toString()+"'" : "null";
	}
	
	private String testIfNull(Integer valor, String valorIfNull) {
		return valor!=null ? "'"+valor.toString()+"'" : "'"+valorIfNull+"'";
	}
	
	private String formataJsonParaFormatoXml(String json) {
		return json.replaceAll("'", "\"").replaceAll(",}", "}").replaceAll(",]", "]");
	}
	
	private String limpaNumeroProcesso(String valor) {
		return valor!=null ? valor.replaceAll("\\.", "").replaceAll("-", "") : "00000000000000000000";
	}

	public Resposta entregarManifestacoes(RemessaSeeuBean remessa) throws CriminalBusinessException {
		
		Resposta resp = new Resposta();

		try {

			RespostaSeeuBean resposta = this.entregaManifestacaoProcessulAoSEUU(remessa);

			resp.setNumeroProcessoSeeu(resposta.getNumeroProcessoExecucao());
			
			resp.getMessage().add("Sucesso ao enviar manifestação processual para o SEEU.");

			RespostaManifestacaoProcessual entregaCertidaoAoPJe = this.entregaCertidaoAoPJe(remessa, resposta, resposta.getGuiaHTML(), resposta.getDadosTribunal());
			
			resp.setNumeroProcessoPje(entregaCertidaoAoPJe.getProtocoloRecebimento());
			
			resp.getMessage().add("Sucesso ao enviar certidão processual para o PJE.");
			
			return resp;
			
		} catch (CriminalBusinessException e) {
			throw e;
			
		} catch (EntregaCertiadoPJEException e) {
			logger.error("Erro ao entregar manifestações: " + e.getMessage(), e);
			resp.getMessage().add("Erro ao entregar certidão ao PJE: " + e.getMessage());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return resp;
	}

	private Dispositivo recuperarDispositivoPorId(Long id) {
		return dispositivoService.recuperarDispositivoPorId(id);
	}

    private String converterEmParagrafoFormatoIdentificadorPena(String paragrafo) {
        if (paragrafo == null) {
            return "0";
        }
        if (StringUtil.normalize(paragrafo.toUpperCase()).contains(PREFIXO_PARAGRAFO_UNICO)) {
            return "1";
        }
        return paragrafo;
    }

	private void configurarDocumentosDeIdentificacao(ReuBean reu, Pessoa pessoa) {
		if (reu.getCpf() != null) {
			String cpf = reu.getCpf().replaceAll("\\.", "").replaceAll("-", "");
			DocumentoIdentificacao documento = criarDocumentoIdentificacao("CPF", cpf);
			pessoa.setNumeroDocumentoPrincipal(new CadastroIdentificador());
			pessoa.getNumeroDocumentoPrincipal().setValue(cpf);
			pessoa.getDocumento().add(documento);
		}

		if (reu.getDocumentosIdentificacao() != null) {
			for (DocumentoIdentificacaoDTO docIdentificacao : reu.getDocumentosIdentificacao()) {
				if(!docIdentificacao.isCPF()) {
					DocumentoIdentificacao documento = criarDocumentoIdentificacao(docIdentificacao.getTipo(),
							docIdentificacao.getNumero());
					if (documento != null) {
						pessoa.getDocumento().add(documento);
					}
				}
				
			}
		}
	}

	private DocumentoIdentificacao criarDocumentoIdentificacao(String codigo, String numero) {
		ModalidadeDocumentoIdentificador modalidade = new CorrelacaoDocumentoIdentificacaoMNIService()
				.converter(codigo);
		DocumentoIdentificacao documento = null;
		if (modalidade != null) {
			documento = new DocumentoIdentificacao();
			documento.setTipoDocumento(modalidade);
			documento.setCodigoDocumento(numero);
		}
		return documento;
	}

	public RespostaConsultaProcesso consultarProcessoSeeu(String numeroProcesso, boolean incluirCabecalho) throws CriminalBusinessException, Exception{
		RequisicaoConsultaProcesso requisicao = new RequisicaoConsultaProcesso();
		SeeuTribunal dadosTribunal = getDadosTribunal();
		requisicao.setIdConsultante(dadosTribunal.getDsIdentificador());
		requisicao.setSenhaConsultante(this.gerarSenha(dadosTribunal));
		requisicao.setIncluirCabecalho(incluirCabecalho);
		requisicao.setNumeroProcesso(createNumeroUnico(numeroProcesso));
		requisicao.setMovimentos(false);
		requisicao.setIncluirDocumentos(false);
		RespostaConsultaProcesso resposta = mniService.consultarProcesso(requisicao, wsdlSeeu);
		return resposta;
	}

	private NumeroUnico createNumeroUnico(String numeroProcesso) {
		NumeroUnico numeroUnico = new NumeroUnico();
		numeroUnico.setValue(numeroProcesso);
		return numeroUnico;
	}


}

