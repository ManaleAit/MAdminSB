package ensa.pay.exception;

import java.util.HashSet;
import java.util.Set;

public class FieldError extends Throwable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String field;
    private Set<String> errors = new HashSet<>();

    public FieldError(String field, Set<String> errors) {
        this.field = field;
        this.errors = errors;
    }

    public FieldError() {
    }

    public FieldError(ExceptionCode apiResourceNotFound, String ouvrier_not_found) {
    }

    public String getField() {
        return field;
    }

    public FieldError setField(String field) {
        this.field = field;
        return this;
    }

    public Set<String> getErrors() {
        return errors;
    }

    public FieldError setErrors(Set<String> errors) {
        this.errors = errors;
        return this;
    }

	public static void main(String[] args) {
	
		
	}
}
