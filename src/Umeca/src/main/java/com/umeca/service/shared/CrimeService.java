package com.umeca.service.shared;

import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.shared.CommentRequest;
import org.springframework.web.servlet.ModelAndView;

public interface CrimeService {
    ModelAndView generatePage(String s, ModelAndView model, Long userId);
    boolean deleteComment(String sRole, CommentRequest model, User user, ResponseMessage response);
    void doDeleteNotificationReviewer(Long idNotif);
}
