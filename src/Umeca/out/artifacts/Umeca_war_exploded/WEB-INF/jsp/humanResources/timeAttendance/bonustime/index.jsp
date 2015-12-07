<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
  <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
  <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>
  <script src="${pageContext.request.contextPath}/assets/scripts/app/humanResources/bonusTime/bonusTimeCtrl.js"></script>
  <script type="text/javascript">

  </script>
  <title>Gesti&oacute;n de recursos</title>
</head>

<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

  <script>

    doJustify = function (id) {
      if (id == undefined) id = 0;
      window.showUpsert(id, "#angJsjqGridId", "<c:url value='/humanResources/bonustime/upsertBonusTime.html'/>", "#GridDevices");
    };


    $(document).ready(function () {
      jQuery("#GridBonusTime").jqGrid({
        url: '<c:url value='/humanResources/bonustime/list.json' />',
        autoencode: true,
        datatype: "json",
        mtype: 'POST',
        colNames: ['ID', 'Nombre', 'Fecha de Registro', 'Hora de salida', 'Horas extra', 'Acci&oacute;n'],
        colModel: [
          {name: 'id', index: 'id', hidden: true},
          {name: 'name', index: 'name', width: 200, align: "center", sorttype: 'string', searchoptions: {sopt: ['bw']}},
          {name: 'eventDate',index: 'eventDate',width: 200,align: "center",sorttype: 'string',searchoptions: {sopt: ['bw']}},
          {name: 'eventTime',index: 'eventTime',width: 200,align: "center",sorttype: 'string',search: false},
          {name: 'bonusTime',index: 'bonusTime',width: 200,align: "center",sorttype: 'string',search: false},
          {name: 'Action',index: 'Action',width: 200,align: "center",sortable: false,search: false,formatter: window.actionFormatter},
        ],
        rowNum: 10,
        rowList: [10, 20, 30],
        pager: '#GridPager',
        sortname: 'eventDate',
        height: 350,
        viewrecords: true,
        shrinkToFit: false,
        sortorder: "asc",
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
        add: false,
        refresh: true, refreshicon: 'icon-refresh green',
        del: false,
        search: false
      });

      jQuery("#GridBonusTime").jqGrid('navSeparatorAdd', '#GridPager');
      jQuery("#GridBonusTime").jqGrid('navButtonAdd', "#GridPager",
              {
                caption: "",
                title: "Exportar a excel",
                buttonicon: 'icon-download-alt blue',

                onClickButton: function () {
                  try {
                    $("#GridBonusTime").jqGrid('toExcelFile', {nombre: "datosXls", formato: "excel"});
                  } catch (e) {
                  }
                }
              });

      jQuery("#GridBonusTime").jqGrid('filterToolbar', {
        stringResult: true,
        searchOperators: true,
        searchOnEnter: true,
        multipleSearch: true,
        ignoreCase: true
      });
    });

  </script>

  <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Gesti&oacute;n de horas extra</h2>

  <div id="angJsjqGridId" ng-controller="modalDlgController">
    <table id="GridBonusTime" class="element-center" style="margin: auto"></table>
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