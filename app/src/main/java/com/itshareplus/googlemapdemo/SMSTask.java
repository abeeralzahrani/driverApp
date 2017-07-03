package com.itshareplus.googlemapdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by AZeaage on 5/31/2017.
 */

public class SMSTask extends AsyncTask<String, Void, String> {

        AppCompatActivity appCompatActivity;
        Context ctx;
        AlertDialog alertDialog;
        SMSTask(Context ctx){
            this.ctx=ctx;
            appCompatActivity= (AppCompatActivity)new AppCompatActivity();
        }

        @Override
        protected String doInBackground(String... voids){
            String customerPhone= voids[0];
            String messageText = voids[1];
            String url_str = "http://api.unifonic.com/wrapper/sendSMS.php?userid=a.gafar@alkaffary.com&password=Expert_1&msg="+messageText+"&sender=alkaffary&to="+customerPhone+"&encoding=UTF8";
            URL url=null;
            try {
                url=new URL(url_str);

                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream Is=httpURLConnection.getInputStream();
                BufferedReader buffredReader=new BufferedReader(new InputStreamReader(Is,"iso-8859-1"));
                String response="";
                String line="";
                while ((line=buffredReader.readLine())!=null){
                    response+=line;
                }
                buffredReader.close();
                Is.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException | UnsupportedEncodingException | ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            alertDialog=new AlertDialog.Builder(ctx).create();
            alertDialog.setTitle("Login Information");
        }
    }

