package com.umeca.controller.shared;

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

                if (act.getBelong() != null)
                    addresses += ", Propio: " + act.getBelong().getName();
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

                referencesString += ", " + act.getRelationship().getName();

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

                jobsString += ", Patrón: " + act.getNameHead();

                jobsString += ", Tel.: " + act.getPhone();

                jobsString += ", Dirección: " + act.getAddress();

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

        if (drugs  != null && drugs .size() > 0)
            for (Drug act : drugs ) {
                if (drugsString  != "")
                    drugsString  += "\n";

                drugsString  += "-" + act.getDrugType().getName();

                drugsString  += ", Periocidad: " + act.getPeriodicity().getName();

                if(act.getDrugType().getSpecification().equals(true))
                    drugsString  += ", Especificación: " + act.getSpecificationType();

                drugsString  += ", Cantidad: " + act.getQuantity();

                drugsString  += ", Útlimo consumo: " + dateFormat.format(act.getLastUse());
            }

        return drugsString ;
    }

}
