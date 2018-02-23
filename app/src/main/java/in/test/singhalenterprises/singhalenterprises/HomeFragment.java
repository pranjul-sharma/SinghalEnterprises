package in.test.singhalenterprises.singhalenterprises;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.synnapps.carouselview.CarouselView;

import static android.view.View.GONE;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    int len[] = new int[ProductsContract.category.length - 1];
    int[] drawables = new int[len.length * 3];
    int[] images = {R.drawable.img_10, R.drawable.img_2, R.drawable.img_11, R.drawable.img_3, R.drawable.img_12, R.drawable.img_1};

    public HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recycler_home);

        CarouselView carouselView = view.findViewById(R.id.carouselView);
        //carouselView.setPageCount(images.length);
        //carouselView.setImageListener(new ImageListener() {
        //@Override
        // public void setImageForPosition(int position, ImageView imageView) {
        //      imageView.setAdjustViewBounds(true);
        //  imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        // imageView.setImageResource(images[position]);
        // }
        //});
        carouselView.setVisibility(GONE);

        initDL();
        HomeCustomAdapter customAdapter = new HomeCustomAdapter(getContext(), drawables, ProductsContract.category, len);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        return view;
    }

    private void initDL() {
        for (int i = 0; i < ProductsContract.category.length - 1; i++) {
            len[i] = ProductsContract.namesProducts[i].length;
            for (int i1 = 0; i1 < 3; i1++) {
                drawables[i * 3 + i1] = ProductsContract.drawablesProducts[i][i1];
            }
        }
    }
}
