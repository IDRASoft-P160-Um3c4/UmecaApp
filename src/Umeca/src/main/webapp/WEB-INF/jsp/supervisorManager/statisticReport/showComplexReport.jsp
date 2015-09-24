<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>


    <title>Reporte estad&iacute;stico</title>


    <style charset="utf-8" type="text/css">



        rect:hover {
            fill: #607D8B !important;
        }

        form {
            position: absolute;
            right: 10px;
            top: 10px;
        }

    </style>

    <script src="${pageContext.request.contextPath}/assets/scripts/d3/d3.min.js"></script>
    <script>

        window.onload = function () {
            var initDate = "${initDate}";
            var endDate = "${endDate}";
            var extraData = "${extraData}";
            var title = "${title}";
            var extraData = "${extraData}";
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
                cases.push({"name":(e[0].id + 1)+ ". " +e[0].name});
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

            var margin = {top: 50, right: 90, bottom: 75, left: 30},
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
                    .range(["#00BCD4", "#E91E63", "#009688", "#3F51B5","#B3F324","#E8A922","#FF3619","#A822E8","#2580FF"]);


            var len = cases.length;

            if (len > 4) {
                color = d3.scale.ordinal().range(["#F44336", "#E91E63", "#9C27B0", "#673AB7", "#3F51B5", "#2196F3", "#00BCD4", "#009688", "#4CAF50", "#8BC34A", "#FFEB3B", "#FFC107", "#FF9800", "#795548", "#9E9E9E", "#438755", "#687452", "#326519" ]);
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
                    .style("font-size", "16px")
                //.style("text-decoration", "underline")
                    .text(title + " - " + extraData);


            svg.append("text")
                    .attr("x", (width / 2))
                    .attr("y", 0 - 15)
                    .attr("text-anchor", "middle")
                    .style("font-size", "12px")
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
                    .attr("y", 965)
                    .attr("height", function (d) {
                        return height - y(d.y);
                    })
                    .attr("dx", -5)
                    .attr("dy", "1.2em")
                    .style("fill", "black")
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
                    .attr("dy", "40");


            d3.selectAll("input").on("change", change);



            var legend = svg.selectAll(".legend")
                    .data(cases.slice())
                    .enter().append("g")
                    .attr("class", "legend")
                    .attr("transform", function (d, i) {
                        return "translate(0," + i * 20 + ")";
                    });

            legend.append("rect")
                    .attr("x", width - 18)
                    .attr("width", 18)
                    .attr("height", 18)
                    .style("fill", function (d, i) {
                        return color(i);
                    });

            legend.append("text")
                    .attr("x", width + 5)
                    .attr("y", 9)
                    .attr("dy", ".35em")
                    .style("font-family", "sans-serif")
                    .style("font-size", "11px")
                    .text(function (d) {
                        return d.name;
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


</head>

<body>
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content col-xs-12 col-xs-offset-1">

    <form>
        <label><input type="hidden" name="mode" value="grouped"></label>
        <label><input type="hidden" name="mode" value="stacked" checked></label>
    </form>


    <h2 class="element-center">
        <i class="icon icon-file"></i>&nbsp;&nbsp;Reporte estad&iacute;stico por operador
    </h2>


    <br/>
    <br/>

    <div class="row-fluid center">
        <div class="chartBar"></div>
        <canvas width="1920" height="1080" style="display:none"></canvas>
    </div>

    <div class="row element-center">
        <a href="<c:url value='/supervisorManager/statisticReport/index.html' />">
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

