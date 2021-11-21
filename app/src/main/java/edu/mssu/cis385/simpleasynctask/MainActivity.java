package edu.mssu.cis385.simpleasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.AsyncTask;

public class MainActivity extends AppCompatActivity {

        private static final String TEXT_STATE = "currentText";
        private TextView mTextView = null;
        private ProgressBar progressBar;
        private int i = 0;
        private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView1);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        if(savedInstanceState!=null){
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }

    }

    public void startTask(View view) {
        mTextView.setText(R.string.napping);

        new SimpleAsyncTask(mTextView).execute();
    }

    public void onClick(View v){
        i = progressBar.getProgress();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (i < 100){
                    i += 1;
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setProgress(i);
                    }
                });
                try{
                    Thread.sleep(100);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_STATE,
                mTextView.getText().toString());
    }
}
