<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
  <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
  <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>
  <script src="${pageContext.request.contextPath}/assets/scripts/app/humanResources/absence/absenceCtrl.js"></script>


  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/chosen.min.css"/>
  <script src="${pageContext.request.contextPath}/assets/scripts/umeca/chosen.jquery.min.js"></script>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>
  <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/daterangepicker.min.js"></script>

  <title>Gesti&oacute;n de faltas</title>
</head>

<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

  <script>

    doJustify = function (id) {
      if (id == undefined) id = 0;
      window.showUpsert(id, "#angJsjqGridId", "<c:url value='/humanResources/absence/upsertAbsence.html'/>", "#GridAbsence");
    };

    addAbsence = function() {
      window.showUpsert(0, "#angJsjqGridId", "<c:url value='/humanResources/absence/addAbsence.html'/>", "#GridAbsence");
    }

    $(document).ready(function () {
      jQuery("#GridAbsence").jqGrid({
        url: '<c:url value='/humanResources/absence/list.json' />',
        autoencode: true,
        datatype: "json",
        mtype: 'POST',
        colNames: ['ID', 'Nombre', 'Fecha de Registro', 'Tipo', 'Faltas', 'Acci&oacute;n'],
        colModel: [
          {name: 'id', index: 'id', hidden: true},
          {name: 'name', index: 'name', width: 200, align: "center", sorttype: 'string', searchoptions: {sopt: ['bw']}},
          {name: 'date',index: 'date',width: 200,align: "center",sorttype: 'string',searchoptions: {sopt: ['bw']}},
          {name: 'type',index: 'type',width: 200,align: "center",sorttype: 'string',search: false},
          {name: 'value',index: 'value',width: 200,align: "center",sorttype: 'string',search: false},
          {name: 'Action',index: 'Action',width: 200,align: "center",sortable: false,search: false,formatter: window.actionFormatter},
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
          var dates = $(this).jqGrid('getCol', 'date', false);

          for (var i = 0; i < ids.length; i++) {
            var cl = ids[i];
            var be = "";
            var date = new Date(1*dates[i]);
            be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Justificaci&oacute;n\" onclick=\"doJustify(" + cl + ");\"><span class=\"glyphicon glyphicon-ok\"></span></a>";

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

      jQuery("#GridAbsence ").jqGrid('navGrid', '#GridPager', {
        edit: false,
        add: true, addfunc: addAbsence, addicon: 'icon-plus-sign purple',
        refresh: true, refreshicon: 'icon-refresh green',
        del: false,
        search: false
      });

      jQuery("#GridAbsence").jqGrid('navSeparatorAdd', '#GridPager');
      jQuery("#GridAbsence").jqGrid('navButtonAdd', "#GridPager",
              {
                caption: "",
                title: "Exportar a excel",
                buttonicon: 'icon-download-alt blue',

                onClickButton: function () {
                  try {
                    $("#GridAbsence").jqGrid('toExcelFile', {nombre: "datosXls", formato: "excel"});
                  } catch (e) {
                  }
                }
              });

      jQuery("#GridAbsence").jqGrid('filterToolbar', {
        stringResult: true,
        searchOperators: true,
        searchOnEnter: true,
        multipleSearch: true,
        ignoreCase: true
      });
    });

  </script>

  <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Gesti&oacute;n de faltas del personal</h2>

  <div id="angJsjqGridId" ng-controller="modalDlgController">
    <table id="GridAbsence" class="element-center" style="margin: auto"></table>
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