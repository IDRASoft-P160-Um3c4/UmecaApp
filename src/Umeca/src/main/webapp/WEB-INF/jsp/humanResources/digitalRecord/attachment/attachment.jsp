<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script>
    $(document).ready(function () {

        upsertAttachment = function (id) {
            window.showUpsertWithIdEmployee(id, "#angJsjqGridAttachment", "<c:url value='/humanResources/digitalRecord/upsertAttachment.html'/>", "#GridAttachment", undefined, ${idEmployee});
        };

        deleteAttachment = function (id) {
            window.showObsolete(id, "#angJsjqGridAttachment", "<c:url value='/humanResources/digitalRecord/deleteAttachment.json'/>", "#GridAttachment");
        };

        window.download = function (id) {
            var goTo = "<c:url value='/shared/uploadFileGeneric/downloadFile.html'/>" + "?id=" + id;
            window.goToUrlMvcUrl(goTo);
        };

        $(document).ready(function () {
            jQuery("#GridAttachment").jqGrid({
                autoencode: true,
                url: '<c:url value="/humanResources/digitalRecord/listAttachment.json?id="/>' +${idEmployee},
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'fileId', 'Nombre', 'Fecha', 'Descripci&oacute;n', 'Acci&oacute;n'],
                colModel: [
                    {name: 'id', index: 'id', hidden: true},
                    {name: 'fileId', index: 'fileId', hidden: true},
                    {
                        name: 'attachmentName',
                        index: 'attachmentName',
                        width: 220,
                        align: "center",
                        sorttype: 'string',
                        search: false
                    },
                    {
                        name: 'creationTime',
                        index: 'creationTime',
                        width: 110,
                        align: "center",
                        sorttype: 'string',
                        search: false
                    },
                    {
                        name: 'attachmentDescription',
                        index: 'attachmentDescription',
                        width: 110,
                        align: "center",
                        sorttype: 'string',
                        search: false
                    },
                    {
                        name: 'Action',
                        width: 65,
                        align: "center",
                        sortable: false,
                        search: false,
                        formatter: window.actionFormatter
                    }
                ],
                rowNum: 10,
                rowList: [10, 20, 30],
                pager: '#GridPagerAttachment',
                sortname: 'id',
                height: 200,
                viewrecords: true,
                shrinkToFit: false,
                sortorder: "asc",
                caption: "&nbsp;",
                altRows: true,
                gridComplete: function () {
                    var ids = $(this).jqGrid('getDataIDs');
                    var files = $(this).jqGrid('getCol', 'fileId', false);

                    for (var i = 0; i < ids.length; i++) {
                        var cl = ids[i];
                        var idF = files[i];
                        var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar archivo\" onclick=\"window.upsertAttachment('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";
                        be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar archivo\" onclick=\"window.deleteAttachment('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
                        be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Descargar archivo\" onclick=\"window.download('" + idF + "');\"><i class=\"glyphicon glyphicon-cloud-download\"></i></a>  ";
                        $(this).jqGrid('setRowData', ids[i], {Action: be});
                    }
                }

                ,
                loadComplete: function () {
                    var table = this;
                    setTimeout(function () {
                        updatePagerIcons(table);
                        enableTooltips(table);
                    }, 0);
                }
            });

            jQuery("#GridAttachment").jqGrid('navGrid', '#GridPagerAttachment', {
                edit: false, editicon: 'icon-pencil blue',
                add: true, addfunc: window.upsertAttachment, addicon: 'icon-plus-sign purple',
                refresh: true, refreshicon: 'icon-refresh green',
                del: false,
                search: false
            });

            jQuery("#GridAttachment").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });

        })
        ;
    })
    ;

</script>

<div class="row element-center">

    <input type="hidden" id="hidIdCase" value="{{fm.objView.idCase}}"/>
    <input type="hidden" id="urlGridAttachment" value="listAttachment.json?idCase={{fm.objView.idCase}}"/>

    <div class="col-xs-12">
        <h2><i class="purple icon-camera bigger-100">&nbsp;</i>Documentos adjuntos</h2>
        <br/>

        <div id="angJsjqGridAttachment" ng-controller="modalDlgController">
            <table id="GridAttachment" class="element-center" style="margin: auto"></table>
            <div id="GridPagerAttachment"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
                </div>
            </div>
        </div>

    </div>
</div>
