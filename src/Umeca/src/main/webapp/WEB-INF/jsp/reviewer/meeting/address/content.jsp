<div class="row">
    <div class="col-xs-2">
        <i class="icon-remove-sign red link-image" style="display: none;"  onclick="verification(this)"></i>
        <a class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-list-alt orange" style="display: none;"></i> </a>
            <ul class="dropdown-menu">
                <li>
                    <label for="r1"><input type="radio" name="radiocp" id="r1" onclick="checkSource()"> &nbsp;José Ramierez</label><br/>
                        <label>&nbsp;<i class="icon-remove red"> &nbsp;</i>asldjkfas fasdlfkj sdlkfj aslkdf</label>
                    <div class="hr hr-2"></div>
                </li>
                <li>
                    <label for="r2"><input type="radio" name="radiocp" id="r2" onclick="checkSource()"> &nbsp;Carlos Gómez</label><br/>
                    <label>&nbsp;<i class="icon-ok green"> &nbsp;</i>55569</label>
                    <div class="hr hr-2"></div>
                </li>
            </ul>
        </a>
        <label for="cp">Código  postal:</label>
    </div>
    <div class="col-xs-3">
        <input type="text" id="inputWarning" class="width-90" value="55569"/>
    </div>
    <div class="col-xs-2 col-xs-offset-1 element-right">
        Elije:
    </div>
    <div class="col-xs-3">
        <select class="form-control element-center" ng-model="colonia" ng-init="Colonia Obrera">
            <option value="Colonia Industrial">Colonia Industrial</option>
            <option value="Colonia Obrera">Colonia Obrera</option>
        </select>
    </div>
</div>
<br/>
<div class="row">
    <div class="col-xs-2 element-right">
        Estado:
    </div>
    <div class="col-xs-3">
        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
               data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido"
               type="text" value="" readonly="readonly">
    </div>
    <div class="col-xs-2 element-right col-xs-offset-1">
        Municipio:
    </div>
    <div class="col-xs-3">
        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
               data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido"
               type="text" value="" readonly="readonly">
    </div>
</div>
<br/>
<div class="row">
    <div class="col-xs-2 element-right">
        Colonia:
    </div>
    <div class="col-xs-3">
        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
               data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido"
               type="text" value="" ng-model="colonia" readonly="readonly">
    </div>
    <div class="col-xs-1 element-right">
        No Ext
    </div>
    <div class="col-xs-2">
        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
               data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido"
               type="text" value="">
    </div>
    <div class="col-xs-1 element-right">
        No Int
    </div>
    <div class="col-xs-2">
        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
               data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido"
               type="text" value="">
    </div>
</div>
<br/>
<div class="row">
    <div class="col-xs-2 element-right">
        Propiedad:
    </div>
    <div class="col-xs-3">
        <select class="form-control element-center">
            <option value="0">Si</option>
            <option value="1">No</option>
        </select>
    </div>
    <div class="col-xs-2 col-xs-offset-1 element-right">
        Tipo de domiclio:
    </div>
    <div class="col-xs-3">
        <select class="form-control element-center" ng-model="typeDomicile" ng-init="typeDomicile=0">
            <option value="0">Actual</option>
            <option value="1">Secundario</option>
            <option value="2">Anterior</option>
        </select>
    </div>
    <br/>
    <br/>
    <div class="row" ng-show="typeDomicile==2">
        <div class="col-xs-10 col-xs-offset-1">
            <div class="widget-box">
                <div class="widget-header">
                    <h4>Domicilio Anterior</h4>
                </div>
                <div class="widget-body">
                    <br/>
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="row">
                                <div class="col-xs-5 element-left">
                                    Tiempo de residencia:
                                </div>
                                <div class="col-xs-7">
                                    <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
                                           data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido" id="name"
                                           type="text" value="">
                                </div>
                            </div>
                            <br/>
                            <div class="row element-left">
                                <div class="col-xs-3">
                                    Motivo de la mudanza:
                                </div>
                                <div class="col-xs-9">
                                    <textarea id="form-field-11" class="form-control"></textarea>
                                </div>
                            </div>
                            <br/>
                            <div class="row">
                                <div class="hr hr-8"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row" ng-show="typeDomicile==0 || typeDomicile==1">
        <div class="col-xs-10 col-xs-offset-1">
            <div class="widget-box">
                <div class="widget-header">
                    <h4>Domicilio actual/secundario</h4>
                </div>

                <div class="widget-body">
                    <br/>
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="row">
                                <div class="col-xs-5">
                                    Tiempo de vivir en el domicilio:
                                </div>
                                <div class="col-xs-7">
                                    <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
                                           data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido" id="name"
                                           type="text" value="">
                                </div>
                            </div>
                            <br/>
                            <div class="row">
                                <div class="col-xs-3">
                                    Descripción:
                                </div>
                                <div class="col-xs-9">
                                    <textarea id="form-field-11" class="form-control"></textarea>
                                </div>
                            </div>
                            <br/>
                            <div class="row schedule_visible">
                                <div class="widget-box">
                                    <div class="widget-header">
                                        <h5><i class="glyphicon glyphicon-calendar "></i>Disponibilidad</h5>
                                    </div>

                                    <div class="widget-body">
                                        <br/>
                                        <%@ include file="/WEB-INF/jsp/reviewer/meeting/shared/schedule.jsp"%>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="hr hr-8"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>