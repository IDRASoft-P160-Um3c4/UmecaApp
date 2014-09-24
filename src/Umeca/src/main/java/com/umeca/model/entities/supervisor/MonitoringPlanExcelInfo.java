package com.umeca.model.entities.supervisor;

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
                if (returnStr != "")
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
                if (returnStr != "")
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
                if (returnStr != "")
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
                actRec.setAidSource(actSource.getName() + " - " + actSource.getDescription());
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

                if (mpArr.getId() == actMonPlanId) {
                    flg = true;
                    SelectList assArr = this.idToObject(mpArr.getAssignedArrangementId(), lstArrangement);
                    SelectList newObj = new SelectList(assArr.getId(), assArr.getName(), this.setStatus(mpArr.getStatus()));
                    lstAssArr.add(newObj);
                }
                if (flg == true && mpArr.getActMonPlanId() != actMonPlanId)
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
                if (act.getId() == id) {
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

}
