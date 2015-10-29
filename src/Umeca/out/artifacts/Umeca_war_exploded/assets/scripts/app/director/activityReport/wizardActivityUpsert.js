app.controller('wizardActivityUpsertController', function ($scope, $http) {

    $scope.tabItem = "report";

    $scope.wiz = {};

    $scope.$on("onNextTab", function(ev, data){
        $scope.tabItem = data;
    });

    $scope.$on("onChange", function(ev, data){
        if(!data || !data.key)
            return;
        $scope.wiz[data.key] = data.value;
    });

    $scope.$on("onFinish", function(ev, data){
        $scope.end();
    });

    $scope.end = function(){
        try{
            $scope.MsgErr = "";
            $scope.working = true;

            var resp = $scope.validate();
            if(resp.isSucc == false){
                $scope.MsgErr = resp.msg;
                $scope.tabItem = resp.key;
                $scope.working = false;
                return;
            }

            var data = resp.data;
            window.showConfirmFull(undefined, "#angJsjqGridId", $scope.url, undefined,
                "Informe de actividades", "Usted ha seleccionado lo siguiente:<br/>" +
                "<br/>Nombre: " + data.report.reportName + ", Fecha: " + data.report.startDate +
                "<br/>Descripci&oacute;n: " + data.report.reportDesc +
                "<br/><br/><div class='col-xs-offset-2 element-left'>Actividad(es) de la direcci&oacute;n: &nbsp;&nbsp;&nbsp;" + data.activity.length +
                "<br/>Actividad(es) del coordinador de supervisi&oacute;n: &nbsp;&nbsp;&nbsp;" + data.supervision.length +
                "<br/>Actividad(es) del coordinador de evaluaci&oacute;n: &nbsp;&nbsp;&nbsp;" + data.evaluation.length +
                "<br/>Actividad(es) del director: &nbsp;&nbsp;&nbsp;" + data.management.length +
                "<br/>Proyecto(s): &nbsp;&nbsp;&nbsp;" + data.project.length +
                "<br/>Canalizacion(es): &nbsp;&nbsp;&nbsp;" + data.channeling.length +
                "<br/>Minuta(s): &nbsp;&nbsp;&nbsp;" + data.minute.length +
                "<br/><br/></div>&iquest;Desea generar el informe de actividades con estos reportes?", "info",
                {
                    prepareData: function(){
                        return JSON.stringify(resp.data);
                    },
                    contentType: "application/json",
                    callbackOk: function(){
                        window.goToUrlMvcUrl($scope.urlBack);
                        $scope.working = false;
                    },
                    callbackErr: function(){
                        $scope.working = false;
                    }
                });
        }catch (e){
            $scope.working = false;
        }
    };

    $scope.validate = function(){
        var ret = {isSucc: true};

        if(!$scope.wiz || $scope.wiz.length == 0 || !$scope.wiz["report"]){
            ret.isSucc = false;
            ret.msg = "No se han definido los datos del informe";
            ret.key = "report";
            return ret;
        }

        var lstArch = {
            activity: [],
            supervision: [],
            evaluation: [],
            management: [],
            project: [],
            channeling: [],
            minute: []
        };
        var hasArc = false;

        for(var key in $scope.wiz){
            ret.key = key;
            var val = $scope.wiz[key];
            switch (key){
                case "report":
                    if( !val.startDate || val.startDate.length < 10){
                        ret.isSucc = false;
                        ret.msg = "No se ha definido una fecha válida";
                        return ret;
                    }
                    if( !val.reportName || val.reportName.length <= 0){
                        ret.isSucc = false;
                        ret.msg = "No se ha definido el nombre del informe";
                        return ret;
                    }
                    if( !val.reportDesc || val.reportDesc.length <= 0){
                        ret.isSucc = false;
                        ret.msg = "No se ha definido la descripción del informe";
                        return ret;
                    }
                    lstArch.report = val;
                    break;

                default:
                    var lst = [];

                    for(var ik in val){
                        if(val[ik]){
                            try{
                                lst.push(parseInt(ik.substr(1)));
                            }catch(e){
                            }
                        }
                    }

                    if(lst.length != 0)
                        hasArc = true;

                    lstArch[key] = lst;
                    break;
            }
        }

        if(hasArc === false){
            ret.isSucc = false;
            ret.msg = "Al menos debe tener una actividad, proyecto o minuta para el reporte";
            ret.key = "activity";
            return ret;
        }

        ret.isSucc = true;
        ret.data = lstArch;
        return ret;
    };

});