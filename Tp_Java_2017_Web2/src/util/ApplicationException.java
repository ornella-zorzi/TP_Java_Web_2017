package util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	public ApplicationException(Throwable e, String message, Level errorLevel){
		this(e,message);
		Logger logger = LogManager.getLogger(getClass());
		logger.log(errorLevel,message);
	}
}
