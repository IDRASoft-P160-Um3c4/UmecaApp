package com.umeca.service.reviewer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.model.entities.reviewer.CoDefendant;
import com.umeca.model.entities.reviewer.Crime;
import com.umeca.model.entities.reviewer.CurrentCriminalProceeding;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 5/06/14
 * Time: 05:14 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("legalService")
public class LegalServiceImpl implements LegalService {

    @Override
    public List<Crime> generateCrime(String crimeS, CurrentCriminalProceeding ccp) {
        Gson gson = new Gson();
        List<Crime> listCrime = gson.fromJson(crimeS,new TypeToken<List<Crime>>(){}.getType());
        for(Crime c: listCrime){
           c.setCriminalProceeding(ccp);
        }
        return listCrime;
    }

    @Override
    public List<CoDefendant> getnerateCoDefendant(String listCoDefendant, CurrentCriminalProceeding ccp) {
        Gson gson = new Gson();
        List<CoDefendant> listCodefendant = gson.fromJson(listCoDefendant,new TypeToken<List<CoDefendant>>(){}.getType());
        for(CoDefendant c: listCodefendant){
            c.setCriminalProceeding(ccp);
        }
        return listCodefendant;
    }
}

