<!DOCTYPE html>
<HTML lang="es-ES">
<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUm.jsp" %>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>
<%@ include file="/WEB-INF/jsp/shared/login.jsp" %>

<sec:authorize access="isAnonymous()">
    <%@ include file="/WEB-INF/jsp/shared/index.jsp" %>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_SUPERVISOR')">
    <%@ include file="/WEB-INF/jsp/supervisor/index.jsp" %>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_SUPERVISOR_MANAGER')">
    <%@ include file="/WEB-INF/jsp/supervisorManager/index.jsp" %>
</sec:authorize>

</div>
<div class="row">
    <div class="col-xs-8 col-xs-offset-2">
        <%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
    </div>
</div>
</body>
</html>

