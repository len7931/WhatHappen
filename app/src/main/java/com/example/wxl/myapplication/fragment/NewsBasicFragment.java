package com.example.wxl.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wxl.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsBasicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsBasicFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAB_TYPE= "tab_type";

    private String mType;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_basic, container, false);
        ButterKnife.bind(this, view);
        initSwipeLayout();
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // TODO handle recyclerview scrolling
            }
        });
    }

    private void initSwipeLayout() {
        mSwipe.setOnRefreshListener(this);
    }


    @Override
    public void onRefresh() {
        // TODO handle refresh

    }
}
