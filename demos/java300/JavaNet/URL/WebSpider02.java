package JavaNet.URL;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * 说明：模拟爬虫
 *
 * @Auther: 11432_000
 * @Date: 2019/1/11 15:01
 * @Description:
 */
public class WebSpider02 {
    public static void main(String[] args) throws Exception{
        //获取URL
        URL url = new URL("https://www.dianping.com");
        //下载资源(模拟浏览器)
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String msg = null;
        while ((msg = reader.readLine()) != null){
            System.out.print(msg);
        }
        reader.close();
    }
}
