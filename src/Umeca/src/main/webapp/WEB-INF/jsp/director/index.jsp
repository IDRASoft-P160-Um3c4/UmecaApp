<div class="page-content">
    <div class="container body-content">

        <div class="row">
            <div class="space-20"></div>
            <div class="col-xs-8 col-xs-offset-1">
                <div class="widget-box transparent" id="recent-box">
                    <div class="widget-header">
                        <h4 class="lighter smaller">
                            <i class="icon-rss orange"></i>
                            Pizarra de informaci&oacute;n (Top 10)
                        </h4>

                        <div class="widget-toolbar no-border" ng-init='lstNotification = ${lstNotification};'>
                            <ul class="nav nav-tabs" id="recent-tab">
                                <li class="active">
                                    <a data-toggle="tab" href="#tasks-tab">Actividades</a>
                                </li>
                                <li>
                                    <a data-toggle="tab" href="#notifications-tab">Notificaciones
                                        &nbsp;&nbsp; <span class="badge badge-danger" ng-show="lstNotification.length > 0">
                                            {{lstNotification.length}}
                                        </span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>


                    <div class="widget-body">
                        <div class="widget-main padding-4">
                            <div class="tab-content padding-8 overflow-visible">
                                <%@ include file="/WEB-INF/jsp/director/msgActivities.jsp" %>
                                <%@ include file="/WEB-INF/jsp/director/msgNotifications.jsp" %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>