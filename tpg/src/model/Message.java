package model;

import java.io.Serializable;

public class Message implements Serializable {	

	private static final long serialVersionUID = 1L;
	private Protocol op;
	private String message;
	
	public Message(Protocol op) {	
		this.op = op;
	}
	
	public Message(Protocol op, String position) {	
		this.op = op;
		this.message = position;
	}
	
	public Message() {	
	}

	public Protocol getOp() {
		return op;
	}

	public void setOp(Protocol op) {
		this.op = op;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}