package br.jus.cnj.pje.criminal.aop;

import javax.persistence.EntityManager;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.jus.cnj.pje.criminal.model.entities.CodigoTribunal;

@Aspect
@Component
public class RepositoryInterceptor {

    @Autowired
    private EntityManager entityManager;

    private Logger log = LoggerFactory.getLogger(RepositoryInterceptor.class);

    @Before("execution(* br.jus.cnj.pje.criminal.repositories.OrgaoProcedimentoOriginarioRepository.*(..))"
            + "|| execution(* br.jus.cnj.pje.criminal.repositories.ProcessoRepository.*(..))"
            + "|| execution(* br.jus.cnj.pje.criminal.repositories.ProcessoProcedimentoOrigemRepository.*(..))"
            + "")
    public void inWebLayer(JoinPoint jointPoint) throws Throwable {
        if (entityManager.isOpen()) {
            Session session = entityManager.unwrap(Session.class);

            log.debug("Filtrando resultados pelo codigo do tribunal!");
            CodigoTribunal codigoTribunal = TribunalContext.codigoTribunal();

            Filter filter = session.enableFilter("tribunalFilter");
            filter.getFilterDefinition().getParameterNames();
            filter.setParameter("JTR", codigoTribunal.jtr());
        }
    }
}
