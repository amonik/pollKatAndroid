package com.example.amon.pollkat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Presenter extends ActionBarActivity {
    String line = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presenter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_presenter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static String TAG = "IP";
    public void Send(View view) {
        int id = view.getId();


        if (id == R.id.pollKatSendToServerButton) {
            EditText editText = (EditText) findViewById(R.id.pollKatPresenterQuestionBoxID);
            String q = editText.getText() + "";
            final String qns = q.replace(" ", "%20");
            //new PostQuestion().execute(qns);
        //}
    //}
            Thread thrd = new Thread(){
                @Override
                public void  run(){
                    try {

                        SharedPreferences settings = getSharedPreferences("pollKatIP",
                                Context.MODE_PRIVATE);
                        String ip = settings.getString("IP", null);
                        Log.d(TAG, ip);
                        //removed this now in shared preferences.
                        //String ip = "http://192.168.252.28:8080/";

                        URL url = new URL(ip+"post_question/"+qns);
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        readStream(con.getInputStream());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            };
            thrd.start();
        }
    }
    private void readStream(InputStream in) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                Log.d("pollKAT", line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}////works perfectly
 /*   class PostQuestion extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... que) {//String... means varied number of inputs & is an array
            // automatically done in another thread
            try {
                String ip = "http://192.168.252.28:8080/";

                URL url = new URL(ip + "post_question/" + que);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                readStream(con.getInputStream());
                if(line.equals("Success!"))
                    return "Posted";
                else
                    return "Failed";
            } catch (Exception e) {
                e.printStackTrace();
                return "Failed";
            }
        }

        @Override
        protected void onPostExecute(String result) {//automatically scheduled in UI thread.
            super.onPostExecute(result);
            Toast.makeText(Presenter.this, result, Toast.LENGTH_LONG).show();
        }

        private void readStream(InputStream in) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(in));

                while ((line = reader.readLine()) != null) {
                    Log.d("pollKAT", line);

                }
            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }

            }
        }
    }
}*/
/*class NetworkThrd implements Runnable{




    @Override
    public void run() {//http://192.168.252.28:8080/post_question/q2
        try {
            String ip = "http://192.168.252.28:8080/";

            URL url = new URL(ip+"post_question/q1");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            readStream(con.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void readStream(InputStream in) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                Log.d("pollKAT", line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
*/