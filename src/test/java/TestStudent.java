import com.leon.wechatrobot.platform.Application;
import com.leon.wechatrobot.platform.bean.Student;
import com.leon.wechatrobot.platform.controller.StudentController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created on 04/04/2018.
 *
 * @author Xiaolei-Peng
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestStudent {

    @Autowired
    private StudentController studentController;

    @Test
    public void test() {
        List<Student> list = studentController.list(1);
        System.out.println(list);
    }
}
