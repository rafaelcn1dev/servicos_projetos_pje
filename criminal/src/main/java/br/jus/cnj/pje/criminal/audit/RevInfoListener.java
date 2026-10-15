package br.jus.cnj.pje.criminal.audit;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;

public class RevInfoListener implements RevisionListener{

	@Override
	public void newRevision(Object revInfo) {
		RevInfo newRevInfo = (RevInfo) revInfo;
		if(SecurityContextHolder.getContext().getAuthentication() != null) {
			newRevInfo.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		}
	}

}
