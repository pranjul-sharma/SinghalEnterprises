package in.test.singhalenterprises.singhalenterprises;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProductActivity extends AppCompatActivity {

    String title;
    int section;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Montserrat-Medium.ttf")
                .setFontAttrId(R.attr.fontPath).build());
        setContentView(R.layout.activity_product);

        Intent intent = getIntent();
        String tag = intent.getStringExtra("EXTRA_TAG");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Products");

        mViewPager = (ViewPager) findViewById(R.id.container);
        setUpViewPager(mViewPager);
        if (tag.equals("home")) {
            title = intent.getStringExtra("title");
            section = intent.getIntExtra("section", 0);
            mViewPager.setCurrentItem(section);
        }
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void setUpViewPager(ViewPager upViewPager) {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < ProductsContract.category.length - 1; i++)
            sectionsPagerAdapter.addFragment(PlaceholderFragment.newInstance(i, getString(ProductsContract.category[i])), getString(ProductsContract.category[i]));

        upViewPager.setAdapter(sectionsPagerAdapter);
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";
        int section;
        String title;

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber, String title) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putString("title", title);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            section = getArguments().getInt(ARG_SECTION_NUMBER);
            title = getArguments().getString("title");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_product, container, false);
            RecyclerView recyclerView = rootView.findViewById(R.id.recycler_product);
            ProductAdapter productAdapter = new ProductAdapter(getContext(), section);
            recyclerView.setAdapter(productAdapter);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            return rootView;
        }

    }

    public static class SectionsPagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> fragsList = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragsList.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            fragsList.add(fragment);
            titles.add(title);
        }

        @Override
        public int getCount() {

            return fragsList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
