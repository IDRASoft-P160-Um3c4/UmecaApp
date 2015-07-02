package com.umeca.controller.shared;

import com.umeca.model.catalog.Questionary;
import com.umeca.model.catalog.QuestionarySection;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.shared.Constants;

import java.text.SimpleDateFormat;
import java.util.List;

public class ExcelConv {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    public ExcelConv() {

    }

    public String genderToString(Boolean gender) {

        if (gender == true) {
            return "Femenino";
        } else return "Masculino";

    }

    public String genderToString(Integer gender) {

        if (gender == 1) {
            return "Femenino";
        } else return "Masculino";

    }


    public String activitiesToString(SocialEnvironment socialEnvironment) {

        String activities = "";

        List<RelSocialEnvironmentActivity> listRelActivities = socialEnvironment.getRelSocialEnvironmentActivities();

        if (listRelActivities != null && listRelActivities.size() > 0)
            for (RelSocialEnvironmentActivity act : listRelActivities) {
                if (activities != "")
                    activities += "\n ";

                activities += "-" + act.getActivity().getName();
                if (act.getSpecification() != null && !act.getSpecification().trim().equals(""))
                    activities += ": " + act.getSpecification();
            }

        return activities;
    }

    public String domicilesToString(Meeting meeting) {

        String addresses = "";

        List<ImputedHome> homes = meeting.getImputedHomes();

        if (homes != null && homes.size() > 0) //se omite la disponibilidad
            for (ImputedHome act : homes) {
                if (addresses != "")
                    addresses += "\n";

                addresses += "-" + act.getAddress().getAddressString();

                if (act.getRegisterType() != null)
                    addresses += ", Tipo: " + act.getRegisterType().getName();

                if (act.getHomeType() != null)
                    addresses += ", Tipo: " + act.getHomeType().getName();
            }

        return addresses;
    }

    public String socialNetworkToString(Meeting meeting) {

        String socialString = "";

        SocialNetwork sN = meeting.getSocialNetwork();

        if (sN != null) {
            List<PersonSocialNetwork> persons = sN.getPeopleSocialNetwork();

            if (persons != null && persons.size() > 0)
                for (PersonSocialNetwork act : persons) {
                    if (socialString != "")
                        socialString += "\n";

                    socialString += "-" + act.getName();

                    socialString += ", " + act.getRelationship().getName();

                    if(act.getRelationship().getSpecification()){
                        socialString += ": " + act.getSpecificationRelationship();
                    }

                    socialString += ", Identificación: " + act.getDocumentType().getName();

                    socialString += ", Edad: " + act.getAge();

                    socialString += ", Tel.: " + act.getPhone();

                    socialString += ", Dependiente: " + act.getDependent().getName();

                    socialString += ", Vive con el imputado: " + act.getLivingWith().getName();

                    if (act.getLivingWith().getId().equals(Constants.ELECTION_NO))
                        socialString += ", Dirección: " + act.getAddress();

                }
        }

        return socialString;
    }

    public String referencesToString(Meeting meeting) {

        String referencesString = "";

        List<Reference> references = meeting.getReferences();

        if (references != null && references.size() > 0)
            for (Reference act : references) {
                if (referencesString != "")
                    referencesString += "\n";

                referencesString += "-" + act.getFullName();

                String relationship = act.getRelationship().getName();
                if(act.getRelationship().getSpecification()){
                    relationship += ": "+ act.getSpecificationRelationship();
                }
                referencesString += ", " + relationship;

                referencesString += ", Identificación: " + act.getDocumentType().getName();

                referencesString += ", Edad: " + act.getAge();

                referencesString += ", Tel.: " + act.getPhone();

                referencesString += ", Dirección: " + act.getAddress();

            }

        return referencesString;
    }

    public String jobsToString(Meeting meeting) {

        String jobsString = "";

        List<Job> jobs = meeting.getJobs();

        if (jobs != null && jobs.size() > 0)
            for (Job act : jobs) {
                if (jobsString != "")
                    jobsString += "\n";

                jobsString += "-" + act.getCompany();

                jobsString += ", Puesto: " + act.getPost();

                jobsString += ", Patr?n: " + act.getNameHead();

                jobsString += ", Tel.: " + act.getPhone();

                jobsString += ", Direcci?n: " + act.getAddress();

                jobsString += ", Tipo: " + act.getRegisterType().getName();

                if (act.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_CURRENT) || act.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_SECONDARY)) {

                    jobsString += ", Inicio: " + dateFormat.format(act.getStart());
                    jobsString += ", Salario semanal: $" + act.getSalaryWeek();

                } else if (act.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS)) {
                    jobsString += ", Inicio: " + dateFormat.format(act.getStartPrev());
                    jobsString += ", Fin: " + dateFormat.format(act.getEnd());
                    jobsString += ", Motivo de cambio: " + act.getReasonChange();
                }
            }

        return jobsString;
    }

    public String drugsToString(Meeting meeting) {

        String drugsString = "";

        List<Drug> drugs = meeting.getDrugs();

        if (drugs != null && drugs.size() > 0)
            for (Drug act : drugs) {
                if (drugsString != "")
                    drugsString += "\n";

                drugsString += "-" + act.getDrugType().getName();

                drugsString += ", Periocidad: " + act.getPeriodicity().getName();

                if (act.getDrugType().getSpecification().equals(true))
                    drugsString += ", Especificaci?n: " + act.getSpecificationType();

                drugsString += ", Cantidad: " + act.getQuantity();

                drugsString += ", ?tlimo consumo: " + dateFormat.format(act.getLastUse());
            }

        return drugsString;
    }

    public String crimesToString(CurrentCriminalProceeding current) {

        String crimesString = "";

        List<Crime> crimes = current.getCrimeList();

        if (crimes != null && crimes.size() > 0)
            for (Crime act : crimes) {
                if (crimesString != "")
                    crimesString += "\n";

                crimesString += "-" + act.getCrime().getName();
                crimesString += ", Art&iacute;culo: " + act.getArticle();
                crimesString += ", Delito federal: " + act.getFederal().getName();
            }

        return crimesString;
    }

    public String coDefToString(CurrentCriminalProceeding current) {

        String coDefString = "";

        List<CoDefendant> coDefendants = current.getCoDefendantList();

        if (coDefendants != null && coDefendants.size() > 0)
            for (CoDefendant act : coDefendants) {
                if (coDefString != "")
                    coDefString += "\n";

                coDefString += "-" + act.getFullName();
                coDefString += ", " + act.getRelationship().getName();
            }

        return coDefString;
    }

    public String tecRevToString(TechnicalReview technicalReview, Integer section) {

        String questionsString = "";

        if (technicalReview != null && section != null) {

            Questionary questionary = null;
            List<QuestionarySection> sections = null;

            List<QuestionReviewRel> questSelected = technicalReview.getQuestionsSel();

            if (questSelected != null && questSelected.size() > 0) {
                questionary = questSelected.get(0).getQuestion().getSection().getParent().getQuestionary();
            }

            if (questionary != null) {
                sections = questionary.getSections();
            }

            QuestionarySection currSection = null;

            switch (section) {
                case 1:
                    if (sections != null && sections.size() > 0) {
                        for (QuestionarySection sect : sections) {
                            if (sect.getCode().equals(Constants.CODE_S1_TEC_REV)) {
                                currSection = sect;
                                break;
                            }
                        }
                    }
                    break;

                case 2:
                    if (sections != null && sections.size() > 0) {
                        for (QuestionarySection sect : sections) {
                            if (sect.getCode().equals(Constants.CODE_S2_TEC_REV)) {
                                currSection = sect;
                                break;
                            }
                        }
                    }
                    break;

                case 3:
                    if (sections != null && sections.size() > 0) {
                        for (QuestionarySection sect : sections) {
                            if (sect.getCode().equals(Constants.CODE_S3_TEC_REV)) {
                                currSection = sect;
                                break;
                            }
                        }
                    }
                    break;

                case 4:
                    if (sections != null && sections.size() > 0) {
                        for (QuestionarySection sect : sections) {
                            if (sect.getCode().equals(Constants.CODE_S4_TEC_REV)) {
                                currSection = sect;
                                break;
                            }
                        }
                    }
                    break;

                case 5:
                    if (sections != null && sections.size() > 0) {
                        for (QuestionarySection sect : sections) {
                            if (sect.getCode().equals(Constants.CODE_S5_TEC_REV)) {
                                currSection = sect;
                                break;
                            }
                        }
                    }
                    break;

                case 6:

                    Integer totalRisk = technicalReview.getTotalRisk();

                    questionsString += "-Total: " + totalRisk + "\n";

                    if (totalRisk < -15)
                        questionsString += "-" + Constants.TEC_REV_HIGH_RISK + "\n";
                    else if (totalRisk > -16 && totalRisk < 0)
                        questionsString += "-" + Constants.TEC_REV_MEDIUM_RISK + "\n";
                    else if (totalRisk > -1 && totalRisk < 10)
                        questionsString += "-" + Constants.TEC_REV_LOW_RISK + "\n";
                    else if (totalRisk > 9)
                        questionsString += "-" + Constants.TEC_REV_MINIMUM_RISK + "\n";

                    questionsString += "-Comentarios: " + technicalReview.getComments();

                    break;
            }

            if (currSection != null) {
                for (QuestionarySection subsec : currSection.getChilds()) {
                    if (questionsString != "")
                        questionsString += "\n";

                    questionsString += "-" + subsec.getName();

                    for (QuestionReviewRel selQues : questSelected) {

                        if (selQues.getQuestion().getSection().getCode().equals(subsec.getCode())) {
                            if (questionsString != "")
                                questionsString += "\n";

                            questionsString += "   ." + selQues.getQuestion().getQuestion();
                        }
                    }
                }
            }
        }

        return questionsString;
    }

    public String fieldSourcesToString(SourceVerification source, Integer section) {

        String answersSourceString = "";

        List<FieldMeetingSource> fields = source.getFieldMeetingSourceList();

        System.out.println();

        for (FieldMeetingSource act : fields) {
            if (act.getFieldVerification().getSectionCode() == section) {

                if (answersSourceString != "")
                    answersSourceString += "\n";

                answersSourceString += "-" + act.getFieldVerification().getFieldName() + " : " + act.getValue();

            }
        }

        return answersSourceString;
    }

}
