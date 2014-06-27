<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp"%>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/uniqueDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>
    <title>Aprobación de fuentes a verificar</title>
</head>
<body scroll="no" ng-app="ptlUmc">
    <%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

    <div class="container body-content">
        <script>

        </script>

        <h2 class="element-center"><i class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;Validar fuentes</h2>

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
