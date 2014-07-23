<div id="divConfirm" class="modal fade" data-backdrop="static" ng-cloak>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">


                <div class="alert alert-info">
                    <h4 class="modal-title element-center">Cerrar caso</h4>
                    <br/>
                    Se cerrar&aacute; el caso por que no existe una vinculaci&oacute;n a proceso. Debe ingresar sus credenciales para
                    confimar esta acci&oacute;n.
                </div>
            </div>

            <div class="modal-body">

                <div class="row">
                    <div class="col-xs-10 col-xs-offset-1">

                        <div class="row">
                            <label for="confirmComment">Comentario</label>
                            <br/>
                            <textarea class="input-xxlarge form-control limited" id="confirmComment"
                                      name="confirmComment"
                                      ng-model="m.confirmComment" maxlength="980" data-val="true"
                                      data-val-required="Comenatario(s) adicionales es un campo requerido">
                                </textarea>
                            <br/>
                        <span class="field-validation-valid" data-valmsg-for="confirmComment"
                              data-valmsg-replace="true"></span>
                        </div>

                        <div class="row" ng-show="m.confirmComment.length>0">
                            <label for="credPass">Password</label>
                            <br/>
                            <input id="credPass" ng-model="m.credPass" name="credPass"
                                   type="password" data-val="true" class="input-xlarge"
                                   data-val-required="Password es un campo requerido"/>
                            <br/>
                        <span class="field-validation-valid" data-valmsg-for="credPass"
                              data-valmsg-replace="true"></span>
                        </div>

                    </div>
                </div>


                <div class="row" ng-show='MsgError'>
                    <div class="col-xs-8 col-xs-offset-2 alert alert-danger element-center">
                        <span class="control-label element-center">{{MsgError}}</span>
                    </div>
                </div>


                <div class="modal-footer">
                    <span class="btn btn-info btn-sm" ng-disabled="!m.credPass"
                            ng-click="submitReloadHF('#FormFormatId','<c:url value='/supervisor/hearingFormat/doUpsert.json'/>',validateConfirm)">
                        Aceptar
                    </span>
                    <span type="button" class="btn btn-default btn-danger btn-sm" ng-click='hideDlg();'>Cancelar</span>
                </div>

            </div>
        </div>
    </div>
</div>