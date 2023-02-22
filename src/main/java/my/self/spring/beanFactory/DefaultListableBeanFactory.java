package my.self.spring.beanFactory;

import my.self.spring.beanDefination.AnnotateBeanDefinition;
import my.self.spring.beanDefination.BeanDefinitionRegister;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author koi
 * @date 2023/2/22 11:36
 */
public class DefaultListableBeanFactory implements BeanFactory, BeanDefinitionRegister {

    private final Map<String, AnnotateBeanDefinition> beanDefinitionMap =
            new ConcurrentHashMap<>(256);

    // 只有bean都注册以后，才能有getBean
    @Override
    public Object getBean(String beanName) {
        return null;
    }

    @Override
    public void registerBeanDefinition(String beanName, AnnotateBeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanName, beanDefinition);
    }
}
