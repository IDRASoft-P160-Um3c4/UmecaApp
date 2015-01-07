package com.umeca.service.reviewer;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.QuestionarySection;
import com.umeca.model.entities.reviewer.QuestionReviewRel;
import com.umeca.model.entities.reviewer.QuestionarySectionView;
import com.umeca.model.entities.reviewer.TechnicalReview;
import com.umeca.model.entities.reviewer.View.TechnicalReviewInfoFileAllSourcesView;
import com.umeca.model.entities.reviewer.View.TechnicalReviewInfoFileView;

import java.util.List;

public interface TechnicalReviewService {
    QuestionarySectionView getSections(QuestionarySection obj);

    List<QuestionReviewRel> generateQuesRevRel(TechnicalReview technicalReview, String txtListSel);

    String genLstJsonQuesSel(List<QuestionReviewRel> lstQuesSelPrev);

    TechnicalReviewInfoFileView fillInfoFile(Long idVerification);

    TechnicalReviewInfoFileAllSourcesView fillInfoFileAllSources(Long id);

    Integer calculateLevelRisk(Integer total);

    public ResponseMessage doUpsert(TechnicalReview result);
}
