import com.hankcs.hanlp.mining.word2vec.Vector;
import com.leon.wechatrobot.platform.bean.QAPairBean;
import com.leon.wechatrobot.platform.dao.QAPairDao;
import com.leon.wechatrobot.platform.util.common.Config;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 30/11/2017.
 *
 * @author Xiaolei-Peng
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:spring/spring-mvc.xml"})
public class TestQuestionAnswer {

//    @Before
//    public void init() {
//        ApplicationContext context = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
//        questionAnswerService = (QuestionAnswerService) context.getBean("questionAnswerServiceImpl");
//    }




    @Test
    public void test() {

        Vector vector = Config.docVectorModel.query("什么是模式识别");
        System.out.println(vector);
    }

    @Test
    public void testConvertQuestionAnswerToVec() {
//        List<QAPairBean> list = questionAnswerService.listQuestionAnswer(null);
//        File file = new File("testVec.txt");
//        try {
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//            FileWriter fw = new FileWriter(file.getAbsoluteFile());
//            BufferedWriter bw = new BufferedWriter(fw);
//            int i=0;
//            for (QAPairBean qaPair : list) {
//                    Vector vector = Config.docVectorModel.query(qaPair.getQuestion());
//                    if (vector == null) {
//                        System.out.println("null : " + qaPair.getId() + " " + qaPair.getQuestion());
////                        List<Term> termList = StandardTokenizer.segment(qaPair.getQuestion());
////                        Vector vector1 = Config.docVectorModel.query(qaPair.getQuestion());
////                        System.out.println("segment : " + termList);
//                    } else {
//                        try {
//                            Field field = Vector.class.getDeclaredField("elementArray");
//                            field.setAccessible(true);
//                            float[] value = (float[]) field.get(vector);
//                            System.out.println(Arrays.toString(value));
//                            bw.write(""+qaPair.getId());
//                            bw.write(" ");
//                            for (int j = 0; j < value.length; j++) {
//                                bw.write(""+value[j]);
//                                if (j == value.length-1) {
//                                    bw.newLine();
//                                } else {
//                                    bw.write(" ");
//                                }
//                            }
//                        } catch (NoSuchFieldException|IllegalAccessException e) {
//                            System.out.println(qaPair.getQuestion());
//                            e.printStackTrace();
//                        }
//                    }
//            }
//            System.out.println("the i is :" + i);
//            bw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
