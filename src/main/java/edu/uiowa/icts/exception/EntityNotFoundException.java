package edu.uiowa.icts.exception;

/**
 * @author rrlorent
 * @since June 30, 2015
 */
public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7722434224985333450L;

	public EntityNotFoundException() {
		super();
	}

	public EntityNotFoundException( String message, Throwable cause ) {
		super( message, cause );
	}

	public EntityNotFoundException( String message ) {
		super( message );
	}

	public EntityNotFoundException( Throwable cause ) {
		super( cause );
	}

}