<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<div class="row">
    <div class="col-xs-10 element-center col-xs-offset-1">
        <h2><i class="blue icon-globe bigger-100"></i> &nbsp;Facilidad de abandonar el país</h2>
        <br/>
        <div class="row">
                <div class="col-xs-12">
                    <div class="col-xs-9 element-left">
                        ¿El detenido cuenta con documentación oficial que facilite que abandone el país?:
                    </div>
                    <div class="col-xs-3">
                        <select class="form-control element-center">
                            <option value="0">Si</option>
                            <option value="1">No</option>
                        </select>
                    </div>
                </div>
        </div>
        <br/>
        <div class="row">
            <div class="col-xs-12">
              <div class="col-xs-9 element-left">
                  ¿El detenido ha vivido en otro país?:
              </div>
              <div class="col-xs-3">
                  <select class="form-control element-center" ng-model="p1" ng-init="p1=1">
                      <option value="0">Si</option>
                      <option value="1">No</option>
                  </select>
              </div>
            </div>
        </div>
        <div class="row" ng-show="p1==0">
            <br/>
            <div class="col-xs-12">
                <div class="col-xs-3 element-left">
                    ¿Hace cuanto tiempo?:
                </div>
                <div class="col-xs-3">
                    <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
                           data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido"
                           type="text" value="">
                </div>
                <div class="col-xs-2">
                    Motivo
                </div>
                <div class="col-xs-4">
                    <textarea id="form-field-11" class="form-control"></textarea>
                </div>
            </div>
        </div>
        <div class="row">
            <br/>
            <div class="col-xs-12">
                <div class="col-xs-9 element-left">
                    ¿El detenido cuenta con familiares y/o amigos cercanos en otro país?:
                </div>
                <div class="col-xs-3">
                    <select class="form-control element-center" ng-model="p2" ng-init="p2=1">
                        <option value="0">Si</option>
                        <option value="1">No</option>
                    </select>
                </div>
            </div>
        </div>
        <br/>
        <div class="row" ng-show="p2==0">
            <div class="col-xs-12">
            <div class="col-xs-9 element-left" ng-show="p2==0">
                ¿Mantiene comunicación con ellos?:
            </div>
            <div class="col-xs-3" ng-show="p2==0">
                <select class="form-control element-center">
                    <option value="0">Si</option>
                    <option value="1">No</option>
                </select>
            </div>
            </div>
        </div>
    </div>
</div>