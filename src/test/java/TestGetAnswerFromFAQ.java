import com.leon.wechatrobot.platform.controller.AskController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created on 11/02/2018.
 *
 * @author Xiaolei-Peng
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:spring/spring-mvc.xml"})
public class TestAsk {

    @Autowired
    private AskController askController;

    @Test
    public void testAsk() {
        String question = "模式识别是什么?";
        System.out.println(askController.ask(question));
    }
}
