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
        // ��ȡbean����
        DefaultListableBeanFactory beanFactory = obtainBeanFactory();
        // ��AppConfig·���µ����е�bean����ɨ�裬ע�ᵽbean����beanDefinitionMap
        invokeBeanFactoryPostProcessors(beanFactory);
        // ��ʼ��beandefinition������ĵ���bean���ŵ�����bean��������
        finishBeanFactoryInitilization(beanFactory);
    }

    private void finishBeanFactoryInitilization(DefaultListableBeanFactory beanFactory) {
        //TODO: ��ɸ÷���
        beanFactory.preInstanceSingleton();

    }

    private void invokeBeanFactoryPostProcessors(DefaultListableBeanFactory beanFactory) {
        // ��������࣬doScan������û����beanFactory����
        beanFactory.doScan();
    }

    private DefaultListableBeanFactory obtainBeanFactory() {
        return this.beanFactory;
    }

    public Object getBean(String beanName) {
        return this.beanFactory.getBean(beanName);
    }
}
