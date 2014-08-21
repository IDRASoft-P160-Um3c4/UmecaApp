package com.umeca.model.entities.supervisor;

/**
 * Created by Vmware on 19/08/2014.
 */
public class ExcelTecRevSelQuestDto {

    private Long idCase;
    private String parentCode;
    private String code;
    private String question;
    private String subSectName;

    public ExcelTecRevSelQuestDto(Long idCase, String parentCode, String code, String question, String subSectName) {
        this.idCase = idCase;
        this.parentCode = parentCode;
        this.code = code;
        this.question = question;
        this.subSectName = subSectName;
    }


    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSubSectName() {
        return subSectName;
    }

    public void setSubSectName(String subSectName) {
        this.subSectName = subSectName;
    }
}
