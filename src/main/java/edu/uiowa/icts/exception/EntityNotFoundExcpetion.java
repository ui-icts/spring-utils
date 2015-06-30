package edu.uiowa.icts.exception;

/**
 * @author rrlorent
 * @since June 30, 2015
 */
public class EntityNotFoundExcpetion extends RuntimeException {

	private static final long serialVersionUID = 7722434224985333450L;

	public EntityNotFoundExcpetion() {
		super();
	}

	public EntityNotFoundExcpetion( String message, Throwable cause ) {
		super( message, cause );
	}

	public EntityNotFoundExcpetion( String message ) {
		super( message );
	}

	public EntityNotFoundExcpetion( Throwable cause ) {
		super( cause );
	}

}