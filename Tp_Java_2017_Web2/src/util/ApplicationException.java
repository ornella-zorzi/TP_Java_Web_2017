package util;

import java.io.Serializable;

public class ApplicationException extends Exception implements Serializable  {
 	private Throwable innerException;
	private String message;
	
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public ApplicationException(Throwable e, String message){
		this.innerException=e;
		this.setMessage(message);
	}
}
