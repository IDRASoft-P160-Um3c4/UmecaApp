app.controller('choiceInformationController', function($scope, $timeout, $q,sharedSvc) {
    $scope.def= $q.defer();
    $scope.selectSource=true;
    $scope.init = function(){
        $(".icon-ok-circle").each(function() {
            $( this ).css("cursor","pointer");
            $( this ).attr("rel","tooltip");
            $( this ).attr("title","Coincide la información");
        });
        $(".icon-remove-circle").each(function() {
            $( this ).css("cursor","pointer");
            $( this ).attr("rel","tooltip");
            $( this ).attr("title","No coincide la información");
        });

        $(".icon-ban-circle").each(function() {
            $( this ).css("cursor","pointer");
            $( this ).attr("rel","tooltip");
            $( this ).attr("title","No conoce la información");
        });
        $("input:text").each(function() {
            $( this ).attr('disabled','disabled');
        });
        $("input:radio").each(function() {
            $( this ).attr('disabled','disabled');
        });
        $("select").each(function(){
            $(this).prop("disabled", true);;
        });
        $("textarea").each(function() {
            $( this ).attr('disabled','disabled');
        });
    };


    $timeout(function() {
        $scope.init();
    }, 0);

    $scope.showChoices = function(code,id){
        window.showChoices(code,id);
    };

            $scope.pastToJson = function(string){
                var result = jQuery.parseJSON( string );
                if(result == ""){
                    return  "[]";
                }else{
                    return result;
                }
            } ;


    $scope.submit = function (formId, urlToPost, hasReturnId) {

        if ($(formId).valid() == false) {
            $scope.Invalid = true;
            return false;
        }

        $scope.WaitFor = true;

        if (hasReturnId === true) {
            $.post(urlToPost, $(formId).serialize())
                .success($scope.handleSuccessWithId)
                .error($scope.handleError);
        }
        else {
            $.post(urlToPost, $(formId).serialize())
                .success($scope.handleSuccess)
                .error($scope.handleError);
        }
        return true;
    };

    $scope.setDlg = function (dlg, urlToSubmit) {
        $scope.Model.dlg = dlg;
        $scope.Model.url = urlToSubmit;

        dlg.on('hidden.bs.modal', function () {
            dlg.data('modal', null);
            dlg.replaceWith("");
        });
    };

});
