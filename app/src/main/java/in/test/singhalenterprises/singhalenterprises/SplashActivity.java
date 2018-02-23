package in.test.singhalenterprises.singhalenterprises;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SplashActivity extends AppCompatActivity {

    AnimationDrawable animationDrawable;
    RelativeLayout relativeLayout;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        relativeLayout = (RelativeLayout) findViewById(R.id.splash_layout);
        iv = (ImageView) findViewById(R.id.imageView);
        animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(2000);
        iv.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in));
        animationDrawable.start();

       /* iv.animate()
            .scaleXBy(6)
            .scaleYBy(6)
            .setDuration(700)
            .alpha(2)
            .setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    float[] heigthAndWidth={120,120};
                    float minusWidth = 30,minusHeight = 10;
                    iv.animate()
                            .scaleXBy(-6f).scaleYBy(-6f)
                            .alpha(.1f)
                            .translationX(((heigthAndWidth[0] / 2) - minusWidth))
                            .translationY(-((heigthAndWidth[1] / 2) - minusHeight))
                            .setDuration(1000)
                            .setListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {
                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                // remove image view from framlayout
                                }
                                @Override
                                public void onAnimationCancel(Animator animation) {
                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {
                                }
                            }).start();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            }).start();
*/
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int temp = 0;
                while (temp < 6000) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    temp += 100;
                }
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        thread.start();
    }
}
