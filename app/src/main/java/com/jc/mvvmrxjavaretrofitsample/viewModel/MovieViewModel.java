package com.jc.mvvmrxjavaretrofitsample.viewModel;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jc.mvvmrxjavaretrofitsample.R;
import com.jc.mvvmrxjavaretrofitsample.model.entity.Movie;

/**
 * Created by HaohaoChang on 2017/2/11.
 */
//单向绑定 刷新的UI部分
//它的原理是：如果设置好绑定（双向或者单向），单向：展示数据
    //双向：展示的同时能接受数据
    //这边我是把原本的数据展示的数据换掉了，如果可以的话，对照原本的项目来看，你就能发现我干了啥。
public class MovieViewModel extends BaseObservable {
    private Movie movie;

    public MovieViewModel(Movie movie) {
        this.movie = movie;
    }

    public String getCoverUrl() {
        //原本的获取CoverUrl由于没有小图，用大图替代一下，大家看看就好
        return movie.getData().get(0).getPoster();
    }


    public String getTitle() {
        return movie.getAlias();
    }

    //这里展示的豆瓣的
    public float getRating() {
        //因为top250星星还选5个的话，全都是黄的……
        //我也不太懂（没关注过），我换成了10个星星，最起码可以看出点区别来
        float myfloat = Float.parseFloat(movie.getDoubanRating());
        return myfloat;
    }

    //无所谓了，这里展示另外一个数据
    public String getRatingText(){
        return movie.getDoubanRating();
    }

    public String getYear() {
        return movie.getYear();
    }

    public String getMovieType() {
        return movie.getData().get(0).getGenre();
    }

    public String getImageUrl() {

        return movie.getData().get(0).getPoster();

    }

    @BindingAdapter({"imageUrl"})
    // 当某些属性需要自定义处理逻辑的时候可以使用 BindingAdapter
    // Glide，就像 Picasso，可以从多个源去加载和显示图片
    // 同时也兼顾缓存和在做图片处理的时候维持一个低内存消耗。
    // 它已经在 Google 官方 APP （如 Google 2015开发者大会的应用程序）中使用了，
    // 就和 Picasso 一样受欢迎。
    public static void loadImage(ImageView imageView,String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(R.drawable.cover)
                .error(R.drawable.cover)
                .into(imageView);

    }

}
