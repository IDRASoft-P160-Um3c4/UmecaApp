window.relMouseCoords = function (e) {
    var el = e.target, c = el;
    var scaleX = c.width / c.offsetWidth || 1;
    var scaleY = c.height / c.offsetHeight || 1;

    if (!isNaN(e.offsetX))
        return { x: e.offsetX * scaleX, y: e.offsetY * scaleY };

    var x = e.pageX, y = e.pageY;
    do {
        x -= el.offsetLeft;
        y -= el.offsetTop;
        el = el.offsetParent;
    } while (el);
    return { x: x * scaleX, y: y * scaleY };
};


window.showUpsert = function (id, divScope, urlToGo, jqGridToUse, urlToContinue) {
    var scope = angular.element($(divScope)).scope();
    scope.show({ id: id }, urlToGo).
        then(function () {

            if(urlToContinue !== undefined){
                window.goToUrlMvcUrl(urlToContinue);
                return;
            }

            $(jqGridToUse).trigger("reloadGrid");
        });

};
window.showConfirmService = function (id, divScope, urlToGo, jqGridToUse) {
    var scope = angular.element($(divScope)).scope();
    scope.doConfirm({ id: id }, urlToGo).
        then(function () { $(jqGridToUse).trigger("reloadGrid"); });
};


window.showConfirmCancelDocument = function (id, folio, divScope, urlToGo, jqGridToUse) {
    var scope = angular.element($(divScope)).scope();
    scope.doCancelDocument({ uuid: id }, urlToGo, folio).
        then(function () { $(jqGridToUse).trigger("reloadGrid"); });
};

window.showObsolete = function (id, divScope, urlToGo, jqGridToUse) {
    var scope = angular.element($(divScope)).scope();
    scope.doObsolete({ id: id }, urlToGo).
        then(function () { $(jqGridToUse).trigger("reloadGrid"); });
};

window.showAction = function (id, divScope, urlToGo, jqGridToUse, title, message, type) {
    var scope = angular.element($(divScope)).scope();
    scope.doAction({ id: id }, urlToGo, title, message, type).
        then(function () { $(jqGridToUse).trigger("reloadGrid"); });
};

window.showModalFormDlg = function (divModalid, formId) {
    var dlgCat = $(divModalid);
    dlgCat.modal('show');

    $.validator.unobtrusive.parse(formId);

    $(divModalid).injector().invoke(function ($compile, $rootScope) {
        $compile($(divModalid))($rootScope);
        $rootScope.$apply();
    });

    var scope = angular.element(dlgCat).scope();
    scope.setDlg(dlgCat);
};


window.goToUrlMvcUrl = function (url, params) {
    for (var key in params) {
        var param = params[key] || '';
        url = url.replace(key, param);
    }

    try {
        window.location.replace(url);
    } catch(e) {
        window.location = url;
    } 
};

//replace icons with FontAwesome icons like above
function updatePagerIcons(table) {
    var replacement =
    {
        'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
        'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
        'ui-icon-seek-next' : 'icon-angle-right bigger-140',
        'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
    };
    $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
        var icon = $(this);
        var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

        if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
    })
}

function enableTooltips(table) {
    $('.navtable .ui-pg-button').tooltip({container:'body'});
    $(table).find('.ui-pg-div').tooltip({container:'body'});
}
