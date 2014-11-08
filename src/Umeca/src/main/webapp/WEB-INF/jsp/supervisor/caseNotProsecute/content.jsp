<%--<div class="row">--%>
<%--<div class="col-xs-12 element-center" ng-init="blockR=${r.block == null ? true: r.block}">--%>
<%--<div class="col-xs-6 element-right">--%>
<%--&iquest;El imputado cuenta con referencias personales?--%>
<%--</div>--%>
<%--<div class="col-xs-2">--%>
<%--<input type="radio" name="block"--%>
<%--id="blockYes" ng-value="true" ng-model="blockR" ng-change="fillModel()">--%>
<%--<label for="blockYes">Si</label> &nbsp;&nbsp;&nbsp;--%>
<%--<input type="radio"  name="block"--%>
<%--id="blockNo" ng-value="false" ng-model="blockR" ng-change="fillModel()">--%>
<%--<label for="blockNo">No</label>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="row">--%>
<%--<div class="hr hr-3"></div>--%>
<%--</div>--%>
<%--<br/>--%>
<div class="row">

    <div class="col-xs-12">
        <input type="hidden" ng-update-hidden name="id" id="id" ng-model="caseInfo.caseId"
               ng-init='caseInfo = ${caseInfo}; crimes = ${crimes}'>

        <div class="row">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row">
                        <div class="profile-info-name">CARPETA DE INVESTIGACI&Oacute;N</div>
                        <div class="profile-info-value element-left">
                            {{caseInfo.folderId}}
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row">
                        <div class="profile-info-name">NOMBRE</div>
                        <div class="profile-info-value element-left">
                            {{caseInfo.personName}}
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row">
                        <div class="profile-info-name">FECHA DE NO JUDICIALIZACI&Oacute;N</div>
                        <div class="profile-info-value element-left">
                            {{caseInfo.stringNotProsecute}}
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row">
                        <div class="profile-info-name">DELITOS</div>
                        <div class="profile-info-value element-left">
                            <div class="row center" ng-repeat="crime in crimes">

                                <div class="col-xs-12">
                                    <div class="col-xs-1">
                                        <i class="icon-rss orange"></i>
                                    </div>
                                    <div class="col-xs-100 element-left">
                                        <p ng-bind-html="formatHtml(crime)"></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<br/>

<div class="row">
    <div class="col-xs-12">
        <div class="row">
            <div class="col-xs-12">
                <div class="widget-header widget-header-small header-color-dark">
                    <h6>* Raz&oacute;n por la que abre el caso</h6>
                </div>
                <div class="widget-body">
                    <div class="widget-main padding-12">
                        <textarea id="comment" name="comment" ng-model="comment" ng-init="comment='';"
                                  class="width-100" rows="5"></textarea>
                    </div>
                </div>
            </div>
        </div>
        <br>

        <div class="row">
            <div class="col-xs-12">
                <div class="widget-header widget-header-small header-color-dark">
                    <h6>* Ingresese su contrase&ntilde;a para confirmar la acci&oacute;n</h6>
                </div>
                <div class="widget-body">
                    <div class="widget-main padding-12">
                        <input type="password" id="password" name="password" ng-model="password"
                               class="width-100" ng-init="password='';">
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>