package in.test.singhalenterprises.singhalenterprises;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class WebViewActivity extends AppCompatActivity {
    WebView webView;
    LinearLayout relativeLayout;
    Toolbar toolbar;
    TextView textView;
    private ProgressDialog pd;
    private String[] urlsEcatalogue = new String[]{"https://drive.google.com/open?id=0BzgVitO8_6J4T0pzS1BFY1FlNW8", "https://drive.google.com/open?id=0BzgVitO8_6J4N05KMU43eWZTRVE", "https://drive.google.com/open?id=0BzgVitO8_6J4Tzh5YjdFcGtHcG8"};
    private String[] urlsBlog = new String[]{"http://singhalenterprises.in/2016/10/07/cyber-security-charts/",
            "http://singhalenterprises.in/2016/12/31/happy-new-year/", "http://singhalenterprises.in/2016/12/25/merry-christmas/", "http://singhalEnterprises.in"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Montserrat-Medium.ttf")
                .setFontAttrId(R.attr.fontPath).build());
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_web_view);
        int id = getIntent().getIntExtra("ITEM_CLICKED", 3);
        String action = getIntent().getStringExtra("ACTION_NAME");
        Log.v("tagg", id + " " + action);
        webView = (WebView) findViewById(R.id.webview);
        textView = (TextView) findViewById(R.id.tt_blog);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        relativeLayout = (LinearLayout) findViewById(R.id.layout_blog);
        pd = new ProgressDialog(WebViewActivity.this);
        pd.setMessage("Please wait Loading...");
        pd.setCancelable(false);
        pd.show();
        setSupportActionBar(toolbar);
        webView.setWebViewClient(new MyBrowser());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        if (action.equals("blog")) {
            relativeLayout.removeView(textView);
            getSupportActionBar().hide();
            webView.loadUrl(urlsBlog[id]);
        }
        if (action.equals("ecatalogue")) {
            getSupportActionBar().setTitle("E-catalogue");
            webView.loadUrl(urlsEcatalogue[id]);
            textView.setText("Please wait while catalogue file is being processed.");

        }

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            if (!pd.isShowing()) {
                pd.show();
            }

            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (pd.isShowing()) {
                pd.dismiss();
                relativeLayout.removeView(textView);
            }

        }
    }
}
