package com.leon.wechatrobot.platform.selecter;

import com.hankcs.hanlp.seg.common.Term;
import com.leon.wechatrobot.platform.model.CandidateAnswer;
import com.leon.wechatrobot.platform.model.CandidateAnswerCollection;
import com.leon.wechatrobot.platform.model.Evidence;
import com.leon.wechatrobot.platform.model.Question;
import com.leon.wechatrobot.platform.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created on 27/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class CommonCandidateAnswerSelect implements CandidateAnswerSelect {

//    private static final Logger LOG = LoggerFactory.getLogger(CommonCandidateAnswerSelect.class);

    @Autowired
    private QuestionService questionService;

    @Override
    public void select(Question question, Evidence evidence) {
        CandidateAnswerCollection candidateAnswerCollection = new CandidateAnswerCollection();

        List<Term> words = questionService.segmentQuestion(evidence.getTitle() + evidence.getSnippet(), true);
        for (Term word : words) {
            if (word.length() < 2){
//                LOG.debug("忽略长度小于2的候选答案："+word);
                continue;
            }
            if(word.nature.toString().toLowerCase().startsWith(question.getQuestionType().getPos().toLowerCase())){
                CandidateAnswer answer = new CandidateAnswer();
                answer.setAnswer(word.word);
                candidateAnswerCollection.addAnswer(answer);
//                LOG.debug("成为候选答案："+word);
            }
            //处理人名
//            else if(question.getQuestionType().getPos().equals("nr") && word.getPartOfSpeech()==PartOfSpeech.I){
//                if(PersonName.is(word.getText())){
//                    CandidateAnswer answer = new CandidateAnswer();
//                    answer.setAnswer(word.getText());
//                    candidateAnswerCollection.addAnswer(answer);
////                    LOG.debug("成为候选答案："+word);
//                }
//            }
        }
        evidence.setCandidateAnswerCollection(candidateAnswerCollection);

    }
}
