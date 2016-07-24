package com.simplexu.good.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.simplexu.good.R;
import com.simplexu.good.model.CollectBean;
import com.simplexu.good.ui.activity.DataActivity;
import com.simplexu.good.ui.adapter.CollectAdapter;
import com.simplexu.good.utils.CollectDB;

import java.util.List;

public class CollectFragment extends Fragment {

    private EasyRecyclerView mEasyRecyclerView;
    private TextView mTextView;

    private CollectAdapter mCollectAdapter;
    private List<CollectBean> mCollectBeen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collect, container, false);
        mTextView = (TextView) view.findViewById(R.id.tv_snackbar);
        init(view);
        onListener();
        return view;
    }

    private void init(View view) {
        mCollectBeen = CollectDB.getInstance(getContext()).loadCollect();
        mEasyRecyclerView = (EasyRecyclerView) view.findViewById(R.id.layout_collect);
        mEasyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mCollectAdapter = new CollectAdapter(getContext());
        mCollectAdapter.addAll(mCollectBeen);
        mEasyRecyclerView.setAdapterWithProgress(mCollectAdapter);
    }

    private void onListener() {
        mCollectAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(),DataActivity.class);
                intent.putExtra("Url",mCollectAdapter.getItem(position).getUrl());
                intent.putExtra("Desc",mCollectAdapter.getItem(position).getDesc());
                intent.putExtra("PublishedAt",mCollectAdapter.getItem(position).getPublishedAt());
                intent.putExtra("Who",mCollectAdapter.getItem(position).getWho());
                intent.putExtra("Type",mCollectAdapter.getItem(position).getType());
                startActivity(intent);
            }
        });
        mCollectAdapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemClick(final int position) {

                new AlertDialogWrapper.Builder(getContext())
                        .setTitle("请确认是否取消收藏")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                CollectDB.getInstance(getContext()).deleteFavourite(mCollectBeen.get(position));
                                mCollectBeen = CollectDB.getInstance(getContext()).loadCollect();
                                mCollectAdapter.clear();
                                mCollectAdapter.addAll(mCollectBeen);
                                mEasyRecyclerView.setAdapterWithProgress(mCollectAdapter);
                                Snackbar.make(mTextView,"文章已经取消收藏",Snackbar.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
                return true;
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        mCollectAdapter.clear();
        mCollectAdapter.addAll(mCollectBeen);
        mEasyRecyclerView.setAdapterWithProgress(mCollectAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
