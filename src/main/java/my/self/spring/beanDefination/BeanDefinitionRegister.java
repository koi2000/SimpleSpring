package my.self.spring.beanDefination;

/**
 * @author koi
 * @date 2023/2/21 22:36
 */
public interface BeanDefinitionRegister {
    void registerBeanDefinition(String beanName, AnnotateBeanDefinition beanDefinition);
}
