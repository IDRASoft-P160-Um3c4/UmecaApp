<!DOCTYPE html>
<html>
<head>
    <title></title>
    <style>
        .photoRow {
            padding-right: 0px;
        !important
        }
    </style>
</head>
<body>
<div class="panel panel-default panel-primary">
    <div class="panel-heading">
        <span class="icon-tasks"></span>&nbsp;&nbsp;Datos del acuerdo
    </div>
    <div class="panel-body">
        <div class="row" style="position: relative;">
            <div class="col-xs-12">
                <div class="row">
                    <div class="col-xs-12 photoRow">
                        <div class="profile-user-info profile-user-info-striped">

                            <div class="profile-info-row two-lines">
                                <div class="profile-info-name">ACUERDO</div>
                                <div class="profile-info-value element-left">
                                    <span>{{agreementData.title}}&nbsp;</span>
                                </div>
                            </div>


                            <div class="profile-info-row two-lines">
                                <div class="profile-info-name">TEMA(S)</div>
                                <div class="profile-info-value element-left">
                                    <span>{{agreementData.theme}}&nbsp;</span>
                                </div>
                            </div>

                            <div class="profile-info-row two-lines">
                                <div class="profile-info-name">FECHA DE REGISTRO</div>
                                <div class="profile-info-value element-left">
                                    <span>{{agreementData.agreementDate}}&nbsp;</span>
                                </div>
                            </div>

                            <div class="profile-info-row two-lines">
                                <div class="profile-info-name">&Aacute;REA</div>
                                <div class="profile-info-value element-left">
                                    <span>{{agreementData.area}}&nbsp;</span>
                                </div>
                            </div>

                            <div class="profile-info-row two-lines">
                                <div class="profile-info-name">REALIZADO</div>
                                <div class="profile-info-value element-left">
                                    <span>{{agreementData.isDoneStr}}&nbsp;</span>
                                </div>
                            </div>

                            <div class="profile-info-row two-lines">
                                <div class="profile-info-name">CONCLUIDO</div>
                                <div class="profile-info-value element-left">
                                    <span>{{agreementData.isFinishedStr}}&nbsp;</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </div>
</div>
</body>
</html>