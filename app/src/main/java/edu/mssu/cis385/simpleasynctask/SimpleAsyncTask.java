package edu.mssu.cis385.simpleasynctask;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask  extends AsyncTask <Void, Integer, String> {

    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar>progressBar;

    SimpleAsyncTask(TextView mTextView, ProgressBar progressBar){

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressBar.get().setVisibility(View.VISIBLE);
        progressBar.get().setProgress(0);
    }

    @Override
    protected String doInBackground(Void... voids) {

        Random r = new Random();
        int n = r.nextInt(11);

        int s = n * 200;

        {
            try {
                for(int i = 0; i < n; i++){
                    progressBar.get().setProgress(((int) ((i / (float) n ) * 100)));

                Thread.sleep(200);}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            return "Awake at last after sleeping for " + s + " milliseconds!";

        }
    }


    protected void onPostExecute(String result){

        mTextView.get().setText(result);

    }

}
