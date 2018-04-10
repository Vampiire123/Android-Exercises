package com.example.syl.mock;

import com.example.syl.mock.model.AbsError;

public interface GetSharedLink {
    String getLink();
    void getLinkAsync(Listener listener);

    interface Listener{
        void onSuccess(String link);
        void onFailure(AbsError e);
    }
}
