<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
    <style>
        .labelInfo{
            color: whitesmoke;
            text-align: right;
            background-color: #428BCA;
            min-height: 40px;
            border-radius: 20px;
        }
        .detailsShow{
            width:100%;
            border-width:1px;
            border-color: #428BCA;
            border-style:none none solid none;
            text-align: center;
            color: #003956;
        }
        h1{
            color: #428BCA;
            font-family: "Verdana";
            text-align: center;
        }
        h4{
            color: #003956;
            font-family: "Verdana";
        }
        table{
            font-family: "Verdana";
            font-size: x-small;
        }

        table th{
            color: whitesmoke;
            text-align: center;
            background-color: #428BCA;
            min-height: 40px;
            border-radius: 20px;
        }
        .fieldInfo{
            border-bottom: 1px solid #428BCA;
        }
        td{
            padding: 5px 5px 5px 5px;
        }
    </style>
</head>
<body style=" text-align: center !important;">
<h1>Bit&aacute;cora del caso</h1>
<h4>DATOS DEL CASO</h4>
<%@ include file="/WEB-INF/jsp/shared/logCase/generalDataCaseFile.jsp"%>
<h4 >Listado de actividades</h4>
<table border="1">
    <tr class="labelInfo">
       <th>
         Fecha y hora
       </th>
        <th>
           Actividad
        </th>
        <th>
            Realizado por
        </th>
        <th>
            Detalles
        </th>
    </tr>
<c:forEach var="act" items="${activities}">
       <tr>
           <td style="text-align: center;">
                       ${act.dateString}


           </td style="text-align: center;">
           <td>

                       ${act.activity}


           </td>
           <td style="text-align: center;">

                       ${act.userName}


           </td>
           <td style="text-align: left;">
               <div class="detailsShow">
                   <strong>${act.title}</strong>

               </div>                           <br/>
                   ${act.resume}

           </td>
       </tr>
    </c:forEach>
</table>




</body>
</html>
