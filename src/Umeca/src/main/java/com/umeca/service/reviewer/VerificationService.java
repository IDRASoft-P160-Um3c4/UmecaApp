package com.umeca.service.reviewer;

import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.SourceVerification;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 18/06/14
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
public interface VerificationService {
    void createVerification(Case c);

    List<SourceVerification> convertAllInitSourcesVerif(Case c);
}
