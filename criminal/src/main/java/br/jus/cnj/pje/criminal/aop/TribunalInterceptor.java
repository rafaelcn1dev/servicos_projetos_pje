package br.jus.cnj.pje.criminal.aop;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.jus.cnj.pje.criminal.error.TribunalNaoIdentificadoException;
import br.jus.cnj.pje.criminal.error.UsuarioSemPermissaoNoTribunalSolicitadoException;
import br.jus.cnj.pje.criminal.model.entities.CodigoTribunal;
import br.jus.cnj.pje.criminal.services.TribunalService;
import br.jus.cnj.pje.criminal.services.UsuarioAutenticadoService;
import br.jus.cnj.pje.criminal.util.PjeLegacyClientUtil;

@Aspect
@Component
public class TribunalInterceptor {

    private static final Logger log = LoggerFactory.getLogger(TribunalInterceptor.class);

    @Autowired
    TribunalService tribunalService;
    
    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    @Around("@annotation(InboundRequest)")
    public Object logInboundRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("Interceptando a requisicao de entrada...");
        log.debug("Limpando tribunal context!");
        TribunalContext.clear();

        log.debug("Identificando appName do tribunal no cabecalho da chamada");
        String appName = extrairLegacyAppName()
        		.orElseThrow(TribunalNaoIdentificadoException::new);
        
        log.debug("Buscando codigoTribunal pelo appName!");
        CodigoTribunal codigoTribunal = tribunalService.recuperarCodigoTribunal(appName);
        if(codigoTribunal != null) {
        	log.info("Tribunal identificado: [" + codigoTribunal.siglaTribunal() + "]");
        }else {
        	throw new TribunalNaoIdentificadoException();
        }
        
        log.debug("Verificando se o usuário de sistema possui permissão no tribunal que informou");
        if(usuarioAutenticadoService.usuarioSistema() && !usuarioAutenticadoService.permissaoTribunal(codigoTribunal)) {
        	throw new UsuarioSemPermissaoNoTribunalSolicitadoException();
        }

        try {
	        log.debug("Identificando o tribunal atual na variável de Thread local");
	        TribunalContext.setCodigoTribunal(codigoTribunal);
	        
            log.debug("Continuando com a execução!");
            return joinPoint.proceed();
        } finally {
            log.debug("Limpando tribunal context ao final da requisição!");
            TribunalContext.clear();
        }
    }

    private Optional<String> extrairLegacyAppName(){
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes != null ? attributes.getRequest() : null;

        Optional<String> legacyAppName = Optional.empty();
        
      if(request != null) {
        String legacyAppNameHeader = request.getHeader(PjeLegacyClientUtil.HEADER_PJE_LEGACY_APP);
        if(StringUtils.isNotBlank(legacyAppNameHeader)) {
          legacyAppName = Optional.of(legacyAppNameHeader);    			
        }
      }
      return legacyAppName;
    }
}
