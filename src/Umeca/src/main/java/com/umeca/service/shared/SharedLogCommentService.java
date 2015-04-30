package com.umeca.service.shared;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.supervisorManager.LogComment;
import com.umeca.repository.supervisorManager.LogCommentRepository;

import java.util.Calendar;

public class SharedLogCommentService {

    public static void generateLogComment(String comments, User userSender, Case caseDet,
                                          String action, User userReceiver, String type, LogCommentRepository localLogCommentRepository) {
        LogComment commentModel = new LogComment();
        Calendar now = Calendar.getInstance();
        commentModel.setComments(comments);
        commentModel.setAction(action);
        commentModel.setCaseDetention(caseDet);
        commentModel.setReceiveUser(userReceiver);
        commentModel.setSenderUser(userSender);
        commentModel.setTimestamp(now);
        commentModel.setType(type);
        localLogCommentRepository.save(commentModel);
    }
}
