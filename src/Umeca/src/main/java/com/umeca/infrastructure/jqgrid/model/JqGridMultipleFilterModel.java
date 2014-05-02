package com.umeca.infrastructure.jqgrid.model;

import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 4/30/14
 * Time: 4:57 PM
 */
public class JqGridMultipleFilterModel {
    public String groupOp;
    public List<JqGridRulesModel> rules;

    public String getGroupOp() {
        return groupOp;
    }

    public void setGroupOp(String groupOp) {
        this.groupOp = groupOp;
    }

    public List<JqGridRulesModel> getRules() {
        return rules;
    }

    public void setRules(List<JqGridRulesModel> rules) {
        this.rules = rules;
    }
}
