package com.umeca.model.entities.supervisor;

import com.umeca.model.shared.MonitoringConstants;
import com.umeca.model.shared.SelectList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vmware on 24/09/2014.
 */
public class MonitoringPlanExcelInfo {

    private SupervisionLogReport supervisionLogReport;// = ;
    private List<SelectList> lstArrangement;// = arrangementRepository.findLstArrangementByCaseId(caseId);
    private List<SelectList> lstActivities; //supervisionActivityRepository.findByMonPlanId(id);
    private List<SelectList> lstGoals;//=activityGoalRepository.findByMonPlanId(id);
    private List<SelectList> lstSources;//framingReferenceRepository.findAllValidByCaseId(caseId);
    private List<ActivityMonitoringPlanLog> lstActMonPlan;// = activityMonitoringPlanRepository.getListByMonPlanId(id);
    private List<ActivityMonitoringPlanArrangementLog> lstActMonPlanArrangement;// = activityMonitoringPlanRepository.getListActMonPlanArrangementByMonPlanId(id);

    private List<ReconstructedActivityInfo> lstReconstructed;

    private List<SelectList> lstFulfillment;

    public SupervisionLogReport getSupervisionLogReport() {
        return supervisionLogReport;
    }

    public void setSupervisionLogReport(SupervisionLogReport supervisionLogReport) {
        this.supervisionLogReport = supervisionLogReport;
    }

    public List<SelectList> getLstArrangement() {
        return lstArrangement;
    }

    public void setLstArrangement(List<SelectList> lstArrangement) {
        this.lstArrangement = lstArrangement;
    }

    public List<SelectList> getLstActivities() {
        return lstActivities;
    }

    public void setLstActivities(List<SelectList> lstActivities) {
        this.lstActivities = lstActivities;
    }

    public List<SelectList> getLstGoals() {
        return lstGoals;
    }

    public void setLstGoals(List<SelectList> lstGoals) {
        this.lstGoals = lstGoals;
    }

    public List<SelectList> getLstSources() {
        return lstSources;
    }

    public void setLstSources(List<SelectList> lstSources) {
        this.lstSources = lstSources;
    }

    public List<ActivityMonitoringPlanLog> getLstActMonPlan() {
        return lstActMonPlan;
    }

    public void setLstActMonPlan(List<ActivityMonitoringPlanLog> lstActMonPlan) {
        this.lstActMonPlan = lstActMonPlan;
    }

    public List<ActivityMonitoringPlanArrangementLog> getLstActMonPlanArrangement() {
        return lstActMonPlanArrangement;
    }

    public void setLstActMonPlanArrangement(List<ActivityMonitoringPlanArrangementLog> lstActMonPlanArrangement) {
        this.lstActMonPlanArrangement = lstActMonPlanArrangement;
    }

    public String lstArrangementToStr() {
        String returnStr = "";

        if (lstArrangement != null && lstArrangement.size() > 0) {
            for (SelectList actArr : lstArrangement) {
                if (returnStr.isEmpty() == false)
                    returnStr += "\n";
                if (actArr.getName() != null && actArr.getDescription() != null) {
                    returnStr += "-" + actArr.getName();
                    returnStr += " : " + actArr.getDescription();
                }
            }
        }
        return returnStr;
    }

    public String lstActivitiesToStr() {
        String returnStr = "";

        if (lstActivities != null && lstActivities.size() > 0) {
            for (SelectList actAct : lstActivities) {
                if (returnStr.isEmpty() == false)
                    returnStr += "\n";
                if (actAct.getName() != null) {
                    returnStr += "-" + actAct.getName();
                }
            }
        }
        return returnStr;
    }

    public String lstGoalsToStr() {
        String returnStr = "";

        if (lstGoals != null && lstGoals.size() > 0) {
            for (SelectList actGoal : lstGoals) {
                if (returnStr.isEmpty() == false)
                    returnStr += "\n";
                if (actGoal.getName() != null) {
                    returnStr += "-" + actGoal.getName();
                }
            }
        }
        return returnStr;
    }

    public void doReconstructedActivityInfo() {

        lstReconstructed = new ArrayList<>();
        if (lstActMonPlan != null && lstActMonPlan.size() > 0) {
            for (ActivityMonitoringPlanLog act : lstActMonPlan) {

                SelectList actSup = this.idToObject(act.getActSupervisionId(), lstActivities);
                SelectList actSource = this.idToObject(act.getAidSourceId(), lstSources);

                String comments = act.getComments();

                ReconstructedActivityInfo actRec = new ReconstructedActivityInfo();

                actRec.setStart(act.getStart());
                actRec.setEnd(act.getEnd());
                actRec.setSupActivity(actSup.getName());
                actRec.setLstAssignedArrangements(this.generateAssignedArrangements(act.getId()));

                String aidSource = actSource.getName();
                if (actSource.getDescription() != null)
                    aidSource += " - " + actSource.getDescription();
                else
                    aidSource += "";
                actRec.setAidSource(aidSource);

                actRec.setStatus(act.getStatus());
                actRec.setComments(act.getComments());
                actRec.setUser(act.getUser());

                lstReconstructed.add(actRec);
            }
        }
    }

    public List<SelectList> generateAssignedArrangements(Long actMonPlanId) {
        Boolean flg = false;
        List<SelectList> lstAssArr = new ArrayList<>();
        if (lstActMonPlanArrangement != null && lstActMonPlanArrangement.size() > 0) {
            for (int i = lstActMonPlanArrangement.size() - 1; i >= 0; i--) {

                ActivityMonitoringPlanArrangementLog mpArr = lstActMonPlanArrangement.get(i);

                if (mpArr.getActMonPlanId().equals(actMonPlanId)) {
                    flg = true;
                    SelectList assArr = this.idToObject(mpArr.getAssignedArrangementId(), lstArrangement);
                    SelectList newObj = new SelectList(assArr.getId(), assArr.getName(), this.setStatus(mpArr.getStatus()));
                    lstAssArr.add(newObj);
                }
                if (flg == true && (mpArr.getActMonPlanId().equals(actMonPlanId) == false))
                    break;
            }
        }
        return lstAssArr;
    }

    public String setStatus(Integer status) {
        switch (status) {
            case -1:
                return "No definido";
            case 0:
                return "Incumplido";
            case 1:
                return "Cumplido";
            default:
                return "";
        }
    }

    public SelectList idToObject(Long id, List<SelectList> list) {

        SelectList ret = null;

        if (list != null && list.size() > 0) {
            for (SelectList act : list) {
                if (act.getId().equals(id)) {
                    ret = act;
                    break;
                }
            }
        }

        return ret;
    }

    public List<ReconstructedActivityInfo> getLstReconstructed() {
        return lstReconstructed;
    }

    public void setLstReconstructed(List<ReconstructedActivityInfo> lstReconstructed) {
        this.lstReconstructed = lstReconstructed;
    }


    public String summarySupervisionActivities() {
        String returnStr = "";

        if (this.lstReconstructed != null && this.lstReconstructed.size() > 0) {
            returnStr += "-Actividades registradas: " + Integer.toString(this.lstReconstructed.size()) + "\n";
            returnStr += "-Tipo: " + this.summaryActsToStr() + "\n";
            returnStr += "-Realizadas por el supervisor: " + this.summaryNoActsByStatus(MonitoringConstants.STATUS_ACTIVITY_DONE) + "\n";
            returnStr += "-No realizadas por el supervisor: " + this.summaryNoActsByStatus(MonitoringConstants.STATUS_ACTIVITY_FAILED) + "\n";
            returnStr += "-Obligaciones no definidas: " + this.summaryNoArrangementsByStatus(-1) + "\n";
            returnStr += "-Obligaciones incumplidas: " + this.summaryNoArrangementsByStatus(0) + "\n";
            returnStr += "-Obligaciones cumplidas: " + this.summaryNoArrangementsByStatus(1) + "\n";
        }

        return returnStr;
    }

    public String summaryActsToStr() {
        String returnStr = "";

        for (ReconstructedActivityInfo activityInfo : this.lstReconstructed) {
            if (returnStr.isEmpty() == false)
                returnStr += ",";
            returnStr += activityInfo.getSupActivity();
        }

        return returnStr;
    }

    public String summaryNoActsByStatus(String status) {
        Integer noActs = 0;
        for (ActivityMonitoringPlanLog act : this.lstActMonPlan) {
            if (act.getStatus().equals(status))
                noActs++;
        }

        return noActs.toString();
    }

    public String summaryNoArrangementsByStatus(Integer status) {
        Integer noActs = 0;

        for (ActivityMonitoringPlanArrangementLog act : this.lstActMonPlanArrangement) {
            if (act.getStatus().equals(status))
                noActs++;
        }
        return noActs.toString();
    }

    public List<SelectList> getLstFulfillment() {
        return lstFulfillment;
    }

    public void setLstFulfillment(List<SelectList> lstFulfillment) {
        this.lstFulfillment = lstFulfillment;
    }

    public String summarySupervisionFulfillmentReport() {
        String returnStr = "";
        Integer total = 0;
        Integer partial = 0;
        Integer accomplished = 0;

        if (this.lstFulfillment != null & this.lstFulfillment.size() > 0) {

            for (SelectList act : this.lstFulfillment) {
                if (act.getDescription().equals(MonitoringConstants.FULFILLMENT_TYPE_CODE_FULFILLMENT))
                    accomplished++;
                else if (act.getDescription().equals(MonitoringConstants.FULFILLMENT_TYPE_CODE_PARTIAL))
                    partial++;
                else if (act.getDescription().equals(MonitoringConstants.FULFILLMENT_TYPE_CODE_TOTAL))
                    total++;
            }

            returnStr += "-Incumplimiento parcial: " + partial + "\n";
            returnStr += "-Incumplimiento total: " + total + "\n";
            returnStr += "-Cumplimiento: " + accomplished + "\n";

        }

        return returnStr;
    }
}
