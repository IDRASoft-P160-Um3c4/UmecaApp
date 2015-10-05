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

            var margin = {top: 50, right: 20, bottom: 200, left: 30},
                    width = 1920 - margin.left - margin.right,
                    height = 1080 - margin.top - margin.bottom;

            var x = d3.scale.ordinal()
                    .domain(d3.range(m))
                    .rangeRoundBands([0, width], .08);

            var y = d3.scale.linear()
                    .domain([0, yStackMax])
                    .range([height, 0]);

            var color = d3.scale.linear()
                    .domain([0, n - 1])
                    .range(["#aad", "#556"]);

            var color = d3.scale.ordinal()
                    .range(["#00BCD4", "#E91E63", "#009688", "#3F51B5", "#B3F324", "#E8A922", "#FF3619", "#A822E8", "#2580FF"]);


            var len = cases.length;

            if (len > 4) {
                color = d3.scale.ordinal().range(["#F44336", "#E91E63", "#9C27B0", "#673AB7", "#3F51B5", "#2196F3", "#00BCD4", "#009688", "#4CAF50", "#8BC34A", "#FFEB3B", "#FFC107", "#FF9800", "#795548", "#9E9E9E", "#438755", "#687452", "#326519"]);
            }


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
                //.style("text-decoration", "underline")
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
                    .attr("dx", -4)
                    .attr("dy", "1.2em")
                    .style("fill", "white")
                    .style("font", "11px sans-serif")
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
                    .attr("y", 840)
                    .attr("height", function (d) {
                        return height - y(d.y);
                    })
                    .attr("dx", -5)
                    .attr("dy", "1.2em")
                    .style("fill", "black")
                    .style("text-anchor", "middle")
                    .style("font", "11px sans-serif")
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
                0, 325, 650, 975, 1300, 1625,
                0, 325, 650, 975, 1300, 1625,
                0, 325, 650, 975, 1300, 1625
            ];
            var legend_line = [
                900, 900, 900, 900, 900, 900,
                925, 925, 925, 925, 925, 925,
                950, 950, 950, 950, 950, 950
            ];

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
                                        ng-click="findLargeSupervisorReport('#FormStatisRep','<c:url value='/channelingManager/statisticReport/showLargeReport.html'/>');">
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
        <canvas width="1920" height="1080" style="display:none"></canvas>
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

