<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>

<div class="row">
    <div class="col-xs-12">
        <div class="col-sm-3">
            <h3 class="header smaller lighter blue">
                <small>Carpeta de investigaci&oacute;n:</small>
                &nbsp;${idFolder}
            </h3>
        </div>
        <div class="col-sm-8">
            <h3 class="header smaller lighter blue">
                <small>Nombre del imputado:</small>
                &nbsp;${fullNameImputed}
            </h3>
        </div>
        <div class="col-sm-1">
            <h3 class="header smaller lighter blue">
                <small>Edad:</small>
                &nbsp;${age}
            </h3>
        </div>
    </div>
</div>
<div class="row" ng-show="${tStart==null?false:true}">
    <div class="col-xs-12">
        <div class="col-sm-8">
            <h3 class="header smaller lighter blue">
                <small>Entrevista de riesgos procesales:</small>
                &nbsp;${tStart}&nbsp;-&nbsp;${tEnd}
            </h3>
        </div>
        <div class="col-sm-4">
            <h3 class="header smaller lighter blue">
                <small>Evaluador:</small>
                &nbsp;${reviewerFullname}
            </h3>
        </div>
    </div>
</div>

</body>
</html>