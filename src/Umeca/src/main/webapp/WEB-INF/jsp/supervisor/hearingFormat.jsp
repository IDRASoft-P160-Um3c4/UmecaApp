<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/hearingFormatCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/hiddenDrct.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/daterangepicker.min.js"></script>
    <script type="text/javascript">
        jQuery(function ($) {
            $('#id-disable-check').on('click', function () {
                var inp = $('#form-input-readonly').get(0);
                if (inp.hasAttribute('disabled')) {
                    inp.setAttribute('readonly', 'true');
                    inp.removeAttribute('disabled');
                    inp.value = "This text field is readonly!";
                }
                else {
                    inp.setAttribute('disabled', 'disabled');
                    inp.removeAttribute('readonly');
                    inp.value = "This text field is disabled!";
                }
            });

            $('.date-picker').datepicker({autoclose: true}).next().on(ace.click_event, function () {
                $(this).prev().focus();
            });
            $('input[name=date-range-picker]').daterangepicker().prev().on(ace.click_event, function () {
                $(this).next().focus();
            });

            $('#modal-form input[type=file]').ace_file_input({
                style: 'well',
                btn_choose: 'Drop files here or click to choose',
                btn_change: null,
                no_icon: 'icon-cloud-upload',
                droppable: true,
                thumbnail: 'large'
            })

            $('#modal-form').on('shown.bs.modal', function () {
                $(this).find('.chosen-container').each(function () {
                    $(this).find('a:first-child').css('width', '210px');
                    $(this).find('.chosen-drop').css('width', '210px');
                    $(this).find('.chosen-search input').css('width', '200px');
                });
            })


            $('#timeVincProcess').timepicker({
                minuteStep: 1,
                showSeconds: true,
                showMeridian: false
            }).next().on(ace.click_event, function () {
                $(this).prev().focus();
            });


            $('#registerTime').timepicker({
                minuteStep: 1,
                showSeconds: true,
                showMeridian: false
            }).next().on(ace.click_event, function () {
                $(this).prev().focus();
            });

            $('#initTime').timepicker({
                minuteStep: 1,
                showSeconds: true,
                showMeridian: false
            }).next().on(ace.click_event, function () {
                $(this).prev().focus();
            });

            $('#endTime').timepicker({
                minuteStep: 1,
                showSeconds: true,
                showMeridian: false
            }).next().on(ace.click_event, function () {
                $(this).prev().focus();
            });

        });
    </script>

    <title>Formato de audiencia</title>
</head>

<body scroll="no" ng-app="ptlUmc">

<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<h2 class="element-center"><i class="icon-archive"></i>&nbsp;&nbsp;Formato de audiencia</h2>

<form id="FormFormatId" name="FormFormatId" class="form-horizontal"
      role="form" ng-controller="upsertController" method="post">
<br/>

<div class="container body-content" ng-controller="hearingFormatController">

<div class="row">

<div class="widget-box">
<div class="widget-header">Datos generales</div>

<div class="widget-body">

<div class="row">
<div class="col-xs-10 col-xs-offset-1">

<div class="row">
    <br/>

    <div class="col-xs-3">
        <label>No. Carpeta:</label>

        <div class="input-group">
            <input type="text" class="form-control search-query input-small"
                   placeholder="No. de carpeta..."/>
                                    <span class="input-group-btn">
                                        <button type="button" class="btn btn-purple btn-sm">
                                            Buscar
                                            <i class="icon-search icon-on-right bigger-110"></i>
                                        </button>
                                    </span>
        </div>
    </div>
</div>

<div class="row">
    <br/>

    <div class="col-xs-3">
        <label for="numDate">Cita</label>
        <br/>
        <input id="numDate" type="text" class="input-large"/>
    </div>

    <div class="col-xs-3">
        <label for="registerTime">Hora registro</label>

        <div class="input-group bootstrap-timepicker">
            <input id="registerTime" type="text" class="form-control"/>
                                            <span class="input-group-addon">
                                                <i class="icon-time bigger-110"></i>
                                            </span>
        </div>
    </div>

    <div class="col-xs-3">
        <label for="dateHearing">Fecha de la audiencia</label>

        <div class="input-group">
            <input class="form-control date-picker" id="dateHearing" type="text"
                   data-date-format="dd-mm-yyyy"/>
                                                <span class="input-group-addon">
                                                    <i class="icon-calendar bigger-110"></i>
                                                </span>
        </div>

    </div>

    <div class="col-xs-3">
        <label for="room">Sala</label>
        <br/>
        <input id="room" type="text" class="input-large"/>
    </div>

</div>

<div class="row">
    <br/>

    <div class="col-xs-3">
        <label for="initTime">Hora inicio</label>

        <div class="input-group bootstrap-timepicker">
            <input id="initTime" type="text" class="form-control"/>
                                            <span class="input-group-addon">
                                                <i class="icon-time bigger-110"></i>
                                            </span>
        </div>
    </div>

    <div class="col-xs-3">
        <label for="endTime">Hora termino</label>

        <div class="input-group bootstrap-timepicker">
            <input id="endTime" type="text" class="form-control"/>
                                            <span class="input-group-addon">
                                                <i class="icon-time bigger-110"></i>
                                            </span>
        </div>
    </div>

    <div class="col-xs-6">

        <label for="judge">Juez</label>
        <br/>
        <input id="judge" type="text" class="input-xxlarge"/>
    </div>

</div>

<div class="row">
    <br/>

    <div class="col-xs-6">

        <label for="mpName">Ministerio publico</label>
        <br/>
        <input id="mpName" type="text" class="input-xxlarge"/>
    </div>

    <div class="col-xs-6">

        <label for="defName">Defensor</label>
        <br/>
        <input id="defName" type="text" class="input-xxlarge"/>
    </div>

</div>


<div class="row">
    <br/>

    <div class="col-xs-4">

        <label for="impName">Nombre(s)</label>
        <br/>
        <input id="impName" type="text" class="input-xxlarge"/>

    </div>

    <div class="col-xs-4">

        <label for="impFLastName">Primer apellido</label>
        <br/>
        <input id="impFLastName" type="text" class="input-xxlarge"/>

    </div>


    <div class="col-xs-4">

        <label for="impLLastName">Segundo apellido</label>
        <br/>
        <input id="impLLastName" type="text" class="input-xxlarge"/>

    </div>

</div>

<div class="row">
    <br/>

    <div class="col-xs-4">

        <label for="birthDate">Fecha de nacimiento:</label>

        <div class="row">
            <div class="col-xs-8 col-sm-11">
                <div class="input-group">
                    <input class="form-control date-picker" id="birthDate" type="text"
                           data-date-format="dd-mm-yyyy"/>
                                                <span class="input-group-addon">
                                                    <i class="icon-calendar bigger-110"></i>
                                                </span>
                </div>
            </div>
        </div>

    </div>

    <div class="col-xs-4">

        <label for="impAge">Edad</label>
        <br/>
        <input id="impAge" type="text" class="input-xxlarge" readonly/>

    </div>

    <div class="col-xs-4">

        <label for="impTel">Telefono(s)</label>
        <br/>
        <input id="impTel" type="text" class="input-xxlarge"/>

    </div>

</div>

<div class="row">
    <br/>

    DIRECCIOOOON;

</div>

<div class="row">
    <br/>

    <div class="col-xs-6">
        <label for="crimes">Delito(s)</label>
        <br/>
        <textarea class="form-control limited" name="crimes" id="crimes"
                  maxlength="980"
                  required>${crimes}</textarea>
    </div>

    <div class="col-xs-6">
        <label for="aditionalData">Datos adicionales de la audiencia</label>
        <br/>
        <textarea class="form-control limited" name="aditionalData" id="aditionalData"
                  maxlength="980"
                  required>${aditionalData}</textarea>
    </div>


</div>

<div class="row">
    <br/>

    <div class="col-xs-3 col-xs-offset-1">
        <label>Control de detención</label>

        <div class="radio">
            <label>
                <input class="ace col-xs-1" type="radio">
                <span class="lbl col-xs-10">&nbsp;&nbsp;Legal</span>
            </label>
            <label>
                <input class="ace col-xs-1" type="radio">
                <span class="lbl col-xs-10">&nbsp;&nbsp;Ilegal</span>
            </label>
        </div>
    </div>

    <div class="col-xs-3 col-xs-offset-1">
        <label>Ampliación del termino</label>

        <div class="radio">
            <label>
                <input class="ace col-xs-1" type="radio">
                <span class="lbl col-xs-10">&nbsp;&nbsp;72 hrs</span>
            </label>
            <label>
                <input class="ace col-xs-1" type="radio">
                <span class="lbl col-xs-10">&nbsp;&nbsp;144 hrs</span>
            </label>
            <label>
                <input class="ace col-xs-1" type="radio">
                <span class="lbl col-xs-10">&nbsp;&nbsp;No</span>
            </label>
        </div>
    </div>
    <div class="col-xs-3 col-xs-offset-1">
        <label>Audiencia</label>

        <div class="radio">
            <label>
                <input class="ace col-xs-1" type="radio">
                <span class="lbl col-xs-10">&nbsp;&nbsp;SCPP</span>
            </label>
            <label>
                <input class="ace col-xs-1" type="radio">
                <span class="lbl col-xs-10">&nbsp;&nbsp;MC</span>
            </label>
        </div>
    </div>

</div>

<div class="row">
    <br/>

    <div class="col-xs-5 col-xs-offset-1">

        <div class="row">
            <label>Formulación de imputación</label>

            <div class="radio">

                <label>
                    <input class="ace col-xs-1" type="radio" ng-model="genImpRadio" value="true">
                    <span class="lbl col-xs-10">&nbsp;&nbsp;Si</span>

                </label>

                <label>
                    <input class="ace col-xs-1" type="radio" ng-model="genImpRadio" value="false">
                    <span class="lbl col-xs-10">&nbsp;&nbsp;No</span>
                </label>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-6">
                <br/>
                <label for="dateGenImp">Fecha</label>

                <div class="input-group">

                    <input class="form-control date-picker input-small" ng-disabled="genImpRadio=='false'"
                           id="dateGenImp"
                           type="text"
                           data-date-format="dd-mm-yyyy"/>
                                                <span class="input-group-addon">
                                                    <i class="icon-calendar bigger-110"></i>
                                                </span>
                </div>
            </div>
        </div>

    </div>

    <div class="col-xs-5 col-xs-offset-1">

        <div class="row">
            <label>Vinculación a proceso</label>

            <div class="radio">
                <label>
                    <input class="ace col-xs-1" type="radio" ng-model="vincProcessRadio" value="true">
                    <span class="lbl col-xs-10">&nbsp;&nbsp;Si</span>
                </label>
                <label>
                    <input class="ace col-xs-1" type="radio" ng-model="vincProcessRadio" value="false">
                    <span class="lbl col-xs-10">&nbsp;&nbsp;No</span>
                </label>
            </div>
        </div>

        <div class="row">

            <div class="col-xs-6">

                <div class="row">
                    <br/>
                    <label>Sala:</label>
                    <input type="text" class="input-small" ng-disabled="vincProcessRadio=='false'"/>
                </div>


                <div class="row">
                    <br/>
                    <label for="dateVincProcess">Fecha:</label>

                    <div class="input-group">
                        <input class="form-control date-picker" ng-disabled="vincProcessRadio=='false'"
                               id="dateVincProcess"
                               type="text"
                               data-date-format="dd-mm-yyyy"/>
                <span class="input-group-addon">
                    <i class="icon-calendar bigger-110"></i>
                </span>
                    </div>
                </div>

                <div class="row">
                    <br/>
                    <label for="timeVincProcess">Hora</label>

                    <div class="input-group bootstrap-timepicker">
                        <input id="timeVincProcess" type="text" class="form-control input-small"
                               ng-disabled="vincProcessRadio=='false'"/>
                                            <span class="input-group-addon">
                                                <i class="icon-time bigger-110"></i>
                                            </span>

                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

</div>

</div>
<br/>
</div>
</div>
</div>


<div class="row">

    <div class="widget-box">
        <div class="widget-header">Medidas cautelares</div>

        <div class="widget-body">

            <div class="row">

                <div class="col-xs-11 col-xs-offset-1">


                    <div class="row" ng-repeat="arrangment in lstArrangment">
                        <div class="checkbox col-xs-offset-1">
                            <label>
                                <input class="ace col-xs-1"
                                       ng-disabled="flgIsEvaluated == true"
                                       type="checkbox">
                                <span class="lbl col-xs-10">&nbsp;&nbsp;{{arrangment.name}}</span>
                            </label>
                        </div>
                        <div>
                            <label>Comentarios</label>
                            <textarea class="form-control limited"
                                      maxlength="980"></textarea>
                        </div>
                    </div>


                </div>

                <br/>

            </div>
        </div>
    </div>

</div>

<%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
<%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>

</div>
</div>
</form>
</body>
</html>



