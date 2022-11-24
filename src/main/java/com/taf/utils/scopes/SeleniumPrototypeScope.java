package com.taf.utils.scopes;

import lombok.SneakyThrows;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SeleniumPrototypeScope implements Scope {

    public static final String SCOPE_SELENIUM_ELEMENT_PROTOTYPE = "selenium_prototype";
    private final Map<String, Map<Integer, Object>> parametricPrototypes = new ConcurrentHashMap<>();

    @SneakyThrows
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Field argumentsField = objectFactory.getClass().getDeclaredFields()[3];
        argumentsField.setAccessible(true);
        Object[] arguments = (Object[]) argumentsField.get(objectFactory);
        if (arguments.length != 1) {
            throw new UnsupportedOperationException(SCOPE_SELENIUM_ELEMENT_PROTOTYPE + " scope supports only one parameter");
        }
        int argumentsHashCode = ((WebElement) arguments[0]).hashCode();

        return parametricPrototypes
                .computeIfAbsent(name, n -> new ConcurrentHashMap<>())
                .computeIfAbsent(argumentsHashCode, h -> objectFactory.getObject());
    }

    @Override
    public Object remove(String name) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return SCOPE_SELENIUM_ELEMENT_PROTOTYPE;
    }
}
