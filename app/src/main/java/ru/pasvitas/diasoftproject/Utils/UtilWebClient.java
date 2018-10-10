package ru.pasvitas.diasoftproject.Utils;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class UtilWebClient extends WebViewClient {


        private LinkedList<IFinisherListener> listeners = new LinkedList<>();

        @SuppressWarnings("deprecation") @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @TargetApi(Build.VERSION_CODES.N) @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());

            return true;
        }

        @Override public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            for (IFinisherListener listener : listeners)
            {
                try {
                    listener.getLink(new URL(url));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

        }

        public void addListener(IFinisherListener iFinisherListener)
        {
            listeners.add(iFinisherListener);
        }

        public void removeListener(IFinisherListener iFinisherListener)
        {
            listeners.remove(iFinisherListener);
        }
}
