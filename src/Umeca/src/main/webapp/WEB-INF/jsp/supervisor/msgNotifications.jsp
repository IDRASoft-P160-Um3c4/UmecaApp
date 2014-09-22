<div id="notifications-tab" class="tab-pane" ng-init ='lstNotification = ${lstNotification}; '>
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
                            Caso {{n.caseId}} (<strong>{{n.mpId}}</strong>) Imputado: <strong>{{n.personName}}</strong> <br/></span>
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
        </div>

        <div class="hr hr-double hr8"></div>
    </div>
</div>

