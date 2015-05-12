<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>

  $(document).ready(function () {
    window.showModalFormDlg("#dlgUpModalId", "#FormUpId");
  });

</script>

<div>
  <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
    <div class="modal-dialog" style="width:900px">
      <div class="modal-content"  ng-controller="detentionRecordController">
        <div class="modal-header">
          <div class="alert alert-info ">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h5 class="element-center"><i class="glyphicon glyphicon-share"></i>&nbsp;&nbsp;Judicializar caso</h5>
          </div>
        </div>
        <div class="modal-body">
          <form id="FormUpId" name="FormUpId" class="form-horizontal"
                role="form">
            <br/>
            <input type="hidden" name="detainedId" value= ${detainedId};">
            <div class="row">
              <div class="col-xs-10 col-xs-offset-1">
                <div class="panel panel-default panel-primary">
                  <div class="panel-heading">
                    <span class="glyphicon glyphicon-share"></span>&nbsp;&nbsp;Finalizar proyecto
                  </div>
                  <div class="panel-body">
                    <div class="row">
                      <div class="col-xs-12">
                        <div class="row">
                          <div class="col-xs-8 col-xs-offset-2 element-center">
                            <strong>&iquest;Est&aacute; seguro que desea finalizar el proyecto ${projectName}?</strong>
                          </div>
                        </div>
                        <br/>
                      </div>
                    </div>
                    <div class="row" ng-show="MsgError">
                      <br/>
                      <div class="col-xs-12">
                        <div class="alert alert-danger element-center"  ng-bind-html="MsgError">
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button class="btn btn-default btn-sm" ng-click="cancel()">
            Cancelar
          </button>
          <button class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                  ng-click="submitProsecute('#FormUpId', '<c:url value='/detentionRecord/doProsecute.json' />')">
            Aceptar
          </button>
        </div>
      </div>
    </div>
  </div>
</div>

