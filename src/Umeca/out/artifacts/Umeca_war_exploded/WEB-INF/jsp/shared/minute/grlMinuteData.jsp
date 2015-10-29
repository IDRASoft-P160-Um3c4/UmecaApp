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
        <span class="icon-tasks"></span>&nbsp;&nbsp;Datos de la minuta
    </div>
    <div class="panel-body">
        <div class="row" style="position: relative;">
            <div class="col-xs-12">
                <div class="row">
                    <div class="col-xs-12 photoRow">
                        <div class="profile-user-info profile-user-info-striped">

                            <div class="profile-info-row two-lines">
                                <div class="profile-info-name">MINUTA</div>
                                <div class="profile-info-value element-left">
                                    <span>{{minuteData.title}}&nbsp;</span>
                                </div>
                            </div>
                            <div class="profile-info-row two-lines">
                                <div class="profile-info-name">FECHA DE REGISTRO</div>
                                <div class="profile-info-value element-left">
                                    <span>{{minuteData.minuteDate}} {{minuteData.startTime}}&nbsp;</span>
                                </div>
                            </div>
                            <div class="profile-info-row two-lines">
                                <div class="profile-info-name">ENCARGADO</div>
                                <div class="profile-info-value element-left">
                                    <span>{{minuteData.attendant}}&nbsp;</span>
                                </div>
                            </div>
                            <div class="profile-info-row two-lines">
                                <div class="profile-info-name">Lugar</div>
                                <div class="profile-info-value element-left">
                                    <span>{{minuteData.place}}&nbsp;</span>
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