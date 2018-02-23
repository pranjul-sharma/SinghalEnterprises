package in.test.singhalenterprises.singhalenterprises;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by pranjul on 10/9/2017.
 */

public class KnowUsFragment extends Fragment {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 456;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 123;
    String[] titles = {"Rate Us", "Who we are ", "Contact us"};
    int[] imgs = {R.drawable.ic_star_black_24dp, R.drawable.logo, R.drawable.ic_message_black_24dp};

    public KnowUsFragment() {
    }

    public static boolean checkPermission(final Context context, String str) {
        String per = null;
        int req = 0;
        String message = null;

        if (str.equals("phone")) {
            per = Manifest.permission.CALL_PHONE;
            req = MY_PERMISSIONS_REQUEST_CALL_PHONE;
            message = "Enable call permission to make phone call.";

        } else if (str.equals("msg")) {
            per = Manifest.permission.SEND_SMS;
            req = MY_PERMISSIONS_REQUEST_SEND_SMS;
            message = "Enable message permission to send message.";
        }
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, per) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.CALL_PHONE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage(message);
                    final String finalPer = per;
                    final int finalReq = req;
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{finalPer}, finalReq);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{per}, req);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static void intentCall(Context context) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+919810088657"));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        context.startActivity(intent);
    }

    public static void intentMsg(Context context) {
        Intent intent1 = new Intent(context, FeedbackActivity.class);
        intent1.putExtra("TAG", "compose");
        context.startActivity(intent1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_know_us, container, false);
        //ListView lv = view.findViewById(R.id.list);
        RecyclerView rv = view.findViewById(R.id.recycler_know_us);
        BlogAdapter adapter = new BlogAdapter(getContext(), imgs, titles, "knowus");
        rv.setAdapter(adapter);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        /*ListAdapter listAdapter = new ListAdapter(getContext(),titles,imgs);
        listAdapter.notifyDataSetChanged();
        lv.setAdapter(listAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Snackbar.make(view,"google app store link will be given here",Snackbar.LENGTH_LONG).show();
                        break;
                    case 1:
                        final Intent intent = new Intent(getContext(),ProductDetailActivity.class);
                        intent.putExtra("EXTRA_TAG","know us");
                        startActivity(intent);
                        break;
                    case 2:
                        DisplayMetrics metrics = getResources().getDisplayMetrics();
                        int width = metrics.widthPixels;
                       // Dialog dialog = new Dialog(getContext());
                       // dialog.setContentView(R.layout.custom_dialog);
                        dialog.getWindow().setLayout((8*width)/9, WindowManager.LayoutParams.WRAP_CONTENT );
                        dialog.setCancelable(true);
                        Button call = dialog.findViewById(R.id.call);
                        call.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                boolean check=checkPermission("phone");
                                if(check){
                                   intentCall();
                                }
                            }
                        });

                        Button message = dialog.findViewById(R.id.message);
                        message.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                boolean check1=checkPermission("msg");
                                if (check1){
                                    intentMsg();
                                }
                            }
                        });
                        dialog.show();
                        break;
                }
            }
        });
*/
        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    intentCall(getContext());
                } else {
                    //code for deny
                    Toast.makeText(getContext(), "User denied to grant permission", Toast.LENGTH_SHORT).show();
                }
                break;
            case MY_PERMISSIONS_REQUEST_SEND_SMS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    intentMsg(getContext());
                } else {
                    //code for deny
                    Toast.makeText(getContext(), "User denied to grant permission", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}

