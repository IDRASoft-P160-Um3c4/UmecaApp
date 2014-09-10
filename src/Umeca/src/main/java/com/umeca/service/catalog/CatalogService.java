package com.umeca.service.catalog;

import com.umeca.model.ResponseMessage;
import com.umeca.model.ResponseMessageAddress;
import com.umeca.model.catalog.Location;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 5/06/14
 * Time: 05:14 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CatalogService {
    ResponseMessageAddress findLocationByZipCode(String zipCode);
    Location findLocationById(Long idLocation);
    ResponseMessageAddress getStatesByCountry(Long countryId);
    ResponseMessageAddress findMunicipalityByIdState(Long idState);
    ResponseMessageAddress findLocationByMunId(Long idMun);

    ResponseMessage findIdsLocationById(Long id);
}
