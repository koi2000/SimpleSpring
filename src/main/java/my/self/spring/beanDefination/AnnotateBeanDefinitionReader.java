package my.self.spring.beanDefination;

import my.self.spring.annonation.Scope;

/**
 * @author koi
 * @date 2023/2/21 22:10
 */
public class AnnotateBeanDefinitionReader {

    private BeanDefinitionRegister registry;



    public AnnotateBeanDefinitionReader(BeanDefinitionRegister registry) {
        this.registry = registry;
    }

    // ע��·��ɨ�����bean��bean������
    public void register(Class<?> componentClass) {
        registerBean(componentClass);
    }

    private void registerBean(Class<?> componentClass) {
        doRegisterBean(componentClass);
    }

    private void doRegisterBean(Class<?> componentClass) {
        // ��AppConfig��������һ��BeanDefinition
        AnnotateGenericBeanDefinition beanDefinition =
                new AnnotateGenericBeanDefinition();

        beanDefinition.setClazz(componentClass);
        if (componentClass.isAnnotationPresent(Scope.class)){
            String scope = componentClass.getAnnotation(Scope.class).value();
            beanDefinition.setScope(scope);
        }else {
            beanDefinition.setScope("singleton");
        }
        //beanDefinition ������ɺ�Ҫ��BeanFactory����Beanע��

        BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinition,this.registry);
    }

}
