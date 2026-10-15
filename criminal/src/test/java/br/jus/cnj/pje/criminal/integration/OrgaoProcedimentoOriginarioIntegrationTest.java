package br.jus.cnj.pje.criminal.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.jus.cnj.pje.criminal.CriminalApplication;
import br.jus.cnj.pje.criminal.model.entities.Municipio;
import br.jus.cnj.pje.criminal.model.entities.OrgaoProcedimentoOriginario;
import br.jus.cnj.pje.criminal.model.entities.TipoOrigem;
import br.jus.cnj.pje.criminal.repositories.MunicipioRepository;
import br.jus.cnj.pje.criminal.repositories.TipoOrigemRepository;
import br.jus.cnj.pje.criminal.util.PjeLegacyClientUtil;
import br.jus.cnj.pje.pjecommons.model.enums.TribunalEnum;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CriminalApplication.class)
@AutoConfigureMockMvc
public class OrgaoProcedimentoOriginarioIntegrationTest {

    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private TipoOrigemRepository tipoOrigemRepository;
    
    @Autowired
    private MunicipioRepository municipioRepository;

    @Test
    public void test0_criarOrgaoProcedimentoOriginario() throws Exception {
    	OrgaoProcedimentoOriginario orgao = this.getOrgaoProcedimentoOriginario();
    	
    	mvc.perform(
    			post("/orgaosProcedimentoOriginario")
				.contentType(MediaType.APPLICATION_JSON)
				.header(PjeLegacyClientUtil.HEADER_PJE_LEGACY_APP, "pje-tjma-1g")
				.content(this.getMapper().writeValueAsString(orgao)))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.codigoTribunal", is("810")))
				.andDo(print());
    }    
    
    @Test
    public void test1_filtroPorTenant() throws Exception {
    	mvc.perform(
    			get("/orgaosProcedimentoOriginario")
				.contentType(MediaType.APPLICATION_JSON)
				.header(PjeLegacyClientUtil.HEADER_PJE_LEGACY_APP, "pje-tjma-1g"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$[0].codigoTribunal", is("810")))
				.andDo(print());
    }
    
    private OrgaoProcedimentoOriginario getOrgaoProcedimentoOriginario() {
    	OrgaoProcedimentoOriginario orgao = new OrgaoProcedimentoOriginario();
    	
    	orgao.setTipoOrigem(this.getTipoOrigem());
    	orgao.setAtivo(true);
    	orgao.setCep("00000-000");
    	orgao.setDsCodOrigem("123123");
    	orgao.setDsNomeOrgao("Orgao originario de teste");
    	orgao.setNmBairro("Bairro teste");
    	orgao.setNmLogradouro("Logradouro teste");
    	orgao.setMunicipio(this.getMunicipio());
    	
    	return orgao;
    }
    
	private TipoOrigem getTipoOrigem() {
		TipoOrigem tipoOrigem = new TipoOrigem();
		
		tipoOrigem.setDsTipoOrigem("Tipo origem de teste");
		tipoOrigem.setInObrigatorioNumeroOrigem(false);
		tipoOrigem.setAtivo(true);
		tipoOrigem.setCodigoTribunal(TribunalEnum.TJMA.getJtr());
		
		tipoOrigem = this.tipoOrigemRepository.save(tipoOrigem);
		
		return tipoOrigem;
	}
	
	private Municipio getMunicipio() {
		Municipio municipio = new Municipio();
		
		municipio.setCodigoIbge("000000");
		municipio.setMunicipio("Municipio TESTE");
		municipio.setUf("DF");
		
		municipio = this.municipioRepository.save(municipio);
		
		return municipio;
	}
    
	private ObjectMapper getMapper() {
		ObjectMapper mapper = new ObjectMapper();
		return mapper;
	}
	
}
