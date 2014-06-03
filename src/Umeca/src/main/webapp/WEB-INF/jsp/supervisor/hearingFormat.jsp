<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/hearingFormatCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/hiddenDrct.js"></script>

    <title>Formato de audiencia</title>
</head>

<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<h2 class="element-center"><i class="icon-archive"></i>&nbsp;&nbsp;Formato de audiencia</h2>


<form id="FormTecRevId" name="FormTecRevId" class="form-horizontal"
      role="form" ng-controller="upsertController" method="post">
    <br/>


    <div class="container body-content" ng-controller="hearingFormatController">


        <!--<input type="hidden" name="idFolder" id="idFolder" value="${idFolder}"/>-->

        <div ng-init=''>

            <div class="row">
                <div class="widget-box">
                    <div class="widget-header">Datos generales</div>

                    <div class="widget-body">

                        <div class="control-group">
                            <br/>


                            <div class="row">
                                <div class="col-xs-2 col-xs-offset-1">
                                    No. Carpeta:
                                </div>
                                <div class="col-xs-2 element-left">
                                    <input class="form-control">
                                </div>
                            </div>

                            <br/>

                            <br/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">

                aki va lo demas

            </div>

            <div class="row element-right">
                            <span class="btn btn-default btn-sm"
                                  ng-click="returnUrl('/reviewer/technicalReview/index.html')">
                                Cancelar
                            </span>
                            <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                                  ng-click="submitRedirect('#FormTecRevId', '/reviewer/technicalReview/doUpsert.json',false,validateSave)"
                                  ng-show="flgIsEvaluated == false">
                                  Guardar
                            </span>
            </div>

            <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
            <%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
        </div>
</form>
</body>
</html>