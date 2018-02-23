package in.test.singhalenterprises.singhalenterprises;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by pranjul on 10/10/2017.
 */

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.MyView> {
    Context context;
    int[] drawables;
    String[] names;
    String tag;

    BlogAdapter(Context context, int[] drawables, String[] names, String tag) {
        this.context = context;
        this.drawables = drawables;
        this.names = names;
        this.tag = tag;
    }

    @Override
    public BlogAdapter.MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyView(LayoutInflater.from(context).inflate(R.layout.card_blog, parent, false));
    }

    private boolean isNetworkEnabled() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(HomeActivity.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onBindViewHolder(BlogAdapter.MyView holder, final int position) {
        holder.imageView.setImageResource(drawables[position]);
        holder.textView.setText(names[position]);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkEnabled()) {
                    Intent intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra("ITEM_CLICKED", position);
                    intent.putExtra("ACTION_NAME", tag);
                    context.startActivity(intent);
                } else {
                    Snackbar.make(view, "No Internet Connection!!!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            }
        });

        if (tag.equals("knowus")) {

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (position) {
                        case 0:
                            Snackbar.make(view, "google app store link will be given here", Snackbar.LENGTH_LONG).show();
                            break;
                        case 1:
                            final Intent intent = new Intent(context, ProductDetailActivity.class);
                            intent.putExtra("EXTRA_TAG", "know us");
                            context.startActivity(intent);
                            break;
                        case 2:
                            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
                            int width = metrics.widthPixels;
                            Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.custom_dialog);
                            dialog.getWindow().setLayout((6 * width) / 7, WindowManager.LayoutParams.WRAP_CONTENT);
                            dialog.setCancelable(true);
                            final Button call = dialog.findViewById(R.id.call);
                            call.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    boolean check = KnowUsFragment.checkPermission(context, "phone");
                                    if (check) {
                                        KnowUsFragment.intentCall(context);
                                    }
                                }
                            });

                            Button message = dialog.findViewById(R.id.message);
                            message.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    boolean check1 = KnowUsFragment.checkPermission(context, "msg");
                                    if (check1) {
                                        KnowUsFragment.intentMsg(context);
                                    }
                                }
                            });
                            dialog.show();
                            break;
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public class MyView extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        CardView cardView;

        public MyView(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_card_blog);
            cardView = itemView.findViewById(R.id.cardView_blog);
            imageView = itemView.findViewById(R.id.image_card_blog);
        }
    }
}
