package teamd.binar.com.retrofit.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import teamd.binar.com.retrofit.R;
import teamd.binar.com.retrofit.model.*;

import java.util.List;

/**
 * Created by bayuramadeza on 4/4/2018.
 */

public class ArtikelAdapter extends RecyclerView.Adapter<ArtikelAdapter.Holder> {

    private List<Article> articleList;

    public ArtikelAdapter(List<Article> articleList) {
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        //cara 1
        //ImageView imageView = holder.itemView.findViewById(R.id.iv_news);

        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        public Holder(View itemView) {
            super(itemView);
        }

        public void bind(int position) {
            Article article = articleList.get(position);
            ImageView imageBerita = itemView.findViewById(R.id.iv_news);
            TextView textJudulBerita = itemView.findViewById(R.id.tv_news);
            TextView tanggalBerita = itemView.findViewById(R.id.tv_tanggal);

            Picasso.get()
                    .load(article.getUrlToImage())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imageBerita);
            textJudulBerita.setText(article.getTitle());
            tanggalBerita.setText(String.valueOf(article.getSource().getName()));
        }
    }
}
