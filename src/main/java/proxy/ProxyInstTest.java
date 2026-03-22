package proxy;

import util.FileSystem;
import util.MyFileSystem;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyInstTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        FileSystem fileSystem = new MyFileSystem();
        InvocationHandler handler = new JDKInvocationHandler(fileSystem);

        // 使用 newProxyInstance() 创建动态代理类实例

        FileSystem proxyInstance1 = (FileSystem) Proxy.newProxyInstance(
                FileSystem.class.getClassLoader(),
                new Class[]{FileSystem.class},
                handler
        );

        System.out.println(
                Arrays.toString(proxyInstance1.list(new File("/root")))
        );

        System.out.println("proxyInstance1 的类名：" + proxyInstance1.getClass());
        System.out.println("proxyInstance1 类名toString：" + proxyInstance1.toString());

        System.out.println("===================================================");

        // 使用反射的方式创建动态代理类实例
        Class proxyClass = Proxy.getProxyClass(
                FileSystem.class.getClassLoader(),
                new Class[]{FileSystem.class}
        );

        FileSystem proxyInstance2 = (FileSystem) proxyClass.getConstructor(
                new Class[]{InvocationHandler.class}
        ).newInstance(new Object[]{handler});

        System.out.println(
                Arrays.toString(proxyInstance2.list(new File("/root")))
        );

        System.out.println("proxyInstance2 的类名：" + proxyInstance2.getClass());
        System.out.println("proxyInstance2 类名toString：" + proxyInstance2.toString());
    }
}
