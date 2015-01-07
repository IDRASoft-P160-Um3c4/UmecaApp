package com.umeca.model;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 5/06/14
 * Time: 05:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResponseMessageAddress {
    private boolean hasError;
    private String message;
    private String data;

    public ResponseMessageAddress(){
    }

    public ResponseMessageAddress(boolean hasError, String message, String data){
        this.hasError = hasError;
        this.message = message;
        this.data = data;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
