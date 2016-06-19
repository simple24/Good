package com.simplexu.good.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.simplexu.good.R;
import com.simplexu.good.model.bean.GoodBean;
import com.simplexu.good.presenter.impl.HomePresenterImpl;
import com.simplexu.good.ui.activity.DataActivity;
import com.simplexu.good.ui.activity.ImgActivity;
import com.simplexu.good.ui.adapter.DataAdapter;
import com.simplexu.good.ui.adapter.ImgAdapter;
import com.simplexu.good.ui.view.IHomeView;

import java.util.List;

public class HomeFragment extends Fragment implements IHomeView{

    private String title;

    private EasyRecyclerView mEasyRecyclerView;
    private ImgAdapter mImgAdapter;
    private DataAdapter mDataAdapter;

    private HomePresenterImpl mHomePresenter;

    private Handler handler = new Handler();
    private int page = 1;

    public static HomeFragment getInstance(String title){
        HomeFragment homeFragment = null;
        if(homeFragment == null){
            homeFragment = new HomeFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title",title);
            homeFragment.setArguments(bundle);
        }
        return homeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        title = bundle.getString("title");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mEasyRecyclerView = (EasyRecyclerView) view.findViewById(R.id.layout_home);

        initView();
        return view;
    }


    private void initView() {

        mHomePresenter = new HomePresenterImpl(this);

        if (title.equals("福利")){
            mHomePresenter.getData("福利",20,1);
            StaggeredGridLayoutManager staggeredGridLayoutManager = new
                    StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
            mEasyRecyclerView.setLayoutManager(staggeredGridLayoutManager);
            mImgAdapter = new ImgAdapter(getContext());
            mEasyRecyclerView.setAdapterWithProgress(mImgAdapter);
            mImgAdapter.setMore(R.layout.layout_progress,loadMoreListener);
            startActivity(mImgAdapter);
        }else {
            if (title.equals("安卓")){
                mHomePresenter.getData("Android",20,1);
            }else if (title.equals("ios")){
                mHomePresenter.getData("iOS",20,1);
            }else if (title.equals("休闲")){
                mHomePresenter.getData("休息视频",20,1);
            }else if (title.equals("前端")){
                mHomePresenter.getData("前端",20,1);
            }
            mEasyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mDataAdapter = new DataAdapter(getContext());
            mEasyRecyclerView.setAdapterWithProgress(mDataAdapter);
            mDataAdapter.setMore(R.layout.layout_progress,loadMoreListener);
            startActivity(mDataAdapter);
        }
        mEasyRecyclerView.setRefreshListener(onRefreshListener);
    }

    public void startActivity(final RecyclerArrayAdapter<GoodBean.Result> adapter){
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (title.equals("福利")){
                    Intent intent = new Intent(getContext(),ImgActivity.class);
                    intent.putExtra("Url",adapter.getItem(position).getUrl());
                    intent.putExtra("Desc",adapter.getItem(position).getDesc());
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(getContext(),DataActivity.class);
                    intent.putExtra("Url",adapter.getItem(position).getUrl());
                    intent.putExtra("Desc",adapter.getItem(position).getDesc());
                    intent.putExtra("PublishedAt",adapter.getItem(position).getPublishedAt());
                    intent.putExtra("Who",adapter.getItem(position).getWho());
                    intent.putExtra("Type",adapter.getItem(position).getType());
                    startActivity(intent);
                }

            }
        });

    }

    @Override
    public void showProgress() {
        mEasyRecyclerView.showProgress();
    }

    @Override
    public void addDataView(List<GoodBean.Result> results) {

        if (title.equals("福利")){
            mImgAdapter.addAll(results);
        }else {
            mDataAdapter.addAll(results);
        }
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError() {
        mEasyRecyclerView.showError();

    }

    public SwipeRefreshLayout.OnRefreshListener onRefreshListener =
            new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (title.equals("安卓")){
                        mDataAdapter.clear();
                        mHomePresenter.getData("Android",20,1);
                    }else if (title.equals("ios")){
                        mDataAdapter.clear();
                        mHomePresenter.getData("iOS",20,1);
                    }else if (title.equals("休闲")){
                        mDataAdapter.clear();
                        mHomePresenter.getData("休息视频",20,1);
                    }else if (title.equals("前端")){
                        mDataAdapter.clear();
                        mHomePresenter.getData("前端",20,1);
                    }else if (title.equals("福利")) {
                        mImgAdapter.clear();
                        mHomePresenter.getData("福利", 20, 1);
                    }
                    page = 2;
                }
            },1000);
        }
    };

    public RecyclerArrayAdapter.OnLoadMoreListener loadMoreListener =
            new RecyclerArrayAdapter.OnLoadMoreListener() {
        @Override
        public void onLoadMore() {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (title.equals("安卓")){
                        mHomePresenter.getData("Android",20,page);
                    }else if (title.equals("ios")){
                        mHomePresenter.getData("iOS",20,page);
                    }else if (title.equals("休闲")){
                        mHomePresenter.getData("休息视频",20,page);
                    }else if (title.equals("前端")){
                        mHomePresenter.getData("前端",20,page);
                    }else if (title.equals("福利")) {
                        mHomePresenter.getData("福利", 20, page);
                    }
                    page++;
                }
            },1000);
        }
    };

}
