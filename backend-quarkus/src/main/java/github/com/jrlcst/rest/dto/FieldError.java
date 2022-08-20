package github.com.jrlcst.rest.dto;

/**
 * classe Value Object para criação de erro personalizado
 */
public class FieldError {
    private String Field;
    private String message;

    public FieldError(String field, String message) {
        Field = field;
        this.message = message;
    }

    public String getField() {
        return Field;
    }

    public void setField(String field) {
        Field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

