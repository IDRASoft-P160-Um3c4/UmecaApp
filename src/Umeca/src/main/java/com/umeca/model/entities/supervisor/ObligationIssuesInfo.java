package com.umeca.model.entities.supervisor;

public class ObligationIssuesInfo {
    private Long idCase;
    private String name;
    private String cause;

    public ObligationIssuesInfo(Long idCase, String name, String cause) {
        this.idCase = idCase;
        this.name = name;
        this.cause = cause;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }
}
