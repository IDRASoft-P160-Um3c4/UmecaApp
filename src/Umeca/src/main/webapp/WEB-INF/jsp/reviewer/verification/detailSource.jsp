



<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });
</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:500px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-success ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center">&nbsp;&nbsp;Verificaci&oacute;n</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCatId" name="FormCatId" ng-submit="submit('#FormCatId')" class="form-horizontal" role="form">
                        <br />
                        <div class="row">
                            <div class="col-xs-3">
                                Raz&oacute;n de veracidad:
                            </div>
                            <div class="col-xs-9">
                                <textarea id="form-field-11" class="form-control"></textarea>
                            </div>
                        </div>
                    </form>
                    <br />
                    <div class="row">
                        <div class="col-xs-12">
                            <div ng-show="MsgError" class="alert alert-danger element-center"  ng-bind-html="MsgError">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submit('#FormCatId', '/reviewer/meeting/doNewMeeting.json');">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>



