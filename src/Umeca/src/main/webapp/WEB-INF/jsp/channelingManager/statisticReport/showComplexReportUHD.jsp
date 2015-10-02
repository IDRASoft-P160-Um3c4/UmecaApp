<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.umeca.model.shared.Constants" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>

    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/upsertCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/modalDlgCtrl.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/daterangepicker.min.js"></script>

    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/hiddenDrct.js"></script>


    <script src="${pageContext.request.contextPath}/assets/scripts/app/managereval/statisticReportCtrl.js"></script>


    <script src="${pageContext.request.contextPath}/assets/scripts/d3/d3.min.js"></script>

    <title>Reporte estad&iacute;stico</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content col-xs-12 col-xs-offset-1">

    <script>

        window.onload = function () {
            var initDate = "${initDate}";
            var endDate = "${endDate}";
            var extraData = "${extraData}";
            var title = "${title}";
            var yAxisStr = "${yAxis}";
            var dataSet = ${data};
            var names = [];
            var cases = [];

            var element = dataSet[0];
            for (i = 0; i < element.length; i++) {
                names.push({"name": element[i].user});
            }

            for (i = 0; i < dataSet.length; i++) {
                var e = dataSet[i];
                cases.push({"name": (e[0].id + 1) + ". " + e[0].name});
            }


            var n = dataSet.length, // number of layers
                    m = names.length, // number of samples per layer
                    stack = d3.layout.stack(),
                    layers = stack(dataSet),
                    yGroupMax = d3.max(layers, function (layer) {
                        return d3.max(layer, function (d) {
                            return d.y;
                        });
                    }),
                    yStackMax = d3.max(layers, function (layer) {
                        return d3.max(layer, function (d) {
                            return d.y0 + d.y;
                        });
                    });

            var margin = {top: 50, right: 20, bottom: 700, left: 30},
                    width = 3840 - margin.left - margin.right,
                    height = 2160 - margin.top - margin.bottom;

            var x = d3.scale.ordinal()
                    .domain(d3.range(m))
                    .rangeRoundBands([0, width], .08);

            var y = d3.scale.linear()
                    .domain([0, yStackMax])
                    .range([height, 0]);


            var color = d3.scale.ordinal()
                    .range([
                        "#ef5350", "#f44336", "#e53935", "#d32f2f", "#c62828", "#b71c1c",
                        "#EC407A", "#E91E63", "#D81B60", "#C2185B", "#AD1457", "#880E4F",
                        "#AB47BC", "#9C27B0", "#8E24AA", "#7B1FA2", "#6A1B9A", "#4A148C",
                        "#7E57C2", "#673AB7", "#5E35B1", "#512DA8", "#4527A0", "#311B92",
                        "#5C6BC0", "#3F51B5", "#3949AB", "#303F9F", "#283593", "#1A237E",
                        "#42A5F5", "#2196F3", "#1E88E5", "#1976D2", "#1565C0", "#0D47A1",
                        "#29B6F6", "#03A9F4", "#039BE5", "#0288D1", "#0277BD", "#01579B",
                        "#26C6DA", "#00BCD4", "#00ACC1", "#0097A7", "#00838F", "#006064",
                        "#26A69A", "#009688", "#00897B", "#00796B", "#00695C", "#004D40",
                        "#66BB6A", "#4CAF50", "#43A047", "#388E3C", "#2E7D32", "#1B5E20",
                        "#9CCC65", "#8BC34A", "#7CB342", "#689F38", "#558B2F", "#33691E",
                        "#D4E157", "#CDDC39", "#C0CA33", "#AFB42B", "#9E9D24", "#827717",
                        "#FFEE58", "#FFEB3B", "#FDD835", "#FBC02D", "#F9A825", "#F57F17",
                        "#FFCA28", "#FFC107", "#FFB300", "#FFA000", "#FF8F00", "#FF6F00",
                        "#FFA726", "#FF9800", "#FB8C00", "#F57C00", "#EF6C00", "#E65100",
                        "#FF7043", "#FF5722", "#F4511E", "#E64A19", "#D84315", "#BF360C",
                        "#8D6E63", "#795548", "#6D4C41", "#5D4037", "#4E342E", "#3E2723",
                        "#BDBDBD", "#9E9E9E", "#757575", "#616161", "#424242", "#212121",
                        "#78909C", "#607D8B", "#546E7A", "#455A64", "#37474F", "#263238",

                    ]);


            var xAxis = d3.svg.axis()
                    .scale(x)
                    .orient("bottom")
                    .tickFormat(function (d) {
                        return names[d].name;
                    });

            var yAxis = d3.svg.axis()
                    .scale(y)
                    .orient("left");


            var svg = d3.select(".chartBar").append("svg")
                    .attr("width", width + margin.left + margin.right)
                    .attr("height", height + margin.top + margin.bottom)
                    .append("g")
                    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");


            svg.append("g")
                    .attr("class", "y axis")
                    .call(yAxis)
                    .append("text")
                    .attr("transform", "rotate(-90)")
                    .attr("y", 6)
                    .attr("dy", ".71em")
                    .style("text-anchor", "end")
                    .text(yAxisStr);

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

            var layer = svg.selectAll(".layer")
                    .data(layers)
                    .enter().append("g")
                    .attr("class", "layer")
                    .style("fill", function (d, i) {
                        return color(i);
                    });

            var rect = layer.selectAll("rect")
                    .data(function (d) {
                        return d;
                    })
                    .enter().append("rect")
                    .attr("x", function (d) {
                        return x(d.x);
                    })
                    .attr("y", height)
                    .attr("width", x.rangeBand())
                    .attr("height", 0);


            var text = layer.selectAll("layer")
                    .data(function (d) {
                        return d;
                    })
                    .enter().append("text")
                    .attr("x", function (d) {
                        return x(d.x) + x.rangeBand() / 2;
                    })
                    .attr("y", function (d) {
                        return y(d.y);
                    })
                    .attr("dy", "1.2em")
                    .style("fill", "white")
                    .style("font", "3px sans-serif")
                    .text(function (d) {
                        return d.y;
                    });


            var textN = layer.selectAll("layer")
                    .data(function (d) {
                        return d;
                    })
                    .enter().append("text")
                    .attr("x", function (d, i, j) {
                        return x(d.x) + x.rangeBand() / n * j + ((x.rangeBand() / n) / 2);
                    })
                    .attr("width", x.rangeBand() / n)
                    .transition()
                    .attr("y", 1410)
                    .attr("height", function (d) {
                        return height - y(d.y);
                    })
                    .attr("dy", "1.2em")
                    .style("fill", "black")
                    .style("text-anchor", "middle")
                    .style("font", "2px sans-serif")
                    .text(function (d) {
                        return d.id + 1;
                    });


            text.transition()
                    .delay(function (d, i) {
                        return i * 10;
                    })
                    .attr("y", function (d) {
                        return y(d.y0 + d.y);
                    })
                    .attr("height", function (d) {
                        return y(d.y0) - y(d.y0 + d.y);
                    });


            rect.transition()
                    .delay(function (d, i) {
                        return i * 10;
                    })
                    .attr("y", function (d) {
                        return y(d.y0 + d.y);
                    })
                    .attr("height", function (d) {
                        return y(d.y0) - y(d.y0 + d.y);
                    });


            y.domain([0, yGroupMax]);


            svg.transition()
                    .delay(2500)
                    .selectAll(".y.axis")
                    .call(yAxis);


            svg.append("g")
                    .attr("class", "x axis")
                    .attr("transform", "translate(0," + height + ")")
                    .call(xAxis)
                    .selectAll("text")
                    .style("text-anchor", "middle")
                    .attr("dy", "35");


            d3.selectAll("input").on("change", change);


            var startp = svg.append("g").attr("class", "legendbox").attr("id", "mylegendbox");
            var legend_tabs = [
                0, 750, 1500, 2250, 3000,
                0, 750, 1500, 2250, 3000,
                0, 750, 1500, 2250, 3000,
                0, 750, 1500, 2250, 3000,
                0, 750, 1500, 2250, 3000,
                0, 750, 1500, 2250, 3000,
                0, 750, 1500, 2250, 3000,
                0, 750, 1500, 2250, 3000,
                0, 750, 1500, 2250, 3000,
                0, 750, 1500, 2250, 3000,
                0, 750, 1500, 2250, 3000,
                0, 750, 1500, 2250, 3000,
                0, 750, 1500, 2250, 3000,
                0, 750, 1500, 2250, 3000,
                0, 750, 1500, 2250, 3000,
                0, 750, 1500, 2250, 3000,
                0, 750, 1500, 2250, 3000,
                0, 750, 1500, 2250, 3000,
                0, 750, 1500, 2250, 3000,
                0, 750, 1500, 2250, 3000,
                0, 750, 1500, 2250, 3000,
                0, 750, 1500, 2250, 3000,
                0, 750, 1500, 2250, 3000,
                0, 750, 1500, 2250, 3000
            ];
            var legend_line = [
                1500, 1500, 1500, 1500, 1500,
                1525, 1525, 1525, 1525, 1525,
                1550, 1550, 1550, 1550, 1550,
                1575, 1575, 1575, 1575, 1575,
                1600, 1600, 1600, 1600, 1600,
                1625, 1625, 1625, 1625, 1625,
                1650, 1650, 1650, 1650, 1650,
                1675, 1675, 1675, 1675, 1675,
                1700, 1700, 1700, 1700, 1700,
                1725, 1725, 1725, 1725, 1725,
                1750, 1750, 1750, 1750, 1750,
                1775, 1775, 1775, 1775, 1775,
                1800, 1800, 1800, 1800, 1800,
                1825, 1825, 1825, 1825, 1825,
                1850, 1850, 1850, 1850, 1850,
                1875, 1875, 1875, 1875, 1875,
                1900, 1900, 1900, 1900, 1900,
                1925, 1925, 1925, 1925, 1925,
                1950, 1950, 1950, 1950, 1950,
                1975, 1975, 1975, 1975, 1975,
                2000, 2000, 2000, 2000, 2000,
                2025, 2025, 2025, 2025, 2025,
                2050, 2050, 2050, 2050, 2050,
                2075, 2075, 2075, 2075, 2075
            ];


            var filterSelected = '${filterSelected}';
            if (filterSelected === '<%=Constants.REPORT_STATISTIC_MANAGER_REPORT_C%>') {
                var legend_tabs = [
                    0, 750, 1500, 2250, 3000,
                    0, 750, 1500, 2250, 3000,
                    0, 750, 2250, 3000,
                    0, 750, 1500, 2250, 3000,
                    0, 750, 1500, 2250, 3000,
                    0, 1500, 2250, 3000,
                    0, 750, 1500, 2250, 3000,
                    0, 750, 1500, 2250, 3000,
                    0, 750, 1500, 2250, 3000,
                    0, 750, 2250, 3000,
                    0, 750, 3000,
                    0, 750, 2250, 3000,
                    0, 1500, 2250, 3000,
                    0, 1500, 2250, // 3000,
                    0, 750, 1500, 2250, 3000,
                    0, 750, 1500, 2250, 3000,
                    0, 750, 1500, 2250, 3000,
                    0, 750, 1500, 2250, 3000,
                    0, 750, 1500, 2250, 3000,
                    0, 750, 1500, 2250, 3000,
                    0, 750, 1500, 2250, 3000,
                    0, 750, 1500, 2250, 3000,
                    0, 750, 1500, 2250, 3000,
                    0, 750, 1500, 2250, 3000
                ];
                var legend_line = [
                    1500, 1500, 1500, 1500, 1500,
                    1525, 1525, 1525, 1525, 1525,
                    1550, 1550, 1550, 1550, 1575,
                    1575, 1575, 1575,  1575, // 1575,
                    1600, 1600, 1600, 1600, 1600,
                    1625, 1625, 1625, 1625, // 1625,
                    1650, 1650, 1650, 1650, 1650,
                    1675, 1675, 1675, 1675, 1675,
                    1700, 1700, 1700, 1700, // 1700,
                    1700, 1725, 1725, 1725, 1725,
                    1750, 1750, 1750, // 1750, 1750,
                    1775, 1775, 1775, 1775, // 1775,
                    1800, 1800, 1800, 1800, // 1800,
                    1825, 1825, 1825, // 1825, // 1825,
                    1850, 1850, 1850, 1850, 1850,
                    1875, 1875, 1875, 1875, 1875,
                    1900, 1900, 1900, 1900, 1900,
                    1925, 1925, 1925, 1925, 1925,
                    1950, 1950, 1950, 1950, 1950,
                    1975, 1975, 1975, 1975, 1975,
                    2000, 2000, 2000, 2000, 2000,
                    2125, 2125, 2125, 2125, 2125,
                    2150, 2150, 2150, 2150, 2150,
                    2175, 2175, 2175, 2175, 2175
                ];

            }


            var legend = startp.selectAll(".legend")
                    .data(color.domain().slice())
                    .enter().append("g")
                    .attr("class", "legend")
                    .attr("transform", function (d, i) {
                        return "translate(" + legend_tabs[i] + "," + legend_line[i] + ")";
                    });

            legend.append("rect")
                    .attr("x", 0)
                    .attr("width", 18)
                    .attr("height", 18)
                    .style("fill", color);

            legend.append("text")
                    .data(cases.slice())
                    .attr("x", 22)
                    .attr("y", 9)
                    .attr("dy", ".35em")
                    .style("text-anchor", "begin")
                    .style("font", "11px sans-serif")
                    .text(function (d) {
                        return d.name
                    });


            //style
            svg.selectAll(".x.axis path")
                    .style("display", "none");

            svg.selectAll(".axis text")
                    .style("font-family", "sans-serif")
                    .style("font-size", "12px");

            svg.selectAll(".axis path")
                    .style("fill", "none")
                    .style("stroke", "#000")
                    .style("shape-rendering", "crispEdges");

            svg.selectAll(".axis line")
                    .style("fill", "none")
                    .style("stroke", "#000")
                    .style("shape-rendering", "crispEdges");


            var timeout = setTimeout(function () {
                d3.select("input[value=\"grouped\"]").property("checked", true).each(change);
            }, 2000);

            function change() {
                clearTimeout(timeout);
                if (this.value === "grouped") transitionGrouped();
                else transitionStacked();
            }

            function transitionGrouped() {
                y.domain([0, yGroupMax]);

                svg.transition()
                        .duration(750)
                        .selectAll(".y.axis")
                        .call(yAxis);

                rect.transition()
                        .duration(500)
                        .delay(function (d, i) {
                            return i * 10;
                        })
                        .attr("x", function (d, i, j) {
                            return x(d.x) + x.rangeBand() / n * j;
                        })
                        .attr("width", x.rangeBand() / n)
                        .transition()
                        .attr("y", function (d) {
                            return y(d.y);
                        })
                        .attr("height", function (d) {
                            return height - y(d.y);
                        });

                text.transition()
                        .duration(500)
                        .delay(function (d, i) {
                            return i * 10;
                        })
                        .attr("x", function (d, i, j) {
                            return x(d.x) + x.rangeBand() / n * j + ((x.rangeBand() / n) / 2);
                        })
                        .attr("width", x.rangeBand() / n)
                        .transition()
                        .attr("y", function (d) {
                            return y(d.y);
                        })
                        .attr("height", function (d) {
                            return height - y(d.y);
                        });
            }

            function transitionStacked() {
                y.domain([0, yStackMax]);

                svg.transition()
                        .duration(750)
                        .selectAll(".y.axis")
                        .call(yAxis);

                rect.transition()
                        .duration(500)
                        .delay(function (d, i) {
                            return i * 10;
                        })
                        .attr("y", function (d) {
                            return y(d.y0 + d.y);
                        })
                        .attr("height", function (d) {
                            return y(d.y0) - y(d.y0 + d.y);
                        })
                        .transition()
                        .attr("x", function (d) {
                            return x(d.x);
                        })
                        .attr("width", x.rangeBand());

                text.transition()
                        .duration(500)
                        .delay(function (d, i) {
                            return i * 10;
                        })
                        .attr("y", function (d) {
                            return y(d.y0 + d.y);
                        })
                        .attr("height", function (d) {
                            return y(d.y0) - y(d.y0 + d.y);
                        })
                        .transition()
                        .attr("x", function (d) {
                            return x(d.x) + x.rangeBand() / 2;
                        })
                        .attr("width", x.rangeBand());

            }

            d3.select("#save").on("click", function () {
                var html = d3.select("svg")
                        .attr("version", 1.1)
                        .attr("xmlns", "http://www.w3.org/2000/svg")
                        .node().parentNode.innerHTML;

                var imgSrc = 'data:image/svg+xml;base64,' + btoa(unescape(encodeURIComponent(html)));


                var canvas = document.querySelector("canvas"), context = canvas.getContext("2d");

                var image = new Image;
                image.src = imgSrc;
                image.onload = function () {
                    context.drawImage(image, 0, 0);
                    binaryBlob();

                    var a = document.createElement("a");
                    a.download = "reporte.png";
                    a.href = canvas.toDataURL("image/png");


                    a.click();
                };

            });

            function binaryBlob() {
                var byteString = atob(document.querySelector("canvas").toDataURL().replace(/^data:image\/(png|jpg);base64,/, ""));
                var ab = new ArrayBuffer(byteString.length);
                var ia = new Uint8Array(ab);
                for (var i = 0; i < byteString.length; i++) {
                    ia[i] = byteString.charCodeAt(i);
                }
                var dataView = new DataView(ab);
                var blob = new Blob([dataView], {type: "image/png"});
                var DOMURL = self.URL || self.webkitURL || self;
                var newurl = DOMURL.createObjectURL(blob);


            }

        };


    </script>


    <form>
        <label><input type="hidden" name="mode" value="grouped"></label>
        <label><input type="hidden" name="mode" value="stacked" checked></label>
    </form>


    <h2 class="element-center">
        <i class="icon icon-file"></i>&nbsp;&nbsp;Reporte estad&iacute;stico por operador
    </h2>

    <form id="FormStatisRep" name="FormStatisRep" class="form-horizontal"
          role="form" ng-controller="statisticReportController" method="post" ng-cloak>


        <div class="row">
            <div class="col-xs-12 element-center">
                <input type="hidden" ng-update-hidden ng-model="idDistrict" ng-init="idDistrict = ${idDistrict}"
                       name="idDistrict" id="idDistrict" ng-value="idDistrict">
                <input type="hidden" ng-model="initDate" ng-init="initDate = '${initDate}'"
                       name="initDate" id="initDate" ng-value="initDate">
                <input type="hidden" ng-update-hidden ng-model="endDate" ng-init="endDate = '${endDate}'"
                       name="endDate" id="endDate">
                <input type="hidden" ng-update-hidden ng-model="filterSelected"
                       ng-init="filterSelected = '${filterSelected}'"
                       name="filterSelected" id="filterSelected">
                <input type="hidden" ng-update-hidden ng-model="idSupervisor" name="idSupervisor" id="idSupervisor">

                <label for="supervisor">Supervisor</label>
                <select id="supervisor"
                        ng-model="supervisor"
                        ng-init='lstSupervisors = ${lstSupervisors}; supervisor = lstSupervisors[0]; idSupervisor = supervisor.id;'
                        ng-options="e.description for e in lstSupervisors"
                        ng-change="idSupervisor = supervisor.id">
                </select>
                                  <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                                        ng-click="findLargeSupervisorReport('#FormStatisRep','<c:url value='/supervisorManager/statisticReport/showLargeReport.html'/>');">
                                    Realizar b&uacute;squeda
                                </span>
            </div>


        </div>
        <br/>
    </form>

    <div class="row-fluid center">
        <div class="chartBar"></div>
        <%--<div id="svgdataurl"></div>--%>
        <%--<div id="pngdataurl"></div>--%>
        <canvas width="3840" height="2160" style="display:none"></canvas>
    </div>

    <div class="row element-center">
        <a href="<c:url value='/channelingManager/statisticReport/index.html' />">
            <button class="btn">
                <i class="glyphicon glyphicon-stats"></i>&nbsp;Obtener otro reporte

            </button>
        </a>
        <button class="btn btn-info" id="save">
            <i class="glyphicon glyphicon-picture"></i>&nbsp;Descargar reporte
        </button>
    </div>

    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
</div>

</body>
</html>

