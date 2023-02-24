package my.self.spring.applicationContext;

import my.self.spring.beanDefination.AnnotateBeanDefinition;
import my.self.spring.beanDefination.AnnotateBeanDefinitionReader;
import my.self.spring.beanDefination.BeanDefinitionRegister;
import my.self.spring.beanFactory.DefaultListableBeanFactory;

/**
 * @author koi
 * @date 2023/2/21 21:57
 */
public class AnnotationConfigApplicationContext extends GenericApplicationContext implements BeanDefinitionRegister {

    private AnnotateBeanDefinitionReader reader;


    // 如果有人调用无参构造，必须先调用父类的无参构造
    // 父类初始化 defaultListableBeanFactory
    public AnnotationConfigApplicationContext() {
        this.reader = new AnnotateBeanDefinitionReader(this);
    }

    public AnnotationConfigApplicationContext(Class<?> componentClass) {
        //1. 读 componentClass 也就是扫描路径所在的类 AppConfig 定义一个阅读器专门读取 AnnotateBeanDefinitionReader
        // 先调用父类构造函数，初始化beanFactory
        this();

        //2. 先把这个类 AppConfig 注册到 bean工厂里(BeanDefinition + registerBeanDefinition + FactoryBean)
        // registerBeanDefinition + FactoryBean
        register(componentClass);


        //3. 扫描路径，然后提取出这个路径下所有的bean，然后注册到bean工厂
        // refresh方法要放到父类中，让所有的子类都能使用
        // 核心方法，为了让子类g
        refresh();
    }

    private void register(Class<?> componentClass) {
        this.reader.register(componentClass);
    }
}
