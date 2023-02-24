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

    protected void refresh() {
        // 获取bean工厂
        DefaultListableBeanFactory beanFactory = obtainBeanFactory();
        // 把AppConfig路径下的所有的bean进行扫描，注册到bean工厂beanDefinitionMap
        invokeBeanFactoryPostProcessors(beanFactory);
        // 初始化beandefinition所代表的单例bean，放到单例bean的容器里
        finishBeanFactoryInitilization(beanFactory);
    }

    private void finishBeanFactoryInitilization(DefaultListableBeanFactory beanFactory) {
        //TODO: 完成该方法
        beanFactory.preInstanceSingleton();

    }

    private void invokeBeanFactoryPostProcessors(DefaultListableBeanFactory beanFactory) {
        // 简化了这个类，doScan方法并没有在beanFactory里面
        beanFactory.doScan();
    }

    private DefaultListableBeanFactory obtainBeanFactory() {
        return this.beanFactory;
    }

    public Object getBean(String beanName) {
        return this.beanFactory.getBean(beanName);
    }
}
