package com.rpham64.android.zumperproject.ui.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.rpham64.android.zumperproject.R;
import com.rpham64.android.zumperproject.models.Restaurant;
import com.rpham64.android.zumperproject.ui.utils.adapters.ListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Rudolf on 4/2/2017.
 */

public class ListFragment extends Fragment implements ListPresenter.View {

    private static final String TAG = ListFragment.class.getName();

    public static final int offset = 6;

    @BindView(R.id.list_recycler_view) UltimateRecyclerView recyclerView;

    private Unbinder mUnbinder;
    private ListPresenter mPresenter;
    private ListAdapter mAdapter;

    private String token;
    private int currentPage;

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new ListPresenter();
        mPresenter.fetchRestaurants();

        currentPage = 1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mPresenter.attachView(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL, false));
        recyclerView.reenableLoadmore();
        recyclerView.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore(int itemsCount, int maxLastVisiblePosition) {
                if (maxLastVisiblePosition >= itemsCount - offset) {

                    Log.i(TAG, "load more");
                    Log.i(TAG, "Items Count: " + itemsCount);
                    Log.i(TAG, "Last position: " + maxLastVisiblePosition);

                    // Load more
                    mPresenter.fetchRestaurants();

                }
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void showRestaurants(List<Restaurant> restaurants) {

        if (isAdded() && mAdapter == null) {
            mAdapter = new ListAdapter(getContext(), restaurants);
            recyclerView.setAdapter(mAdapter);
        }

        Log.i(TAG, "Size: " + restaurants.size());

        currentPage++;

        if (currentPage == 1) {
            // Set list of restaurants
            mAdapter.setRestaurants(restaurants);
        } else {
            // Add to set
            mAdapter.addRestaurants(restaurants);
        }
    }
}
