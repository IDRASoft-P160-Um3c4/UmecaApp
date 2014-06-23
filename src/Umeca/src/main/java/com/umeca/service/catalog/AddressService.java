package com.umeca.service.catalog;

import com.umeca.model.ResponseMessageAddress;
import com.umeca.model.catalog.Location;
import com.umeca.model.entities.reviewer.Address;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 5/06/14
 * Time: 05:14 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AddressService {
   List<String> validateAddress(Address model);

    void fillCatalogAddress(ModelAndView model);

    void fillModelAddress(ModelAndView model, Long addressId);
}
