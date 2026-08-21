package br.jus.cnj.pje.storage.jcr.server.web;

import static br.jus.cnj.pje.storage.jcr.common.StorageConstants.DEFAULT_MIMETYPE;
import static br.jus.cnj.pje.storage.jcr.common.StorageConstants.HEADER_CONTENT_HASH;
import static br.jus.cnj.pje.storage.jcr.common.StorageConstants.HEADER_CONTENT_LENGTH;
import static br.jus.cnj.pje.storage.jcr.common.StorageConstants.HEADER_CONTENT_RANGE;
import static br.jus.cnj.pje.storage.jcr.common.StorageConstants.HEADER_CONTENT_TYPE;
import static br.jus.cnj.pje.storage.jcr.common.StorageConstants.HEADER_IS_TEMPORARY;
import static br.jus.cnj.pje.storage.jcr.common.StorageConstants.HEADER_LAST_MODIFIED;
import static br.jus.cnj.pje.storage.jcr.common.StorageConstants.HEADER_NODE_PATH;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_CONFLICT;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_NO_CONTENT;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.apache.commons.lang3.StringUtils.defaultIfBlank;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.jus.cnj.pje.storage.jcr.common.Range;
import br.jus.cnj.pje.storage.jcr.server.Attributes;
import br.jus.cnj.pje.storage.jcr.server.Resource;
import br.jus.cnj.pje.storage.jcr.server.StorageService;
import br.jus.cnj.pje.storage.jcr.server.exception.StorageException;

@Controller
@RequestMapping("/documents/{id}")
public class StorageController {
	private static final Logger log = LoggerFactory.getLogger(StorageController.class);
	private StorageService storageService;
	
	@Autowired
	public StorageController(StorageService storageService) {
		this.storageService = storageService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public void getResource(@PathVariable String id, HttpServletResponse response) throws IOException {
		// Verifica se o nó existe. Caso não exista responder com status 404.
		Resource recurso = storageService.getResourceById(id);
		if(recurso == null) {
			response.setStatus(SC_NOT_FOUND);
			return;
		}
		
		// Consulta o nó no repositório. Caso seja temporário responder com status 409 (Conflict)
		if(recurso.getAttributes().isTemporary()) {
			response.setStatus(SC_CONFLICT);
			return;
		}
		
		InputStream in = recurso.getData();
		OutputStream out = response.getOutputStream();
		try {
			response.setContentType(recurso.getAttributes().getMimetype());
			response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", id));
			response.setStatus(SC_OK);
			IOUtils.copy(in, out);
		} finally {
			recurso.close();
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping(method=RequestMethod.HEAD)
	public void headResource(@PathVariable String id, HttpServletResponse response) throws IOException {
		Attributes atributos = storageService.getAttributesById(id);
		
		// Verifica se o nó existe. Caso não exista responder com status 404.
		if(atributos == null) {
			response.setStatus(SC_NOT_FOUND);
			return;
		}
		
		response.setContentType(atributos.getMimetype());
		response.setHeader(HEADER_CONTENT_HASH, id);
		response.setHeader(HEADER_CONTENT_LENGTH, String.valueOf(atributos.getSize()));
		response.setHeader(HEADER_NODE_PATH, atributos.getPath());
		response.setHeader(HEADER_IS_TEMPORARY, String.valueOf(atributos.isTemporary()));
		response.setHeader(HEADER_LAST_MODIFIED, atributos.getLasModifiedFmt());
		response.setStatus(SC_OK);
	}
	
	@RequestMapping(method={RequestMethod.POST, RequestMethod.PUT})
	public void putResource(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			Range range = Range.parse(request.getHeader(HEADER_CONTENT_RANGE));
			String mimetype = defaultIfBlank(request.getHeader(HEADER_CONTENT_TYPE), DEFAULT_MIMETYPE);
			
			storageService.persistFile(id, mimetype, range.isLast(), request.getInputStream());
			
			response.setStatus(SC_NO_CONTENT);
		} catch (ParseException e) {
			response.setStatus(SC_BAD_REQUEST);
		}
	}
	
	@ExceptionHandler(StorageException.class)
	private void handleStorageException(StorageException ex, HttpServletRequest request, HttpServletResponse response) {
		log.warn(String.format("Conflito na execução da requisição %s", request.getRequestURI()), ex);
		response.setStatus(SC_CONFLICT);
	}
	
	@ExceptionHandler(Exception.class)
	private void handleInternalError(Exception ex, HttpServletRequest request, HttpServletResponse response) {
		log.error(String.format("Falha ao tratar requisição %s", request.getContextPath()), ex);
		response.setStatus(SC_INTERNAL_SERVER_ERROR);
	}
}
