package br.jus.cnj.pje.criminal.error;

public class PjeErrorDetail {

	private Integer status;
	private long timestamp;
	private String error;
	private String exception;
	private String message;
//	private List<String> errors;

	public PjeErrorDetail() {
	}

	public PjeErrorDetail(Integer status, long timestamp, String error, String exception, String message) {
		this.status = status;
		this.timestamp = timestamp;
		this.error = error;
		this.exception = exception;
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

//	public List<String> getErrors() {
//		return errors;
//	}
//
//	public void setErrors(List<String> errors) {
//		this.errors = errors;
//	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
