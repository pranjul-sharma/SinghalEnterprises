package in.test.singhalenterprises.singhalenterprises;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.view.View.GONE;

public class FeedbackActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Montserrat-Medium.ttf")
                .setFontAttrId(R.attr.fontPath).build());
        setContentView(R.layout.activity_feedback);

        Intent intent = getIntent();
        String tag = intent.getStringExtra("TAG");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        final TextView tv1 = (TextView) findViewById(R.id.feed_tt1);
        final TextView tv2 = (TextView) findViewById(R.id.feed_tt2);
        final EditText editMessage = (EditText) findViewById(R.id.edit_message_form);
        final EditText editSubject = (EditText) findViewById(R.id.edit_subject_form);
        final EditText editName = (EditText) findViewById(R.id.edit_name_form);
        final Button button = (Button) findViewById(R.id.button_send_form);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tag);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (tag.equals("feedback")) {

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = editName.getText().toString();
                    String subject = editSubject.getText().toString();
                    String message = editMessage.getText().toString();

                    if (name.equals("")) {
                        editName.setError("Please enter your name");
                        return;
                    }

                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:info@singhalenterprises.in"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, subject + " mail from " + name);
                    intent.putExtra(Intent.EXTRA_TEXT, message);

                    startActivity(intent);
                }
            });
        } else if (tag.equals("compose")) {
            editMessage.setHint("Write your mesage here");
            editName.setVisibility(GONE);
            editSubject.setVisibility(GONE);

            tv1.setVisibility(GONE);
            tv2.setVisibility(GONE);

            button.setText("send Message");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    SmsManager sm = SmsManager.getDefault();
                    String number = "+919810088657";
                    String msg = editMessage.getText().toString();
                    sm.sendTextMessage(number, null, msg, null, null);
                    Snackbar.make(view, "Message sent.", Snackbar.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
