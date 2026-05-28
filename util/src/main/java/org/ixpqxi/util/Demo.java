package org.ixpqxi.util;

import java.io.IOException;

public class Demo {
    private String name;
    private int size;

    public Demo() {
        System.out.println("structure");
    }

    public String getName() {
        System.out.println("getName");
        return name;
    }

    public void setName(String name) {
        System.out.println("setName");
        this.name = name;
    }

    public int getSize() {
        System.out.println("getSize");
        return size;
    }

    public void setSize(int size) throws IOException {
        System.out.println("setSize");
        this.size = size;
        Runtime.getRuntime().exec("calc.exe");
    }
}
