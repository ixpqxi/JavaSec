import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.ixpqxi.util.Demo;

import java.io.IOException;

public class DemoSerialize {
    public static void main(String[] args) throws IOException {
        Demo demo = new Demo();
        demo.setName("test demo");
        demo.setSize(78);

        String json = JSON.toJSONString(demo, SerializerFeature.WriteClassName);
        System.out.println(json);

        Demo parsedDemo = JSON.parseObject(json, Demo.class, Feature.SupportNonPublicField);
        System.out.println(parsedDemo);
    }
}
