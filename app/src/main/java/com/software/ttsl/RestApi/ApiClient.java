package com.software.ttsl.RestApi;
import com.software.ttsl.Utils.SessionManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {

    public static final String BASE_URL = /*"http://27.106.119.154:28080/cpw/"*/ "http://www.epearl.in";/*"http://114.79.173.10:25437/cpw/"*/;



    public static final String HEADER_CONTENT_TYPE = "application/json";
    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        if (retrofit==null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder();
            builder.addInterceptor(logging);
            okhttp3.OkHttpClient okHttpClient = builder.build();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit;


    }

    public static Map<String,String > getHeader(){
        Map<String,String> map = new HashMap<String, String>();
        map.put("Content-Type",HEADER_CONTENT_TYPE);
        map.put("userid",SessionManager.getInstance().getUserKeyId());
        return map;
    }



    public static class MyOkHttpInterceptor implements Interceptor {
        public static final String HEADER_CONTENT_TYPE = "application/x-www-form-urlencoded";


        @Override
        public Response intercept(Chain chain) throws IOException {
            SessionManager sessionManager = SessionManager.getInstance();
            Request originalRequest = chain.request();
            Request.Builder builder = originalRequest.newBuilder();
            builder.addHeader("Content-Type", HEADER_CONTENT_TYPE);
            if(!sessionManager.getFirstAPI()) {
                builder.addHeader("Authorization", sessionManager.getAccessToken());
            }
            Request newRequest = builder.build();

            return chain.proceed(newRequest);
        }
    }
}
