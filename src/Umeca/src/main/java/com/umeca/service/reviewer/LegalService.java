package com.umeca.service.reviewer;

import com.umeca.model.entities.reviewer.Address;
import com.umeca.model.entities.reviewer.CoDefendant;
import com.umeca.model.entities.reviewer.Crime;
import com.umeca.model.entities.reviewer.CurrentCriminalProceeding;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 5/06/14
 * Time: 05:14 PM
 * To change this template use File | Settings | File Templates.
 */
public interface LegalService {
   List<Crime> generateCrime(String crimeS, CurrentCriminalProceeding ccp);

    List<CoDefendant> getnerateCoDefendant(String listCoDefendant, CurrentCriminalProceeding ccp);
}
