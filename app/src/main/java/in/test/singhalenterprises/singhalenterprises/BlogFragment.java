package in.test.singhalenterprises.singhalenterprises;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.synnapps.carouselview.CarouselView;

public class BlogFragment extends Fragment {

    int[] drawables = {R.drawable.cyber, R.drawable.happy_new_year, R.drawable.merr_christmas};
    String[] names = {"Cyber security", "Happy New Year", "Mery Christmas"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ecatalogue, container, false);
        CarouselView carouselView = view.findViewById(R.id.carouselView);
        carouselView.setVisibility(View.GONE);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_blog);
        recyclerView.setAdapter(new BlogAdapter(getContext(), drawables, names, "blog"));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        return view;
    }
}
