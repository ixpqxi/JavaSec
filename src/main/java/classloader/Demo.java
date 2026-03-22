package classloader;

public class Demo {
    static {
        System.out.println("静态代码块");
    }

    {
        System.out.println("构造代码块");
    }

    Demo() {
        System.out.println("无参构造方法");
    }

    Demo(String s) {
        System.out.println("有参构造方法");
    }

    public static void check() {
        System.out.println("静态方法");
    }
}
