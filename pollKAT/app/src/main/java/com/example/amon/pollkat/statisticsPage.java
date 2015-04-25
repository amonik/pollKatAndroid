package com.example.amon.pollkat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class statisticsPage extends ActionBarActivity {

    public static String line = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_page);


        String singleQuestion = getIntent().getStringExtra("question");
        TextView textView1 = (TextView) findViewById(R.id.textQuestion);
        textView1.setText(singleQuestion);
        Thread thrd = new Thread(){
            @Override
            public void  run(){
                try {
                    SharedPreferences settings = getSharedPreferences("pollKatIP",
                            Context.MODE_PRIVATE);
                    String ip = settings.getString("IP", null);
                    Log.d("IP Address", ip);
                    String id = "1";//getIntent().getStringExtra("ID");
                    URL url = new URL(ip+"request_statistics/"+id);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    readStream(con.getInputStream());
                    Log.d("getting",line);
                    String yesCount = line.substring(line.indexOf("@")+1,line.lastIndexOf("@"));
                    String noCount = line.substring(line.lastIndexOf("@")+1);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        thrd.start();
    }

    private void readStream(InputStream in) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in));

            line = reader.readLine();
            Log.d("pollKAT", line);

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





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_answer, menu);
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
}
