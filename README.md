# My Spring

## Ŀ��
дһ���򵥰汾��Spring

BeanFactory: ����������bean���ṩ��ȡbean�ķ���

BeanDefinition: bean�Ķ��壬�洢������String scope(����sigleton������prototype)������bean��ԭ��

ApplicationContext�������������ģ������� (������)����Ҫ���� BeanDefinition�����ɣ���
    BeanDefinition�����ݡ�(ע��,beanDefinitionע��, beanDefinitionRegister (����: registerBeanDefinition))�� BeanFactory ����bean��
    getBean��������ApplicationContext��ߵķ�����? 
    �ǵģ����getbean��������㵹һ��Դ��Ļ�����ᷢ�֣��������յ��õ� BeanFactory ��getBean