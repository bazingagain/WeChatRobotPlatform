import com.leon.wechatrobot.platform.model.Evidence;
import com.leon.wechatrobot.platform.service.impl.searchEngine.BaiduSearchEngineServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created on 23/02/2018.
 *
 * @author Xiaolei-Peng
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:spring/spring-mvc.xml"})
public class TestBaiduEngine {

    @Test
    public void testGetEvidenceFromBaidu() {
        BaiduSearchEngineServiceImpl im = new BaiduSearchEngineServiceImpl();
        List<Evidence> ls = im.search("什么是曼哈顿距离");
        for (Evidence e: ls) {
            System.out.println(e.getSnippet());
        }

    }
}
