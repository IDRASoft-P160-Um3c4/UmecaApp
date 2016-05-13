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

            var dataSet = ${data};
            var total = ${total};
            var initDate = "${initDate}";
            var endDate = "${endDate}";
            var extraData = "${extraData}";
            var title = "${title}";
            var extraData = "${extraData}";


            var color = d3.scale.ordinal()
                    .range(["#00BCD4", "#E91E63", "#009688", "#3F51B5"]);


            var len = dataSet.length;

            if (len > 4) {
                color = d3.scale.ordinal().range(["#F44336", "#E91E63", "#9C27B0", "#673AB7", "#3F51B5", "#2196F3", "#00BCD4", "#009688", "#4CAF50", "#8BC34A", "#FFEB3B", "#FFC107", "#FF9800", "#795548", "#9E9E9E"]);
            }

            if (len > 8) {
                dataSet.map(function (d) {
                    d.name = d.subName;
                })
            }

            var margin = {top: 50, right: 240, bottom: 50, left: 40},
                    width = 1280 - margin.left - margin.right,
                    height = 720 - margin.top - margin.bottom;

            var dataSetDefault = [
                {name: "Opinion", value: 0},
                {name: "Informe", value: 0},
                {name: "Negacion", value: 0},
                {name: "Solo entrevista", value: 0}];


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
                //.attr("stroke", "orange")
                // .attr("stroke-width", function(d,i){return d.value/2;})
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
                    .style("font", "12px sans-serif")
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
                    .text("Personas");


            svg.append("text")
                    .attr("x", (width / 2))
                    .attr("y", 0 - 35)
                    .attr("text-anchor", "middle")
                    .style("font", "16px sans-serif")
                    .text(title + "  -  " + extraData);


            svg.append("text")
                    .attr("x", (width / 2))
                    .attr("y", 0 - 15)
                    .attr("text-anchor", "middle")
                    .style("font", "12px sans-serif")
                    .text("Periodo: "+initDate + " - " + endDate);


            //legend
            var legend = svg.append("g")
                    .attr("class", "legend")
                //.attr("x", w - 65)
                //.attr("y", 50)
                    .attr("height", 100)
                    .attr("width", 100)
                    .attr('transform', 'translate(40,40)');

            var legendRect = legend.selectAll('.legend').data(color.domain().slice());

            legendRect.enter()
                    .append("rect")
                    .attr("x", width - 65)
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
                    .attr("x", width - 52);

            legendText
                    .attr("y", function (d, i) {
                        return i * 20 + 9;
                    })
                    .style("font-size", "12px")
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
                    a.download = "reporte.png";
                    a.href = canvas.toDataURL("image/png");

                    //var pngimg = '<img src="'+a.href+'">';
                    //d3.select("#pngdataurl").html(pngimg);


                    a.click();
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

    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>

    <h2 class="element-center">
        <i class="icon icon-file"></i>&nbsp;&nbsp;Reporte Estad&iacute;stico
    </h2>

    <form id="FormStatisRep" name="FormStatisRep" class="form-horizontal"
          role="form" ng-controller="statisticReportController" method="post" ng-cloak>

        <div class="col-xs-12 element-center">
            <input type="hidden" ng-model="initDate" ng-init="initDate = '${initDate}'"
                   name="initDate" id="initDate" ng-value="initDate">
            <input type="hidden" ng-update-hidden ng-model="endDate" ng-init="endDate = '${endDate}'"
                   name="endDate" id="endDate">
            <input type="hidden" ng-update-hidden ng-model="idReportType"
                   ng-init="idReportType = '${idReportType}'"
                   name="idReportType" id="idReportType">
            <input type="hidden" ng-update-hidden ng-model="idDistrict"
                   ng-init="idDistrict = '${idDistrict}'"
                   name="idDistrict" id="idDistrict">
            <input type="hidden" ng-update-hidden ng-model="reportType"
                   ng-init="reportType = '${reportType}'"
                   name="reportType" id="reportType">


            <div class="row" ng-disabled = "reportType != '<%=Constants.REPORT_STATISTIC_CHANNELING_H%>'" ng-show="reportType == '<%=Constants.REPORT_STATISTIC_CHANNELING_H%>'" >
                <div class="col-xs-12 element-center">

                    <label for="ChannelingType">Tipo de canalizaci&oacute;n</label>
                    <select id="ChannelingType"
                            ng-model="ChannelingType"
                            ng-init='lstChannelingType = ${lstChannelingType==null?"[]":lstChannelingType};'
                            ng-options="e.name for e in lstChannelingType"
                            ng-change="idParameter = ChannelingType.id">
                    </select>
                <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                      ng-click="findChannelingTypeReport('#FormStatisRep','<c:url value='/channelingManager/statisticReport/showReport.html'/>');">
                    Realizar b&uacute;squeda
                </span>
                </div>

            </div>
            <div class="row" ng-disabled = "reportType != '<%=Constants.REPORT_STATISTIC_CHANNELING_I%>'" ng-show="reportType == '<%=Constants.REPORT_STATISTIC_CHANNELING_I%>'" >
                <div class="col-xs-12 element-center">

                    <label for="InstitutionName">Tipo de canalizaci&oacute;n</label>
                    <select id="InstitutionName"
                            ng-model="InstitutionName"
                            ng-init='lstChannelingInstitutionName = ${lstChannelingInstitutionName==null?"[]":lstChannelingInstitutionName};'
                            ng-options="e.name for e in lstChannelingInstitutionName"
                            ng-change="idParameter = InstitutionName.id">
                    </select>
                <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                      ng-click="findChannelingTypeReport('#FormStatisRep','<c:url value='/channelingManager/statisticReport/showReport.html'/>');">
                    Realizar b&uacute;squeda
                </span>
                </div>
            </div>            </span>
            </div>

        <input type="hidden" ng-update-hidden ng-model="idParameter" name="idParameter"
               id="idParameter" ng-init="idParameter='${idParameter}'">
        <br/>
    </form>

    <div class="row-fluid center">
        <div class="chartBar"></div>
        <%--<div id="svgdataurl"></div>--%>
        <%--<div id="pngdataurl"></div>--%>
        <canvas width="1280" height="720" style="display:none"></canvas>
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
