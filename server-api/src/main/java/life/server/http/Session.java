package life.server.http;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 该类用来处理 客户端的发送信息问题
 */
public class Session {

    private static CloseableHttpClient httpClient = HttpClients.createDefault();





    public static CloseableHttpResponse send(HttpUriRequest httpRequest){

        CloseableHttpResponse httpResponse = null;
        try{
            httpResponse = httpClient.execute(httpRequest);
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(httpResponse != null){
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpResponse;

    }


    public static CloseableHttpResponse get(String url){
       return send(new HttpGet(url));
    }

    /**
     * json格式
     * @param url
     * @param entity
     * @return
     */
    public static CloseableHttpResponse post(String url,HttpEntity entity){
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-type","application/json");
        return send(httpPost);
    }

    public static CloseableHttpResponse post(String url,Map<String,String> map){

        String str = JSON.toJSONString(map);
        ByteArrayEntity entity = null;
        try {
            byte[] bytes = str.getBytes("UTF-8");
            entity = new ByteArrayEntity(bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return post(url,entity);


    }



}
