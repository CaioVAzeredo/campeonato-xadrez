package com.example.camp_xadrez.api.infra.exception.model;

public class ErrorDetail {
    private String field;
    private Object rejectValue;
    private String message;

    public ErrorDetail(String field, Object rejectValue, String message) {
        this.field = field;
        this.rejectValue = rejectValue;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getRejectValue() {
        return rejectValue;
    }

    public void setRejectValue(Object rejectValue) {
        this.rejectValue = rejectValue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}