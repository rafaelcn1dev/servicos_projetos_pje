package br.jus.cnj.pje.criminal.audit;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

@Entity
@Table(name = "revinfo", schema = "audit")
@RevisionEntity(RevInfoListener.class)
public class RevInfo extends DefaultRevisionEntity{
	private static final long serialVersionUID = 1L;

	private String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
