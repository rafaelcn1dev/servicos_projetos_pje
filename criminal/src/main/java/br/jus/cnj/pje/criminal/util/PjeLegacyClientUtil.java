package br.jus.cnj.pje.criminal.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import br.jus.cnj.pje.criminal.aop.TribunalContext;
import br.jus.cnj.pje.criminal.clients.PjeLegacyClient;
import br.jus.cnj.pje.criminal.clients.PjeLegacyClientInterceptor;
import br.jus.cnj.pje.criminal.model.entities.CodigoTribunal;
import feign.Client;
import feign.Feign;
import feign.Logger;
import feign.slf4j.Slf4jLogger;

@Component
public class PjeLegacyClientUtil {
	
	@Autowired(required = false)
	private EurekaClient eurekaClient;
	
	@Autowired(required = false)
	private FeignContext feignContext;
	
	public static final String HEADER_PJE_LEGACY_APP = "X-pje-legacy-app";
	public static final String HEADER_PJE_LEGACY_CONTEXT_PATH = "X-pje-legacy-context-path";

	public String getPjeLegacyAppName(HttpServletRequest request) {
		return request.getHeader(HEADER_PJE_LEGACY_APP);
	}

	private CodigoTribunal codigoTribunal(){
		return TribunalContext.codigoTribunal();
	}
	
	public String getSiglaPjeLegacyAppName(HttpServletRequest request) {
		String sigla = null;
		
		if(codigoTribunal() != null){
			sigla = codigoTribunal().siglaTribunal();
		}
    	return sigla;
	}
	
    public String recuperarJTR() {
		String jtr = null;
		if(codigoTribunal() != null){
			jtr = codigoTribunal().jtr();
		}
    	return jtr;
    }

	public PjeLegacyClient createPjeLegacyClient(HttpServletRequest request) {
		String pjeLegacyAppName = request.getHeader(HEADER_PJE_LEGACY_APP);
		FeignClientsConfiguration config = this.feignContext.getInstance(FeignClientsConfiguration.class.getName(), FeignClientsConfiguration.class);		
		InstanceInfo gatewayInstance = this.eurekaClient.getNextServerFromEureka("GATEWAY-SERVICE", false);
		
		if(pjeLegacyAppName == null || pjeLegacyAppName.isEmpty()) {
			pjeLegacyAppName = "pje-legacy";
		}
		
		return Feign.builder()
				.logger(new Slf4jLogger(PjeLegacyClient.class))
				.logLevel(Logger.Level.FULL)
				.requestInterceptor(new PjeLegacyClientInterceptor())
				.client(new Client.Default(null,null))
				.decoder(config.feignDecoder())
				.target(PjeLegacyClient.class, gatewayInstance.getHomePageUrl() + pjeLegacyAppName + "/" + this.getPjeLegacyContextPath(request, pjeLegacyAppName));
	}	
	
	private String getPjeLegacyContextPath(HttpServletRequest request, String pjeLegacyAppName) {
		String contextPath = "pje";
		
		if(request != null) {
			if(!StringUtils.isEmpty(request.getHeader(HEADER_PJE_LEGACY_CONTEXT_PATH))) {
				contextPath = request.getHeader(HEADER_PJE_LEGACY_CONTEXT_PATH);
			} else {
				InstanceInfo pjeLegacyInstance = this.eurekaClient.getNextServerFromEureka(pjeLegacyAppName, false);
				String[] splitStr = pjeLegacyInstance.getHomePageUrl().split("/");
				
				if(splitStr.length == 4) {
					contextPath = splitStr[3];
				}
			}
		}
		
		if(!StringUtils.isEmpty(contextPath) && contextPath.startsWith("/")) {
			contextPath = contextPath.substring(1);
		}
		
		return contextPath;		
	}
	
}
