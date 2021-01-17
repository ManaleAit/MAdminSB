package ensa.pay.exception;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class MicroServiceException extends RuntimeException {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ExceptionCode exceptionCode;
	    private String tickedId;
	    private String msg;
	    private String technicalMessage;
	    private LocalDateTime time;
	    private Set<Error> errors = new HashSet<>();

	    public MicroServiceException(ExceptionCode exceptionCode) {
	        this(exceptionCode, null, null, null);
	    }

	    public MicroServiceException(ExceptionCode exceptionCode, String message) {
	        this(exceptionCode, message, null, null);
	    }

	    public MicroServiceException(ExceptionCode exceptionCode, String message, String technicalMessage) {
	        this(exceptionCode, message, technicalMessage, null);
	    }

	    public MicroServiceException(ExceptionCode exceptionCode, String message, String technicalMessage, Throwable cause) {
	        super(message, cause);
	        this.msg = message;
	        this.technicalMessage = technicalMessage;
	        this.exceptionCode = exceptionCode;
	        tickedId = UUID.randomUUID().toString();
	        this.time = LocalDateTime.now();
	    }

	    public ExceptionCode getExceptionCode() {
	        return exceptionCode;
	    }

	    public MicroServiceException setExceptionCode(ExceptionCode exceptionCode) {
	        this.exceptionCode = exceptionCode;
	        return this;
	    }

	    public String getTickedId() {
	        return tickedId;
	    }

	    public MicroServiceException setTickedId(String tickedId) {
	        this.tickedId = tickedId;
	        return this;
	    }

	    public String getMsg() {
	        return msg;
	    }

	    public MicroServiceException setMsg(String msg) {
	        this.msg = msg;
	        return this;
	    }

	    public String getTechnicalMessage() {
	        return technicalMessage;
	    }

	    public MicroServiceException setTechnicalMessage(String technicalMessage) {
	        this.technicalMessage = technicalMessage;
	        return this;
	    }

	    public LocalDateTime getTime() {
	        return time;
	    }

	    public MicroServiceException setTime(LocalDateTime time) {
	        this.time = time;
	        return this;
	    }

	    public Set<Error> getErrors() {
	        return errors;
	    }

	    public MicroServiceException setErrors(Set<Error> errors) {
	        this.errors = errors;
	        return this;
	    }
}
