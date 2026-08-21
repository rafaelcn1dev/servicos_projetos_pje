package br.jus.cnj.pje.storage.jcr.server.support;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.annotation.PostConstruct;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.nodetype.NodeType;

import org.apache.jackrabbit.commons.cnd.CndImporter;
import org.apache.jackrabbit.commons.cnd.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class JCRSessionInitializer {
	private final static Logger log = LoggerFactory.getLogger(JCRSessionInitializer.class);
	
	private Repository repository;
	private String username;
	private String password;
	
	@Autowired
	public JCRSessionInitializer(Repository repository, String username, String password) {
		this.repository = repository;
		this.username = username;
		this.password = password;
	}
	
	@PostConstruct
	public void init() throws ParseException, RepositoryException, IOException {
		Session session = null;
		try {
			log.info("Registering custom node types...");
			
			session = repository.login(new SimpleCredentials(username, password.toCharArray()));
			NodeType[] nodeTypes = CndImporter.registerNodeTypes(new InputStreamReader(getClass().getResourceAsStream("/custom_nodetypes.cnd")), session);
			for(NodeType nodeType:nodeTypes){
				log.info(String.format("Node type '%s' registered...", nodeType.getName()));
			}
		} finally {
			if(session != null && session.isLive()) session.logout();
		}
	}
}
