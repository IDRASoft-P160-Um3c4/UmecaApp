package com.umeca.controller.catalogs;

import com.umeca.model.ResponseMessageLocations;
import com.umeca.service.catalog.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 5/06/14
 * Time: 05:10 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class CatalogController {

    @Autowired
    CatalogService catalogService;

    @RequestMapping(value = "/catalogs/locationsByZipCode", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessageLocations upsertPersonalData(@RequestParam String zipCode){
        return catalogService.findLocationByZipCode(zipCode);
    }

}
