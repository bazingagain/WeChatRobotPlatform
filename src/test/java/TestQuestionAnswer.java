import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import com.leon.wechatrobot.platform.bean.QAPairBean;
import com.leon.wechatrobot.platform.dao.QAPairDao;
import com.leon.wechatrobot.platform.service.QuestionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created on 30/11/2017.
 *
 * @author Xiaolei-Peng
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:spring/spring-mvc.xml"})
public class TestQuestionAnswer {

    @Autowired
    private QAPairDao qaPairDao;

    @Autowired
    private QuestionService questionService;

    @Test
    public void testInitQAPair() {
        List<QAPairBean> list = qaPairDao.listQAPairByCategory("模式识别");
        Assert.assertNotNull(list);
    }

    @Test
    public void test(){
        String[] testCase = new String[]{
                "签约仪式前，秦光荣、李纪恒、仇和等一同会见了参加签约的企业家。",
                "王国强、高峰、汪洋、张朝阳光着头、韩寒、小四",
                "张浩和胡健康复员回家了",
                "王总和小丽结婚了",
                "编剧邵钧林和稽道青说",
                "这里有关天培的有关事迹",
                "龚学平等领导,邓颖超生前",
        };
        Segment segment = HanLP.newSegment().enableNameRecognize(true);
        for (String sentence : testCase)
        {
            List<Term> termList = segment.seg(sentence);
            System.out.println(termList);
        }
    }

    @Test
    public void segmentTest() {
        System.out.println(questionService.segmentQuestion("压压是谁?", true));
    }

}
