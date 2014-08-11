<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--
* Project: Umeca
* User: Israel
* Date: 4/30/14
* Time: 9:53 AM
-->

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp"%>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>
    <title>Usuarios</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <script>
        window.viewSource = function(id) {
            var params= [];
            params["idParam"]=id;
            window.goToUrlMvcUrl("<c:url value='/reviewer/verification/sources.html?id=idParam'/>",params);
        };

        window.obsolete = function (id) {
            window.showObsolete(id, "#angJsjqGridId", "/management/user/obsolete.json", "#GridId");
        };

        $(document).ready(function() {
            jQuery("#GridId").jqGrid({
                url: '<c:url value='/reviewer/verification/list.json' />',
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'Carpeta de <br/>investigaci&oacute;n','Nombre','G&eacute;nero', 'Estatus','Estatus Description', 'Acci&oacute;n'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'idFolder', index: 'idFolder', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'fullname', index: 'fullname', width: 500, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'genderString', index: 'genderString', width: 150, align: "center", search:false},
                    { name: 'statusDescription', index: 'statusDescription', width: 200, align: "center", search:false},
                    { name: 'statusCode', index: 'statusCode', hidden:true},
                    { name: 'Action', width: 70, align: "center", sortable: false, search: false }
                ],
                rowNum: 10,
                rowList: [10, 20, 30],
                pager: '#GridPager',
                sortname: 'statusCode',
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
                        var status = row.statusCode;
                        var be = "";
                        if (status == "AUTHORIZED") {
                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Entrevistar fuentes\" onclick=\"window.viewSource('" + cl + "');\"><span class=\"icon-group blue\"></span></a>";
                        }else if(status == "MEETING_COMPLETE"){
                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Verificatión final\" onclick=\"window.disable('" + cl+"');\"><span class=\"glyphicon glyphicon-ok-circle\"></span></a>";
                        }
                        $(this).jqGrid('setRowData', ids[i], { Action: be });
                    }
                },
                loadComplete : function() {
                    var table = this;
                    setTimeout(function(){
                        updatePagerIcons(table);
                        enableTooltips(table);
                    }, 0);
                }
            });

            jQuery("#GridId").jqGrid('navGrid', '#GridPager', {
                edit: false, editicon : 'icon-notes blue',
                add: false,
                refresh: true, refreshicon : 'icon-refresh green',
                del: false,
                search: false});

            jQuery("#GridId").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });
        });

    </script>

    <h2 class="element-center"><i class=" icon-check  "></i>&nbsp;&nbsp;Procesos de verificaci&oacute;n</h2>

    <div id="angJsjqGridId" ng-controller="modalDlgController">
        <table id="GridId" class="element-center" style="margin: auto"></table>
        <div id="GridPager"></div>
        <div class="blocker" ng-show="working">
            <div>
                Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt="" />
            </div>
        </div>
    </div>

    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp"%>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp"%>
</div>

</body>
</html>