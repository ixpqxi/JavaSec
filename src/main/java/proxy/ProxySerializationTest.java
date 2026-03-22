package proxy;

import util.FileSystem;
import util.MyFileSystem;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxySerializationTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        FileSystem fileSystem = new MyFileSystem();
        InvocationHandler handler = new JDKInvocationHandler(fileSystem);

        FileSystem instance = (FileSystem) Proxy.newProxyInstance(
                FileSystem.class.getClassLoader(),
                new Class[]{FileSystem.class},
                handler
        );

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);

        out.writeObject(instance);
        out.flush();
        out.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bais);

        FileSystem test = (FileSystem) in.readObject();

        System.out.println("反序列化类实例类名:" + test.getClass());
        System.out.println("反序列化类实例toString:" + test.toString());

        // ObjectStreamClass是Java序列化机制中的元数据管理器
        // lookup方法获取指定类对应的ObjectStreamClass实例
        ObjectStreamClass osc = ObjectStreamClass.lookup(test.getClass());
        System.out.println("反序列化代理类的 serialVersionUID: " + osc.getSerialVersionUID());
        System.out.println("getFields：" + Arrays.toString(osc.getFields()));
        System.out.println("getField：" + osc.getField("serialVersionUID"));
    }
}
