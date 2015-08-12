<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/handingOverCtrl.js"></script>
<script>
    jQuery(function ($) {
        $('.date-picker').datepicker({autoclose: true}).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });

        $('#handingOverTime').timepicker({
            minuteStep: 1,
            showSeconds: true,
            showMeridian: false
        }).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });

    });
</script>
<div ng-controller="handingOverController">

    <div class="row"
         ng-init="handingOverMil=${handingOverMil==null?'':handingOverMil}; hasHandingOverInfo=${hasHandingOverInfo==null?false:hasHandingOverInfo}">
        <div class="col-xs-12">
            <div class="col-xs-2 col-xs-offset-8">
                <h4 class="header smaller lighter blue">
                    <small>Fecha actual:</small>
                    &nbsp;{{currentDate}}
                </h4>
            </div>
            <div class="col-xs-2">
                <h4 class="header smaller lighter blue">
                    <small>Hora actual:</small>
                    &nbsp;{{currentTime}}
                </h4>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <form id="FormHandingOverId" name="FormHandingOverId" class="form-horizontal" role="form" method="post">
                <div class="col-xs-4">
                    <input type="hidden" name="idCase" value="${idCase}"/>

                    <div class="col-xs-4">
                        <label>Fecha de detenci&oacute;n</label>
                    </div>
                    <div class="col-xs-8">
                        <div class="input-group bootstrap-timepicker">
                            <input id="handingOverDate" name="handingOverDate"
                                   ng-model="handingOverDateStr"
                                   class="form-control date-picker"
                                   id="id-date-picker-1" type="text"
                                   data-date-format="yyyy/mm/dd" data-val="true"
                                   data-val-required="Fecha de detenci&oacute;n es un campo requerido"/>
                            <span class="input-group-addon">
                            <i class="icon-calendar bigger-110"></i>
                            </span>
                        </div>
                    </div>
                    <div class="col-xs-8 col-xs-offset-4">
                        <span class="field-validation-valid" data-valmsg-for="handingOverDate"
                              data-valmsg-replace="true"></span>
                    </div>
                </div>
                <div class="col-xs-4">
                    <div class="col-xs-4">
                        <label for="handingOverTime">Hora de detenci&oacute;n</label>
                    </div>
                    <div class="col-xs-8">
                        <div class="input-group bootstrap-timepicker">
                            <input id="handingOverTime" name="handingOverTime" ng-model="handingOverTimeStr"
                                   readonly type="text" class="form-control umeca-time-picker"
                                   data-val="true"
                                   data-val-required="Hora de detenci&oacute;n es un campo requerido"/>
                        <span class="input-group-addon"><i
                                class="icon-time bigger-110"></i></span>
                        </div>
                    </div>
                    <div class="col-xs-8 col-xs-offset-4">
                        <span class="field-validation-valid" data-valmsg-for="handingOverTime"
                              data-valmsg-replace="true"></span>
                    </div>
                </div>
            </form>
            <div class="col-xs-2 element-left">
            <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true" ng-show="${!readonlyBand}"
                  ng-click="submitDetInfo('#FormHandingOverId','<c:url value='/reviewer/meeting/saveTimeDetention.json'/>');">
            Guardar informaci&oacute;n de detenci&oacute;n
            </span>
            </div>
        </div>
    </div>
    <div class="row" ng-show="hasHandingOverInfo==true">
        <div class="col-xs-12">
            <h4 class="header smaller lighter blue">
                <small>Tiempo que resta para cumplir el t&eacute;rmino de 48 horas:</small>
                &nbsp;{{leftTime}}
            </h4>
        </div>
    </div>
</div>
<script>
    var date=new Date();
    $('#handingOverDate').datepicker({autoclose:true, endDate:date}).next().on(ace.click_event, function(){
        $(this).prev().focus();
    });
</script>