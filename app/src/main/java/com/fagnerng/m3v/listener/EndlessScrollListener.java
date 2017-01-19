package com.fagnerng.m3v.listener;

import android.widget.AbsListView;

/**
 * Created by fagner on 18/01/2017.
 */

public abstract class EndlessScrollListener implements AbsListView.OnScrollListener {

    private int mVisibleThreshold = 20;
    private int mCurrentPage = 0;
    private int mTotalItemsPreviously = 0;

    private boolean mLoading = false;

    private int mStartingPageIndex = 0;

    public EndlessScrollListener() {
    }

    public EndlessScrollListener(int mVisibleThreshold) {
        this.mVisibleThreshold = mVisibleThreshold;
    }

    public EndlessScrollListener(int mVisibleThreshold, int startPage) {
        this.mVisibleThreshold = mVisibleThreshold;
        this.mStartingPageIndex = startPage;
        this.mCurrentPage = startPage;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
    {
        if (totalItemCount < mTotalItemsPreviously) {
            this.mCurrentPage = this.mStartingPageIndex;
            this.mTotalItemsPreviously = totalItemCount;
            if (totalItemCount == 0) { this.mLoading = true; }
        }
        if (mLoading && (totalItemCount > mTotalItemsPreviously)) {
            mLoading = false;
            mTotalItemsPreviously = totalItemCount;
            mCurrentPage++;
        }

        if (!mLoading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + mVisibleThreshold)) {
            onLoadMore(mTotalItemsPreviously, mVisibleThreshold);
            this.mTotalItemsPreviously += mVisibleThreshold;
        }
    }

    public abstract void onLoadMore(int skip, int limit);

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }
}
