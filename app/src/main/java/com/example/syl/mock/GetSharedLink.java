package com.example.syl.mock;

import android.net.sip.SipSession;

public interface GetSharedLink {
    void getLinkAsynk(SipSession.Listener listener);

    interface Listener{
        void onSuccess(String link);
        void onFailure(AbsError e);
    }
}
