window.colorActMonPlan = function(status, end, today){
    switch(status){
        case "NO REALIZADA":
            return 'label-grey';
        case "PRE_NUEVA":
            return "label-pre-new";
        case "PRE_MODIFICADA":
            return "label-pre-update";
        case "PRE_ELIMINADA":
            return "label-pre-delete";
        case "REALIZADA":
            return 'label-success';
        case "NUEVA":
        case "MODIFICADA":
            if(end === undefined || today === undefined || end >= today)
                return "label-info";
            return 'label-danger'
    }
};