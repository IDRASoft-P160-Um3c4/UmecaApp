<!DOCTYPE html>
<HTML lang="es-ES">
<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUm.jsp"%>
</head>
<body scroll="no" ng-app="ptlUmc">
    <%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>
    <%@ include file="/WEB-INF/jsp/shared/login.jsp" %>

    <div class="jumbotron">
        <h1>Sistema UMECA</h1>
        <p class="lead">Pantallas inicial.</p>
        <p><a href="/" class="btn btn-primary btn-large">Inicio &raquo;</a></p>
    </div>

    <div class="row">
        <div class="col-xs-4">
            <h2>Noticia 1</h2>
            <p>
                Bla bla bla
            </p>
            <p><a class="btn btn-default" href="/">Ir a &raquo;</a></p>
        </div>
        <div class="col-xs-4">
            <h2>Noticia 2</h2>
            <p>
                Bla bla bla
            </p>
            <p><a class="btn btn-default" href="/">Ir a &raquo;</a></p>
        </div>
        <div class="col-xs-4">
            <h2>Noticia 3</h2>
            <p>
                Bla bla bla
            </p>
            <p><a class="btn btn-default" href="/">Ir a &raquo;</a></p>
        </div>
    </div>

    <%@ include file="/WEB-INF/jsp/shared/footer.jsp"%>
</body>
</html>

