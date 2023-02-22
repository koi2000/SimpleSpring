package my.self.spring.applicationContext;

import my.self.spring.beanDefination.AnnotateBeanDefinition;
import my.self.spring.beanDefination.BeanDefinitionRegister;
import my.self.spring.beanFactory.DefaultListableBeanFactory;

/**
 * @author koi
 * @date 2023/2/22 12:01
 */
public class GenericApplicationContext implements BeanDefinitionRegister {

    private DefaultListableBeanFactory beanFactory;

    public GenericApplicationContext() {
        this.beanFactory = new DefaultListableBeanFactory();
    }

    @Override
    public void registerBeanDefinition(String beanName, AnnotateBeanDefinition beanDefinition) {
        this.beanFactory.registerBeanDefinition(beanName, beanDefinition);
    }
}
