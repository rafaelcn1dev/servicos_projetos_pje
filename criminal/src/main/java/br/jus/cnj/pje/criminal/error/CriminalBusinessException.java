package br.jus.cnj.pje.criminal.error;

public class CriminalBusinessException extends Exception{

	private static final long serialVersionUID = 3032189350268291156L;
	
	public static final String RESTRICAO_ACESSO_CNJ = "Somente administradores do CNJ podem criar ou alterar tipos de origem.";

	public CriminalBusinessException(String errorMessage, Throwable err) {
	    super(errorMessage, err);
	}
	
    public CriminalBusinessException(String errorMessage) {
        super(errorMessage);
    }
    
    public CriminalBusinessException(Throwable err) {
        super(err);
    }
	
}
