package util;

import java.io.File;

public class MyFileSystem implements FileSystem {
    public int spaceTotal = 101;

    @Override
    public String[] list(File file) {
        System.out.println("正在执行[" + this.getClass().getName() + "]类的list方法，参数:[" + file + "]");
        return file.list();
    }
}
