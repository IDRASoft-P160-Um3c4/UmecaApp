package com.umeca.controller.supervisor;

import com.umeca.infrastructure.security.SecureString;
import com.umeca.model.shared.Constants;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.hibernate.ejb.HibernateEntityManagerFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

@Controller
public class SharedController {

    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    private SharedUserService userService;
    @PersistenceContext(unitName = "punit")
    private javax.persistence.EntityManager entityManager;

    /*
        *url|user|pass|id_user|id_imputed|type{0 - enrollment|1 - verify}
        key: 1234567890123481
        "jdbc:mariadb://192.168.11.135:3306/sgumeca|root|root|4|1|0"
        * */


    @RequestMapping(value = "/supervisor/shared/enrollment", method = RequestMethod.GET, produces = "application/x-java-jnlp-file")
    public @ResponseBody String enrollment(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response){

        try{
            StringBuffer url = request.getRequestURL();
            String uri = request.getRequestURI();
            String ctx = request.getContextPath();
            String urlBase = url.substring(0, url.length() - uri.length() + ctx.length()) + "/assets/app/";
            Properties prop = ((SessionFactoryImpl) ((HibernateEntityManagerFactory) entityManager.getEntityManagerFactory()).getSessionFactory()).getProperties();
            DriverManagerDataSource dmds= (DriverManagerDataSource) prop.get("hibernate.connection.datasource");
            String jUrl = dmds.getUrl();
            String username = dmds.getUsername();
            String password = dmds.getPassword();
            StringBuilder lineArgument = new StringBuilder();
            lineArgument.append(jUrl);
            lineArgument.append("|");
            lineArgument.append(username);
            lineArgument.append("|");
            lineArgument.append(password);
            lineArgument.append("|");
            lineArgument.append(userService.GetLoggedUserId());
            lineArgument.append("|");
            lineArgument.append("1");
            lineArgument.append("|");
            lineArgument.append("0"); //Enrollment

            String encLineArgument = SecureString.encrypt(Constants.KEY_CYPHER_APP, lineArgument.toString());


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/x-java-jnlp-file"));
            response.setContentType("application/x-java-jnlp-file");
            return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<jnlp spec=\"1.0\" codebase=\"" + urlBase + "\">\n" +
                    "<information>\n" +
                    "    <title>Enrolamiento</title>\n" +
                    "    <vendor>IDRASoft</vendor>\n" +
                    "    <description>Enrolamiento</description></information>\n" +
                    "<resources>\n" +
                    "    <property name=\"jnlp.publish-url\" value=\"" + urlBase + "\"/>\n" +
                    "    <j2se version=\"1.7+\" />\n" +
                    "    <jar href=\"sensor.jar\" />\n" +
                    "</resources>\n" +
                    "<application-desc main-class=\"com.idrasoft.onetouch.MainForm\">\n" +
                    "<argument>" + encLineArgument + "</argument>\n" +
                    "</application-desc>\n" +
                    "<security>\n" +
                    "    <all-permissions/>\n" +
                    "</security>\n" +
                    "</jnlp>\n";
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "enrollment", userService);
            return null;
        }
    }


}
