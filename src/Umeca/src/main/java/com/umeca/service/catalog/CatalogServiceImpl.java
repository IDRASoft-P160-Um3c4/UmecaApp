package com.umeca.service.catalog;

import com.google.gson.Gson;
import com.umeca.model.ResponseMessage;
import com.umeca.model.ResponseMessageAddress;
import com.umeca.model.catalog.Location;
import com.umeca.model.catalog.Municipality;
import com.umeca.model.catalog.State;
import com.umeca.model.catalog.dto.LocationDto;
import com.umeca.model.catalog.dto.MunicipalityDto;
import com.umeca.repository.catalog.CountryRepository;
import com.umeca.repository.catalog.LocationRepository;
import com.umeca.repository.catalog.MunicipalityRepository;
import com.umeca.repository.catalog.StateRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    SharedLogExceptionService logException;

    @Autowired
    SharedUserService sharedUserService;

    @Autowired
    LocationRepository locationRepository;
    @Autowired
    StateRepository stateRepository;
    @Autowired
    MunicipalityRepository municipalityRepository;
    @Autowired
    CountryRepository countryRepository;

    @Override
    public ResponseMessageAddress findLocationByZipCode(String zipCode) {
        ResponseMessageAddress result = new ResponseMessageAddress();
        try{
            Gson gson = new Gson();
            List<Location> locationList = locationRepository.findLocationByZipCode(zipCode);
            List<LocationDto> locationDtoList  = new ArrayList<>();
            for(Location location : locationList){
                locationDtoList.add(new LocationDto().locationDto(location));
            }
            result.setHasError(false);
            result.setMessage("Se ha buscado la información con éxito");
            result.setData(gson.toJson(locationDtoList));
        }catch (Exception e){
            logException.Write(e,this.getClass(),"findLocationByZipCode",sharedUserService);
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
            List<State> states = stateRepository.getStatesByCountry(countryId);
            result.setHasError(false);
            result.setMessage("se ha buscado la información con éxito");
            result.setData(gson.toJson(states));
        }catch (Exception e){
            logException.Write(e,this.getClass(),"getStatesByCountry",sharedUserService);
            result.setHasError(true);
            result.setMessage("Ha ocurrido un error al obtener la información");
        }
        return result;
    }


    @Override
    public ResponseMessageAddress findMunicipalityByIdState(Long idState) {
        ResponseMessageAddress result = new ResponseMessageAddress();
        try {
            Gson gson = new Gson();
            List<Municipality> municipalityList =municipalityRepository.findByIdState(idState);
            List<MunicipalityDto> municipalityDtoList = new ArrayList<>();
            for(Municipality municipality : municipalityList){
                municipalityDtoList.add(new MunicipalityDto().munDto(municipality));
            }
            result.setData(gson.toJson(municipalityDtoList));
            result.setHasError(false);
            result.setMessage("Busqueda exitosa");
        }catch (Exception e){
            logException.Write(e,this.getClass(),"findMunicipalityByIdState",sharedUserService);
            result.setHasError(true);
           result.setMessage(e.getMessage());
        }finally {
            return result;
        }
    }

    @Override
    public ResponseMessageAddress findLocationByMunId(Long idMun) {
        ResponseMessageAddress result = new ResponseMessageAddress();
        try {
            Gson gson = new Gson();
            List<Location> locationList =locationRepository.findLocationByMunId(idMun);
            List<LocationDto> locationDtoList = new ArrayList<>();
            for(Location location: locationList){
                locationDtoList.add(new LocationDto().locationDto(location));
            }
            result.setData(gson.toJson(locationDtoList));
            result.setHasError(false);
            result.setMessage("Busqueda exitosa");
        }catch (Exception e){
            logException.Write(e,this.getClass(),"findLocationByMunId",sharedUserService);
            result.setHasError(true);
            result.setMessage(e.getMessage());
        }finally {
            return result;
        }
    }

    @Override
    public ResponseMessage findIdsLocationById(Long id) {
        try{
        Location l = locationRepository.findOne(id);
        LocationDto locDto = new LocationDto().locationDto(l);
        Gson gson = new Gson();
        return new ResponseMessage(false, gson.toJson(locDto));
        }catch (Exception e){
            return new ResponseMessage(true, "Ha ocurrido un error al obtener los datos.");
        }
    }

}
