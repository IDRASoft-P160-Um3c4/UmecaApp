package com.umeca.service.catalog;

import com.google.gson.Gson;
import com.umeca.model.catalog.State;
import com.umeca.model.catalog.dto.AddressDto;
import com.umeca.model.catalog.dto.StateDto;
import com.umeca.model.entities.reviewer.Address;
import com.umeca.model.shared.Constants;
import com.umeca.repository.catalog.StateRepository;
import com.umeca.repository.reviewer.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 5/06/14
 * Time: 05:14 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("addressService")
public class AddressServiceImpl implements AddressService {

    @Autowired
    StateRepository stateRepository;
    @Autowired
    AddressRepository addressRepository;

    @Override
    public List<String> validateAddress(Address model) {
        List<String> msgList= new ArrayList<>();
        if(model==null){
            msgList.add("Los datos del domicilio son requeridos");
            return msgList;
        }
        if(model.getOutNum()==null){
            msgList.add("El número exterior del domicilio es requerido");
        }else if(model.getOutNum().equals("")){
            msgList.add("El número exterior del domicilio es requerido");
        }
        if(model.getStreet()==null){
            msgList.add("La calle del domicilio es requerido");
        }else if(model.getStreet().equals("")){
            msgList.add("La calle del domicilio es requerido");
        }
        if(model.getLocation()== null){
            msgList.add("La localidad del domicilio es requerida");
        }else if(model.getLocation().getId()== null){
            msgList.add("La localidad del domicilio es requerida");
        }

        return msgList;
    }
    @Override
    public void fillCatalogAddress(ModelAndView model) {
        Gson gson = new Gson();
        List<State> listState = stateRepository.findStatesByCountryAlpha2(Constants.ALPHA2_MEXICO);
        List<StateDto> stateDtoList =new ArrayList<>();
        for(State state : listState){
            stateDtoList.add(new StateDto().stateDto(state));
        }
        model.addObject("listState", gson.toJson(stateDtoList));
    }

    @Override
    public void fillModelAddress(ModelAndView model, Long addressId) {
        Gson gson = new Gson();
        Address a = addressRepository.findOne(addressId);
        if(a!=null){
            model.addObject("address", gson.toJson(new AddressDto().addressDto(a)));
        }


    }

    @Override
    public String fillAddressDto(Long addressId){
        Address a = addressRepository.findOne(addressId);
        if(a ==null){
            a = new Address();
        }
        AddressDto addressDto = new AddressDto().addressDto(a);
        return new Gson().toJson(addressDto);
    }


}

