package edu.mssu.cis385.simpleasynctask;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.nio.channels.AsynchronousChannelGroup;
import java.util.Random;

public class SimpleAsyncTask  extends AsyncTask<Void, Void, String> {

    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> progressBar;

    SimpleAsyncTask(TextView tv){
        mTextView = new WeakReference<>(tv);
    }

    @Override
    protected String doInBackground(Void... voids) {

        Random r = new Random();
        int n = r.nextInt(11);

        int s = n * 200;

        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    protected void onPostExecute(String result){

        mTextView.get().setText(result);
    }
}
