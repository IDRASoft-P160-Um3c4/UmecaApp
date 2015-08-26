<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });

    $('.date-picker').datepicker({autoclose: true}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:600px" ng-controller="requestFulfillmentCtrl"
             ng-init='lstFulfillmentReport = ${lstFulfillmentReport};lstArrangements=${lstArrangements};'>
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-off"></i>&nbsp;&nbsp;Solicitud de
                            reporte de incumplimiento</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCatId" name="FormCatId" ng-submit="submit('#FormCatId')" class="form-horizontal"
                          role="form">

                        <input type="hidden" name="id" value="${idMP}"/>

                        <div class="row">
                            <div class="col-xs-12">
                                <div class='panel panel-primary'>
                                    <div class="panel-heading">
                                        <span class="glyphicon glyphicon-map-marker"></span>&nbsp;&nbsp;Solicitud de
                                        reporte de incumplimiento
                                    </div>

                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-xs-10 col-xs-offset-1 widget-container-span">
                                                <div class="widget-box">
                                                    <div class="widget-header widget-header-small header-color-dark">
                                                        <h6>Tipo de reporte</h6>
                                                    </div>
                                                    <div class="widget-body">
                                                        <div class="widget-main padding-12">
                                                            <input type="hidden" name="fulfillmentReportId" value="{{fulfillmentType.id}}"/>
                                                            <div>
                                                                <select class="form-control element-center"
                                                                        ng-model="fulfillmentType"
                                                                        ng-options="e.name for e in lstFulfillmentReport">
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-10 col-xs-offset-1 widget-container-span">
                                                <input type="hidden" name="lstArrangement" value="{{lstArrangements}}"/>
                                                <div class="widget-box">
                                                    <div class="widget-header widget-header-small header-color-dark">
                                                        <h6>Obligaciones procesales</h6>
                                                    </div>
                                                    <input type="hidden" name="" value=""/>

                                                    <div class="widget-body">
                                                        <div class="widget-main padding-12">
                                                            <span ng-class='MsgErrorArrangementSel&&MsgErrorArrangementSel!="" ? "field-validation-error" : "input-validation-valid"'
                                                                  ng-bind-html="MsgErrorArrangementSel"></span>
                                                            <div class="row"
                                                                 ng-repeat="arrangment in lstArrangements">
                                                                <div class="checkbox">
                                                                    <label>
                                                                        <input class="ace"
                                                                               type="checkbox"
                                                                               ng-model="lstArrangements[$index].lock">
                                                                        <span class="lbl col-xs-10">&nbsp;&nbsp;{{arrangment.name}} {{arrangment.description}}</span>
                                                                    </label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-10 col-xs-offset-1 widget-container-span">
                                                <div class="widget-box">
                                                    <div class="widget-header widget-header-small header-color-dark">
                                                        <h6>Fecha de incumplimiento</h6>
                                                    </div>
                                                    <div class="widget-body">
                                                        <div class="widget-main padding-12">
                                                            <div class="row">
                                                                <div class="col-xs-8 col-sm-11">
                                                                    <div class="input-group">
                                                                        <input id="accomplishmentDate"
                                                                               name="accomplishmentDate"
                                                                               ng-model="accomplishmentDate"
                                                                               class="form-control date-picker"
                                                                               type="text"
                                                                               data-date-format="yyyy/mm/dd"
                                                                               data-val="true"
                                                                               data-val-required="Fecha de incumplimiento es un campo requerido"/>
                                    <span class="input-group-addon">
                                        <i class="icon-calendar bigger-110"></i>
                                    </span>
                                                                    </div>
                                <span class="field-validation-valid" data-valmsg-for="accomplishmentDate"
                                      data-valmsg-replace="true"></span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-xs-10 col-xs-offset-1 widget-container-span">
                                                <div class="widget-box">
                                                    <div class="widget-header widget-header-small header-color-dark">
                                                        <h6>Comentarios</h6>
                                                    </div>
                                                    <div class="widget-body">
                                                        <div class="widget-main padding-12">
                                                            <div class="row">
                                                                <div class="col-xs-8 col-sm-11">
                                                                <textarea class="form-control limited" name="comment"
                                                                          ng-model="comment"
                                                                          maxlength="500"
                                                                          data-val="true"
                                                                          data-val-required="Comnetarios es un campo requerido">
                                                                </textarea>
                                                                <span class="field-validation-valid" data-valmsg-for="comment"
                                                                      data-valmsg-replace="true"></span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <br/>

                    <div class="row">
                        <div class="col-xs-12">
                            <div ng-show="MsgError&&MsgError!=''" class="alert alert-danger element-center" ng-bind-html="MsgError">
                                <%--{{MsgError}}--%>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer" id="btn-act-footer">
                    <button class="btn btn-default btn-sm" ng-click="cancel()">Cancelar</button>
                    <button class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                            id="btn-def-ck" ng-click="submit('#FormCatId', '<c:url value='/supervisor/log/requestAccomplishmentLog.json' />')">
                        Solicitar
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

