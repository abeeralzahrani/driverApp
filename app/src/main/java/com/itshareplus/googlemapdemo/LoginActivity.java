package com.itshareplus.googlemapdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {





    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private Button login_button;
    String userID,userPassword;
    userSessionManeger session;
    TextView forgetPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session=new userSessionManeger(getBaseContext());
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        forgetPass=(TextView)findViewById(R.id.forget_password);
        login_button=(Button)findViewById(R.id.email_sign_in_button);
        login_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                userID=mEmailView.getText().toString();
                userPassword=mPasswordView.getText().toString();
                background b=new background(getBaseContext());
                try {
                    String result=b.execute("AuthenticateEmployee",userID,userPassword).get();
                    System.out.println(result+" login");
                    if(result.contains("Error: Invalid EmployeeCode or Password!")){
                        Toast.makeText(getBaseContext(),"Invalid EmployeeCode or Password!",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String json=result.substring(result.indexOf('=')+1,result.indexOf(';'));
                        JSONObject jsonObject =new JSONObject(json);
                        String employeeName = jsonObject.getString("EmployeeName");
                        String employeeCode = jsonObject.getString("EmployeeCode");
                        String PositionTitle = jsonObject.getString("PositionTitle");
                        session.createUserLoginSession(employeeName,employeeCode,PositionTitle);
                        Intent main_Intent=new Intent(getApplication().getApplicationContext(),MainActivity_nav.class);
                        main_Intent.putExtra("PositionTitle",PositionTitle);
                        startActivity(main_Intent);

                    }
                } catch (InterruptedException | ExecutionException | JSONException e){
                    e.printStackTrace();
                }


            }
        });




    }
    public void forgetPassword(View v){
        Toast.makeText(this,"forgetPassword",Toast.LENGTH_SHORT).show();
    }
}

