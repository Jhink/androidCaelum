package br.com.caelum.cadastro.support;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by android5497 on 09/09/15.
 */
public class WebClient {

    private static final String URL = "http://www.caelum.com.br/mobile";

    public String post(String json){
       try{
           DefaultHttpClient client = new DefaultHttpClient();
           HttpPost post = new HttpPost(URL);
           post.setEntity(new StringEntity(json));
           post.setHeader("content-type","application/json");
           post.setHeader("accept","application/json");
           HttpResponse response = client.execute(post);
           HttpEntity entity = response.getEntity();

           return EntityUtils.toString(entity);
       } catch (ClientProtocolException e) {
           e.printStackTrace();
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }

       return null;
    }

}
