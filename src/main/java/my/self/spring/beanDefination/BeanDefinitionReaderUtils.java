package my.self.spring.beanDefination;

/**
 * @author koi
 * @date 2023/2/21 22:43
 */
public class BeanDefinitionReaderUtils {
    public static void registerBeanDefinition(AnnotateBeanDefinition beanDefinition, BeanDefinitionRegister registry) {
        String beanName = ((AnnotateGenericBeanDefinition) beanDefinition).getClazz().getSimpleName();
        registry.registerBeanDefinition(beanName, beanDefinition);
    }
}
