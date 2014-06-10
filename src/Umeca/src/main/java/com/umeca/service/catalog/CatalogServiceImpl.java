package com.umeca.service.catalog;

import com.google.gson.Gson;
import com.umeca.model.ResponseMessageAddress;
import com.umeca.model.catalog.Location;
import com.umeca.model.catalog.State;
import com.umeca.repository.catalog.LocationRepository;
import com.umeca.repository.catalog.StateRepository;
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
    @Autowired
    StateRepository stateRepository;

    @Override
    public ResponseMessageAddress findLocationByZipCode(String zipCode) {
        ResponseMessageAddress result = new ResponseMessageAddress();
        try{
            Gson gson = new Gson();
            List<Location> location = locationRepository.findLocationByZipCode(zipCode);
            result.setHasError(false);
            result.setMessage("Se ha buscado la información con éxito");
            result.setData(gson.toJson(location));
        }catch (Exception e){
            result.setHasError(true);
            result.setMessage("Se ha producido un error en la búsqueda del código postal");
        }
        return result;
    }

    @Override
    public Location findLocationById(Long idLocation){
        return locationRepository.findOne(idLocation);
    }

    @Override
    public ResponseMessageAddress getStatesByCountry(Long countryId) {
        ResponseMessageAddress result = new ResponseMessageAddress();
        try{
            Gson gson = new Gson();
            List<State> states = stateRepository .getStatesByCountry(countryId);
            result.setHasError(false);
            result.setMessage("se ha buscado la información con éxito");
            result.setData(gson.toJson(states));
        }catch (Exception e){
            result.setHasError(true);
            result.setMessage("Ha ocurrido un error al obtener la información");
        }
        return result;
    }
}
