<div id="notifications-tab" class="tab-pane">
    <div class="clearfix">
        <div class="comments">
            <div ng-repeat="n in lstNotification">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="itemdiv commentdiv">
                            <div class="user">
                                <img class="nav-user-photo" src="<c:url value='/assets/avatars/avatar0.png' />"
                                     alt="{{n.senderUser}}"/>
                            </div>

                            <div class="body">
                                <div class="row">
                                    <div class="col-xs-10">
                                        <label class="inline">
                                            <i class="glyphicon glyphicon-paperclip green"></i> &nbsp;&nbsp;
                                            <strong><span class="lbl" ng-bind-html="formatHtml(n.title)"><br/>
                                    </span></strong>
                                        </label>
                                    </div>
                                    <div class="col-xs-2 element-right">
                                        <i class="icon-time"></i>
                                        <span class="lbl blue">{{n.dateNotif.dayOfMonth}}/{{n.dateNotif.month + 1}}/{{n.dateNotif.year}}</span>
                                    </div>
                                </div>

                                <div class="row">
                                    <p ng-bind-html="formatHtml(n.message)"></p>
                                </div>

                            </div>
                            <div class="tools">
                                <div class="action-buttons bigger-125" ng-if="n.urlToDelete&&n.urlToDelete!=''">
                                    <a href="#" ng-click="deleteNotif(n.id, n.urlToDelete);">
                                        <i class="icon-trash red" title="Eliminar notificaci&oacute;n"></i>
                                    </a>
                                </div>

                                <div class="action-buttons bigger-125" ng-if="n.urlToDelete==undefined">
                                    <a href="#" ng-click="deleteNotif(n.id, '<c:url value='${urlToGo}' />');">
                                        <i class="icon-trash red" title="Eliminar notificaci&oacute;n"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div ng-show="{{lstNotification.length==0}}">
                <ul class="item-list">
                    <li class="item-green clearfix">
                        No existen nuevas notificaciones.
                    </li>
                </ul>
            </div>
        </div>

        <div class="hr hr-double hr8"></div>
    </div>
</div>

