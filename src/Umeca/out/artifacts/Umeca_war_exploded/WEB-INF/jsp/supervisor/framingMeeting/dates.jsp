<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
  <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
  <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>
  <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/daterangepicker.min.js"></script>
  <title>Casos para entrevista de encuadre</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

  <script>

    addFramingMeeting = function (id) {
      var goTo = "<c:url value='/supervisor/framingMeeting/framingMeeting.html'/>" + "?id=" + id;
      window.goToUrlMvcUrl(goTo);
    };

    $(document).ready(function () {
      jQuery("#GridId").jqGrid({
        url: '<c:url value='/supervisor/framingMeeting/datesList.json' />',
        autoencode: true,
        datatype: "json",
        mtype: 'POST',
        colNames: ['ID', 'idStatus', 'Carpeta Judicial', 'Nombre completo','Fecha de nacimiento', 'Fecha de cita UMECA', 'Hora cita UMECA', 'Supervisor','ExpiredDate'],
        colModel: [
          {name: 'id', index: 'id', hidden: true},
          {name: 'codeStatus', index: 'codeStatus', hidden: true},
          {name: 'idMP',index: 'idMP',width: 200,align: "center",sorttype: 'string',searchoptions: {sopt: ['bw']}},
          {name: 'fullName',index: 'fullName',width: 300,align: "center",sorttype: 'string',searchoptions: {sopt: ['bw']}},
          {name: 'brthDateTxt',index: 'brthDateTxt',width: 300,align: "center",sorttype: 'string',searchoptions: {sopt: ['bw']}},
          {name: 'umecaDateStr',index: 'umecaDateStr',width: 160,align: "center",sorttype: 'string',searchoptions: {sopt: ['bw']}},
          {name: 'umecaTime',index: 'umecaTime',width: 250,align: "center",sorttype: 'string',searchoptions: {sopt: ['bw']}},
          {name: 'fullNameSupervisor',index: 'fullNameSupervisor',width: 250,align: "center",sorttype: 'string',searchoptions: {sopt: ['bw']}},
          {name: 'umecaDateExpired', index: 'umecaDateExpired', hidden: true, width: 250,align: "center",sorttype: 'string',searchoptions: {sopt: ['bw']}},
        ],
        rowNum: 10,
        rowList: [10, 20, 30],
        pager: '#GridPager',
        sortname: 'idMP',
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
            var be = "";
            switch (status[i]) {

              case 'ST_CASE_HEARING_FORMAT_END':
                be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Agregar entrevista de encuadre\" onclick=\"addFramingMeeting('" + cl + "');\"><span class=\"glyphicon glyphicon-plus\"></span></a>";
                break;
              case 'ST_CASE_FRAMING_MEETING_INCOMPLETE':
                be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Continuar entrevista de encuadre\" onclick=\"addFramingMeeting('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";
                break;
              case 'ST_CASE_FRAMING_MEETING_COMPLETE':
                be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Visualizar entrevista de encuadre\" onclick=\"addFramingMeeting('" + cl + "');\"><span class=\"glyphicon glyphicon-eye-open\"></span></a>";
                break;
              default://ban-circle
                be = "<a style=\"display:inline-block;\" title=\"No cuenta con el formato de audiencia\" href=\"#\"\"><span class=\"glyphicon glyphicon-ban-circle\"></span></a>";
                break;
            }


            if(row.umecaDateExpired === "true"){
              $("#" + cl).css("background-color", "#FF3617");
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

      jQuery("#GridId").jqGrid('navGrid', '#GridPager', {
        edit: false,
        add: false,
        refresh: true, refreshicon: 'icon-refresh green',
        del: false,
        search: false
      });

      jQuery("#GridId").jqGrid('navSeparatorAdd', '#GridPager');
      jQuery("#GridId").jqGrid('navButtonAdd', "#GridPager",
              {
                caption: "",
                title: "Exportar a excel",
                buttonicon: 'icon-download-alt blue',

                onClickButton: function () {
                  try {
                    $("#GridId").jqGrid('toExcelFile', {nombre: "datosXls", formato: "excel"});
                  } catch (e) {
                  }
                }
              });

      jQuery("#GridId").jqGrid('filterToolbar', {
        stringResult: true,
        searchOperators: true,
        searchOnEnter: true,
        multipleSearch: true,
        ignoreCase: true
      });
    });

  </script>

  <h2 class="element-center"><i class="glyphicon glyphicon-calendar"></i>&nbsp;&nbsp;Entrevistas de encuadre programadas
  </h2>

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
    Fecha y hora vencidas:
    <span class="glyphicon glyphicon-stop" style="color:#FF3617; font-size: 15px;"  aria-hidden="true"></span>
  </div>
  <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
  <%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
</div>

</body>
</html>