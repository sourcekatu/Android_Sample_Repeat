// 参考
// https://qiita.com/aftercider/items/81edf35993c2df3de353

package sample.sample_repeat;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView mtxtView;
    Button mbtnStart;
    Button mbtnStop;
    int time = 0;

    final Handler mHandler = new Handler();
    final Runnable r = new Runnable() {
        @Override
        public void run() {
            time++;
            mtxtView.setText(String.valueOf(time));

            // UIスレッド
            mHandler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtxtView = findViewById(R.id.txtTime);
        mbtnStart = findViewById(R.id.btnStart);
        mbtnStop = findViewById(R.id.btnStop);

        mbtnStart.setOnClickListener(this);
        mbtnStop.setOnClickListener(this);
   }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnStart:
            {
                // Start押下中に再度押下すると、処理が2倍になる。押すたびに処理が増えていく。
                mHandler.post(r);
                break;
            }
            case R.id.btnStop:
            {
                mHandler.removeCallbacks(r);
                break;
            }
        }
    }
}