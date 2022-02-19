package com.example.mysuperlibrary;

import java.lang.reflect.Proxy;

public class SuperUtil {

    public static <T> T proxy(Class<T> iface) {
        //noinspection unchecked
        return (T) Proxy.newProxyInstance(
                iface.getClassLoader(),
                new Class[]{iface},
                (proxy, method, args) -> {
                    final ExampleAnnotation annotation = method.getAnnotation(ExampleAnnotation.class);
                    if (annotation != null) {
                        System.out.println(annotation.value());
                        return null;
                    } else if (method.getName().startsWith("say")) {
                        System.out.println(method.getName().substring(3));
                        return null;
                    } else {
                        return method.invoke(proxy, args);
                    }
                }
        );
    }
}
