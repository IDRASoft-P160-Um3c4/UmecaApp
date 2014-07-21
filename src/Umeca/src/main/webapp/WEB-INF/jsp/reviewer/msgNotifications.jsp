<div id="notifications-tab" class="tab-pane">
    <div class="clearfix">
        <div class="comments">
            <div class="itemdiv commentdiv" ng-repeat="n in lstNotification">
                <div class="user">
                    <img class="nav-user-photo" src="<c:url value='/assets/avatars/avatar0.png' />"
                         alt="{{n.senderUser}}"/>
                </div>

                <div class="body">
                    <div class="col-xs-11">

                        <div class="row">
                            <div class="col-xs-9">
                                <label class="inline">
                                    <i class="glyphicon glyphicon-paperclip green"></i> &nbsp;&nbsp;
                                <span class="lbl"><strong>{{n.title}}</strong><br/>
                                    </span>
                                </label>
                            </div>
                            <div class="col-xs-1 col-xs-offset-1">
                                {{myFormatDate(n.orderDate)}}
                            </div>
                        </div>

                        <div class="row">
                            <span>{{n.message}}</span>
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

