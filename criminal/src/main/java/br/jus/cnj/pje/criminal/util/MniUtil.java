package br.jus.cnj.pje.criminal.util;

import java.io.FileInputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.HtmlUtils;

import br.jus.cnj.pje.criminal.enums.RegimePenaEnum;
import br.jus.cnj.pje.criminal.model.beans.DecisaoInstanciaSuperiorBean;
import br.jus.cnj.pje.criminal.model.beans.EventoCriminalBean;
import br.jus.cnj.pje.criminal.model.beans.FugaBean;
import br.jus.cnj.pje.criminal.model.beans.IncidenciaPenalBean;
import br.jus.cnj.pje.criminal.model.beans.PrisaoBean;
import br.jus.cnj.pje.criminal.model.beans.RemessaSeeuBean;
import br.jus.cnj.pje.criminal.model.beans.RespostaSeeuBean;
import br.jus.cnj.pje.criminal.model.beans.ReuBean;
import br.jus.cnj.pje.criminal.model.beans.SentencaCondenatoriaProcessoBean;
import br.jus.cnj.pje.criminal.model.beans.SentencaPronunciaProcessoBean;
import br.jus.cnj.pje.criminal.model.beans.SolturaBean;
import br.jus.cnj.pje.criminal.model.entities.Processo;
import br.jus.cnj.pje.criminal.model.entities.SeeuTribunal;


public class MniUtil {

	/**
	 * Retorna o HTML da guia de execução a ser encaminhada ao SEEU.
	 * @param remessa
	 * @param dadosTribunal
	 * @return
	 */
	public static String geraGuiaExecucaoHTML(RemessaSeeuBean remessa, SeeuTribunal dadosTribunal) {
		
		Processo processoCriminal 							 	= remessa.getProcessoCriminal();
		String orgaoJulgador 								 	= remessa.getOrgaoJulgador();
		Date dataDistribuicao 								 	= remessa.getDataDistribuicao();
		ReuBean reu 										 	= remessa.getReu();
		EventoCriminalBean recebimentoDenuncia				 	= remessa.getRecebimentoDenuncia();
		SentencaCondenatoriaProcessoBean sentencaCondenatoria	= remessa.getSentencaCondenatoria();
		SentencaPronunciaProcessoBean sentencaPronuncia 	 	= remessa.getSentencaPronuncia();
		DecisaoInstanciaSuperiorBean decisaoInstanciaSuperior	= remessa.getDecisaoInstanciaSuperior();
		String observacao 									 	= remessa.getObservacao();
		List<FugaBean> fugas 								 	= remessa.getFugas();
		List<PrisaoBean> prisoes 							 	= remessa.getPrisoes();
		List<SolturaBean> solturas 							 	= remessa.getSolturas();
		String tribunal											= dadosTribunal.getDsTribunal();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		
	
		StringBuilder html = new StringBuilder();
		
		html.append("<html>");
		html.append("<head>");
		html.append("<style>");
		html.append("	* { font-family: Verdana, Tahoma, sans-serif; font-size: 16px; }");
		html.append("	table { width: 100%; }");
		html.append("	td, th { vertical-align: top; }");
		html.append("	th { width: 20%; text-align: left; }");
		html.append("	td { width: 30%; }");
		html.append("	.panel { border: 1px solid #ccc; margin: 20px; }");
		html.append("	.panel-heading { background-color: #eee; color: #000; border-bottom: 1px solid #ccc; }");
		html.append("	.panel-heading, .panel-body { padding: 10px; }");
		html.append("	.cabecalho { text-align: center; font-weight: bold; }");
		html.append("	.cabecalho-guia { font-weight: larger; text-transform: capitalize; }");
		html.append("	.nome-reu { text-decoration: underline; }");
		html.append("	.aviso-assinatura-automatica { text-align: center; }");
		html.append("</style>");
		html.append("</head>");
		html.append("<body>");
		html.append("	<p class='cabecalho'>");
		html.append("		PODER JUDICI&Aacute;RIO<br />");
		html.append("		"+HtmlUtils.htmlEscape(tribunal.toUpperCase())+"<br />");
		html.append("		"+HtmlUtils.htmlEscape(orgaoJulgador.toUpperCase())+"<br /><br /><br />");
		html.append("		<span class='cabecalho-guia'>GUIA DE EXECU&Ccedil;&Atilde;O</span><br />");
		html.append("	</p>");
		html.append("	<div class='panel panel-primary'>");
		html.append("		<div class='panel-heading'>Parte condenada</div>");
		html.append("		<div class='panel-body'>");
		html.append("			<table>");
		html.append("				<tr>");
		html.append("					<th>Nome:</th>");
		html.append("					<td class='nome-reu'>"+HtmlUtils.htmlEscape(reu.getNomePessoa())+"</td>");
		html.append("					<th>Nome da m&atilde;e:</th>");
		html.append("					<td>"+HtmlUtils.htmlEscape(reu.getNomeMae())+"</td>");
		html.append("				</tr>");
		html.append("				<tr>");
		html.append("					<th>Data de nascimento:</th>");
		html.append("					<td>"+sdf.format(reu.getDataNascimento())+"</td>");
		html.append("					<th>CPF:</th>");
		html.append("					<td>"+testIsNull(reu.getCpf())+"</td>");
		html.append("				</tr>");
		html.append("               <tr>");
		html.append("					<th>Sexo:</th>");
		html.append("					<td colspan='3'>"+reu.getSexo()+"</td>");
		html.append("				</tr>");
		html.append("				<tr>");
		html.append("					<th>Endere&ccedil;o:</th>");
		html.append("					<td colspan='3'>");
		html.append("						"+HtmlUtils.htmlEscape(testIsNull(reu.getNomeLogradouro()))+",");
		html.append("						"+HtmlUtils.htmlEscape(testIsNull(reu.getNomeBairro()))+",");
		html.append("						"+HtmlUtils.htmlEscape(testIsNull(reu.getMunicipio()))+"/");
		html.append("						"+HtmlUtils.htmlEscape(testIsNull(reu.getEstado()))+" - ");
		html.append("						"+HtmlUtils.htmlEscape(testIsNull(reu.getNumeroCep())));
		html.append("					</td>");
		html.append("				</tr>");
		html.append("				<tr>");
		html.append("					<th valign='top'>Pris&otilde;es:</th>");
		html.append("					<td colspan='3'>");
		if (prisoes==null) { 
			html.append("						<i>Sem registro</i>");
		}
		else {
			for (PrisaoBean prisao: prisoes) {
				html.append("						<div>");
				html.append("							"+sdf.format(prisao.getDtPrisao())+" - ");
				html.append("							"+HtmlUtils.htmlEscape(testIsNull(prisao.getMotivoPrisao().getMotivoPrisao()))+" - ");
				html.append("							"+HtmlUtils.htmlEscape(testIsNull(prisao.getUnidadePrisional().getDsUnidadePrisional())));
				html.append("						</div>");
			}
		}
		html.append("					</td>");
		html.append("				</tr>");
		html.append("				<tr>");
		html.append("					<th valign='top'>Solturas:</th>");
		html.append("					<td colspan='3'>");
		if (solturas==null) { 
			html.append("						<i>Sem registro</i>");
		}
		else {
			for (SolturaBean soltura: solturas) {
				html.append("						<div>");
				html.append("							"+sdf.format(soltura.getDtSoltura())+" - ");
				html.append("							"+HtmlUtils.htmlEscape(testIsNull(soltura.getMotivo().getMotivoSoltura()))+" - ");
				html.append("							"+HtmlUtils.htmlEscape(testIsNull(soltura.getUnidadePrisional().getDsUnidadePrisional())));
				html.append("						</div>");
			}
		}
		html.append("					</td>");
		html.append("				</tr>");
		html.append("				<tr>");
		html.append("					<th valign='top'>Fugas:</th>");
		html.append("					<td colspan='3'>");
		if (fugas==null ) {
			html.append("							<i>Sem registro</i>");
		}
		else {
			for (FugaBean fuga: fugas) {
				html.append("						<div>");
				html.append("							"+sdf.format(fuga.getDtFuga())+" - ");
				html.append("							"+HtmlUtils.htmlEscape(testIsNull(fuga.getDsFuga()))+" - ");
				html.append("							"+HtmlUtils.htmlEscape(testIsNull(fuga.getUnidadePrisional().getDsUnidadePrisional())));
				html.append("						</div>");
			}
		}
		html.append("					</td>");
		html.append("				</tr>");
		html.append("			</table>");
		html.append("		</div>");
		html.append("	</div>");
		html.append("	<div class='panel panel-primary'>");
		html.append("		<div class='panel-heading'>A&ccedil;&atilde;o penal</div>");
		html.append("		<div class='panel-body'>");
		html.append("			<table>");
		html.append("				<tr>");
		html.append("					<th>Processo:</th>");
		html.append("					<td colspan='3'>"+processoCriminal.getNrProcesso()+"</td>");
		html.append("				</tr>");
		html.append("				<tr>");
		html.append("					<th valign='top'>Vara:</th>");
		html.append("					<td>"+HtmlUtils.htmlEscape(orgaoJulgador)+"</td>");
		html.append("					<th valign='top'>Juiz da senten&ccedil;a:</th>");
		html.append("					<td valign='top'>"+HtmlUtils.htmlEscape(sentencaCondenatoria.getJuizProlator())+"</td>");
		html.append("				</tr>");
		html.append("				<tr>");
		html.append("					<th>Data autua&ccedil;&atilde;o:</th>");
		html.append("					<td>"+sdf.format(dataDistribuicao)+"</td>");
		html.append("					<th>Data do fato:</th>");
		html.append("					<td>"+sdf.format(processoCriminal.getDtLocalFato())+"</td>");
		html.append("				</tr>");
		html.append("				<tr>");
		html.append("					<th>Data do recebimento da den&uacute;cia:</th>");
		html.append("					<td>"+sdf.format(recebimentoDenuncia.getData())+"</td>");
		html.append("					<th>Data senten&ccedil;a:</th>");
		html.append("					<td>"+sdf.format(sentencaCondenatoria.getDataSentenca())+"</td>");
		html.append("				</tr>");
		html.append("				<tr>");
		html.append("				<th>&Eacute; reincidente:</th>");
		html.append("					<td>"+(sentencaCondenatoria.getReincidente()==null || sentencaCondenatoria.getReincidente()?"Sim":"N&atilde;o")+"</td>");
		html.append("					<th>Data da pron&uacute;cia:</th>");
		html.append("					<td>"+(sentencaPronuncia!=null ? sdf.format(sentencaPronuncia.getDataPronuncia()) : "<i>N&atilde;o h&aacute; senten&ccedil;a de pron&uacute;ncia</i>")+"</td>");
		html.append("				</tr>");
		html.append("				<tr>");
		html.append("					<th>Data do tr&acirc;nsito em julgado:</th>");
		html.append("					<td>"+(sentencaCondenatoria.getDataTransitoJulgado()!=null ? sdf.format(sentencaCondenatoria.getDataTransitoJulgado()) : "<i>N&atilde;o transitou</i>")+"</td>");
		html.append("					<th>Data do tr&acirc;nsito para a acusa&ccedil;&atilde;o:</th>");
		html.append("					<td>"+(sentencaCondenatoria.getDataTransitoAcusacao()!=null ? sdf.format(sentencaCondenatoria.getDataTransitoAcusacao()) : "<i>N&atilde;o transitou</i>")+"</td>");
		html.append("				</tr>");
		html.append("			</table>");
		html.append("	</div>");
		html.append("	</div>");
		if (decisaoInstanciaSuperior!=null) { //TODO: terminar essa parte dos recursos
			html.append("	<div class='panel panel-primary'>");
			html.append("		<div class='panel-heading'>Recurso</div>");
			html.append("		<div class='panel-body'>");
			html.append("			<table>");
			html.append("				<tr>");
			html.append("					<th>Houve recurso:</th>");
			html.append("					<td></td>");
			html.append("					<th>Quem recorreu:</th>");
			html.append("					<td></td>");
			html.append("				</tr>");
			html.append("				<tr>");
			html.append("					<th>Data encaminhado recurso ao Tribunal:</th>");
			html.append("					<td></td>");
			html.append("					<th>Data devolu&ccedil;&atilde;o do Tribunal:</th>");
			html.append("					<td></td>");
			html.append("				</tr>");
			html.append("				<tr>");
			html.append("					<th>Ano recurso:</th>");
			html.append("					<td></td>");
			html.append("					<th>Data autua&ccedil;&atilde;o recurso:</th>");
			html.append("					<td></td>");
			html.append("				</tr>");
			html.append("				<tr>");
			html.append("					<th>Data decis&atilde;o recurso:</th>");
			html.append("					<td></td>");
			html.append("					<th>&Oacute;rg&atilde;o julgador:</th>");
			html.append("					<td></td>");
			html.append("				</tr>");
			html.append("				<tr>");
			html.append("					<th>C&acirc;mara julgadora:</th>");
			html.append("					<td>camara</td>");
			html.append("					<th>N&uacute;mero acord&atilde;o:</th>");
			html.append("					<td></td>");
			html.append("				</tr>");
			html.append("				<tr>");
			html.append("					<th>Recurso concedido?</th>");
			html.append("					<td></td>");
			html.append("				</tr>");
			html.append("			</table>");
			html.append("		</div>");
			html.append("	</div>");
		}
		html.append("	<div class='panel panel-primary'>");
		html.append("		<div class='panel-heading'>Condena&ccedil;&atilde;o</div>");
		html.append("		<div class='panel-body'>");
		html.append("			<table>");
		html.append("				<tr>");
		html.append("					<th>Total da pena:</th>");
		html.append("					<td>");
		html.append("						<strong>");
		html.append("							"+testIsNull(sentencaCondenatoria.getRecorrivelPrazoAnos()," anos "));
		html.append("							"+testIsNull(sentencaCondenatoria.getRecorrivelPrazoMeses()," meses "));
		html.append("							"+testIsNull(sentencaCondenatoria.getRecorrivelPrazoDias()," dias "));
		html.append("						</strong>");
		html.append("					</td>");
		html.append("					<th>Regime da pena:</th>");
		html.append("					<td>");
		html.append("						"+(sentencaCondenatoria.getRegime()==RegimePenaEnum.A ? "Aberto" : (sentencaCondenatoria.getRegime()==RegimePenaEnum.S ? "Semiaberto" : "Fechado")));
		html.append("					</td>");		
		html.append("				</tr>");
		html.append("				<tr>");
		html.append("					<th>Tipo execu&ccedil;&atilde;o:</th>");
		html.append("					<td>"+(sentencaCondenatoria.getDataTransitoJulgado()!=null ? "Definitiva" : "Provis&oacute;ria")+"</td>");
		html.append("					<th>Tipo medida de seguran&ccedil;a:</th>");
		html.append("					<td><i>Sem registro</i></td>");
		html.append("				</tr>");
		html.append("				<tr>");
		html.append("					<th>Dias-multa:</th>");
		html.append("					<td>"+testIsNull(sentencaCondenatoria.getDiasMulta())+"</td>");
		html.append("					<th>Valor da multa:</th>");
		html.append("					<td>"+(sentencaCondenatoria.getValorMulta()==null ? "-" : nf.format(sentencaCondenatoria.getValorMulta()))+"</td>");
		html.append("				</tr>");
		html.append("				<tr>");
		html.append("					<th>Pagou multa?</th>");
		html.append("					<td>"+(sentencaCondenatoria.getPagouMulta()==null ? "-" : (sentencaCondenatoria.getPagouMulta() ? "Sim" : "N&atilde;o"))+"</td>");
		html.append("				</tr>");
		html.append("			</table>");
		html.append("		</div>");
		html.append("	</div>");
		html.append("	<div class='panel panel-primary'>");
		html.append("		<div class='panel-heading'>Incid&ecirc;ncias penais:</div>");
		html.append("		<div class='panel-body'>");
		html.append("			<table>");
		html.append("				<tr>");
		html.append("					<th>Tipifica&ccedil;&atilde;o</th>");
		html.append("					<th>Pena</th>");
		html.append("					<th>Multa</th>");
		html.append("				</tr>");
		for (IncidenciaPenalBean tipificacao: sentencaCondenatoria.getTipificacoes()) {
			html.append("			<tr>");
			html.append("				<td>");
			html.append("					"+HtmlUtils.htmlEscape(tipificacao.getDispositivo().getTextoFinal()));
			html.append("				</td>");
			html.append("				<td>");
			html.append("					"+testIsNull(tipificacao.getPena().getPrazoAnos(), " anos "));
			html.append("					"+testIsNull(tipificacao.getPena().getPrazoMeses(), " meses"));
			html.append("					"+testIsNull(tipificacao.getPena().getPrazoDias(), " dias "));
			html.append("				</td>");
			html.append("				<td>");
			if (tipificacao.getPena().getDiasMulta()!=null && tipificacao.getPena().getValorMulta()!=null) {
				html.append("				"+tipificacao.getPena().getDiasMulta()+" dias-multa, ");
				html.append("				"+ nf.format(tipificacao.getPena().getValorMulta())+", ");
				html.append("				"+(tipificacao.getPena().getPagouMulta()!=null && tipificacao.getPena().getPagouMulta() ? "j&aacute; paga" : "n&atilde;o paga"));
			}
			else {
				html.append("				<i>Sem multa</i>");
			}
			html.append("				</td>");
			html.append("			</tr>");
		}
		html.append("			</table>");
		html.append("		</div>");
		html.append("	</div>");
		html.append("	<div class='panel panel-primary'>");
		html.append("		<div class='panel-heading'>Observa&ccedil;&atilde;o:</div>");
		html.append("		<div class='panel-body'>");
		html.append("			"+testIsNull(observacao));
		html.append("		</div>");
		html.append("	</div>");
		html.append("	<p class='aviso-assinatura-automatica'>Documento assinado de forma autom&aacute;tica com certificado institucional, nos termos do artigo 4&deg;-D da Resolu&ccedil;&atilde;o n&deg; 185/2013 do CNJ.</p>");
		html.append("</body>");
		html.append("</html>");
		
		return html.toString();
	}

	public static String geraCertidaoRemessaSeeuHTML(RespostaSeeuBean resposta, String guiaHTML) {
		
		String data = new SimpleDateFormat("dd/MM/YYYY HH:mm").format(Calendar.getInstance().getTime());
		int inicioBody = guiaHTML.indexOf("<body>");

		StringBuilder sb = new StringBuilder(guiaHTML);
		
		String certidaoHTML = 
			"<div class='certidao-pje'>"+
		    "    <p style='text-align: center'>"+
		    "        <span style='font-weight: bold; font-size: larger;'>CERTID&Atilde;O AUTOM&Aacute;TICA</span></br></br></br>"+
		    "		 Processo execução SEEU: <b>"+resposta.getNumeroProcessoExecucao()+"</b>"+
		    "    </p><br />"+ obterProtocoloSeeu(resposta.getProtocoloSeeu()) +
		    "    <p style='text-align: justify'>"+
		    "        Remessa realizada com sucesso em "+data+".<br/>"+
		    "        A guia de execu&ccedil;&atilde;o encaminhada segue em anexo."+
		    "    </p></br>"+
		    "    <p style='text-align: center'><i>Documento assinado de forma autom&aacute;tica com certificado institucional, nos termos do artigo 4&deg;-D da Resolu&ccedil;&atilde;o n&deg; 185/2013 do CNJ.</i></p>"+
		    "    <br/><br/>"+
		    "    <hr/>"+
		    "    <br/><br/>"+
		    "</div>";
		
		return sb.insert(inicioBody+6, certidaoHTML).toString();
	}
	
	private static String obterProtocoloSeeu(String protocoloSeeu) {
		
		if(StringUtils.isNotBlank(protocoloSeeu)) {
			return  "    <p style='text-align: center'>"+
				    "		 Protocolo SEEU: <b>"+protocoloSeeu+"</b>"+
				    "    </p><br />";
		}
		
		return "";		
	}

	private static String testIsNull(Object valor) {
		return valor!=null ? valor.toString() : "-";
	}
	
	private static String testIsNull(Integer valor, String complemento) {
		return valor!=null ? valor.toString()+complemento : "";
	}
}
