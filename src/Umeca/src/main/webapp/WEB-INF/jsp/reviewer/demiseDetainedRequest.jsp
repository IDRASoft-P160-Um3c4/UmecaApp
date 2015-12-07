<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });


</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:600px" >
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-remove"></i>&nbsp;&nbsp;Dejar de mostrar registro</h4>
                    </div>
                </div>
                <div class="modal-body element-center">
                    <form id="FormCatId" name="FormCatId" ng-submit="submit('#FormCatId')" class="form-horizontal"
                          role="form">

                        <input type="hidden" name="id" value="${idCP}"/>

                        <h5>&iquest;Est&aacute; seguro que desea dejar este registro?</h5>
                        <br/>
                        <p>Nota: Esta acci&oacute;n no puede ser revertida.</p>
                    </form>
                    <br/>
                    <div class="row">
                        <div class="col-xs-12">
                            <div ng-show="MsgError&&MsgError!=''" class="alert alert-danger element-center" ng-bind-html="MsgError">
                                <%--{{MsgError}}--%>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="modal-footer" id="btn-act-footer">
                    <button class="btn btn-default btn-sm" ng-click="cancel()">No</button>
                    <button class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                            id="btn-def-ck" ng-click="submit('#FormCatId', '<c:url value='/reviewer/handingOver/demiseRegister.json' />')">
                        Si
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

