package my.self.test;

import my.self.spring.applicationContext.AnnotationConfigApplicationContext;
import my.self.test.config.AppConfig;

/**
 * @author koi
 * @date 2023/2/21 21:22
 */
public class SpringTest {
    public static void main(String[] args) {
        // ����ApplicationContext
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        // ����getBean


    }
}
