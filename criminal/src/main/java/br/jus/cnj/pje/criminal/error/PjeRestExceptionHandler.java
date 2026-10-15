package br.jus.cnj.pje.criminal.error;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

@RestControllerAdvice
public class PjeRestExceptionHandler extends ResponseEntityExceptionHandler{

    private static final Logger log = LoggerFactory.getLogger(PjeRestExceptionHandler.class);
	
    @ExceptionHandler(PjeEntityNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(PjeEntityNotFoundException rnfe,
            HttpServletRequest request) {
        logException("Recurso não encontrado.", request, rnfe);

        PjeErrorDetail errorDetail = new PjeErrorDetail();
        errorDetail.setTimestamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setError("Recurso não encontrado.");
        errorDetail.setMessage(rnfe.getMessage());
        errorDetail.setException(PjeEntityNotFoundException.class.getName());

        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TribunalNaoIdentificadoException.class)
    public ResponseEntity<?> handleTribunalNaoIdentificadoException(TribunalNaoIdentificadoException rnfe,
            HttpServletRequest request) {
        logException("Falha na requisição.", request, rnfe);

        PjeErrorDetail errorDetail = new PjeErrorDetail();
        errorDetail.setTimestamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetail.setError("Falha na requisição");
        errorDetail.setMessage(rnfe.getMessage());
        errorDetail.setException(TribunalNaoIdentificadoException.class.getName());
        
        return new ResponseEntity<>(errorDetail, null, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(UsuarioSemPermissaoNoTribunalSolicitadoException.class)
    public ResponseEntity<?> handleUsuarioSemPermissaoNoTribunalSolicitadoException(UsuarioSemPermissaoNoTribunalSolicitadoException rnfe,
            HttpServletRequest request) {
        logException("Falha na requisição.", request, rnfe);

        PjeErrorDetail errorDetail = new PjeErrorDetail();
        errorDetail.setTimestamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.FORBIDDEN.value());
        errorDetail.setError("Você não tem permissão para acessar o recurso.");
        errorDetail.setMessage(rnfe.getMessage());
        errorDetail.setException(UsuarioSemPermissaoNoTribunalSolicitadoException.class.getName());
        
        return new ResponseEntity<>(errorDetail, null, HttpStatus.FORBIDDEN);
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleResourceForbiddenException(AccessDeniedException rnfe,
            HttpServletRequest request) {
        logException("Acesso negado.", request, rnfe);

        PjeErrorDetail errorDetail = new PjeErrorDetail();
        errorDetail.setStatus(HttpStatus.FORBIDDEN.value());
        errorDetail.setError("Você não tem permissão para acessar essa funcionalidade.");
        errorDetail.setMessage(rnfe.getMessage());
        errorDetail.setException(AccessDeniedException.class.getName());

        return new ResponseEntity<>(errorDetail, null, HttpStatus.FORBIDDEN);
    }
    
    @ExceptionHandler({CriminalBusinessException.class, NumeroProcessoInvalidoException.class})
    public ResponseEntity<?> handleCriminalBusinessException(Exception e,
            HttpServletRequest request) {
        logException("Erro negocial.", request, e);

        PjeErrorDetail errorDetail = new PjeErrorDetail();
        errorDetail.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDetail.setError("Erro negocial.");
        errorDetail.setMessage(e.getMessage());
        errorDetail.setException(e.getClass().getName());
        
        request.removeAttribute(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE);
        
        return new ResponseEntity<PjeErrorDetail>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final StringBuilder errors = new StringBuilder();
        
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.append(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.append(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        
        PjeErrorDetail errorDetail = new PjeErrorDetail();
        errorDetail.setTimestamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetail.setError("Argumentos inválidos.");
        errorDetail.setMessage(errors.toString());
        errorDetail.setException(MethodArgumentNotValidException.class.getName());
        
        return handleExceptionInternal(ex, errorDetail, headers, HttpStatus.BAD_REQUEST, request);
    }
    
    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(org.springframework.dao.DataIntegrityViolationException ex, 
    		HttpServletRequest request) {
        logException("Erro de integridade na base de dados.", request, ex);

    	Throwable excecaoRaiz = buscarExcecaoRaiz(ex);
        PjeErrorDetail errorDetail = new PjeErrorDetail();
        errorDetail.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDetail.setError("Erro de integridade na base de dados.");
        errorDetail.setMessage("Erro ao salvar os dados na base de dados: " + ((excecaoRaiz != null) ? excecaoRaiz.getMessage() : ex.getMessage()));
        errorDetail.setException(excecaoRaiz != null ? excecaoRaiz.getClass().getName() : ex.getClass().getName());

        return new ResponseEntity<>(errorDetail, null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

	private Throwable buscarExcecaoRaiz(Throwable exception) {
		Throwable ex = exception.getCause();
		while (ex != null && ex.getCause() != null && !ex.getCause().equals(ex)) {
			ex = ex.getCause();
		}
		return ex;
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception ex, HttpServletRequest request) {
        logException("Erro nao tratado ao realizar a operação.", request, ex);
		Throwable excecaoRaiz = buscarExcecaoRaiz(ex);
		PjeErrorDetail errorDetail = new PjeErrorDetail();
        errorDetail.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDetail.setError("Erro ao realizar a operação.");
        errorDetail.setMessage("Erro ao realizar a operação: " + ((excecaoRaiz != null) ? excecaoRaiz.getMessage() : ex.getMessage()));
        errorDetail.setException(excecaoRaiz != null ? excecaoRaiz.getClass().getName() : ex.getClass().getName());
        return new ResponseEntity<>(errorDetail, null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}
		if (body == null) {
			Throwable excecaoRaiz = buscarExcecaoRaiz(ex);
			PjeErrorDetail errorDetail = new PjeErrorDetail();
	        errorDetail.setStatus(status == null ? null : status.value());
	        errorDetail.setError("Erro ao realizar operação.");
	        errorDetail.setMessage("Erro ao realizar operação: " + ((excecaoRaiz != null) ? excecaoRaiz.getMessage() : ex.getMessage()));
	        errorDetail.setException(excecaoRaiz != null ? excecaoRaiz.getClass().getName() : ex.getClass().getName());
	        body = errorDetail;
		}
		return new ResponseEntity<>(body, headers, status);
	}

    private void logException(String mensagem, HttpServletRequest request, Exception ex) {
        String metodo = request != null ? request.getMethod() : "N/A";
        String uri = request != null ? request.getRequestURI() : "N/A";
        log.error("{} [{} {}]", mensagem, metodo, uri, ex);
    }
    
}
