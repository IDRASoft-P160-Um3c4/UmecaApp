package com.umeca.model;

public class ResponseUniqueMessage {
	private boolean isUnique;

    public ResponseUniqueMessage(){
    }

    public ResponseUniqueMessage(boolean isUnique){
        this.isUnique = isUnique;
    }

    public boolean isUnique() {
        return isUnique;
    }

    public void setUnique(boolean unique) {
        isUnique = unique;
    }
}
