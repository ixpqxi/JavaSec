package org.ixpqxi.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SerTools {
    public static void serialize(Object obj) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get("ser.bin")));
        oos.writeObject(obj);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static Object unserialize(String Filename) throws IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(Filename)));
        return ois.readObject();
    }
}
