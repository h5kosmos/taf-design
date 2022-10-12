package com.taf.utils.scopes;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import static com.taf.utils.scopes.ParametricPrototypeScope.SCOPE_ELEMENT_PROTOTYPE;

public class ParametricPrototypeBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory factory) throws BeansException {
        factory.registerScope(SCOPE_ELEMENT_PROTOTYPE, new ParametricPrototypeScope());
    }
}
