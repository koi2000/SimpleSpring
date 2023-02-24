package my.self.spring.beanFactory;

import my.self.spring.annonation.ComponentScan;
import my.self.spring.annonation.Scope;
import my.self.spring.annonation.Service;
import my.self.spring.beanDefination.AnnotateBeanDefinition;
import my.self.spring.beanDefination.AnnotateGenericBeanDefinition;
import my.self.spring.beanDefination.BeanDefinitionRegister;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author koi
 * @date 2023/2/22 11:36
 */
public class DefaultListableBeanFactory implements BeanFactory, BeanDefinitionRegister {

    private final Map<String, AnnotateBeanDefinition> beanDefinitionMap =
            new ConcurrentHashMap<>(256);

    private List<String> beanDefinitionNames = new ArrayList<>();
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(256);

    // 只有bean都注册以后，才能有getBean
    @Override
    public Object getBean(String beanName) {
        return doGetBean(beanName);
    }

    private Object doGetBean(String beanName) {
        Object bean = singletonObjects.get(beanName);
        if (bean != null) return bean;
        // 如果没有，需要根据beanDefinition创建bean
        AnnotateGenericBeanDefinition bd = (AnnotateGenericBeanDefinition) beanDefinitionMap.get(beanName);
        Object cBean = createBean(beanName, bd);
        if (bd.getScope().equals("singleton")) {
            // createBean将 beanDefinition转为真正的对象
            singletonObjects.put(beanName, cBean);
        }
        return cBean;
    }

    private Object createBean(String beanName, AnnotateGenericBeanDefinition bd) {
        try {
            return bd.getClazz().getConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void registerBeanDefinition(String beanName, AnnotateBeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanName, beanDefinition);
    }

    public void doScan() {
        System.out.println("-----");
        for (String beanName : beanDefinitionMap.keySet()) {
            AnnotateGenericBeanDefinition bd = (AnnotateGenericBeanDefinition) beanDefinitionMap.get(beanName);
            if (bd.getClazz().isAnnotationPresent(ComponentScan.class)) {
                ComponentScan componentScan = (ComponentScan) bd.getClazz().getAnnotation(ComponentScan.class);
                String basePackage = componentScan.value();
                URL resource = this.getClass().getClassLoader().getResource(basePackage.replace(".", "/"));
                System.out.println(resource);
                File file = new File(resource.getFile());
                if (file.isDirectory()) {
                    for (File f : file.listFiles()) {
                        try {
                            Class clazz = this.getClass()
                                    .getClassLoader()
                                    .loadClass(basePackage.concat(".").concat(f.getName().split("\\.")[0]));
                            if (clazz.isAnnotationPresent(Service.class)) {
                                String name = ((Service) clazz.getAnnotation(Service.class)).value();
                                AnnotateGenericBeanDefinition adb = new AnnotateGenericBeanDefinition();
                                adb.setClazz(clazz);
                                if (clazz.isAnnotationPresent(Scope.class)) {
                                    adb.setScope(((Scope) clazz.getAnnotation(Scope.class)).value());
                                } else {
                                    adb.setScope("singleton");
                                }
                                beanDefinitionMap.put(((Service) clazz.getAnnotation(Service.class)).value(), adb);
                                // 需要有一个地方，记录真正我们定义的bean
                                beanDefinitionNames.add(name);
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void preInstanceSingleton() {
        // 初始化我们定义的bean，需要找到所有我们自定义的beanname
        // 这里拷贝一份而没有直接遍历，因为该对象为多线程访问对象
        // 如果有add操作，会导致for循环失败
        List<String> beanNames = new ArrayList<>(beanDefinitionNames);
        for (String beanName : beanDefinitionNames) {
            AnnotateGenericBeanDefinition bd = (AnnotateGenericBeanDefinition) beanDefinitionMap.get(beanName);
            if (bd.getScope().equals("singleton")) {
                // 创建单例对象，将单例对象放到单例池中
                // getBean方法里边就包含了创建对象，然后放到 singletonObjects里
                getBean(beanName);
            }
        }
    }
}
