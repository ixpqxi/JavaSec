import com.alibaba.fastjson.JSON;
import org.ixpqxi.util.Demo;

public class VulnDemo {
    public static void main(String[] args) {
        String json = "{\"@type\":\"org.ixpqxi.util.Demo\",\"name\":\"test demo\",\"size\":78}";
        Demo demo = JSON.parseObject(json, Demo.class);
        System.out.println(demo);
    }
}
