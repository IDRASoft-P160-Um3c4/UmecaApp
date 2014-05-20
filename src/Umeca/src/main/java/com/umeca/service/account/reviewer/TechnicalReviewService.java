package com.umeca.service.account.reviewer;

import com.umeca.model.catalog.QuestionarySection;
import com.umeca.model.entities.reviewer.QuestionReviewRel;
import com.umeca.model.entities.reviewer.QuestionarySectionView;
import java.util.List;

public interface TechnicalReviewService {
    QuestionarySectionView getSections(QuestionarySection obj);
    List<QuestionReviewRel> generateQuesRevRel(Long meetingId, String txtListSel);
}
