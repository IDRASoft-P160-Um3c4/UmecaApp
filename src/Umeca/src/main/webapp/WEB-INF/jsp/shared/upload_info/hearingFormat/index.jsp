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

    var arrSelectedCases = [];

    onClickCheck = function (id, el) {
      var idx;

      idx = arrSelectedCases.indexOf(id);

      if (el.checked == true) {
        if (idx < 0)
          arrSelectedCases.push(id);
      } else if (el.checked == false) {
        if (idx > -1)
          arrSelectedCases.splice(idx, 1);
      }
    };

    $(document).ready(function () {
      jQuery("#GridId").jqGrid({
        url: '<c:url value='/shared/upload_info/hearingFormat/list.json' />',
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
            var be = '<input id="chk_' + ids[i] + '" type="checkbox" onChange="onClickCheck(' + ids[i] + ', this);">';
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

    app.controller('mobileMeetingController', function ($scope, $timeout, $http, $rootScope, $sce) {

      $scope.WaitFor = false;
      $scope.errorMsg="";
      $scope.successMsg="";

      $scope.doSave = function (){

        $scope.WaitFor = true;
        $scope.errorMsg="";
        $scope.successMsg="";

        if(!arrSelectedCases.length>0){
          $scope.errorMsg = $sce.trustAsHtml("Debe seleccionar al menos un caso.");
          $scope.WaitFor = false;
          return;
        }

        var currentTimeout = null;
        var url = "../saveAssignedCases.json";

        var ajaxConf;

        ajaxConf = {
          method: "POST",
          params: {param: JSON.stringify(arrSelectedCases), type:"HEARING_FORMAT"}
        };

        ajaxConf.url = url;

        if (currentTimeout) {
          $timeout.cancel(currentTimeout);
        }

        currentTimeout = $timeout(function () {
          $http(ajaxConf)
                  .success(function (data) {
                    $scope.WaitFor = false;

                    if (data.hasError == undefined) {
                      data = data.responseMesage;
                    }

                    if (data.hasError == true) {
                      $scope.successMsg="";
                      $scope.errorMsg=$sce.trustAsHtml(data.message);
                    }
                    else {
                      $scope.errorMsg="";
                      $scope.successMsg=$sce.trustAsHtml(data.message);
                      $("#GridId").trigger("reloadGrid");
                    }
                  })
                  .error(function () {
                    $scope.WaitFor = false;
                    $scope.errorMsg = $sce.trustAsHtml("Error de red, intente m&aacute;s tarde.");
                  });
        }, 200); };

    });

  </script>

  <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Descargar entrevistas de riesgos
    a tableta</h2>

  <div class="row" ng-controller="mobileMeetingController">
    <div id="finishConfirm" align="right">
            <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true" ng-confirm-action
                  confirm-message="&iquest;Est&aacute; seguro que desea asignar a tableta los casos seleccionados?"
                  confirm-title="Guardar asignaci&oacute;n de casos" confirm-type="info"
            <%--confirmed-click-action="doSave('#FormFormatId','<c:url value='/supervisor/hearingFormat/doUpsert.json'/>',validateSave);">--%>
                  confirmed-click-action="doSave();">
                  Guardar
            </span>
    </div>

    <div ng-show="successMsg&&successMsg!=''" class="alert alert-success element-center success-font">
      <span ng-bind-html="successMsg"></span>
    </div>

    <div ng-show="errorMsg&&errorMsg!=''" class="alert alert-danger element-center error-font">
      <span ng-bind-html="errorMsg"></span>
    </div>

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
</div>

</body>
</html>