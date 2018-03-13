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

    @Test
    public void testGetQAPair() {
        QAPairBean bean = qaPairDao.getQAPairById(10);
        System.out.println(bean.getCategory());
    }

    @Test
    public void testInitQAPair() {
        List<QAPairBean> list = qaPairDao.listQAPairByCategory("模式识别");
        System.out.println(list.size());
        Assert.assertNotNull(list);
    }
    
}
