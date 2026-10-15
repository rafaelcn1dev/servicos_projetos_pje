package br.jus.cnj.pje.criminal.unit.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.jus.cnj.pje.criminal.controllers.v1.TipoOrigemRestController;
import br.jus.cnj.pje.criminal.model.entities.TipoOrigem;
import br.jus.cnj.pje.criminal.services.TipoOrigemService;

@RunWith(SpringRunner.class)
@WebMvcTest(TipoOrigemRestController.class)
public class TipoOrigemRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private TipoOrigemService tipoOrigemService;

	@Test
	@WithMockUser(username="user", roles = {"USER"})
	public void testCriarTipoOrigem() throws JsonProcessingException, Exception {
		TipoOrigem tipoOrigem = this.getTipoOrigem();
		
		when(tipoOrigemService.criarTipoOrigem(tipoOrigem)).thenReturn(tipoOrigem);
		
		mockMvc.perform(
				post("/tiposOrigem").with(csrf())
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(this.getMapper().writeValueAsString(tipoOrigem))
				.characterEncoding("UTF-8"))
		.andExpect(status().isOk())
		.andDo(print());			
	}

	@Test
	@WithMockUser(username="user", roles = {"USER"})
	public void testAlterarTipoOrigem() throws JsonProcessingException, Exception {
		TipoOrigem tipoOrigem = this.getTipoOrigem();
		
		when(tipoOrigemService.recuperarTipoOrigem(1l)).thenReturn(tipoOrigem);
		
		mockMvc.perform(
				put("/tiposOrigem").with(csrf())
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(this.getMapper().writeValueAsString(tipoOrigem))
				.characterEncoding("UTF-8"))
		.andExpect(status().isOk())
		.andDo(print());			
	}

	@Test
	@WithMockUser(username="user", roles = {"USER"})
	public void testRecuperarTiposOrigem() throws Exception {
		TipoOrigem tipoOrigem = new TipoOrigem();
		tipoOrigem.setId(1L);
		
		List<TipoOrigem> lista = new ArrayList<TipoOrigem>();
		lista.add(tipoOrigem);
		
		when(tipoOrigemService.recuperarTiposOrigem()).thenReturn(lista);
		
		mockMvc.perform(get("/tiposOrigem")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	@WithMockUser(username="user", roles = {"USER"})
	public void testInativarTipoOrigem() throws JsonProcessingException, Exception {
		TipoOrigem tipoOrigem = this.getTipoOrigem();
		TipoOrigem tipoOrigemInativo = this.getTipoOrigem();
		tipoOrigemInativo.setAtivo(false);
		
		when(tipoOrigemService.recuperarTipoOrigem(tipoOrigem.getId())).thenReturn(tipoOrigem);
		when(tipoOrigemService.inativarTipoOrigem(tipoOrigem)).thenReturn(tipoOrigemInativo);
		
		mockMvc.perform(
				delete("/tiposOrigem/" + tipoOrigem.getId()).with(csrf())
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(this.getMapper().writeValueAsString(tipoOrigem))
				.characterEncoding("UTF-8"))
		.andExpect(status().isOk())
		.andDo(print());		
	}
	
	private TipoOrigem getTipoOrigem() {
		TipoOrigem tipoOrigem = new TipoOrigem();
		
		tipoOrigem.setId(1l);
		tipoOrigem.setDsTipoOrigem("Tipo origem de teste");
		tipoOrigem.setInObrigatorioNumeroOrigem(false);
		tipoOrigem.setAtivo(true);
		
		return tipoOrigem;
	}
	
	private ObjectMapper getMapper() {
		ObjectMapper mapper = new ObjectMapper();
		return mapper;
	}

}
