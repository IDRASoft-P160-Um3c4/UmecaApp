<div id="notifications-tab" class="tab-pane">
    <div class="clearfix">
        <div class="comments">
            <div class="itemdiv commentdiv" ng-repeat="n in lstNotification">
                <div class="user">
                    <img class="nav-user-photo" src="<c:url value='/assets/avatars/avatar0.png' />" alt="{{n.senderUser}}" />
                </div>

                <div class="body">
                    <div class="name">
                        <span class="blue">{{n.senderUser}}</span>
                    </div>

                    <div class="time">
                        <i class="icon-time"></i>
                        <span class="blue">{{n.timestamp}}</span>
                    </div>

                    <div class="text">
                        <i class="icon-quote-left"></i>
                        <span class="lbl"><strong>{{n.typeName}}</strong></span>
                            <span ng-class="(n.action === 'RECHAZADO AUTORIZAR' || n.action === 'RECHAZADO REPORTE INCUMPLIMIENTO' ||  n.action === 'RECHAZADO TERMINAR' ? 'red'
                                : (n.action === 'SOLICITUD AUTORIZAR REPORTE INCUMPLIMIENTO' || n.action === 'EN PROCESO DE TERMINAR' ? 'color-warning' : 'green'))">&nbsp;({{n.action}})</span><br/>
                            <span>
                            Carpeta Judicial: <strong>{{n.mpId}}</strong> Imputado: <strong>{{n.personName}}</strong> <br/></span>
                        <div class="font-size-sm">
                            <div ng-bind-html="formatHtml(n.comments)"></div>
                        </div>
                    </div>
                </div>

                <div class="tools">
                    <div class="action-buttons bigger-125">
                        <a href="#" ng-click="deleteMsg(n.id, '<c:url value='${urlToGo}' />');">
                            <i class="icon-trash red" title="Eliminar mensaje"></i>
                        </a>
                    </div>
                </div>
            </div>

            <div ng-repeat="nA in lstNotificationA">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="itemdiv commentdiv">
                            <div class="user">
                                <img class="nav-user-photo" src="<c:url value='/assets/avatars/avatar0.png' />"
                                     alt="{{nA.senderUser}}"/>
                            </div>
                            <div class="body">
                                <div class="row">
                                    <div class="col-xs-10">
                                        <label class="inline">
                                            <span class="lbl blue">{{nA.senderUser}}</span>&nbsp;&nbsp;
                                            <strong><span class="lbl" ng-bind-html="formatHtml(nA.title)"></span></strong>
                                        </label>
                                    </div>
                                    <div class="col-xs-2 element-right">
                                        <i class="icon-time"></i>
                                        <span class="lbl blue">{{nA.dateNotifTx}}</span>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-10">
                                        <span class="lbl" ng-bind-html="formatHtml(nA.message)"></span>
                                    </div>
                                </div>

                            </div>
                            <div class="tools">
                                <div class="action-buttons bigger-125">
                                    <a href="#" ng-click="deleteNotif(nA.id, '<c:url value='${urlToGoA}' />',true);">
                                        <i class="icon-trash red" title="Eliminar notificaci&oacute;n"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div ng-show="lstNotification.length==0&&lstNotificationA.length==0">
                <ul class="item-list">
                    <li class="item-green clearfix">
                        No existen nuevas notificaciones.
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

