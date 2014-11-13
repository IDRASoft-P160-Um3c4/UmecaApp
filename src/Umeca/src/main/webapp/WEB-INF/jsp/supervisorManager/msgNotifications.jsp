<div id="notifications-tab" class="tab-pane active" ng-init ='lstNotification = ${lstNotification}; '>
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

                    <div class="text" ng-show="n.action!= 'INFORMATION'">
                        <i class="icon-quote-left"></i>
                        <span class="lbl"><strong>{{n.typeName}}</strong></span>
                            <span ng-class="(n.action === 'RECHAZADO AUTORIZAR' || n.action === 'RECHAZADO REPORTE INCUMPLIMIENTO' ||  n.action === 'RECHAZADO TERMINAR' ? 'red'
                                : (n.action === 'SOLICITUD AUTORIZAR REPORTE INCUMPLIMIENTO' || n.action === 'EN PROCESO DE TERMINAR' ? 'color-warning' : 'green'))">&nbsp;({{n.action}})</span><br/>
                            <span>
                            Caso {{n.caseId}} (<strong>{{n.mpId}}</strong>) Imputado: <strong>{{n.personName}}</strong> <br/></span>
                        <div class="font-size-sm">
                            <span style="display: inline; word-wrap: break-word;">{{n.comments}}</span>
                        </div>
                    </div>
                    <div class="text" ng-show="n.action == 'INFORMATION'">
                        <i class="icon-quote-left"></i>
                        <strong><span class="lbl" ng-bind-html="formatHtml(n.type)"></span></strong>
                            <span ng-class="'green'">&nbsp;(INFORMATIVO)</span><br/>
                        <div class="font-size-sm">
                            <span style="display: inline; word-wrap: break-word;"><p ng-bind-html="formatHtml(n.comments)"></p></span>
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

