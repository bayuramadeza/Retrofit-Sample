package teamd.binar.com.retrofit.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import teamd.binar.com.retrofit.MyNewsApp;
import teamd.binar.com.retrofit.R;
import teamd.binar.com.retrofit.adapter.ArtikelAdapter;
import teamd.binar.com.retrofit.model.Article;
import teamd.binar.com.retrofit.model.ArtikelRespons;
import teamd.binar.com.retrofit.model.Source;

public class HomeActivity extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();
    List<Article> articleList = new ArrayList<>();
    ArtikelAdapter artikelAdapter = new ArtikelAdapter(articleList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        getArticle();
    }

    private void initView() {
        RecyclerView recyclerViewArtikel = findViewById(R.id.rv_news);

        recyclerViewArtikel.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewArtikel.setAdapter(artikelAdapter);
    }

    private void getArticle() {
        MyNewsApp.services.getArticle().enqueue(new Callback<ArtikelRespons>() {
            @Override
            public void onResponse(Call<ArtikelRespons> call, Response<ArtikelRespons> response) {
                Log.d(TAG, "onResponse: "+ new Gson().toJsonTree(response));
                articleList.addAll(response.body().getArticles());
                artikelAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArtikelRespons> call, Throwable t) {
                Log.e(TAG, "onFailure: Error" + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
