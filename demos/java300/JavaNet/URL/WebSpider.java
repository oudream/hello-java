package JavaNet.URL;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * 说明：模拟爬虫
 *
 * @Auther: 11432_000
 * @Date: 2019/1/11 15:01
 * @Description:
 */
public class WebSpider {
    public static void main(String[] args) throws Exception{
        //获取URL
        URL url = new URL("https://www.jd.com");
        //下载资源
        InputStream inputStream = url.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream ,"UTF-8"));
        String msg = null;
        while ((msg = reader.readLine()) != null){
            System.out.print(msg);
        }
        reader.close();
    }
}
