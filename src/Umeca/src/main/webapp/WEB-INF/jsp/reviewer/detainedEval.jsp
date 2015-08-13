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

            var _1Mil = 1000;
            var _1minMil = 60 * (_1Mil);
            var _1hourMil = 60 * (_1minMil);
            var _48hrsMil = 48 * (_1hourMil);

            window.calcLeftTime = function (dateMil) {
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
                }
                else {
                    leftTime = "El plazo ha vencido.";
                }
                return leftTime;
            };

            $("#GridId").jqGrid({
                url: '<c:url value='/reviewer/handingOver/list.json' />',
                autoencode: true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'detentionDateMil', 'Imputado', 'Edad', 'Carpeta de Investigaci&oacute;n', 'Fecha de inicio', 'Hora de inicio','Delito', 'Tiempo que resta'],
                colModel: [
                    {name: 'id', hidden: true},
                    {name: 'detentionDateMil', hidden: true},
                    {name: 'fullname', width: 150, align: "center", sortable: false, search: false},
                    {name: 'age', width: 150, align: "center", sortable: false, search: false},
                    {name: 'idFolder', width: 150, align: "center", sortable: false, search: false},
                    {name: 'initDateStr', width: 150, align: "center", sortable: false, search: false},
                    {name: 'initTimeStr', width: 150, align: "center", sortable: false, search: false},
                    {name: 'crime', width: 150, align: "center", sortable: false, search: false},
                    {name: 'leftTime', width: 150, align: "center", sortable: false, search: false}
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
                        var result = window.calcLeftTime(mil);
                        $(this).jqGrid('setRowData', ids[i], {leftTime: result});
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
            }, 1000);

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

    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
</div>
</body>
</html>
