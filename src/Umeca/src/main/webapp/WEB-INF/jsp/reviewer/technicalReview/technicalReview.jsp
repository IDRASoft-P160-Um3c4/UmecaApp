<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/technicalReviewCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/hiddenDrct.js"></script>

    <title>Opini&oacute;n t&eacute;cnica</title>
</head>

<body scroll="no" ng-app="ptlUmc">

<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<h2 class="element-center"><i class="icon-archive"></i>&nbsp;&nbsp;Opini&oacute;n t&eacute;cnica</h2>


<form id="FormTecRevId" name="FormTecRevId" class="form-horizontal"
      role="form" ng-controller="upsertController" method="post">
<br/>


<div class="container body-content" ng-controller="tecRevController">

<input type="hidden" name="idVerification" id="idVerification" value="${idVerification}"/>
<input type="hidden" name="txtListQuest" id="txtListQuest" ng-init="lstQuestSel=${lstQuestSel_prev}"
       value="{{lstQuestSel}}"/>
<input type="hidden" name="subtotalsTxt" id="subtotalsTxt" value="{{lstSubtotSrv}}"/>


<div ng-init='sectionList=${listaSecc}; returnId=${returnId}; canEdit=${canEdit}; flgIsEvaluated=${hasRevTec}; flgShowRisk=${showRisk}; lstSubtotSrv=${lstSubtotTxt_prev};
urlManagereval="<c:url value='/managereval/showCaseEvaluation/index.html'/>"; urlTecRev="<c:url value='/reviewer/technicalReview/index.html'/>";
urlManagerSup="<c:url value='/supervisor/showCaseSupervision/index.html'/>";'>

<div class="widget-box">
    <div class="widget-header">Datos generales</div>

    <div class="widget-body">

        <div class="control-group">
            <br/>


            <div class="row">
                <div class="col-xs-2 col-xs-offset-1">
                    No. Carpeta:
                </div>
                <div class="col-xs-2 element-left">
                    <input class="form-control" ng-model="foldId" ng-init='foldId="${foldId}"' disabled>
                </div>
            </div>

            <br/>

            <div class="row">
                <div class="col-xs-2 col-xs-offset-1">
                    Nombre del imputado:
                </div>
                <div class="col-xs-5 element-left">
                    <input class="form-control" ng-model="imputedFullName"
                           ng-init='imputedFullName="${imputedFullName}"' disabled>
                </div>
            </div>

            <br/>
        </div>
    </div>
</div>


<div class="row element-right" ng-show="flgIsEvaluated==true && canEdit==false">
<span class="btn btn-default btn-sm" ng-click="returnUrl(returnId)">
                                Regresar
                            </span>

</div>


<div class="tabbable tabs-left">
<br/>
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
                            <input class="ace col-xs-1"
                                   ng-disabled="flgIsEvaluated == true && canEdit==false"
                                   name="{{subsect.tabId}}"
                                   type="{{question.type}}"
                                   ng-click="changeVal(question.type,subsect.tabId,question.questionId,question.ptsValue);"
                                   ng-checked="checkQuest(question.questionId);">
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
                            <div class="control-group">

                                <br/>

                                <div class="row">
                                    <div class="col-xs-offset-1">
                                        <label>Total:</label>
                                        <input type="text" readonly ng-model="totTecRev" name="totalRisk"
                                               id="totalRisk"
                                               ng-init="totTecRev=${totRisk_prev}"/>
                                        &nbsp;
                                                    <span class="btn btn-default"
                                                          ng-click="calcRisk();"
                                                          ng-show="flgIsEvaluated == false || canEdit==true">Calcular</span>
                                    </div>
                                    <br/>
                                </div>

                                <div ng-show="flgShowRisk == true">

                                    <div class="row">

                                        <div class="col-xs-10 col-xs-offset-1">
                                            <label for="comments">Comentarios</label>
                                            <textarea class="form-control limited" name="comments" id="comments"
                                                      maxlength="980"
                                                      ng-disabled="flgIsEvaluated == true && canEdit==false"
                                                      required
                                                      data-val="true"
                                                      data-val-required="Comentarios es un campo requerido"
                                                    >${comments}</textarea>


                                        </div>
                                        <div class="col-xs-10 col-xs-offset-1">
                                                        <span class="field-validation-valid" data-valmsg-for="comments"
                                                              data-valmsg-replace="true"></span>
                                        </div>
                                    </div>


                                    <div class="row" ng-show="totTecRev<-15">
                                        <br/>

                                        <div class="alert alert-danger col-xs-10 col-xs-offset-1">

                                            <strong>
                                                <i class="icon-link"></i>
                                                Riesgo alto!
                                            </strong>
                                            Libertad muy dif&iacute;cil de cumplir.
                                            <br>
                                        </div>
                                    </div>


                                    <div class="row"
                                         ng-show="totTecRev>-16 && totTecRev<0">
                                        <br/>

                                        <div class="alert alert-warning col-xs-10 col-xs-offset-1">

                                            <strong>
                                                <i class="icon-warning-sign"></i>
                                                Riesgo medio!
                                            </strong>
                                            Se puede recomendar combinaci&oacute;n de medidas cautelares en
                                            libertad bajo niveles de supervisi&oacute;n.
                                            <br>
                                        </div>
                                    </div>

                                    <div class="row"
                                         ng-show="totTecRev>-1 && totTecRev<10">
                                        <br/>

                                        <div class="alert alert-info col-xs-10 col-xs-offset-1">

                                            <strong>
                                                <i class="icon-tags"></i>
                                                Riesgo bajo!
                                            </strong>
                                            Se puede recomendar combinaci&oacute;n de medidas cautelares en
                                            libertad bajo niveles de supervisi&oacute;n.
                                            <br>
                                        </div>
                                    </div>

                                    <div class="row" ng-show="totTecRev>9">
                                        <br/>

                                        <div class="alert alert-success col-xs-10 col-xs-offset-1">

                                            <strong>
                                                <i class="icon-unlink"></i>
                                                Riesgo m&iacute;nimo!
                                            </strong>
                                            Se puede recomendar combinaci&oacute;n de medidas cautelares en
                                            libertad bajo niveles de supervisi&oacute;n.
                                            <br>
                                        </div>
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
            <div ng-show="MsgErrorLst.length > 0" class="alert alert-danger element-center">
                <ul ng-repeat="error in MsgErrorLst">
                    <li>{{error}}</li>
                </ul>
            </div>
        </div>
    </div>

    <div class="row element-right">
                            <span class="btn btn-default btn-sm"
                                  ng-click="returnUrl(returnId)">
                                Cancelar
                            </span>
                            <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                                  ng-click="submitRedirect('#FormTecRevId', '<c:url value='/reviewer/technicalReview/doUpsert.json'/>',false,validateSave)"
                                  ng-show="flgIsEvaluated == false || canEdit==true">
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