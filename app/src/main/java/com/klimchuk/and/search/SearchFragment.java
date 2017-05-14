package com.klimchuk.and.search;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.klimchuk.and.R;
import com.klimchuk.and.activity.BackPressedCallback;
import com.klimchuk.and.activity.MainActivity;
import com.klimchuk.and.data.Place;
import com.klimchuk.and.maps.IMaps;
import com.klimchuk.and.search.ISearch.SearchCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.klimchuk.and.data.source.StaticDataCache.getTagsStringArray;

/**
 * Created by alexey on 13.05.17.
 */

public class SearchFragment extends Fragment implements SearchContract.View, BackPressedCallback, IMaps.ShowToolbarCallback {

    @BindView(R.id.et_search)
    AutoCompleteTextView editText;

    @BindView(R.id.toolbar)
    View toolbar;

    private SearchCallback searchCallback;

    private ISearch.ClosePlaceCallback mCloseCallback;

    private SearchContract.Presenter mPresenter;

    public static SearchFragment newInstance() {

        Bundle args = new Bundle();

        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);

        ButterKnife.bind(this, view);

        searchCallback = ((MainActivity) getActivity());
        mCloseCallback = ((MainActivity) getActivity());

        mPresenter = new SearchPresenter(this);

        return view;
    }

    void onChangeFocus() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        System.out.println(editText.hasFocus());
        editText.setSelected(false);
    }

    @OnClick(R.id.btn_search)
    public void onClick(View v) {
        mPresenter.startSearch(editText.getText().toString());
    }

    @OnClick(R.id.btn_close)
    public void onCloseClick(View v) {
        mCloseCallback.onCloseClick();
    }

    @Override
    public Context getAppContext() {
        return getContext();
    }

    @Override
    public void onSearch(List<Place> place) {
        onChangeFocus();
        searchCallback.onSearch(place);
    }

    @Override
    public void setAdapter(String[] tagsStringArray) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, getTagsStringArray());
        editText.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        onChangeFocus();
    }

    @Override
    public void setToolbarVisibility(int visibility) {
        if (visibility == View.VISIBLE) {
            toolbar.animate()
                    .translationY(1)
                    .alpha(1f)
                    .setDuration(300)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            toolbar.setVisibility(View.VISIBLE);
                        }
                    });
        } else {
            toolbar.animate()
                    .translationY(0)
                    .alpha(0.0f)
                    .setDuration(300)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            toolbar.setVisibility(View.GONE);
                        }
                    });
        }
    }
}
