package rest;


import models.Item;
import models.shopResult;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ashiq Uz Zoha on 9/13/15.
 * Dhrubok Infotech Services Ltd.
 * ashiq.ayon@gmail.com
 */
public class trshopClient {

    private static GitApiInterface gitApiInterface ;
    private static String baseUrl = "http://shop.trqq.com/" ;

    public static GitApiInterface getClient() {
        if (gitApiInterface == null) {

/*
            OkHttpClient okClient = new OkHttpClient();
           okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });*/


            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                   // .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            gitApiInterface = client.create(GitApiInterface.class);
        }
        return gitApiInterface ;
    }

    public interface GitApiInterface {

        //@Headers("User-Agent: Retrofit2.0mihua-App")
 /*       @GET("/mobile/index.php?act=goods&op=goods_list")
        Call<GoodsBean> getGoods_list( @Body String page);*/

        @GET("/mobile/index.php")
        Call<shopResult> getGoods_list(@Query("act") String act, @Query("op") String op, @Query("page") String page);

        @GET("/mobile/index.php?act=goods&op=goods_list")
        Call<shopResult> getGoods_list( @Query("page") String page);

        @POST("/user/create")
        Call<Item> createUser(@Body String name, @Body String email);

        @PUT("/user/{id}/update")
        Call<Item> updateUser(@Path("id") String id, @Body Item user);
    }

}
