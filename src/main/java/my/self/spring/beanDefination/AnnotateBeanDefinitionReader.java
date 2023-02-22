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

    // 注册路径扫描这个bean到bean工厂里
    public void register(Class<?> componentClass) {
        registerBean(componentClass);
    }

    private void registerBean(Class<?> componentClass) {
        doRegisterBean(componentClass);
    }

    private void doRegisterBean(Class<?> componentClass) {
        // 把AppConfig这个类读成一个BeanDefinition
        AnnotateGenericBeanDefinition beanDefinition =
                new AnnotateGenericBeanDefinition();

        beanDefinition.setClazz(componentClass);
        if (componentClass.isAnnotationPresent(Scope.class)){
            String scope = componentClass.getAnnotation(Scope.class).value();
            beanDefinition.setScope(scope);
        }else {
            beanDefinition.setScope("singleton");
        }
        //beanDefinition 创建完成后，要给BeanFactory进行Bean注册

        BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinition,this.registry);
    }

}
