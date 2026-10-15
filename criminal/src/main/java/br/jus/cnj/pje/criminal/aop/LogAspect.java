package br.jus.cnj.pje.criminal.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.netflix.discovery.EurekaClient;

@Aspect
@Component
public class LogAspect {
	
	@Autowired(required = false)
	private EurekaClient discoveryClient;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("rawtypes")
	@Around("within(br.jus.cnj.pje..*RestController)")
	public Object doSomething(ProceedingJoinPoint pjp) throws Throwable{
		
		long start = System.currentTimeMillis();
		Object obj = pjp.proceed();
		long end = System.currentTimeMillis() - start;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null) {
			MDC.put("username", auth.getName());			
		} else {
			MDC.put("username", "NO USER");
		}
		// Nome da classe
		MDC.put("classe", pjp.getTarget().getClass().getName());		
		// Nome do método executado
		MDC.put("metodo", pjp.getSignature().getName());
		// Tempo de excução do método
		MDC.put("execucao", end + " ms");

		// Informações da instância do serviço
		if(this.discoveryClient != null) {
			MDC.put("instanceId", discoveryClient.getApplicationInfoManager().getInfo().getInstanceId());
			MDC.put("service", discoveryClient.getApplicationInfoManager().getInfo().getAppName());
		}
		
		// Status code do response
		if(obj instanceof ResponseEntity<?>){
			MDC.put("status",((ResponseEntity) obj).getStatusCode().toString());
		}
		
		log.info("Metodo " + pjp.getSignature().getName() + " executado em " + end + " milisegundos");
		
		return obj;
	}
}
