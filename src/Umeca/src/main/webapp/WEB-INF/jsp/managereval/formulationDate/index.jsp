<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
</style>
<html>
<head>
  <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp"%>
  <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css" />
  <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/daterangepicker.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/scripts/app/managereval/formulationDate/formulationDateCtrl.js"></script>
  <title>Entrevistas</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

  <script>
    window.upsert = function(id) {
      window.showUpsert(id, "#angJsjqGridId", '<c:url value='/managereval/formulationDate/upsert.html' />', "#GridId");
    };

    $(document).ready(function() {
      jQuery("#GridId").jqGrid({
        url: '<c:url value='/managereval/formulationDate/list.json' />',
        datatype: "json",
        autoencode:true,
        mtype: 'POST',
        colNames: ['ID','Fecha registro formulaci&oacute;n','Oficio','C&eacutedula de notificaci&oacute;n','Datos imputado','Datos evaluador','Fecha audiencia', 'Fecha entrevista Umeca', 'Acci&oacute;n'],
        colModel: [
          { name: 'id', index: 'id', hidden: true },
          { name: 'stampDate', index: 'stampDate', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
          { name: 'documentNumber', index: 'documentNumber', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
          { name: 'notification', index: 'notification', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
          { name: 'fullNameimputed', index: 'fullNameimputed', width: 200, align: "center", sortable: false, search: false},
          { name: 'evaluator', index: 'evaluator', width: 200, align: "center", sortable: false, search: false},
          { name: 'dateHearing', index: 'dateHearing', width: 200, align: "center", sortable: false, search: false},
          { name: 'dateUmecaInterview', index: 'umecaDateInterview', width: 200, align: "center", sortable: false, search: false},
          { name: 'Action', width: 70, align: "center", sortable: false, search: false,formatter:window.actionFormatter}
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
            var status = row.status+"";
            var be="";
            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Responder solicitud\" onclick=\"window.responseRequest('" + cl + "');\"><i class=\" icon-envelope\"></i></a>";
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
        edit: false, editicon: 'icon-pencil blue',
        add: true, addfunc: window.upsert, addicon: 'icon-plus-sign purple',
        refresh: true, refreshicon: 'icon-refresh green',
        del: false,
        search: false});

      jQuery("#GridId").jqGrid('navGrid', '#GridPager', {
        edit: false, editicon : 'icon-pencil blue',
        add: false,
        refresh: true, refreshicon : 'icon-refresh green',
        del: false,
        search: false});
    });

  </script>

  <h2 class="element-center"><i class="icon icon-calendar"></i>&nbsp;&nbsp;Cita formulaci&oacute;n</h2>
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
