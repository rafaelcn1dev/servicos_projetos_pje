package br.jus.cnj.pje.storage.jcr.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;

import org.apache.jackrabbit.JcrConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Monta uma estrutura de nós no JCR com base na data atual (yyyy/mm/dd) e uma pasta sequêncial (0,1,2,3,...,n).
 * Esta estrutrua é necessário devido a uma limitação do número máximo de nós por pasta,
 * que não deve ser maior que 10.000, conforme a documentação do próprio Jackrabbit.
 * 
 * @author leoneparise
 *
 */
public class DatePathBuilder implements PathBuilder {
	private static final Logger log = LoggerFactory.getLogger(DatePathBuilder.class);
	private final int maxDoc;
	
	public DatePathBuilder(int maxDoc) {
		this.maxDoc = maxDoc;
		log.info(String.format("Creating PathBuilder with maximum documents: %d", maxDoc));
	}
	
	@Override
	public Node buildPath(Node root) throws RepositoryException {
		// Monta estrutura de nós na forma yyyy/mm/dd
		Node base = buildDatePath(root);
		
		// Se a estrutura não possui nós no dia, adicionar um nó de número '0'
		if(!base.hasNodes()) {
			return base.addNode("0", JcrConstants.NT_FOLDER);
		}
		else {
			// Obtém o nó de número 'n'
			Node last = getLastNode(base);
			
			// Se o nó possui menos filhos que o máximo, retorna o próprio nó.
			// Caso contrário, cria um novo nó com númeração 'n+1'. 
			if(last.getNodes().getSize() < maxDoc)
				return last;
			else
				return base.addNode(getNextNode(last), JcrConstants.NT_FOLDER);
		}
	}
	
	/**
	 * Obtém o número do próximo nó do diretório
	 * 
	 * @param last diretório de pesquisa
	 * @return numero do próximo nó do diretório
	 * @throws RepositoryException
	 */
	private String getNextNode(Node last) throws RepositoryException {
		int num = new Integer(last.getName());
		return new Integer(num + 1).toString();
	}
	
	/**
	 * Obtém o últino nó do diretório.
	 * 
	 * @param root diretório de pesquisa
	 * @return último nó do diretório
	 * @throws RepositoryException
	 */
	private Node getLastNode(Node root) throws RepositoryException {
		List<Integer> numbers = new ArrayList<Integer>();
		NodeIterator it = root.getNodes();
		while(it.hasNext())
			numbers.add(new Integer(it.nextNode().getName()));
		
		Collections.sort(numbers);
		return root.getNode(numbers.get(numbers.size() - 1).toString());
	}
	
	/**
	 * Monta uma estrutura de nós no formato {@code yyyy/mm/dd} onde {@code yyyy} é referente
	 * ao ano {@code mm}, o mês e {@code dd} o dia atual. Caso a estrutura já exista, o método
	 * retorna a estrutura montada.
	 * 
	 * @param root nó raiz
	 * @return estrutura de dados montada com base na data do dia atual de execução
	 * @throws RepositoryException
	 */
	private Node buildDatePath(Node root) throws RepositoryException {
		String[] data = new String[]{getAno(), getMes(), getDia()};
		Node curr = root;
		for(int i = 0; i < data.length; i++) {
			if(!curr.hasNode(data[i]))
				curr = curr.addNode(data[i], JcrConstants.NT_FOLDER);
			else
				curr = curr.getNode(data[i]);
		}
		
		return curr;
	}
	
	/**
	 * Obtém o ano atual com quatro dígitos
	 * 
	 * @return ano atual
	 */
	private static String getAno() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(Calendar.getInstance().getTime());
	}
	
	/**
	 * Obtém o mês atual com dois dígitos
	 * 
	 * @return mês atual
	 */
	private static String getMes() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		return sdf.format(Calendar.getInstance().getTime());
	}
	
	
	/**
	 * Obtém o dia dia do mês atual com dois dígitos
	 *  
	 * @return dia do mês atual
	 */
	private static String getDia() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		return sdf.format(Calendar.getInstance().getTime());
	}
}
