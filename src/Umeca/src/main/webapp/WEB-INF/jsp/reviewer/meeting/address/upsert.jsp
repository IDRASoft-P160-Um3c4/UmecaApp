 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormAddressId");
    });

</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="addressController" ng-cloak>
        <div class="modal-dialog" style="width:900px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-home "></i>&nbsp;&nbsp;Domicilio</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormAddressId" name="FormAddressId" class="form-horizontal" role="form">
                        Ingrese su c&oacute;digo postal para obtnener autom&aacute;ticamente su informaci&oacute;n
                        <br/>
                        <br/>
                        <%@ include file="/WEB-INF/jsp/reviewer/meeting/address/content.jsp"%>
                    </form>
                    <br />
                    <div class="row">
                        <div class="col-xs-12">
                            <div ng-show="MsgError" class="alert alert-danger element-center">
                                {{MsgError}}
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submit('#FormAddressId','<c:url value="/reviewer/meeting/address/doUpsert.json?idCase=${idCase}"/>');">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>

