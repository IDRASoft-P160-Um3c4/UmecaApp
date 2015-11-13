<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/uniqueDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/enterKeyDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/showMessageErrorDrct.js"></script>
    <title>Autorizar fuentes</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">
    <script>

        $(document).ready(function () {


            window.showRequestDemiseRegister = function (id) {
                window.showUpsert(id, "#angJsjqGridId", "<c:url value='/reviewer/handingOver/requestDetainedDemise.html'/>", "#GridId");
            };

            var _1Mil = 1000;
            var _1minMil = 60 * (_1Mil);
            var _1hourMil = 60 * (_1minMil);
            var _48hrsMil = 48 * (_1hourMil);

            window.calcLeftTime = function (dateMil,cl) {
                var now = new Date();
                var leftTime = "";
                var diff = now.getTime() - dateMil;
                if (diff < _48hrsMil) {
                    var left = _48hrsMil - diff;
                    var hrs = parseInt(left / _1hourMil);
                    var left = left - (hrs * _1hourMil);
                    var mins = parseInt(left / _1minMil);
                    var left = left - (mins * _1minMil);
                    var sec = parseInt(left / _1Mil);

                    if (parseInt(hrs) < 10)
                        hrs = "0" + hrs;
                    if (parseInt(mins) < 10)
                        mins = "0" + mins;
                    if (parseInt(sec) < 10)
                        sec = "0" + sec;

                    leftTime = hrs + ":" + mins + ":" + sec;

                    if(hrs < 3 ){
                        console.log("Faltan menos de 3 horas")
                        $("#" + cl).css("background-color", "#FF9313");
                    }
                }
                else {
                    leftTime = "El plazo ha vencido.";
                    $("#" + cl).css("background-color", "#FF3617");
                }
                return leftTime;
            };

            $("#GridId").jqGrid({
                url: '<c:url value='/reviewer/handingOver/list.json' />',
                autoencode: true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'detentionDateMil', 'Imputado', 'Edad', 'Carpeta de Investigaci&oacute;n', 'Fecha de inicio', 'Hora de inicio','Delito', 'Tiempo que resta','Acci&oacute;n'],
                colModel: [
                    {name: 'id', hidden: true},
                    {name: 'detentionDateMil', hidden: true},
                    {name: 'fullname', width: 150, align: "center",  sorttype: 'string', searchoptions: { sopt: ['bw']}},
                    {name: 'age', width: 150, align: "center", sortable: false, search: false},
                    {name: 'idFolder', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw']}},
                    {name: 'initDateStr', width: 150, align: "center", sortable: false, search: false},
                    {name: 'initTimeStr', width: 150, align: "center", sortable: false, search: false},
                    {name: 'crime', width: 150, align: "center", sortable: false, search: false},
                    {name: 'leftTime', width: 150, align: "center", sortable: false, search: false},
                    {name: 'Action', width: 75, align: "center", sortable: false, search: false, formatter: window.actionFormatter}
                ],
                rowNum: 10,
                rowList: [10, 20, 30],
                pager: '#GridPager',
                sortname: 'id',
                height: 450,
                viewrecords: true,
                shrinkToFit: false,
                sortorder: "desc",
                caption: "&nbsp;",
                altRows: true,
                gridComplete: function () {
                    var ids = $(this).jqGrid('getDataIDs');
                    for (var i = 0; i < ids.length; i++) {
                        var cl = ids[i];
                        var row = $(this).getRowData(cl);
                        var mil = row.detentionDateMil;
                        var result = window.calcLeftTime(mil,cl);
                        $(this).jqGrid('setRowData', ids[i], {leftTime: result});
                    }
                    for (var i = 0; i < ids.length; i++) {
                        var cl = ids[i];
                        var be = "";
                            if(result == "El plazo ha vencido."){
                                be += "<a href=\"javascript:;\" style=\"display:inline-block; color: #FFFFFF;\" title=\"Dejar de mostrar registro\" onclick=\"window.showRequestDemiseRegister('" + cl + "');\"><span class=\"glyphicon glyphicon-remove\"></span></a>";
                            }
                        $(this).jqGrid('setRowData', ids[i], {Action: be});
                    }
                },
                loadComplete: function () {
                    var table = this;
                    setTimeout(function () {
                        updatePagerIcons(table);
                        enableTooltips(table);
                    }, 0);
                }
            });

            $("#GridId").jqGrid('navGrid', '#GridPager', {
                edit: false,
                add: false,
                refresh: true, refreshicon: 'icon-refresh green',
                del: false,
                search: false
            });

            $("#GridId").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });

            setInterval(function () {
                $("#GridId").trigger("reloadGrid");
            }, 300000);

        });
    </script>

    <h2 class="element-center"><i class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;Puesta a disposici&oacute;n</h2>

    <div id="angJsjqGridId" ng-controller="modalDlgController">
        <table id="GridId" class="element-center" style="margin: auto"></table>
        <div id="GridPager"></div>
        <div class="blocker" ng-show="working">
            <div>
                Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
            </div>
        </div>
    </div>
    <div class="col-xs-12 element-center">
        Plazo vencido:
        <span class="glyphicon glyphicon-stop" style="color:#FF3617; font-size: 15px;"  aria-hidden="true"></span>
        Menos de 3 horas:
        <span class="glyphicon glyphicon-stop" style="color:#FF9313; font-size: 15px;"  aria-hidden="true"></span>
    </div>

    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
</div>
</body>
</html>
