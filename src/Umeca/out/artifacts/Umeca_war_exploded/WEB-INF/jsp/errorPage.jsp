<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
</head>

<body scroll="no">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content element-center">

    <div class="error-container">
        <div class="well">
            <h1 class="grey lighter smaller">
											<span class="blue bigger-125">
												<i class="icon-random"></i>
											</span>
                No se puede procesar la solicitud.
            </h1>

            <div>
                <hr/>
                <h3 class="lighter smaller">
                    <i class="icon-wrench icon-animated-wrench bigger-125"></i>
                    Es posible que el recurso al que intenta acceder:
                </h3>
<div class="element-center col-xs-12">
    <div class="element-left col-xs-4 col-xs-offset-4">
                <ul class="list-unstyled spaced inline bigger-110 margin-15">
                    <li>
                        <i class="icon-hand-right blue"></i>
                        No este disponible.
                    </li>
                    <li>
                        <i class="icon-hand-right blue"></i>
                        No existe.
                    </li>
                    <li>
                        <i class="icon-hand-right blue"></i>
                        No tiene permisos suficientes.
                    </li>
                </ul>
    </div>
    <div class="col-xs-10 "/>
</div>
            </div>

            <div class="space"></div>

            <hr/>
            <div class="space"></div>

            <div class="center">
                <a href="<c:url value='/index.html' />" class="btn btn-grey">
                    <i class="icon-arrow-left"></i>
                    Regresar a la p&aacute;gina principal
                </a>
            </div>
        </div>
    </div>

    <%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
</div>

</body>
</html>