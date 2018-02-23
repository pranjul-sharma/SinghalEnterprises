package in.test.singhalenterprises.singhalenterprises;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

class HomeCustomAdapter extends RecyclerView.Adapter<HomeCustomAdapter.MyViewHolder> {
    int[] drawables;
    int[] len;
    int[] titles;
    Context context;
    int[] tempR = {64, 128, 179, 225, 255};
    int[] tempG = {196, 216, 229, 245, 255};
    int[] tempB = {255, 255, 252, 254, 255};

    public HomeCustomAdapter(Context context, int[] drawables, int[] titles, int[] len) {
        this.drawables = drawables;
        this.titles = titles;
        this.len = len;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.text2.setText(context.getString(titles[position]));

        holder.text1.setText(len[position] + " Products");
        int temp = position * 3;
        holder.img1.setImageBitmap(getRoundedCornerBitmap(drawables[temp], 25));
        holder.img2.setImageBitmap(getRoundedCornerBitmap(drawables[temp + 1], 25));
        holder.img3.setImageBitmap(getRoundedCornerBitmap(drawables[temp + 2], 25));
        //holder.img1.setImageResource(drawables[temp]);
        //holder.img2.setImageResource(drawables[temp+1]);
        //holder.img3.setImageResource(drawables[temp+2]);
        //holder.relativeLayout.setBackgroundColor(Color.rgb(tempR[position%3],tempG[position%3],tempB[position%3]));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra("section", position);
                intent.putExtra("title", holder.text2.getText().toString());
                intent.putExtra("EXTRA_TAG", "home");
                context.startActivity(intent);

            }
        });

    }

    public Bitmap getRoundedCornerBitmap(int bitmapId, int pixels) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    @Override
    public int getItemCount() {
        return len.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text1, text2;
        RelativeLayout relativeLayout;
        CardView cardView;
        ImageView img1, img2, img3;

        public MyViewHolder(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.card_text_1);
            text2 = itemView.findViewById(R.id.card_text_2);
            relativeLayout = itemView.findViewById(R.id.relative_card);
            img2 = itemView.findViewById(R.id.card_img_1);
            img1 = itemView.findViewById(R.id.card_img_2);
            img3 = itemView.findViewById(R.id.card_img_3);
            cardView = itemView.findViewById(R.id.card_layout);
        }
    }
}
