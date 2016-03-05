package com.iayon.retrofit20tutorial;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import models.GoodsBean;
import models.Item;
import models.shopResult;
import rest.RestClient;
import rest.trshopClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private UserAdapter adapter ;
    GoodsAdapter mGoodsAdapter;
    List<Item> Users ;
    List<GoodsBean> Goods ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = (ListView) findViewById(R.id.listView);
        Users = new ArrayList<Item>();
        Goods= new ArrayList<GoodsBean>();

        final ProgressDialog dialog = ProgressDialog.show(this, "", "loading...");
        RestClient.GitApiInterface service = RestClient.getClient();
        Call<String> call = service.getUsersNamedTom("weiss");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                dialog.dismiss();
                Log.i("MainActivity", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    String result = response.body();
                    Log.i("MainActivity", "response = " + result);
                    //Users = result.getItems();
                    Log.i("MainActivity", "Items = " +response.message());
                    //adapter = new UserAdapter(MainActivity.this, Users);
                    //listView.setAdapter(adapter);
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                dialog.dismiss();
            }
        });
        // 取消GitResult
        //call.cancel();
        trshopClient.GitApiInterface trshopclient = trshopClient.getClient();
        Call<shopResult> shopResultCall = trshopclient.getGoods_list("10");
        shopResultCall.enqueue(new Callback<shopResult>() {
            @Override
            public void onResponse(Call<shopResult> call, Response<shopResult> response) {
                dialog.dismiss();
                Log.i("MainActivity", "GoodsBeanStatus Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    shopResult result = response.body();

                    Log.i("MainActivity", "GoodsBeanresponse = " + new Gson().toJson(result));
                   // Users = result.getItems();
                    Log.i("MainActivity", "GoodsBeanItems = " + result.getDatas().getGoods_list().size());
                    mGoodsAdapter = new GoodsAdapter(MainActivity.this, result.getDatas().getGoods_list());
                    listView.setAdapter(mGoodsAdapter);
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors

                }
            }

            @Override
            public void onFailure(Call<shopResult> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
