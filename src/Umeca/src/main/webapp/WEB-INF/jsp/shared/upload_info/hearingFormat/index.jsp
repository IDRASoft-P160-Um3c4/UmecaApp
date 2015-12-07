<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
  <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
  <link rel="stylesheet"
        href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>
  <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/daterangepicker.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/rfcDrct.js"></script>
  <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/meetingCtrl.js"></script>
  <title>Entrevistas</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

  <script>

    var lstSupervisor =${lstSupervisor};
    var arrSelectedCases = [];
    var prefixId = "id_cmb_";

    var saveAssignment = function (id) {

      var assignedId = $("#" + prefixId + id).val();
      if (assignedId > 0) {
        var params = {idCase: id, idUser: assignedId, type:"HEARING_FORMAT"};
        window.showActionParams(params, "#angJsjqGridId", "<c:url value='/shared/upload_info/saveAssignedCase.json'/>", "#GridId", "Asignar caso a tableta", "Se asignara el caso al supervisor seleccionado &iquest;Desea continuar?", "info");
      } else {
        alert("Debe seleccionar un supervisor.")
      }
    };

    $(document).ready(function () {
      jQuery("#GridId").jqGrid({
        url: '<c:url value='/shared/upload_info/hearingFormat/list.json' />',
        autoencode: true,
        datatype: "json",
        mtype: 'POST',
        colNames: ['ID', 'Carpeta de <br/> Investigaci&oacute;n', 'Nombre completo', 'Fecha de <br> nacimiento', 'G&eacute;nero', 'Estatus', 'Asignar a', 'Acci&oacute;n'],
        colModel: [
          {name: 'id', index: 'id', hidden: true},
          {
            name: 'idFolder',
            index: 'idFolder',
            width: 150,
            align: "center",
            sorttype: 'string',
            searchoptions: {sopt: ['bw']}
          },
          {
            name: 'fullname',
            index: 'fullname',
            width: 200,
            align: "center",
            sorttype: 'string',
            searchoptions: {sopt: ['bw']}
          },
          {
            name: 'dateBirthString',
            index: 'dateBirthString',
            width: 80,
            align: "center",
            sortable: false,
            search: false
          },
          {
            name: 'genderString',
            index: 'genderString',
            width: 80,
            align: "center",
            sortable: false,
            search: false
          },
          {
            name: 'description',
            index: 'description',
            width: 200,
            align: "center",
            sortable: false,
            search: false
          },
          {
            name: 'assignedUsr',
            width: 200,
            align: "center",
            sortable: false,
            search: false,
            formatter: window.actionFormatter
          },
          {
            name: 'Action',
            width: 70,
            align: "center",
            sortable: false,
            search: false,
            formatter: window.actionFormatter
          }
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
            var cmb = "<select style=\"width:90%;\" id=\"" + prefixId + cl + "\"><option value=\"-1\" selected>Sin asignar</option>";
            $.each(lstSupervisor, function (idx, item) {
              cmb += "<option value=\"" + item.id + "\">" + item.name + "</option>";
            });
            cmb += "</select>";

            var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Guardar asignaci&oacute;n\" onclick=\"saveAssignment('" + cl + "');\"><span class=\"glyphicon glyphicon-thumbs-up\"></span></a>";

            $(this).jqGrid('setRowData', ids[i], {assignedUsr: cmb});
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

      jQuery("#GridId").jqGrid('navGrid', '#GridPager', {
        edit: false, editicon: 'icon-pencil blue',
        add: false,
        refresh: true, refreshicon: 'icon-refresh green',
        del: false,
        search: false
      });

      jQuery("#GridId").jqGrid('navSeparatorAdd', '#GridPager');

      jQuery("#GridId").jqGrid('filterToolbar', {
        stringResult: true,
        searchOperators: true,
        searchOnEnter: true,
        multipleSearch: true,
        ignoreCase: true
      });
    });

  </script>
  <%--<div class="">--%>
  <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Descargar casos para formato de audiencia
    a tableta</h2>

  <div id="angJsjqGridId" ng-controller="modalDlgController">
    <table id="GridId" class="element-center" style="margin: auto"></table>
    <div id="GridPager"></div>
    <div class="blocker" ng-show="working">
      <div>
        Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
      </div>
    </div>
  </div>
</div>

<%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
<%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>

</body>
</html>