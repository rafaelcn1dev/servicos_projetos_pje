package br.jus.cnj.pje.criminal.clients;

import javax.servlet.http.Cookie;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@ComponentScan
public class PjeLegacyClientInterceptor implements RequestInterceptor{

	public static final String PRE_AUTH_TOKEN_HEADER = "X-pje-pre-auth-token";
	
	@Override
	public void apply(RequestTemplate template) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

		String cookies = this.getRequestCookies(attr.getRequest().getCookies());
		
		if(!StringUtils.isEmpty(cookies)) {
			template.header("Cookie", cookies);
		}
		
		if(!StringUtils.isEmpty(attr.getRequest().getHeader(PRE_AUTH_TOKEN_HEADER))) {
			template.header(PRE_AUTH_TOKEN_HEADER, attr.getRequest().getHeader(PRE_AUTH_TOKEN_HEADER));
		}
		
		if(!StringUtils.isEmpty(attr.getRequest().getHeader("authorization"))) {
			template.header("Authorization", attr.getRequest().getHeader("authorization"));
		}
	}
	
	private String getRequestCookies(Cookie[] cookies) {
		StringBuilder sbCookies = new StringBuilder("");
		
		if(cookies != null) {
			for (Cookie cookie : cookies) {
				sbCookies.append(cookie.getName()).append("=").append(cookie.getValue()).append(";");
			}			
		}
		
		return sbCookies.toString();
	}

}
