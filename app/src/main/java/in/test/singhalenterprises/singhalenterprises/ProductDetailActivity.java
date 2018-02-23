package in.test.singhalenterprises.singhalenterprises;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.klinker.android.sliding.SlidingActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProductDetailActivity extends SlidingActivity {


    @Override
    public void init(Bundle savedInstanceState) {

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Montserrat-Medium.ttf")
                .setFontAttrId(R.attr.fontPath).build());
        setContent(R.layout.activity_product_detail);
        TextView tt1 = (TextView) findViewById(R.id.text_product_category);
        TextView tt2 = (TextView) findViewById(R.id.text_product_desc);
        TextView tt3 = (TextView) findViewById(R.id.text_product_tag);

        Intent intent = getIntent();
        String tag = intent.getStringExtra("EXTRA_TAG");
        if (tag.equals("know us")) {

            TextView tv1 = (TextView) findViewById(R.id.item_text1);
            tv1.setText("Who we are ?");
            TextView tv2 = (TextView) findViewById(R.id.item_text2);
            tv2.setText("Client Satisfaction");
            TextView tv3 = (TextView) findViewById(R.id.item_text3);
            tv3.setVisibility(View.GONE);
            tt2.setVisibility(View.GONE);
            setImage(R.drawable.logo);
            setTitle("About us");
            setImageOverlay(0.5f);
            setPrimaryColors(R.color.colorPrimary, R.color.colorPrimaryDark);
            tt1.setText(R.string.who_we_are_details);
            tt3.setText(R.string.client_satisfaction_details);
        } else if (tag.equals("product")) {
            String title = intent.getStringExtra("title");
            int id = intent.getIntExtra("id", R.drawable.logo);
            int pos = intent.getIntExtra("position", 0);
            int page = intent.getIntExtra("page", 0);
            setTitle(title);
            setTitleTextColor(R.color.colorBlack);
            setImage(id);
            //setImageOverlay(-50000000f);
            tt1.setText(ProductsContract.category[page]);
            tt3.setText(ProductsContract.namesProducts[page][pos]);
            tt2.setText(ProductsContract.detailsProduct[page][pos]);
        }


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
