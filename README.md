# My Spring

## 目的
写一个简单版本的Spring

BeanFactory: 工厂，生产bean，提供获取bean的方法

BeanDefinition: bean的定义，存储类似于String scope(单例sigleton，多例prototype)，生产bean的原料

ApplicationContext：容器（上下文）。容器 (上下文)。他要主导 BeanDefinition的生成，把
    BeanDefinition”传递“(注册,beanDefinition注册, beanDefinitionRegister (方法: registerBeanDefinition))给 BeanFactory 生成bean。
    getBean方法不是ApplicationContext里边的方法吗? 
    是的，这个getbean方法如果你倒一倒源码的话，你会发现，他是最终调用的 BeanFactory 的getBean