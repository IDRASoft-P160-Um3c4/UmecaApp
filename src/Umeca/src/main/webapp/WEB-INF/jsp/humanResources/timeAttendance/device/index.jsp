<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
  <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
  <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>
  <script src="${pageContext.request.contextPath}/assets/scripts/app/humanResources/employees/upsertEmployeeCtrl.js"></script>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
  <link rel="stylesheet"
        href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>
  <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/daterangepicker.min.js"></script>

  <title>Dispositivos Bio&eacute;tricos</title>
</head>

<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

  <script>

    showUpsertEmployee = function () {
      window.showUpsert(null, "#angJsjqGridId", "<c:url value='/humanResources/employees/upsertEmployee.html'/>", "#GridDevices");
    };

    deleteEmployee = function (id) {
      window.showObsolete(id, "#angJsjqGridId", "<c:url value='/humanResources/employees/deleteEmployee.json'/>", "#GridDevices");
    };

    showDigitalRecord = function (id) {
      window.goToUrlMvcUrl("<c:url value='/humanResources/digitalRecord/index.html?id='/>" + id);
    };

    downloadDigitalRecord = function (id) {
      var params = [];
      params["idParam"] = id;
      window.goToNewUrl("<c:url value='/humanResources/digitalRecord/digitalRecordSummary.html?id=idParam'/>", params);

    };

    $(document).ready(function () {
      jQuery("#GridDevices").jqGrid({
        url: '<c:url value='/timeAttendance/device/list.json' />',
        autoencode: true,
        datatype: "json",
        mtype: 'POST',
        colNames: ['ID', 'isObsolete', 'Nombre', 'Dirección', 'Puerto', 'Acci&oacute;n'],
        colModel: [
          {name: 'id', index: 'id', hidden: true},
          {name: 'isObsolete', index: 'isObsolete', hidden: true},
          {
            name: 'name',
            index: 'name',
            width: 200,
            align: "center",
            sorttype: 'string',
            searchoptions: {sopt: ['bw']}
          },
          {
            name: 'ip',
            index: 'ip',
            width: 200,
            align: "center",
            sorttype: 'string',
            searchoptions: {sopt: ['bw']}
          },
          {
            name: 'port',
            index: 'port',
            width: 200,
            align: "center",
            sorttype: 'string',
            searchoptions: {sopt: ['bw']}
          },
          {
            name: 'Action',
            index: 'Action',
            width: 200,
            align: "center",
            sortable: false,
            search: false,
            formatter: window.actionFormatter
          },
        ],
        rowNum: 10,
        rowList: [10, 20, 30],
        pager: '#GridPager',
        sortname: 'id',
        height: 280,
        viewrecords: true,
        shrinkToFit: false,
        sortorder: "desc",
        caption: "&nbsp;",
        altRows: true,
        gridComplete: function () {
          var ids = $(this).jqGrid('getDataIDs');
          var obsolete = $(this).jqGrid('getCol', 'isObsolete', false);

          for (var i = 0; i < ids.length; i++) {
            var cl = ids[i];
            var be = "";

            if (obsolete[i] == 'false') {
              be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Expediente digital\" onclick=\"showDigitalRecord(" + cl + ");\"><span class=\"glyphicon glyphicon-list\"></span></a>";
              be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar empleado\" onclick=\"deleteEmployee('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
              be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Descargar expediente\" onclick=\"downloadDigitalRecord('" + cl + "');\"><span class=\"glyphicon glyphicon-file\"></span></a>";
            } else {
              be = "";
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

      jQuery("#GridDevices").jqGrid('navGrid', '#GridPager', {
        edit: false,
        add: true, addfunc: showUpsertEmployee, addicon: 'icon-plus-sign purple',
        refresh: true, refreshicon: 'icon-refresh green',
        del: false,
        search: false
      });

      jQuery("#GridDevices").jqGrid('navSeparatorAdd', '#GridPager');
      jQuery("#GridDevices").jqGrid('navButtonAdd', "#GridPager",
              {
                caption: "",
                title: "Exportar a excel",
                buttonicon: 'icon-download-alt blue',

                onClickButton: function () {
                  try {
                    $("#GridDevices").jqGrid('toExcelFile', {nombre: "datosXls", formato: "excel"});
                  } catch (e) {
                  }
                }
              });

      jQuery("#GridDevices").jqGrid('filterToolbar', {
        stringResult: true,
        searchOperators: true,
        searchOnEnter: true,
        multipleSearch: true,
        ignoreCase: true
      });
    })
    ;

  </script>

  <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Dispositivos Bio&eacute;tricos
  </h2>

  <div id="angJsjqGridId" ng-controller="modalDlgController">
    <table id="GridDevices" class="element-center" style="margin: auto"></table>
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