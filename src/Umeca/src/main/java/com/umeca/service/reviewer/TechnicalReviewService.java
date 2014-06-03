package com.umeca.service.reviewer;

import com.umeca.model.catalog.QuestionarySection;
import com.umeca.model.entities.reviewer.QuestionReviewRel;
import com.umeca.model.entities.reviewer.QuestionarySectionView;
import com.umeca.model.entities.reviewer.TechnicalReview;

import java.util.List;

public interface TechnicalReviewService {
    QuestionarySectionView getSections(QuestionarySection obj);

    List<QuestionReviewRel> generateQuesRevRel(TechnicalReview technicalReview, String txtListSel);

    public String genLstJsonQuesSel(List<QuestionReviewRel> lstQuesSelPrev);

}
