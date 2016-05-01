package com.example.wxl.myapplication.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.wxl.myapplication.R;
import com.example.wxl.myapplication.activity.WebViewActivity;
import com.example.wxl.myapplication.adapter.NewsBasicFragmentAdapter;
import com.example.wxl.myapplication.bean.ChannelItem;
import com.example.wxl.myapplication.loader.ChannelNewsLoader;
import com.example.wxl.myapplication.widget.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsBasicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsBasicFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, LoaderManager.LoaderCallbacks<List<ChannelItem>> {
    private static final String TAB_TYPE= "tab_type";
    private static boolean REFRESH_TO_LATEST = false;

    private String mType;
    private int mPage = 1;
    private NewsBasicFragmentAdapter mAdapter;

    @BindView(R.id.swipel_news_basic)
    SwipeRefreshLayout mSwipe;
    @BindView(R.id.ryv_news_basic)
    RecyclerView mRecycler;


    public NewsBasicFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment NewsBasicFragment.
     */
    public static NewsBasicFragment newInstance(String param1) {
        NewsBasicFragment fragment = new NewsBasicFragment();
        Bundle args = new Bundle();
        args.putString(TAB_TYPE, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mType = getArguments().getString(TAB_TYPE);
        }
        Log.d("wxl", "mType: " + mType + " Oncreate");
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("wxl", "mType: " + mType + " OnCreateView");
        View view = inflater.inflate(R.layout.fragment_news_basic, container, false);
        ButterKnife.bind(this, view);
        initSwipeLayout();
        initRecyclerView();
        return view;
    }

    @Override
    public void onResume() {
        Log.d("wxl", "mType: " + mType + " OnResume");
        super.onResume();
        if (mAdapter.getData() == null)
            getLoader().getPage(1);
    }

    private void initRecyclerView() {
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // skip scroll up case
                if (dy < 0)
                    return;

                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int maxItem = manager.getItemCount();
                int lastItemPos = manager.findLastVisibleItemPosition();
                Log.d("wxl", "mType: " + mType + ", maxItem: " + maxItem + ", lastItem: " + lastItemPos);
                if (lastItemPos == maxItem - 1) {
                    getLoader().getPage(mPage++);
                }

            }
        });

        mAdapter = new NewsBasicFragmentAdapter();
        mAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = (String)view.getTag();
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
        View view = LayoutInflater.from(getContext()).inflate(R.layout.holder_footerview, null, false);
        mAdapter.setFooterView(view);
        mRecycler.setAdapter(mAdapter);
        mRecycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));

    }

    private void initSwipeLayout() {
        mSwipe.setOnRefreshListener(this);
    }


    @Override
    public void onRefresh() {
        Log.d("wxl", "onRefresh");
        REFRESH_TO_LATEST = true;
        getLoader().getPage(1);

    }


    @Override
    public Loader<List<ChannelItem>> onCreateLoader(int id, Bundle args) {
        return new ChannelNewsLoader(getContext(), mType);
    }

    @Override
    public void onLoadFinished(Loader<List<ChannelItem>> loader, List<ChannelItem> data) {
        Log.d("wxl", "mType: " + mType + " OnLoaderFinish");
        if (!isResumed())
            return;
        if (!REFRESH_TO_LATEST) {
            List<ChannelItem> orign = mAdapter.getData();
            if (orign != null) {
                orign.addAll(data);
                data = orign;
            }
        }
        REFRESH_TO_LATEST = false;
        mAdapter.setData(data);
        mSwipe.setRefreshing(false);
    }

    @Override
    public void onLoaderReset(Loader<List<ChannelItem>> loader) {

    }

    public ChannelNewsLoader getLoader() {
        return (ChannelNewsLoader) getLoaderManager().<List<ChannelItem>>getLoader(0);
    }



}
