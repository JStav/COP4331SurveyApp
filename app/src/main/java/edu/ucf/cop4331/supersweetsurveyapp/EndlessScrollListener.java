package edu.ucf.cop4331.supersweetsurveyapp;

import android.widget.AbsListView;

public abstract class EndlessScrollListener implements AbsListView.OnScrollListener {

    // Minimum amount of items preloaded
    private int loadThreshold = 5;

    private int startingPageIndex = 0;

    private int currentPage = 0;

    // Previous amount of items on last load
    private int previousItemCount = 0;

    // True while waiting for more data to load
    private boolean loading = true;

    public EndlessScrollListener(){

    }

    public EndlessScrollListener (int loadThreshold){
        this.loadThreshold = loadThreshold;
    }

    public EndlessScrollListener(int loadThreshold, int startPage){
        this.loadThreshold = loadThreshold;
        this.startingPageIndex = startPage;
        this.currentPage = startPage;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        // Check to see if the item count is zero but the previous item count isn't. If so we have to reset to the initial state.
        if (totalItemCount < previousItemCount) {
            this.currentPage = this.startingPageIndex;
            this.previousItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }
        }

        // If we are still loading, check to see if the data count increased, if so we are done loading. Therefore we update
        // page count and previousItemCount
        if (loading && (totalItemCount > previousItemCount)) {
            loading = false;
            previousItemCount = totalItemCount;
            currentPage++;
        }

        // If we are not loading, check if we passed the load threshold, if we did, we need to load more data.
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + loadThreshold)) {
            onLoadMore(currentPage + 1, totalItemCount);
            loading = true;
        }
    }

    //TODO: Implement onLoadMore when using the EndlessScrollListener
    public abstract void onLoadMore(int page, int totalItemsCount);

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // Don't take any action on changed
    }
}
