package in.test.singhalenterprises.singhalenterprises;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ListAdapter extends BaseAdapter {
    String[] items;
    int[] ids;
    Context context;

    public ListAdapter(Context context, String[] items, int[] ids) {
        this.context = context;
        this.items = items;
        this.ids = ids;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        if (view == null)
            vi = LayoutInflater.from(context).inflate(R.layout.list_row, null);

        ImageView imageView = vi.findViewById(R.id.list_image);
        TextView textView = vi.findViewById(R.id.title);

        imageView.setImageResource(ids[i]);
        textView.setText(items[i]);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return vi;
    }
}
