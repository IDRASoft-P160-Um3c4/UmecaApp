package com.umeca.model.dto.tablet.catalog;

public class TabletFieldVerificationDto {

        public TabletFieldVerificationDto(Long id, String code, String section, Integer sectionCode, String fieldName, Integer indexField, Boolean isObsolete, Integer idSubsection, String type) {
                this.id = id;
                this.code = code;
                this.section = section;
                this.sectionCode = sectionCode;
                this.fieldName = fieldName;
                this.indexField = indexField;
                this.isObsolete = isObsolete;
                this.idSubsection = idSubsection;
                this.type = type;
        }

        private Long id;
        private String code;
        private String section;
        private Integer sectionCode;
        private String fieldName;
        private Integer indexField;
        private Boolean isObsolete;
        private Integer idSubsection;
        private String type;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getCode() {
                return code;
        }

        public void setCode(String code) {
                this.code = code;
        }

        public String getSection() {
                return section;
        }

        public void setSection(String section) {
                this.section = section;
        }

        public Integer getSectionCode() {
                return sectionCode;
        }

        public void setSectionCode(Integer sectionCode) {
                this.sectionCode = sectionCode;
        }

        public String getFieldName() {
                return fieldName;
        }

        public void setFieldName(String fieldName) {
                this.fieldName = fieldName;
        }

        public Integer getIndexField() {
                return indexField;
        }

        public void setIndexField(Integer indexField) {
                this.indexField = indexField;
        }

        public Boolean getIsObsolete() {
                return isObsolete;
        }

        public void setIsObsolete(Boolean isObsolete) {
                this.isObsolete = isObsolete;
        }

        public Integer getIdSubsection() {
                return idSubsection;
        }

        public void setIdSubsection(Integer idSubsection) {
                this.idSubsection = idSubsection;
        }

        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }
}
