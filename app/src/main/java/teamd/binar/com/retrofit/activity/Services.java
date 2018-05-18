package teamd.binar.com.retrofit.activity;

import retrofit2.Call;
import retrofit2.http.GET;
import teamd.binar.com.retrofit.model.ArtikelRespons;

/**
 * Created by bayuramadeza on 4/3/2018.
 */

public interface Services {
    @GET("v2/top-headlines?country=id&category=business&apiKey=b6dd86368b884906ad634bb72e268fbf")
    Call<ArtikelRespons> getArticle();
}
