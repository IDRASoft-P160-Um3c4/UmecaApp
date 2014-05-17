<div class="row">
    <div class="col-xs-2">
        Sustancia:
    </div>
    <div class="col-xs-4">
        <select class="form-control element-center" ng-model="typeDrug" ng-init="typeDrug=0">
            <option value="0">Alcohol</option>
            <option value="1">Solventes</option>
            <option value="2">Marihuana</option>
            <option value="3">Cocaina</option>
            <option value="4">Pastillas</option>
            <option value="5">Otras</option>
        </select>
        <div ng-show="typeDrug==5">
            <br/>
            Especifique:&nbsp;    <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
                                         data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido"
                                         type="text" value="" ng-model="m.name">
        </div>
    </div>
    <div class="col-xs-2">
        Periodicidad:
    </div>
    <div class="col-xs-4">
        <select class="form-control element-center">
            <option value="0">1-2 Semanal</option>
            <option value="1">2-3 Semanal</option>
            <option value="1">4-5 Semanal</option>
            <option value="1">6+ Semanal</option>
            <option value="1">1-2 Quincenal</option>
            <option value="1">1-25 Mensual</option>
        </select>
    </div>
</div>
<br/>
<div class="row">
    <div class="col-xs-2">
        Cantidad:
    </div>
    <div class="col-xs-4">
        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
               data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido"
               type="text" value="" ng-model="m.cant">
    </div>
    <div class="col-xs-2">
        Último consumo:
    </div>
    <div class="col-xs-4">
        <div class="input-group">
            <input class="form-control date-picker" id="dpEndBefore" type="text" data-date-format="dd-mm-yyyy" />
																	<span class="input-group-addon">
																		<i class="icon-calendar bigger-110"></i>
																	</span>
        </div>
    </div>
</div>