package com.example.mom.afflilate.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mom.afflilate.R;
import com.example.mom.afflilate.adaapter.MySearchItemArrayAdapter;
import com.example.mom.afflilate.model.MySearchItem;
import com.example.mom.afflilate.utils.Utilities;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class SearchActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private ImageView mIvBack, mIvNoInternet;
    private View mNoDataView, mNoInternetView;
    private ListView lstSearchItem;
    private SearchView mSearchView;
    private MySearchItem mySearchItem;
    private ArrayList<MySearchItem> alMyContact;
    private MySearchItemArrayAdapter adpMySearchItem;
    private Context mContext;
    private ShimmerFrameLayout mShimmerFrameLayout;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setupToolbar();
        initializeViews();
    }

    private void setupToolbar() {
        try {
            Toolbar toolbar = findViewById(R.id.toolbar);
            mTitle = findViewById(R.id.tvTitle);
            setSupportActionBar(toolbar);
            mTitle.setText(getResources().getString(R.string.search));
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_search;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public int doSetSelectedPosition() {
        return 1;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setSelectedBottomTab(1);
    }

    private void initializeViews() {
        mShimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        mShimmerFrameLayout.setVisibility(View.VISIBLE);
        mNoDataView = findViewById(R.id.no_data);

        mShimmerFrameLayout.startShimmerAnimation();
        mSwipeRefreshLayout.setOnRefreshListener(this);
        lstSearchItem = findViewById(R.id.lstSearchItem);
        alMyContact = new ArrayList<>();
        getSearchIntoArrayList();
    }

    public void getSearchIntoArrayList() {
        mySearchItem = new MySearchItem();
        mySearchItem.setName("Android Developer");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("IOS Developer");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("PHP Developer");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("Kotlin Developerr");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("Java Developer");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("Net Developer");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("PHP Developer");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("Kotlin Developerr");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("Java Developer");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("Net Developer");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("PHP Developer");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("Kotlin Developerr");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("Java Developer");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("Net Developer");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("Java Developer");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("Net Developer");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("PHP Developer");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("Kotlin Developerr");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("Java Developer");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("Net Developer");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("Java Developer");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("Net Developer");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("PHP Developer");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("Kotlin Developerr");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("Java Developer");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("Net Developer");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("Java Developer");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("Net Developer");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("PHP Developer");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("Kotlin Developerr");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("Java Developer");
        alMyContact.add(mySearchItem);

        mySearchItem = new MySearchItem();
        mySearchItem.setName("Net Developer");
        alMyContact.add(mySearchItem);

        mSwipeRefreshLayout.setRefreshing(false);
        mShimmerFrameLayout.stopShimmerAnimation();
        mShimmerFrameLayout.setVisibility(View.GONE);
        adpMySearchItem = new MySearchItemArrayAdapter(SearchActivity.this, R.layout.search_list_item, alMyContact);
        lstSearchItem.setAdapter(adpMySearchItem);
        lstSearchItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                adpMySearchItem.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) myActionMenuItem.getActionView();
        mSearchView.setQueryHint(getResources().getString(R.string.title_search));

        ImageView iconSearch = mSearchView.findViewById(android.support.v7.appcompat.R.id.search_button);
        iconSearch.setColorFilter(getResources().getColor(android.R.color.black));
        ImageView iconClose = mSearchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        iconClose.setColorFilter(getResources().getColor(android.R.color.black));
        TextView textView =  mSearchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        textView.setTextColor(getResources().getColor(android.R.color.black));
        textView.setHintTextColor(getResources().getColor(android.R.color.darker_gray));

        if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == ViewCompat.LAYOUT_DIRECTION_RTL) {
            SearchView.SearchAutoComplete searchAutoComplete = mSearchView.findViewById(R.id.search_src_text);
            searchAutoComplete.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
        }
        mSearchView.setMaxWidth(Integer.MAX_VALUE);

        mSearchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTitle.setVisibility(View.INVISIBLE);
            }
        });

        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Utilities.hideKeyboard(SearchActivity.this);
                mTitle.setVisibility(View.VISIBLE);
                return false;
            }
        });

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Utilities.hideKeyboard(SearchActivity.this);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                onSubmitQuery(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    private void onSubmitQuery(String newText) {
        if (alMyContact != null) {
            if (TextUtils.isEmpty(newText)) {
                adpMySearchItem.filter("");
                lstSearchItem.clearTextFilter();
            } else {
                adpMySearchItem.filter(newText);
            }
        }
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        getSearchIntoArrayList();
    }
}
