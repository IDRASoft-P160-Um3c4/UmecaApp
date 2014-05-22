<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/technicalReviewCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/hiddenDrct.js"></script>

    <title>Opinión técnica</title>
</head>

<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<h2 class="element-center"><i class="icon-archive"></i>&nbsp;&nbsp;Opinión técnica</h2>


<form id="FormTecRevId" name="FormTecRevId" ng-submit="submit('#FormTecRevId')" class="form-horizontal"
      role="form" ng-controller="upsertController" method="post">
<br/>


<div class="container body-content" ng-controller="tecRevController">

<input type="hidden" name="meetingId" id="meetingId" value="${idMeeting}"/>
<input type="hidden" name="txtListQuest" id="txtListQuest" ng-init="lstQuestSel=${lstQuestSel_prev}"
       value="{{lstQuestSel}}"/>

<div ng-init='sectionList=${listaSecc}; flgIsEvaluated=${intHasRevTec};'>

    <div class="tabbable tabs-left">

        <ul class="nav nav-tabs">
            <li ng-repeat="sect in sectionList"
                ng-init="m.extras[$index]=(toObject(sect.extras));"
                ng-class="m.actTab=={{$index}} ? 'active' : ''">

                <a data-toggle="tab" href="{{'#' + sect.tabId}}">
                    <i class="{{m.extras[$index].class}}"></i>
                    {{sect.sectionName}}
                </a>
            </li>

            <li ng-class="m.actTab==5 ? 'active' : ''">

                <a data-toggle="tab" href="#secEval">
                    <i class="icon-gears blue bigger-200"></i>
                    Calcular riesgo
                </a>
            </li>

        </ul>

        <div class="tab-content">

            <div ng-class="m.actTab=={{$index}} ? 'tab-pane active' : 'tab-pane'"
                 ng-repeat="sect in sectionList" id="{{sect.tabId}}">

                <div class="widget-box" ng-repeat="subsect in sect.childs">
                    <div class="widget-header">{{subsect.sectionName}}
                        <div class="widget-toolbar">
                            <a data-action="collapse" href="#"><i class="icon-chevron-up"></i></a>
                        </div>
                    </div>

                    <div class="widget-body">

                        <div class="control-group">
                            <br/>

                            <div class="row" ng-repeat="question in subsect.questions">
                                <div class="{{question.type}} col-xs-offset-1">
                                    <label>
                                        <input class="ace col-xs-1 " name="{{subsect.tabId}}"
                                               type="{{question.type}}"
                                               ng-click="changeVal(question.type,subsect.tabId,question.questionId,question.ptsValue);">
                                        <span class="lbl col-xs-10">&nbsp;&nbsp;{{question.questionText}}</span>
                                    <span class="lbl col-xs-1 element-right">
                                        {{question.ptsValue > 0 ? '+'+question.ptsValue : question.ptsValue}}
                                    </span>
                                    </label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-xs-4 col-xs-offset-8">
                                    <label>Total:</label>
                                    <input type="text" readonly ng-model="lstTot[subsect.tabId]"
                                           ng-init="lstTot[subsect.tabId]=0"/>
                                </div>
                            </div>

                            <br/>
                        </div>
                    </div>
                </div>
            </div>


            <div ng-class="m.actTab==5 ? 'tab-pane active' : 'tab-pane'" id="secEval">
                <div class="widget-box">
                    <div class="widget-header">Calcular riesgo</div>

                    <div class="widget-body">
                        <div class="control-group col-xs-offset-1">
                            <br/>

                            <div class="row">
                                <div class="widget-box col-xs-11">
                                    <div class="widget-header">Riesgo</div>

                                    <div class="widget-body">
                                        <div class="control-group" ng-init="totTecRev=0; flgIsEvaluated=-1">

                                            <br/>

                                            <div class="row">
                                                <div class="col-xs-offset-1">
                                                    <label>Total:</label>
                                                    <input type="text" readonly ng-model="totTecRev" name="totalRisk" id="totalRisk"
                                                           ng-init="totTecRev=${totRisk_prev}"/>
                                                    &nbsp;
                                                    <span class="btn btn-default"
                                                          ng-click="calcRisk();">Calcular</span>
                                                </div>
                                                <br/>
                                            </div>

                                            <div ng-show="flgIsEvaluated>0">

                                                <div class="row" ng-show="totTecRev<-15">
                                                    <div class="alert alert-danger col-xs-10 col-xs-offset-1">

                                                        <strong>
                                                            <i class="icon-link"></i>
                                                            Riesgo alto!
                                                        </strong>
                                                        Libertad muy difícil de cumplir.
                                                        <br>
                                                    </div>
                                                </div>


                                                <div class="row"
                                                     ng-show="totTecRev>-16 && totTecRev<0">
                                                    <div class="alert alert-warning col-xs-10 col-xs-offset-1">

                                                        <strong>
                                                            <i class="icon-warning-sign"></i>
                                                            Riesgo medio!
                                                        </strong>
                                                        Se puede recomendar combinación de medidas cautelares en
                                                        libertad bajo niveles de supervisión.
                                                        <br>
                                                    </div>
                                                </div>

                                                <div class="row"
                                                     ng-show="totTecRev>-1 && totTecRev<10">
                                                    <div class="alert alert-info col-xs-10 col-xs-offset-1">

                                                        <strong>
                                                            <i class="icon-tags"></i>
                                                            Riesgo bajo!
                                                        </strong>
                                                        Se puede recomendar combinación de medidas cautelares en
                                                        libertad bajo niveles de supervisión.
                                                        <br>
                                                    </div>
                                                </div>

                                                <div class="row" ng-show="totTecRev>9">
                                                    <div class="alert alert-success col-xs-10 col-xs-offset-1">

                                                        <strong>
                                                            <i class="icon-unlink"></i>
                                                            Riesgo mínimo!
                                                        </strong>
                                                        Se puede recomendar combinación de medidas cautelares en
                                                        libertad bajo niveles de supervisión.
                                                        <br>
                                                    </div>
                                                </div>

                                                <div>
                                                    <label for="comments">Comentarios</label>
                                                    <textarea class="form-control limited" name="comments" id="comments"
                                                              maxlength="50"></textarea>
                                                </div>
                                            </div>

                                            <br/>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <br/>
                        </div>
                    </div>
                    <br/>
                </div>

                <div class="row">
                    <div class="col-xs-12">
                        <div ng-show="MsgError" class="alert alert-danger element-center">
                            {{MsgError}}
                        </div>
                    </div>
                </div>

                <div class="row element-right">
                            <span class="btn btn-default btn-sm" ng-click="cancel()">
                                Cancelar
                            </span>
                            <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                                  ng-click="submit('#FormTecRevId', '/reviewer/technicalReview/doUpsert.json')">
                                  Guardar
                            </span>
                </div>

            </div>


        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
<%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
</div>
</form>
</body>
</html>