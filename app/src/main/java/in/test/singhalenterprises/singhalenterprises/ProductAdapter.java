package in.test.singhalenterprises.singhalenterprises;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    Context context;
    int page;

    public ProductAdapter(Context context, int page) {
        this.context = context;
        this.page = page;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_card_2, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final int temp = position;
        holder.imageView.setImageResource(ProductsContract.drawablesProducts[page][position]);
        //holder.textView.setMovementMethod(new ScrollingMovementMethod());
        holder.textView.setSelected(true);
        holder.textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        holder.textView.setSingleLine();
        holder.textView.setMarqueeRepeatLimit(10);
        holder.textView.setFocusable(true);
        holder.textView.setHorizontallyScrolling(true);
        holder.textView.setFocusableInTouchMode(true);
        holder.textView.requestFocus();
        holder.textView.setText(ProductsContract.namesProducts[page][position]);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("title", ProductsContract.namesProducts[page][temp]);
                intent.putExtra("id", ProductsContract.drawablesProducts[page][temp]);
                intent.putExtra("position", temp);
                intent.putExtra("page", page);
                intent.putExtra("EXTRA_TAG", "product");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ProductsContract.namesProducts[page].length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
