package hello.java.lang.string;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class SimpleA{
    public static String stringJoin1() {
        int code = 11;
        HashMap<String, Object> datas = new HashMap<String, Object>();
        datas.put("a", 12.3);
        datas.put("b", 12.4);
        datas.put("c", "c1c2");

        return "ApiResultEntity{" +
                "code=" + code +
                ", datas=" + datas +
                '}';
    }

    public static void helloStringJoin1() {
        System.out.println(stringJoin1());
    }

    final static ObjectMapper objectMapper;
    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
        //Modify By liusf 2020/05/30 09:49 開啓SafeMode用於禁用autotype,防止远程代码执行漏洞 https://cloud.tencent.com/announce/detail/1112
        // ParserConfig.getGlobalInstance().setSafeMode(true);
    }

    /**
     * Json字符串转JsonNode
     * @param jsonString
     * @return
     */
    public static JsonNode toJsonNode(String jsonString) {
        try {
            return objectMapper.readTree(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("json error:" + e.getMessage());
        }
        return null;
    }

    public static String file2string(String f) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            br.close();
            return everything;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void helloJson1() {
        JsonNode jsonNode = toJsonNode(file2string("D:\\twant\\go853\\man\\es\\properties.json"));
        try {
            System.out.println("helloJson1.begin:");
            System.out.println(jsonNode.get("hits").get("total").asInt());
            System.out.println(jsonNode.get("hits").get("hits"));
//            System.out.println(jsonNode.get("hits").get("hits"));
//            System.out.println(jsonNode.get("aggregations").get("categoryId").get("buckets"));
            System.out.println("helloJson1.end.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void helloMap1() {
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("isType", "1");
        if (map1.containsKey("istype")) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    public static void helloDate1() {
        String str = "2016-03-04 11:30:31";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        LocalDateTime dt = dateTime.plusDays(-30);
        System.out.println(dt.format(formatter));
    }

    public static void helloRount1() {
        int total = 348;
        int pageSize = 20;
        System.out.println((int)Math.ceil((double)total / pageSize));
    }

    public static void main(String args[]){
        System.out.println("begin:");
//        SimpleB.helloString1();
//        helloStringJoin1();
//        helloJson1();
//        helloMap1();
//        helloDate1();
        helloRount1();
        System.out.println("end.");
    }
}  