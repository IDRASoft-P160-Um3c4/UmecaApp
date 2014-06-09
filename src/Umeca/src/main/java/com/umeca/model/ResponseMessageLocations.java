package com.umeca.model;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 5/06/14
 * Time: 05:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResponseMessageLocations {
    private boolean hasError;
    private String message;
    private String locations;

    public ResponseMessageLocations(){
    }

    public ResponseMessageLocations(boolean hasError, String message, String locations){
        this.hasError = hasError;
        this.message = message;
        this.locations = locations;
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

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }
}
