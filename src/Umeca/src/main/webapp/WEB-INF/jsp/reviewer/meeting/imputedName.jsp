<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title></title>
  </head>
  <body>

    <div class="row">
        <div class="col-sm-3">
            <h3 class="header smaller lighter blue">
                <small>Número de carpeta <br/> de investigación:  </small>
                &nbsp;&nbsp;&nbsp;&nbsp;${idFolder}
            </h3>
        </div>
        <div class="col-sm-8 col-sm-offset-1">
            <h3 class="header smaller lighter blue">
                <small><br/>Nombre del imputado:  </small>
                &nbsp;${fullNameImputed}
            </h3>
        </div>
    </div>

  </body>
</html>