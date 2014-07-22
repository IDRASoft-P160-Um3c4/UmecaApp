<div class="container body-content">
<div class="page-content">


<div class="row">
<div class="space-20"></div>
<div class="col-xs-8 col-xs-offset-1">
<div class="widget-box transparent" id="recent-box">
<div class="widget-header">
    <h4 class="lighter smaller">
        <i class="icon-rss orange"></i>
        Pizarra de información (Top 10)
    </h4>

    <div class="widget-toolbar no-border" ng-init='lstActivities = ${lstActivities}; lstNotification = ${caca};'>
        <ul class="nav nav-tabs" id="recent-tab">
            <li class="active">
                <a data-toggle="tab" href="#tasks-tab">Actividades ${act}</a>
            </li>

            <li>
                <a data-toggle="tab" href="#notifications-tab">Notificaciones
                    &nbsp;&nbsp;<span ng-show="{{lstNotification.length>0}}" class="badge badge-danger">{{lstNotification.length}}</span>
                </a>
            </li>
        </ul>
    </div>
</div>



<div class="widget-body">
<div class="widget-main padding-4">
<div class="tab-content padding-8 overflow-visible">

<%@ include file="/WEB-INF/jsp/reviewer/msgActivities.jsp" %>
<%@ include file="/WEB-INF/jsp/reviewer/msgNotifications.jsp" %>


</div>
</div>
</div>
<!-- /widget-main -->
</div>
<!-- /widget-body -->
</div>
<!-- /widget-box -->
</div>
</div>
</div>
</div>
</div>
<!-- /span -->