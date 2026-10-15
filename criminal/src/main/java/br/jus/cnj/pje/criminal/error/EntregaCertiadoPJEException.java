package br.jus.cnj.pje.criminal.error;

public class EntregaCertiadoPJEException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public EntregaCertiadoPJEException(String errorMessage, Throwable err) {
	    super(errorMessage, err);
	}
	
    public EntregaCertiadoPJEException(String errorMessage) {
        super(errorMessage);
    }
    
    public EntregaCertiadoPJEException(Throwable err) {
        super(err);
    }

}
