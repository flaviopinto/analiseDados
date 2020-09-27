package br.com.fp.exception;

public class AplicacaoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public AplicacaoException(String mensagemErro) {
        super(mensagemErro);
    }
    
    public AplicacaoException(String mensagemErro, Exception causa) {
        super(mensagemErro, causa);
    }
    
    public AplicacaoException(Exception e) {
        super(e);
    }
}
