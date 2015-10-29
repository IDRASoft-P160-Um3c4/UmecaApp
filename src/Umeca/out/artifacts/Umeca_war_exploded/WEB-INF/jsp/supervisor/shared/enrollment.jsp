<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<?xml version="1.0" encoding="utf-8"?>
<jnlp spec="1.0+" codebase="http://localhost:8080/Umeca/assets/app/" href="sensor.jnlp">
    <information>
        <title>Enrolamiento</title>
        <vendor>IDRASoft</vendor>
        <homepage href="http://localhost:8080/Umeca/assets/app/" />
        <description>Enrolamiento</description>
    </information>
    <security>
        <all-permissions/>
    </security>
    <resources>
        <j2se version="1.7+" />
        <jar href="sensor.jar" />
    </resources>
    <application-desc main-class="com.idrasoft.onetouch.MainForm" />
</jnlp>