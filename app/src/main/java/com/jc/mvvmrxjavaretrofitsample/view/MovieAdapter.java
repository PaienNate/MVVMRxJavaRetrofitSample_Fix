package com.jc.mvvmrxjavaretrofitsample.view;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jc.mvvmrxjavaretrofitsample.R;
import com.jc.mvvmrxjavaretrofitsample.databinding.MovieItemBinding;
import com.jc.mvvmrxjavaretrofitsample.model.entity.Movie;
import com.jc.mvvmrxjavaretrofitsample.viewModel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HaohaoChang on 2017/2/11.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.BindingHolder> {
    private List<Movie> movies;
    //对movies进行初始化
    public MovieAdapter() {
        movies = new ArrayList<>();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //这里是预先开启了DataBinding，然后创建时，将movie_item加载上来
        //只是下面这个MovieItemBinding是怎么回事，自动生成？！
        MovieItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.movie_item, parent, false);
        return new BindingHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        //创建新的movieViewModel
        MovieViewModel movieViewModel = new MovieViewModel(movies.get(position));
        //设置对应的ViewModel
        holder.itemBinding.setViewModel(movieViewModel);
    }

    @Override
    //获取数量用的代码
    public int getItemCount() {
        return movies.size();
    }
    //添加电影，并告知UI库去添加，添加在最后一位
    public void addItem(Movie movie) {
        movies.add(movie);
        notifyItemInserted(movies.size() - 1);
    }
    //清空电影，同样告诉它变化（观察者模式，被观察者需要告诉观察者我动了）
    public void clearItems() {
        movies.clear();
        notifyDataSetChanged();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private MovieItemBinding itemBinding;

        public BindingHolder(MovieItemBinding itemBinding) {
            super(itemBinding.cardView);
            this.itemBinding = itemBinding;
        }
    }
}
