<div id="tasks-tab" class="tab-pane active">
    <h4 class="smaller lighter green" ng-show="lstActivities.length > 0">
        <i class="icon-list"></i>
        Actividades por realizar
    </h4>

    <ul id="tasksToday" class="item-list">
        <li class="item-orange clearfix" ng-repeat="a in lstActivities">
            <div class="col-xs-11">

                <div class="row">
                    <div class="col-xs-9">
                        <label class="inline">
                            <i class="glyphicon glyphicon-paperclip green"></i> &nbsp;&nbsp;
                            <strong><span class="lbl" ng-bind-html="formatHtml(a.title)"><br/>
                                    </span></strong>
                        </label>
                    </div>
                    <div class="col-xs-1 col-xs-offset-1">
                        {{myFormatDate(a.orderDate)}}
                    </div>
                </div>

                <div class="row">
                    <%--<p>{{a.message}}</p>--%>
                    <p ng-bind-html="formatHtml(a.message)"></p>
                </div>
            </div>
        </li>
        <li class="item-green clearfix" ng-show="{{lstActivities.length == 0}}">
            No existen actividades pendientes.
        </li>
    </ul>
</div>