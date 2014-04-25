package com.umeca.model;

public class ResponseMessage {

	private boolean hasError;
    private String message;
    private String urlToGo;
    private String title;


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

    public String getUrlToGo() {
        return urlToGo;
    }

    public void setUrlToGo(String urlToGo) {
        this.urlToGo = urlToGo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
