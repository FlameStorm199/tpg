package model;

import java.io.Serializable;

public class Message implements Serializable {	

	private static final long serialVersionUID = 1L;
	private Protocollo op;
	private String message;
	
	public Message(Protocollo op) {	
		this.op = op;
	}
	
	public Message(Protocollo op, String position) {	
		this.op = op;
		this.message = position;
	}
	
	public Message() {	
	}

	public Protocollo getOp() {
		return op;
	}

	public void setOp(Protocollo op) {
		this.op = op;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}