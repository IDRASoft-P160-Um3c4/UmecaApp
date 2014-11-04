
package com.umeca.service.shared;

import com.google.gson.Gson;
import com.umeca.model.ResponseMessage;
import com.umeca.model.dto.victim.VictimDto;
import com.umeca.model.entities.reviewer.Address;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.CurrentCriminalProceeding;
import com.umeca.model.entities.shared.Victim;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.catalog.LocationRepository;
import com.umeca.repository.catalog.RelationshipRepository;
import com.umeca.repository.reviewer.AddressRepository;
import com.umeca.repository.reviewer.CurrentCriminalProceedingRepository;
import com.umeca.repository.shared.VictimRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.catalog.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by Vmware on 06/06/2014.
 */

@Service("victimService")
public class VictimServiceImpl implements VictimService {

    @Autowired
    RelationshipRepository relationshipRepository;
    @Autowired
    VictimRepository victimRepository;
    @Autowired
    AddressService addressService;
    @Autowired
    CaseRepository caseRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    SharedUserService userService;

    @Override
    public ModelAndView upsertVictim(Long id, Long idCase) {
        ModelAndView model = new ModelAndView("/victim/upsert");
        try{
        Gson gson = new Gson();
        model.addObject("lstRelationship", gson.toJson(relationshipRepository.findNotObsolete()));
        model.addObject("idCase", idCase);
        addressService.fillCatalogAddress(model);
        if(id!=null && id > 0){
            Victim victim = victimRepository.findOne(id);
            model.addObject("victim",gson.toJson(new VictimDto().dtoVictim(victim)));
            if(victim.getAddress()!=null){
                addressService.fillModelAddress(model,victim.getAddress().getId());
            }
        }
        }catch (Exception e){
            logException.Write(e, this.getClass(), "upsertVictim", userService);
        }finally {
            return model;
        }
    }

    @Autowired
    CurrentCriminalProceedingRepository currentCriminalProceedingRepository;

    @Transactional
    @Override
    public ResponseMessage doUpsertVictim(Victim victim, Long idCase, String type) {
        try{
            Victim sVictim;

            if(victim.getId()!= null){
                sVictim = victimRepository.findOne(victim.getId());
            }else{
                sVictim = new Victim();
                sVictim.setAddress(new Address());
            }
            sVictim.setFullname(victim.getFullname());
            sVictim.setAge(victim.getAge());
            sVictim.setPhone(victim.getPhone());

            sVictim.getAddress().setStreet(victim.getAddress().getStreet());
            sVictim.getAddress().setInnNum(victim.getAddress().getInnNum());
            sVictim.getAddress().setOutNum(victim.getAddress().getOutNum());
            sVictim.setSpecification(victim.getSpecification());
            sVictim.getAddress().setLocation(locationRepository.findOne(victim.getAddress().getLocation().getId()));
            sVictim.getAddress().setLng(victim.getAddress().getLng());
            sVictim.getAddress().setLat(victim.getAddress().getLat());
            sVictim.getAddress().setAddressString(sVictim.getAddress().toString());
            sVictim.setAddress(addressRepository.save(sVictim.getAddress()));
            if(victim.getRelationship()!=null){
                sVictim.setRelationship(relationshipRepository.findOne(victim.getRelationship().getId()));
            }
            if(type==null){
                CurrentCriminalProceeding ccp = currentCriminalProceedingRepository.findByIdCase(idCase);
                if(ccp==null){
                    Case c = caseRepository.findOne(idCase);
                    ccp = new CurrentCriminalProceeding();
                    c.getMeeting().setCurrentCriminalProceeding(ccp);
                    ccp.setMeeting(c.getMeeting());
                    ccp.setVictims(new ArrayList<Victim>());
                    sVictim.setCriminalProceeding(ccp);
                    ccp.getVictims().add(sVictim);
                    caseRepository.save(c);
                }else{
                    if(ccp.getVictims()==null){
                        ccp.setVictims(new ArrayList<Victim>());
                    }
                    sVictim.setCriminalProceeding(ccp);
                    ccp.getVictims().add(sVictim);
                    victimRepository.save(sVictim);
                    System.out.println("despues de guardar");
                }
            }
            return new ResponseMessage(false, "se ha actualizado con exito");
        }catch (Exception e){
            logException.Write(e, this.getClass(), "doUpsertVictim", userService);
            return new ResponseMessage(true, "Ha ocurrdio un error al actualizar");
        }
    }

    @Transactional
    @Override
    public ResponseMessage deleteVictim(Long id) {
        try{
            Victim v = victimRepository.findOne(id);
            v.setCriminalProceeding(null);
            Address a= v.getAddress();
            v.setAddress(null);
            victimRepository.delete(v);
            addressRepository.delete(a.getId());
            return  new ResponseMessage(false, "Se ah eliminado correctamente");
        }catch (Exception e){
            logException.Write(e, this.getClass(), "deleteVictim", userService);
            return  new ResponseMessage(true,"Ha ocurrido un error al eliminar", "Error al eliminar");
        }
    }
}
