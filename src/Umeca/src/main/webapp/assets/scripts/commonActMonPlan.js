window.colorActMonPlan = function(status, end, today, color, isSuspended){

    if(isSuspended === true){
        return 'label-black';
    }

    switch(status){
        case "NO REALIZADA":
            return color === undefined ? 'label-grey' : 'grey';
        case "PRE_NUEVA":
            return color === undefined ? "label-pre-new" : 'pre-new';
        case "PRE_MODIFICADA":
            return color === undefined ? "label-pre-update" : 'pre-update';
        case "PRE_ELIMINADA":
            return color === undefined ? "label-pre-delete" : 'pre-delete';
        case "REALIZADA":
            return color === undefined ? 'label-success' : 'green';
        case "NUEVA":
        case "MODIFICADA":
            if(end === undefined || today === undefined || end >= today)
                return color === undefined ? "label-info": 'blue';
            return color === undefined ? 'label-danger': 'red';
    }
};

window.changeByStatus = function(status, id){
    var className = 'label-act-evt-';
    if(status === "REALIZADA"){
        className = className + 'done-';
    }
    return className + id;
}

window.idToObject = function (id, lstCat) {
    for (var i = 0; i < lstCat.length; i++) {
        var cat = lstCat[i];
        if (cat.id == id) {
            return cat;
        }
    }
    return null;
};
