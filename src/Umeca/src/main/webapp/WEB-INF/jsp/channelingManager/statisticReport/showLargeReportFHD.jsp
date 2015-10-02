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


            var len = dataSet.length;


            if (len > 8) {
                dataSet.map(function (d) {
                    d.name = d.subName;
                })
            }

            var margin = {top: 50, right: 20, bottom: 600, left: 30},
                    width = 1920 - margin.left - margin.right,
                    height = 1080 - margin.top - margin.bottom;


            var x = d3.scale.ordinal()
                    .domain(d3.range(dataSet.length))
                    .rangeRoundBands([0, width]);

            var y = d3.scale.linear()
                    .domain([0, d3.max(dataSet, function (d) {
                        return d.value;
                    })])
                    .range([height, 0]);

            var xAxis = d3.svg.axis()
                    .scale(x)
                    .orient("bottom")
                    .tickFormat(function (d) {
                        return d +1;
                    });

            var yAxis = d3.svg.axis()
                    .scale(y)
                    .orient("left");

            var xScale = d3.scale.ordinal()
                    .domain(d3.range(dataSet.length))
                    .rangeRoundBands([0, width]);

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



            //bar
            svg.selectAll(".bar")
                    .data(dataSet)
                    .enter().append("rect")
                    .attr("class", "bar")
                    .attr("x", function (d, i) {
                        return x(i);
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
                    .style("font", "6px sans-serif")
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
                    .attr("dy", ".35em")
                    .style("text-anchor", "end")
                    .text("Personas");


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

            var startp = svg.append("g").attr("class", "legendbox").attr("id", "mylegendbox");
            var legend_tabs = [
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300,
                100, 700, 1300
            ];
            var legend_line = [
                450, 450, 450,
                465, 465, 465,
                480, 480, 480,
                495, 495, 495,
                510, 510, 510,
                525, 525, 525,
                540, 540, 540,
                555, 555, 555,
                570, 570, 570,
                585, 585, 585,
                600, 600, 600,
                615, 615, 615,
                630, 630, 630,
                645, 645, 645,
                660, 660, 660,
                675, 675, 675,
                690, 690, 690,
                705, 705, 705,
                720, 720, 720,
                735, 735, 735,
                750, 750, 750,
                765, 765, 765,
                780, 780, 780,
                795, 795, 795,
                810, 810, 810,
                825, 825, 825,
                840, 840, 840,
                855, 855, 855,
                870, 870, 870,
                885, 885, 885,
                900, 900, 900,
                915, 915, 915,
                930, 930, 930,
                945, 945, 945,
                960, 960, 960,
                975, 975, 975,
                990, 990, 990,
                1005, 1005, 1005
            ];

            var filterSelected = '${filterSelected}';
            if (filterSelected === '<%=Constants.REPORT_STATISTIC_MANAGER_REPORT_C%>') {
                var legend_tabs = [
                    0, 0, 0,
                    0, 0, 0,
                    0, 0, 0,
                    0, 0, 0,
                    0, 0, 0,
                    0, 0, 0,
                    0, 0, 0,
                    0, 0, 0,
                    0, 0, 0,
                    0, 0, 0,
                    0, 0, 0,
                    0, 0, 0,
                    650,650,650,
                    650,650,650,
                    650,650,650,
                    650,650,650,
                    650,650,650,
                    650,650,650,
                    650,650,650,
                    650,650,650,
                    650,650,650,
                    650,650,650,
                    650,650,650,
                    650,650,650,
                ];

                var legend_line = [
                    450, 461, 472,
                    483, 494, 505,
                    516, 527, 538,
                    549, 560 ,571,
                    582,593,604,
                    615,626,637,
                    648,659,670,
                    681,692,703,
                    714,725,736,
                    747,758,769,
                    780,791,802,
                    813, 824, 835,
                    450, 461, 472,
                    483, 494, 505,
                    516, 527, 538,
                    549, 560 ,
                    582,593,604,
                    615,626,637,
                    648,659,670,
                    681,692,703,
                    725,736,
                    747,758,769,
                    780,791,802,
                    813, 824, 835,
                    846, 857
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
                    .attr("width", 10)
                    .attr("height", 10)
                    .style("fill", color);

            legend.append("text")
                    .data(dataSet.slice())
                    .attr("x", 15)
                    .attr("y", 5)
                    .attr("dy", ".35em")
                    .style("text-anchor", "begin")
                    .style("font", "11px sans-serif")
                    .text(function (d, i) {
                        return (i+1) + ". " + d.name
                    });




            //style
            svg.selectAll(".x.axis path")
                    .style("display", "none");

            svg.selectAll(".axis text")
                    .style("font-family", "sans-serif")
                    .style("font-size", "8px")
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
          role="form" ng-controller="statisticReportController" method="post"  ng-cloak>


        <div class="row">
            <div class="col-xs-12 element-center">
                <input type="hidden" ng-update-hidden ng-model="idDistrict" ng-init="idDistrict = ${idDistrict}"
                       name="idDistrict" id="idDistrict" ng-value = "idDistrict">
                <input type="hidden" ng-model="initDate" ng-init="initDate = '${initDate}'"
                       name="initDate" id="initDate" ng-value = "initDate">
                <input type="hidden" ng-update-hidden ng-model="endDate" ng-init="endDate = '${endDate}'"
                       name="endDate" id="endDate">
                <input type="hidden" ng-update-hidden ng-model="filterSelected" ng-init="filterSelected = '${filterSelected}'"
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
