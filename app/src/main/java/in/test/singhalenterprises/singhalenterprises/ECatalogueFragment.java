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

import static android.view.View.GONE;


public class ECatalogueFragment extends Fragment {
    int[] images = {R.drawable.img_10, R.drawable.img_2, R.drawable.img_11, R.drawable.img_3, R.drawable.img_12, R.drawable.img_1};
    int[] drawables = {R.drawable.ic_picture_as_pdf_black_24dp, R.drawable.ic_picture_as_pdf_black_24dp, R.drawable.ic_picture_as_pdf_black_24dp};
    String names[] = {"View Chart", "View Catalogue", "Rifle Rack Catalogue"};

    public ECatalogueFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ecatalogue, container, false);
        CarouselView carouselView = view.findViewById(R.id.carouselView);
        /*carouselView.setPageCount(images.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setImageResource(images[position]);
            }
        });
        */
        carouselView.setVisibility(GONE);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_blog);
        recyclerView.setAdapter(new BlogAdapter(getContext(), drawables, names, "ecatalogue"));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        return view;
    }
}
