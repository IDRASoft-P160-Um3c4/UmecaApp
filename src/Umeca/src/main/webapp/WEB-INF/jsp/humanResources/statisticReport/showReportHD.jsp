<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.umeca.model.shared.Constants" %>
<html>

<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>

    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/upsertCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/modalDlgCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/hiddenDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/managereval/statisticReportCtrl.js"></script>

    <script src="${pageContext.request.contextPath}/assets/scripts/d3/d3.min.js"></script>


    <title>Reporte estad&iacute;stico</title>

    <style charset="utf-8" type="text/css">

        label {
            position: absolute;
            top: 10px;
            right: 10px;
        }

        rect {
            -moz-transition: all 0.3s;
            -webkit-transition: all 0.3s;
            -o-transition: all 0.3s;
            transition: all 0.3s;
        }

        .bar:hover {
            fill: #607D8B !important;
        }

    </style>

    <script>
        window.onload = function () {

            var dataSet = ${data};
            var total = ${total};
            var initDate = "${initDate}";
            var endDate = "${endDate}";
            var extraData = "${extraData}";
            var title = "${title}";
            var measure = "${measure}";




            var len = dataSet.length;


            var color = d3.scale.ordinal().range([
                "#F44336", "#EF9A9A",
                "#E91E63", "#F48FB1",
                "#9C27B0", "#CE93D8",
                "#673AB7", "#B39DDB",
                "#3F51B5", "#9FA8DA",
                "#2196F3", "#90CAF9",
                "#00BCD4", "#80DEEA",
                "#009688", "#80CBC4",
                "#4CAF50", "#A5D6A7",
                "#8BC34A", "#C5E1A5",
                "#FFEB3B", "#FFF59D",
                "#FF9800", "#FFCC80",
                "#795548", "#BCAAA4",
                "#9E9E9E", "#EEEEEE"
            ]);


//            if (len > 8) {
//                dataSet.map(function (d) {
//                    d.name = d.subName;
//                })
//            }

            var margin = {top: 50, right: 120, bottom: 50, left: 110},
                    width = 1280 - margin.left - margin.right,
                    height = 720 - margin.top - margin.bottom;


            var m1 = [.8, .3, .4, .3, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .8, .3, .4, .3, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .8, .3, .4, .3, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .8, .3, .4, .3, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .8, .3, .4, .3, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1];
            var m2 = [.3, .9, .4, .4, .4, .4, .4, .6, .6, .6, .6, .6, .6, .6, .6, .3, .9, .4, .4, .4, .4, .4, .6, .6, .6, .6, .6, .6, .6, .6, .3, .9, .4, .4, .4, .4, .4, .6, .6, .6, .6, .6, .6, .6, .6, .3, .9, .4, .4, .4, .4, .4, .6, .6, .6, .6, .6, .6, .6, .6, .3, .9, .4, .4, .4, .4, .4, .6, .6, .6, .6, .6, .6, .6, .6];

            var x = d3.scale.ordinal()
                    .rangeRoundBands([0, width], m1[dataSet.length - 1], m2[dataSet.length - 1]);

            var y = d3.scale.linear()
                    .range([height, 0]);

            var xAxis = d3.svg.axis()
                    .scale(x)
                    .orient("bottom");

            var yAxis = d3.svg.axis()
                    .scale(y)
                    .orient("left");

            var xScale = d3.scale.ordinal()
                    .domain(d3.range(dataSet.length))
                    .rangeRoundBands([0, width], m1[dataSet.length - 1], m2[dataSet.length - 1]);

            var yScale = d3.scale.linear()
                    .domain([0, d3.max(dataSet, function (d) {
                        return d.value;
                    })])
                    .range([0, height]);


            //Create SVG element
            var svg = d3.select(".chartBar").append("svg")
                    .attr("width", width + margin.left + margin.right)
                    .attr("height", height + margin.top + margin.bottom)
                    .append("g")
                    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");


            dataSet.forEach(function (d) {
                d.value = +d.value;
            });

            x.domain(dataSet.map(function (d) {
                if (len > 8) {
                    return d.subName;
                }
                return d.name;
            }));
            y.domain([0, d3.max(dataSet, function (d) {
                return d.value;
            })]);


            //bar
            svg.selectAll(".bar")
                    .data(dataSet)
                    .enter().append("rect")
                    .attr("class", "bar")
                    .attr("x", function (d) {
                        if (len > 8) {
                            return x(d.subName);
                        }
                        return x(d.name);

                    })
                    .attr("width", x.rangeBand())
                    .attr("y", function (d) {
                        return y(d.value);
                    })
                    .attr("height", function (d) {
                        return height - y(d.value);
                    })
                    .style("fill", function (d, i) {
                        return color(i);
                    })
            ;

            //text
            svg.selectAll("text")
                    .data(dataSet)
                    .enter()
                    .append("text")
                    .text(function (d) {
                        return d.value;
                    })
                    .attr("text-anchor", "middle")
                    .attr("x", function (d, i) {
                        return xScale(i) + xScale.rangeBand() / 2;
                    })
                    .attr("y", function (d) {
                        return height - yScale(d.value) + 14;
                    })
                    .style("font", "11px sans-serif")
                    .attr("fill", "white")
                    .attr("class", "textbar");

            // x axis
            svg.append("g")
                    .attr("class", "x axis")
                    .attr("transform", "translate(0," + height + ")")
                    .call(xAxis);

            //y axis
            svg.append("g")
                    .attr("class", "y axis")
                    .call(yAxis)
                    .append("text")
                    .attr("transform", "rotate(-90)")
                    .attr("y", 6)
                    .attr("dy", ".71em")
                    .style("text-anchor", "end")
                    .text(measure);


            svg.append("text")
                    .attr("x", (width / 2))
                    .attr("y", 0 - 35)
                    .attr("text-anchor", "middle")
                    .style("font", "16px sans-serif")
                    .text(title + " - " + extraData);


            svg.append("text")
                    .attr("x", (width / 2))
                    .attr("y", 0 - 15)
                    .attr("text-anchor", "middle")
                    .style("font", "12px sans-serif")
                    .text(initDate + " - " + endDate);


            //legend
            var legend = svg.append("g")
                    .attr("class", "legend")
                    .attr("height", 100)
                    .attr("width", 100)
                    .attr('transform', 'translate(40,40)');

            var legendRect = legend.selectAll('.legend').data(color.domain().slice());

            legendRect.enter()
                    .append("rect")
                    .attr("x", width - 52)
                    .attr("width", 10)
                    .attr("height", 10)
                    .style("fill", color);

            legendRect
                    .attr("y", function (d, i) {
                        return i * 20;
                    });

            var legendText = legend.selectAll('text').data(dataSet);

            legendText.enter()
                    .append("text")
                    .attr("x", width - 38);

            legendText
                    .attr("y", function (d, i) {
                        return i * 20 + 9;
                    })
                    .style("font", "12px sans-serif")
                    .text(function (d, i) {
                        return d.name + " - " + d.value;
                    });

            //style
            svg.selectAll(".x.axis path")
                    .style("display", "none");

            svg.selectAll(".axis text")
                    .style("font-family", "sans-serif")
                    .style("font-size", "11px")
            ;
            svg.selectAll(".axis path")
                    .style("fill", "none")
                    .style("stroke", "#000")
                    .style("shape-rendering", "crispEdges")
            ;

            svg.selectAll(".axis line")
                    .style("fill", "none")
                    .style("stroke", "#000")
                    .style("shape-rendering", "crispEdges")
            ;
//            var sortTimeout = setTimeout(function() {
//                d3.select("input").property("checked", true).each(change);
//            }, 2000);

            d3.select("input").on("change", change);

            function change() {

                //clearTimeout(sortTimeout);

                var x0 = x.domain(dataSet.sort(this.checked
                        ? function (a, b) {
                    return b.value - a.value;
                }
                        : function (a, b) {
                    return d3.ascending(a.name, b.name);
                })
                        .map(function (d) {
                            return d.name;
                        }))
                        .copy();

                svg.selectAll(".bar")
                        .sort(function (a, b) {
                            return x0(a.name) - x0(b.name);
                        });

                svg.selectAll(".textbar")
                        .sort(function (a, b) {
                            return x0(a.name) - x0(b.name);
                        });

                var transition = svg.transition().duration(750),
                        delay = function (d, i) {
                            return i * 50;
                        };


                transition.selectAll(".textbar")
                        .delay(delay)
                        .attr("x", function (d, i) {
                            return xScale(i) + xScale.rangeBand() / 2;
                        });

                transition.selectAll(".bar")
                        .delay(delay)
                        .attr("x", function (d) {
                            return x0(d.name);
                        });


                transition.select(".x.axis")
                        .call(xAxis)
                        .selectAll("g")
                        .delay(delay);

            }

            d3.select("#reset").on("click", reset);

            function reset() {
                svg.selectAll("rect")
                        .sort(function (a, b) {
                            return a.name - b.name;
                        })
                        .transition()
                        .delay(function (d, i) {
                            return i * 50;
                        })
                        .duration(1000)
                        .attr("x", function (d, i) {
                            return xScale(i);
                        });

                svg.selectAll('text')
                        .sort(function (a, b) {
                            return a.name - b.name;
                        })
                        .transition()
                        .delay(function (d, i) {
                            return i * 50;
                        })
                        .duration(1000)
                        .text(function (d) {
                            return d.value;
                        })
                        .attr("text-anchor", "middle")
                        .attr("x", function (d, i) {
                            return xScale(i) + xScale.rangeBand() / 2;
                        })
                        .attr("y", function (d) {
                            return h - yScale(d.value) + 14;
                        });
            };

            d3.select("#save").on("click", function () {
                var html = d3.select("svg")
                        .attr("version", 1.1)
                        .attr("xmlns", "http://www.w3.org/2000/svg")
                        .node().parentNode.innerHTML;

                var imgSrc = 'data:image/svg+xml;base64,' + btoa(unescape(encodeURIComponent(html)));
                ;
                //var img = '<img src="'+imgSrc+'">';
                //d3.select("#svgdataurl").html(img);


                var canvas = document.querySelector("canvas"), context = canvas.getContext("2d");

                var image = new Image;
                image.src = imgSrc;
                image.onload = function () {
                    context.drawImage(image, 0, 0);
                    binaryBlob();

                    var a = document.createElement("a");
                    document.body.appendChild(a);
                    a.setAttribute("type", "hidden");
                    a.download = "reporte.png";
                    a.href = canvas.toDataURL("image/png");

                    //var pngimg = '<img src="'+a.href+'">';
                    //d3.select("#pngdataurl").html(pngimg);


                    a.click();
                    context.clearRect(0, 0, canvas.width, canvas.height);
                };

            });

            function binaryBlob() {
                var byteString = atob(document.querySelector("canvas").toDataURL().replace(/^data:image\/(png|jpg);base64,/, "")); //wtf is atob?? https://developer.mozilla.org/en-US/docs/Web/API/Window.atob
                var ab = new ArrayBuffer(byteString.length);
                var ia = new Uint8Array(ab);
                for (var i = 0; i < byteString.length; i++) {
                    ia[i] = byteString.charCodeAt(i);
                }
                var dataView = new DataView(ab);
                var blob = new Blob([dataView], {type: "image/png"});
                var DOMURL = self.URL || self.webkitURL || self;
                var newurl = DOMURL.createObjectURL(blob);

                //var img = '<img src="'+newurl+'">';
                //d3.select("#img").html(img);
            }

        }
    </script>

</head>

<body>
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content col-xs-12 col-xs-offset-1">


    <h2 class="element-center">
        <i class="icon icon-file"></i>&nbsp;&nbsp;Reporte Estad&iacute;stico
    </h2>


    <ul class="nav nav-tabs nav-justified">
        <li role="" class="active"><a data-toggle="tab" href="#sectionA">Gr&aacute;fica</a></li>
        <li role=""><a data-toggle="tab" href="#sectionB">Tabular</a></li>
    </ul>

    <br/>
    <br/>

    <div class="tab-content">
        <div id="sectionA" class="tab-pane fade in active">
            <div class="row-fluid center">
                <div class="chartBar"></div>
                <%--<div id="svgdataurl"></div>--%>
                <%--<div id="pngdataurl"></div>--%>
                <canvas width="1280" height="720" style="display:none"></canvas>
            </div>
            <div class="row element-center">
                <a href="<c:url value='/humanResources/statisticReport/index.html' />">
                    <button class="btn">
                        <i class="glyphicon glyphicon-stats"></i>&nbsp;Obtener otro reporte

                    </button>
                </a>
                <button class="btn btn-info" id="save">
                    <i class="glyphicon glyphicon-picture"></i>&nbsp;Descargar reporte
                </button>
            </div>
        </div>
        <div id="sectionB" class="tab-pane fade">
            <script>

                window.showConfirmPresence = function (id) {

                    //  window.showUpsert(id, "#angJsjqGridId", '<c:url value='/reviewer/meeting/newMeetingForFormulation.html'/>', "#GridId");
                    window.showUpsert(id, "#angJsjqGridId", '<c:url value='/reviewer/formulation/showAttendaneRecord.html'/>', "#GridId");
                    //   window.showAction(id, "#angJsjqGridId", '', "#GridId","Registrar Asistencia/inasistencia","&iquest;El imputado asisti&oacute; a la cita de entrevista de riesgo?","warning");
                };

                window.showInterview = function (id) {
                    window.showUpsert(id, "#angJsjqGridId", '<c:url value='/reviewer/meeting/newMeetingForFormulation.html'/>', "#GridId");
                }

                window.showReportAbsence = function (id) {
                    window.showUpsert(id, "#angJsjqGridId", '<c:url value='/reviewer/reviewer/absenceReport.html'/>', "#GridId", "Registrar Asistencia/inasistencia", "&iquest;El imputado asisti&oacute; a la cita de entrevista de riesgo?", "warning");
                };

                window.printDocument = function (id) {
                    var goTo = "<c:url value='/reviewer/formulation/printAbsenceReport.html'/>" + "?id=" + id;
                    window.goToUrlMvcUrl(goTo);
                    $("#GridId").trigger("reloadGrid");
                };

                window.showConfirmInformationDelivery = function (id) {
                    window.showAction(id, "#angJsjqGridId", '<c:url value='/reviewer/formulation/confirmInformation.json'/>', "#GridId", "Registrar entrega de informaci&oacute;n", "&iquest;Realiz&oacute; la entrega de la informaci&oacute;n de la entrevista de formulaci&oacute;n?", "warning");
                };
                $(document).ready(function () {
                    jQuery("#GridId").jqGrid({
                        url: '<c:url value='/humanResources/statisticReport/list.json' />',
                        datatype: "json",
                        autoencode: true,
                        mtype: 'POST',
                        postData: {
                            initDate : "${initDate}",
                            endDate : "${endDate}",
                            filterSelected : "${filterSelected}",
                            idReportType : ${idReportType},
                            idDistrict : ${idDistrict},
                            idEmployee : ${idEmployee}
                        },
                        colNames: ['ID', 'Periodo', 'Incidencias'],
                        colModel: [
                            {name: 'id', index: 'id', hidden: true},
                            {
                                name: 'name',
                                index: 'name',
                                width: 200,
                                align: "center",
                                sorttype: 'string',
                                search: false
                            },
                            {
                                name: 'value',
                                index: 'value',
                                width: 170,
                                align: "center",
                                sorttype: 'string',
                                searchoptions: {sopt: ['bw']}
                            }
                        ],
                        rowNum: 30,
                     //   rowList: [10, 20, 30],
                        pager: '#GridPager',
                        sortname: 'id',
                        height: 450,
                        viewrecords: true,
                        shrinkToFit: false,
                        sortorder: "desc",
                        caption: "&nbsp;",
                        altRows: true,
                        gridComplete: function () {
                            var ids = $(this).jqGrid('getDataIDs');
                            for (var i = 0; i < ids.length; i++) {
                                var cl = ids[i];
                                var row = $(this).getRowData(cl);
                                var presenceStr = row.presenceStr + "";
                                var informationDeliveredStr = row.informationDeliveredStr;
                                var be = "";
                                if (presenceStr === "Pendiente") {
                                    be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Registrar asistencia/inasistencia\" onclick=\"window.showConfirmPresence('" + cl + "');\"><i class=\" icon-ok\"></i></a>";
                                }
                                if (presenceStr === "No") {
                                    be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Generar reporte de inasistencia\" onclick=\"window.printDocument('" + cl + "');\"><i class=\" icon-file\"></i></a>";
                                }
                                if (presenceStr === "Si") {
//                                be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Entrevistas de riesgos\" onclick=\"window.showInterview('" + cl + "');\"><i class=\" icon-comments-alt\"></i></a>";
                                    be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Entrega de informaci&oacute;n\" onclick=\"window.showConfirmInformationDelivery('" + cl + "');\"><i class=\" icon-list-alt\"></i></a>";
                                }
                                if(informationDeliveredStr === "Si"){
                                    be = "";

                                }
                                /* be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Registrar asistencia/inasistencia\" onclick=\"window.showConfirmPresence('" + cl + "');\"><i class=\" icon-ok\"></i></a>";
                                 be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Generar reporte de inasistencia\" onclick=\"window.printDocument('" + cl + "');\"><i class=\" icon-file\"></i></a>";
                                 be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Entrega de informaci&oacute;n\" onclick=\"window.showConfirmInformationDelivery('" + cl + "');\"><i class=\" icon-list-alt\"></i></a>";*/
                                $(this).jqGrid('setRowData', ids[i], {Action: be});
                                if (row.attended === "false" && row.presenceStr === "Pendiente") {
                                    $("#" + cl).css("background-color", "#FF3617");
                                }
                            }
                        },
                        loadComplete: function () {
                            var table = this;
                            setTimeout(function () {
                                updatePagerIcons(table);
                                enableTooltips(table);
                            }, 0);
                        }
                    });



                    jQuery("#GridId").jqGrid('navGrid', '#GridPager', {
                        edit: false, editicon: 'icon-pencil blue',
                        add: false,
                        refresh: true, refreshicon: 'icon-refresh green',
                        del: false,
                        search: false
                    });

                    jQuery("#GridId").jqGrid('navButtonAdd', "#GridPager",
                            {
                                caption: "",
                                title: "Descargar Excel",
                                buttonicon: 'icon-download-alt red',

                                onClickButton: function () {
                                    try {

                                        var params = [];
                                        params["initDateParam"] = "${initDate}";
                                        params["endDateParam"] = "${endDate}";
                                        params["filterSelectedParam"] = "${filterSelected}";
                                        params["idReportTypeParam"] = ${idReportType};
                                        params["idDistrictParam"] = ${idDistrict};
                                        params["idEmployeeParam"] = ${idEmployee};

                                     //   params["idParam"] = listIds;
                                     //   params["filters"] = JSON.stringify(selectedFilters);

                                        window.goToUrlMvcUrl("<c:url value='/humanResources/statisticReport/jxls.html?initDate=initDateParam&endDate=endDateParam&filterSelected=filterSelectedParam&idReportType=idReportTypeParam&idDistrict=idDistrictParam&idEmployee=idEmployeeParam'/>", params);

                                    } catch (e) {

                                    }
                                }
                            });


                });

            </script>




            <div id="angJsjqGridId" ng-controller="modalDlgController">
                <table id="GridId" class="element-center" style="margin: auto"></table>
                <div id="GridPager"></div>
                <%--<div class="blocker" ng-show="working">--%>
                    <%--<div>--%>
                        <%--Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>--%>
                    <%--</div>--%>
                <%--</div>--%>
            </div>

        </div>
    </div>


    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
</div>


</body>
</html>
