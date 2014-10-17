<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });
</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:700px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-${type}">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-comment"></i>&nbsp;&nbsp;${title}</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="panel panel-${type}">
                                <div class="panel-heading">
                                    <span class="glyphicon glyphicon-map-marker"></span>&nbsp;&nbsp;${subtitle}
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-xs-10 col-xs-offset-1" ng-init="msgBody=${body}">
                                            <span ng-bind-html="msgBody"> </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer" id="btn-act-footer">
                    <button class="btn btn-${type}" ng-click="cancel()">Regresar</button>
                </div>
            </div>
        </div>
    </div>
</div>

