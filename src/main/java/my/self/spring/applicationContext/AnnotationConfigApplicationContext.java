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


    // ������˵����޲ι��죬�����ȵ��ø�����޲ι���
    // �����ʼ�� defaultListableBeanFactory
    public AnnotationConfigApplicationContext() {
        this.reader = new AnnotateBeanDefinitionReader(this);
    }

    public AnnotationConfigApplicationContext(Class<?> componentClass) {
        //1. �� componentClass Ҳ����ɨ��·�����ڵ��� AppConfig ����һ���Ķ���ר�Ŷ�ȡ AnnotateBeanDefinitionReader
        // �ȵ��ø��๹�캯������ʼ��beanFactory
        this();

        //2. �Ȱ������ AppConfig ע�ᵽ bean������(BeanDefinition + registerBeanDefinition + FactoryBean)
        // registerBeanDefinition + FactoryBean
        register(componentClass);


        //3. ɨ��·����Ȼ����ȡ�����·�������е�bean��Ȼ��ע�ᵽbean����
        // refresh����Ҫ�ŵ������У������е����඼��ʹ��
        // ���ķ�����Ϊ��������g
        refresh();
    }

    private void register(Class<?> componentClass) {
        this.reader.register(componentClass);
    }
}
