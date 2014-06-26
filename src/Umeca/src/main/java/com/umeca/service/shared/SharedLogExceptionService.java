package com.umeca.service.shared;

import com.umeca.model.entities.shared.LogException;
import com.umeca.repository.shared.LogExceptionRepository;
import com.umeca.service.account.SharedUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Project: Umeca
 * User: Israel
 * Date: 6/14/14
 * Time: 10:53 AM
 */

@Service
public class SharedLogExceptionService {

    @Autowired
    LogExceptionRepository logExceptionRepository;

    public void Write(Exception ex, Class<?> cClass, String sMethod, SharedUserService sharedUserService) {
        try {
            String username = sharedUserService.GetLoggedUsername();
            logExceptionRepository.save(new LogException(cClass.getName(), sMethod, ex.getMessage() + " | " + ex.getStackTrace(), username));
        }catch (Exception exIn){
            WriteToFile(ex, cClass, sMethod, exIn);
        }
    }


    public void Write(Exception ex, Class<?> cClass, String sMethod, String username) {
        try {
            logExceptionRepository.save(new LogException(cClass.getName(), sMethod, ex.getMessage() + " | " + ex.getStackTrace(), username));
        }catch (Exception exIn){
            WriteToFile(ex, cClass, sMethod, exIn);
        }
    }

    private void WriteToFile(Exception ex, Class<?> cClass, String sMethod, Exception exIn) {
        try{
            Logger logger = Logger.getLogger(cClass);
            logger.error(sMethod + " | " + ex.getMessage() + " | " + ex.getStackTrace() + " | " + exIn.getMessage() + " | " + exIn.getStackTrace());
        }catch (Exception exw){
            return;
        }


    }
}
