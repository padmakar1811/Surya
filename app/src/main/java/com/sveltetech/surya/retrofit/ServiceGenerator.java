package com.sveltetech.surya.retrofit;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sveltetech.surya.BuildConfig;
import com.sveltetech.surya.app.AppConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServiceGenerator {
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


    private static final Gson gson = new GsonBuilder().setLenient().create();
    private static Retrofit.Builder builderFile = new Retrofit.Builder().baseUrl("https://apis.EXAMPLE.in/v1/").addConverterFactory(GsonConverterFactory.create(gson));
    private static Retrofit retrofit = builderFile.build();

    private static Retrofit.Builder builderFile2 = new Retrofit.Builder().baseUrl("https://apis.EXAMPLE.in/v1/").addConverterFactory(GsonConverterFactory.create(gson));
    private static Retrofit retrofit2 = builderFile2.build();
    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    private static <S> S createService(Class<S> serviceClass, String username, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apis.EXAMPLE.in/v1/")
                .client(provideOkHttpClient())
                .addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(serviceClass);
    }

    // TODO SERVICE FOR PASS TOKEN
    public static <S> S createServiceUtilityV2(Class<S> serviceClass) {
        if (!TextUtils.isEmpty(AppConfig.authToken)) {
//            String authToken = Credentials.basic(BuildConfig.M2P_USERNAME, BuildConfig.M2P_PASSWORD);
            String authToken = AppConfig.authToken;
            return createServiceUtilityV2(serviceClass, authToken);
        }

        return createServiceUtilityV2(serviceClass, null);
    }

    private static <S> S createServiceUtilityV2(
            Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(authToken);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
                httpClient.readTimeout(60, TimeUnit.SECONDS);
                httpClient.connectTimeout(120, TimeUnit.SECONDS);
                builderFile.client(httpClient.build());
                retrofit2 = builderFile2.build();
            }
        }

        return retrofit.create(serviceClass);
    }

//    private static Cache provideCache() {
//        Cache cache = null;
//        try {
//            cache = new Cache(new File(AppController.getInstance().getCacheDir(), "hs-feed-cache"),
//                    100 * 1024 * 1024); // 10 MB
//            Log.d(TAG, "provideCache: created..!");
//        } catch (Exception e) {
//            Log.e(TAG, "Could not create Cache!");
//        }
//        return cache;
//    }

    private static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder().readTimeout(1, TimeUnit.MINUTES).connectTimeout(2, TimeUnit.MINUTES)
                .build();
    }

    public static <S> S createServiceFile(Class<S> serviceClass) {
        httpClient.readTimeout(60, TimeUnit.SECONDS);
        httpClient.connectTimeout(120, TimeUnit.SECONDS);

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder;
            requestBuilder = original.newBuilder()
                    .header("content-type", "application/json")
                    .method(original.method(), original.body());
            Request request = requestBuilder.build();
            return chain.proceed(request);
        });
        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builderFile.client(client).build();
        return retrofit.create(serviceClass);
    }

    static class AuthenticationInterceptor implements Interceptor {
        private String authToken;

        AuthenticationInterceptor(String token) {
            this.authToken = token;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request.Builder builder = original.newBuilder()
                    .header("Authorization", "Bearer " + authToken);

            Request request = builder.build();
            return chain.proceed(request);
        }
    }

}