
<div class="row element-center">
    <div class="col-xs-8 col-xs-offset-2">
        <div class="row">
    <div class="col-xs-4 element-center">
       Día<br/>
        <select class="form-control element-center">
            <option value="0">Lunes</option>
            <option value="1">Martes</option>
            <option value="2">Miércoles</option>
            <option value="3">Jueves</option>
            <option value="4">Viernes</option>
            <option value="5">Sábado</option>
            <option value="6">Domingo</option>
        </select>
    </div>
    <div class="col-xs-3 element-center">
                    Inicio<br/>
        <div class="input-group bootstrap-timepicker">
            <input id="timepickerStart" type="text" class="form-control" />
            <span class="input-group-addon">
				<i class="icon-time bigger-60"></i>
			</span>
        </div>
    </div>
    <div class="col-xs-3 element-center">
        Fin <br/>
        <div class="input-group bootstrap-timepicker">
            <input id="timepickerEnd" type="text" class="form-control" />
            <span class="input-group-addon">
				<i class="icon-time  bigger-40"></i>
			</span>
        </div>
    </div>
    <div class="col-xs-2">
        Acciones <br/><div class="space-5"></div>
        <i class="icon-plus-sign orange"></i>
    </div>
    </div>
        <div class="row">
        <div class="hr hr-6"></div>
        </div>
        <div class="col-xs-9 col-xs-offset-3">
         <div class="row center">
             <div class="col-xs-2">
                 <h5 class="smaller lighter blue">Día</h5>
                 <div class="hr hr-2"></div>
             </div>
             <div class="col-xs-2">
                 <h5 class="smaller lighter blue">Inicio</h5>
                 <div class="hr hr-2"></div>
             </div>
             <div class="col-xs-2">
                 <h5 class="smaller lighter blue">Fin</h5>
                 <div class="hr hr-2"></div>
             </div>
             <div class="col-xs-3">
                 <h5 class="smaller lighter blue">Acciones</h5>
                 <div class="hr hr-2"></div>
             </div>
         </div>
            <div class="row center">
                <div class="col-xs-2">
                    Lunes
                </div>
                <div class="col-xs-2">
                    12:00
                </div>
                <div class="col-xs-2">
                    14:00
                </div>
                <div class="col-xs-3">
                    <a class="red" href="#">
                        <i class="icon-trash bigger-130"></i>
                    </a>
                </div>
            </div>
            <div class="row center">
                <div class="col-xs-2">
                    Martes
                </div>
                <div class="col-xs-2">
                    10:00
                </div>
                <div class="col-xs-2">
                    13:00
                </div>
                <div class="col-xs-3">
                    <a class="red" href="#">
                        <i class="icon-trash bigger-130"></i>
                    </a>
                </div>
            </div>
            </div>
    </div>
    <br/>
</div>
<script>
    $('#timepickerStart').timepicker({
        minuteStep: 1,
        showSeconds: false,
        showMeridian: false
    }).next().on(ace.click_event, function(){
                $(this).prev().focus();
            });$('#timepickerStart').timepicker({
        minuteStep: 1,
        showSeconds: true,
        showMeridian: false
    }).next().on(ace.click_event, function(){
                $(this).prev().focus();
            });

    $('#timepickerEnd').timepicker({
        minuteStep: 1,
        showSeconds: false,
        showMeridian: false
    }).next().on(ace.click_event, function(){
                $(this).prev().focus();
            });$('#timepickerEnd').timepicker({
        minuteStep: 1,
        showSeconds: true,
        showMeridian: false
    }).next().on(ace.click_event, function(){
                $(this).prev().focus();
            });
</script>