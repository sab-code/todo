/**
 * 
 */
package org.softwarily.todo;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Used to aid in returning a 404 when there is a failed lookup for a Todo.
 * 
 * @author Steve Buck (steve@softwarily.org)
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TodoGenericException extends RuntimeException {

	/**
	 * 
	 */
	public TodoGenericException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public TodoGenericException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public TodoGenericException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public TodoGenericException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public TodoGenericException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
