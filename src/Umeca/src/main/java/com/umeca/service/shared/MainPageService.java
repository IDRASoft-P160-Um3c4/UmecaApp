package com.umeca.service.shared;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.shared.CommentRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public interface MainPageService {
    ModelAndView generatePage(HttpServletRequest request, String s, ModelAndView model, Long userId);

    boolean deleteComment(String sRole, CommentRequest model, User user, ResponseMessage response);

    void doDeleteNotificationReviewer(Long idNotif);

    boolean deleteNotification(Long id, Long id1, ResponseMessage response);
}
