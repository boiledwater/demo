package com.demo.transitionanimator.feed;

/**
 * Created by HuLiZhong on 2016/12/21.
 */

public interface FeedConsumer {
    void setFeed(Feed feed);

    void handleError(String message);
}