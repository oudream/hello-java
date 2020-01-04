package JavaRegularExpression;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 说明：练习，获取一个网页上的所有超链接
 *
 * @Auther: 11432_000
 * @Date: 2019/3/3 10:55
 * @Description:
 */
@SuppressWarnings("all")
public class GetUrl {
    public static char a;

    public static void main(String[] args) {
        ArrayList<String> urls = getUrls("http://www.114la.com/bd.html", "href=\\\"(https?://[\\w?&=@\\./:]+)\\\"");
        Iterator<String> iterator = urls.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
        for (int i = 0; i < urls.size(); i++) {
            System.out.println(urls.get(i));
        }
    }

    public static String getUrlInfo(String address)throws MalformedURLException, IOException {
        URL url = new URL(address);
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = url.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String str;
        while ((str = reader.readLine()) != null){
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    public static ArrayList<String> getUrls(String address ,String regExp){
        ArrayList<String> strings = new ArrayList<>();
        try {
            String info = getUrlInfo(address);
            Pattern pattern = Pattern.compile(regExp);
            Matcher matcher = pattern.matcher(info);
            while (matcher.find()){
                String group = matcher.group(1);
                strings.add(group);
            }
            return strings;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
