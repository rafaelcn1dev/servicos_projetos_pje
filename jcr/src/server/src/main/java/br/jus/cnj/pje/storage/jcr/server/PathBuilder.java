package br.jus.cnj.pje.storage.jcr.server;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

/**
 * Define a estratégia de construção de diretórios utilizado pelo serviço de storage
 * 
 * @author leoneparise
 */
public interface PathBuilder {
	
	/**
	 * Monta a estrutura de diretórios para o documento
	 * 
	 * @param root nó raiz
	 * @return estrutura de diretórios
	 */
	public Node buildPath(Node root) throws RepositoryException;
}
