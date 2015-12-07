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

    var saveUnassignment=function (id){
        var params = {idCase: id};
        window.showActionParams(params, "#angJsjqGridId", "<c:url value='/shared/upload_info/unassignCase.json'/>", "#GridId", "Desasociar caso de tableta", "Se desasociar&aacute; el caso de la tableta &iquest;Desea continuar?", "info");
    };

    $(document).ready(function () {
      jQuery("#GridId").jqGrid({
        url: '<c:url value='/shared/upload_info/listTabletEval.json' />',
        autoencode: true,
        datatype: "json",
        mtype: 'POST',
        colNames: ['ID', 'Carpeta de Investigaci&oacute;n', 'Nombre completo', 'Fecha de nacimiento', 'G&eacute;nero', 'Estatus', 'Id estatus', 'Status case', 'Acci&oacute;n'],
        colModel: [
          {name: 'id', index: 'id', hidden: true},
          {
            name: 'idFolder',
            index: 'idFolder',
            width: 200,
            align: "center",
            sorttype: 'string',
            searchoptions: {sopt: ['bw']}
          },
          {
            name: 'fullname',
            index: 'fullname',
            width: 300,
            align: "center",
            sorttype: 'string',
            searchoptions: {sopt: ['bw']}
          },
          {
            name: 'dateBirthString',
            index: 'dateBirthString',
            width: 160,
            align: "center",
            sortable: false,
            search: false
          },
          {
            name: 'genderString',
            index: 'genderString',
            width: 150,
            align: "center",
            sortable: false,
            search: false
          },
          {
            name: 'description',
            index: 'description',
            width: 250,
            align: "center",
            sortable: false,
            search: false
          },
          {name: 'statusCode', index: 'statusCode', hidden: true},
          {name: 'reviewerId', index: 'reviewerId', hidden: true},
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
            var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Desasociar caso\" onclick=\"saveUnassignment('" + cl + "');\"><span class=\"glyphicon glyphicon-thumbs-up\"></span></a>";
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

  <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Desasociar casos de tableta</h2>

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