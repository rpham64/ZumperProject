package com.rpham64.android.zumperproject.ui.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @BindView(R.id.list_recycler_view) RecyclerView recyclerView;

    private Unbinder mUnbinder;
    private ListPresenter mPresenter;
    private ListAdapter mAdapter;

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new ListPresenter();
        mPresenter.fetch();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mPresenter.attachView(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL, false));

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
    }
}
