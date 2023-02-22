package my.self.spring.beanDefination;

/**
 * @author koi
 * @date 2023/2/21 22:26
 */
public class AnnotateGenericBeanDefinition implements AnnotateBeanDefinition{
    private Class clazz;
    private String scope;

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
