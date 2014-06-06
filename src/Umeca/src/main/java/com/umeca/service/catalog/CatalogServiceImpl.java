package com.umeca.service.catalog;

import com.google.gson.Gson;
import com.umeca.model.ResponseMessageLocations;
import com.umeca.model.catalog.Location;
import com.umeca.repository.catalog.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 5/06/14
 * Time: 05:14 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("catalogService")
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    LocationRepository locationRepository;

    @Override
    public ResponseMessageLocations findLocationByZipCode(String zipCode) {
        ResponseMessageLocations result = new ResponseMessageLocations();
        try{
            Gson gson = new Gson();
            List<Location> location = locationRepository.findLocationByZipCode(zipCode);
            result.setHasError(false);
            result.setMessage("Se ha buscado la información con éxito");
            result.setLocations(gson.toJson(location));
        }catch (Exception e){
            result.setHasError(true);
            result.setMessage("Se ha producido un error en la búsqueda del código postal");
        }
        return result;
    }
}
