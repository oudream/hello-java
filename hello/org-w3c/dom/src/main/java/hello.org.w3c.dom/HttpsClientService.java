package hello.org.w3c.dom;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;


/**
 * Created by ixyzero on 2019/11/30.
 */
public class HttpsClientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpsClientService.class);

    private static final int SUCCESS_CODE = 200;
//
//    /**
//     * 发送GET请求
//     * @param url
//     * @param nameValuePairList
//     * @return 字符串
//     * @throws Exception
//     */
//    public static String sendGet(String url, List<NameValuePair> nameValuePairList) throws Exception{
//        CloseableHttpClient client = null;
//        CloseableHttpResponse response = null;
//        try{
//            /**
//             * 创建HttpClient对象
//             */
//            client = HttpClients.createDefault();
//            /**
//             * 创建URIBuilder
//             */
//            URIBuilder uriBuilder = new URIBuilder(url);
//            /**
//             * 设置参数
//             */
//            if (nameValuePairList != null) {
//                uriBuilder.addParameters(nameValuePairList);
//            }
//            /**
//             * 创建HttpGet
//             */
//            HttpGet httpGet = new HttpGet(uriBuilder.build());
//            /**
//             * 设置请求头部编码
//             */
//            httpGet.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
//            /**
//             * 设置返回编码
//             */
//            httpGet.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
//            /**
//             * 请求服务
//             */
//            response = client.execute(httpGet);
//            /**
//             * 获取响应吗
//             */
//            int statusCode = response.getStatusLine().getStatusCode();
//
//            if (SUCCESS_CODE == statusCode){
//                HttpEntity entity = response.getEntity();
//                String result = EntityUtils.toString(entity,"UTF-8");
//                return result;
//            }else{
//                LOGGER.error("HttpClientService-statusCode: {}", statusCode);
//            }
//        }catch (Exception e){
//            LOGGER.error("HttpClientService-Exception: {}", e);
//        } finally {
//            response.close();
//            client.close();
//        }
//        return null;
//    }
//
//    /**
//     * 发送POST请求
//     * @param url
//     * @param nameValuePairList
//     * @return 字符串
//     * @throws Exception
//     */
//    public static String sendPost(String url, List<NameValuePair> nameValuePairList) throws Exception{
//        CloseableHttpClient client = null;
//        CloseableHttpResponse response = null;
//
//        try{
//            client = HttpClients.createDefault();
//            HttpPost post = new HttpPost(url);
//
//            StringEntity entity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
//            post.setEntity(entity);
//            post.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
//            post.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
//
//            response = client.execute(post);
//            int statusCode = response.getStatusLine().getStatusCode();
//            if (SUCCESS_CODE == statusCode){
//                String result = EntityUtils.toString(response.getEntity(),"UTF-8");
//                return result;
//            }else{
//                LOGGER.error("HttpClientService-statusCode：{}", statusCode);
//            }
//        }catch (Exception e){
//            LOGGER.error("HttpClientService-Exception：{}", e);
//        }finally {
//            response.close();
//            client.close();
//        }
//        return null;
//    }

//    /**
//     * 组织请求参数{参数名和参数值下标保持一致}
//     * @param params    参数名数组
//     * @param values    参数值数组
//     * @return 参数对象
//     */
//    public static List<NameValuePair> getParams(Object[] params, Object[] values){
//        /**
//         * 校验参数合法性
//         */
//        boolean flag = params.length>0 && values.length>0 && params.length == values.length;
//        if (flag) {
//            List<NameValuePair> nameValuePairList = new ArrayList<>();
//            for(int i=0; i<params.length; i++) {
//                nameValuePairList.add(new BasicNameValuePair(params[i].toString(),values[i].toString()));
//            }
//            return nameValuePairList;
//        } else {
//            LOGGER.error("HttpClientService-errorMsg：{}", "请求参数为空/参数长度不一致");
//        }
//        return null;
//    }

//
//    public static String sendHttpsGet(String url, List<NameValuePair> nameValuePairList) throws Exception{
//        CloseableHttpClient client = null;
//        CloseableHttpResponse response = null;
//
//        try{
//            PoolingHttpClientConnectionManager connManager = ConnectionManagerBuilder();
//            client = HttpClients.custom().setConnectionManager(connManager).build();
//
//            URIBuilder uriBuilder = new URIBuilder(url);
//            if (nameValuePairList != null) {
//                uriBuilder.addParameters(nameValuePairList);
//            }
//            HttpGet httpGet = new HttpGet(uriBuilder.build());
//            httpGet.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
//            httpGet.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
//
//            response = client.execute(httpGet);
//            int statusCode = response.getStatusLine().getStatusCode();
//
//            if (SUCCESS_CODE == statusCode){
//                HttpEntity entity = response.getEntity();
//                String result = EntityUtils.toString(entity, "UTF-8");
//                return result;
//            } else {
//                LOGGER.error("HttpClientService-errorCode: {}, errorMsg{}", statusCode, "GET请求失败！");
//            }
//        }catch (Exception e){
//            LOGGER.error("HttpClientService-Exception: {}", e);
//        } finally {
//            response.close();
//            client.close();
//        }
//        return null;
//    }
//
//    public static String sendHttpsPost(String url, List<NameValuePair> nameValuePairList) throws Exception{
//        CloseableHttpClient client = null;
//        CloseableHttpResponse response = null;
//
//        try{
//            PoolingHttpClientConnectionManager connManager = ConnectionManagerBuilder();
//            client = HttpClients.custom().setConnectionManager(connManager).build();
//
//            HttpPost post = new HttpPost(url);
//            StringEntity entity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
//            post.setEntity(entity);
//            post.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
//            post.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
//
//            response = client.execute(post);
//            int statusCode = response.getStatusLine().getStatusCode();
//            if (SUCCESS_CODE == statusCode){
//                String result = EntityUtils.toString(response.getEntity(),"UTF-8");
//                return result;
//            }else{
//                LOGGER.error("HttpClientService-statusCode：{}", statusCode);
//            }
//        }catch (Exception e){
//            LOGGER.error("HttpClientService-Exception：{}", e);
//        }finally {
//            response.close();
//            client.close();
//        }
//        return null;
//    }


    public static String sendHttpsPost(String url, String jsonBody) throws Exception{
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;

        try{
            PoolingHttpClientConnectionManager connManager = ConnectionManagerBuilder();
            client = HttpClients.custom().setConnectionManager(connManager).build();

            HttpPost post = new HttpPost(url);
            StringEntity entity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
            post.setEntity(entity);
//            post.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
//            post.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
            post.setHeader("Content-Type", "application/json");
            post.setHeader("x-adviewrtb-version", "2.1");

            response = client.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            if (SUCCESS_CODE == statusCode){
                String result = EntityUtils.toString(response.getEntity(),"UTF-8");
                return result;
            }else{
                LOGGER.error("HttpClientService-statusCode：{}", statusCode);
            }
        }catch (Exception e){
            LOGGER.error("HttpClientService-Exception：{}", e);
        }finally {
            response.close();
            client.close();
        }
        return null;
    }

    public static PoolingHttpClientConnectionManager ConnectionManagerBuilder() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("TLS");

        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        sslContext.init(null, new TrustManager[] { trustManager }, null);

        // 设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslContext))
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

        return connManager;
    }


//
//    public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
//        SSLContext sslContext = SSLContext.getInstance("TLS");
//
//        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
//        X509TrustManager trustManager = new X509TrustManager() {
//            @Override
//            public void checkClientTrusted(
//                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
//                    String paramString) throws CertificateException {
//            }
//
//            @Override
//            public void checkServerTrusted(
//                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
//                    String paramString) throws CertificateException {
//            }
//
//            @Override
//            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                return null;
//            }
//        };
//
//        sslContext.init(null, new TrustManager[] { trustManager }, null);
//        return sslContext;
//    }

//
//    public static void main(String[] args) {
//        String url = "https://ixyzero.com/blog/awk_sed.txt";
//        // 参数值
//        Object [] params = new Object[]{"param1","param2"};
//        // 参数名
//        Object [] values = new Object[]{"value1","value2"};
//        // 获取参数对象
//        List<NameValuePair> paramsList = HttpClientService.getParams(params, values);
//
//        // 发送get
//        String result = null;
//        try {
//            result = sendGet(url, paramsList);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("GET返回信息：\n" + result);
//
//        // 发送get
//        result = null;
//        try {
//            // result = sendHttpsGet(url, paramsList);
//            result = sendHttpsGet(url, null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("[HTTPS]GET返回信息：\n" + result);
//
//        // 发送post
//        url = "https://httpbin.org/post";
//        result = null;
//        try {
//            result = sendPost(url, paramsList);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("POST返回信息：\n" + result);
//
//        // 发送post
//        url = "https://ixyzero.com/blog/wp-comments-post.php";
//        result = null;
//        try {
//            result = sendHttpsPost(url, paramsList);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("[HTTPS]POST返回信息：\n" + result);
//
//    }
}