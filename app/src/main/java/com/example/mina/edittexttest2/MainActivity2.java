package com.example.mina.edittexttest2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.Inflater;

public class MainActivity2 extends AppCompatActivity {
    Button btnlogin, btnsignup, btnforget;
    EditText edtlogin1, edtpassword2;
    ImageView img1;
    CheckBox hide01;
    Toast toast;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        final TextInputLayout usernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        final TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);
        usernameWrapper.setHint("用户名");
        passwordWrapper.setHint("密码");

        //toolbar source
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Inflater inflater = new Inflater();

        btnlogin = (Button) findViewById(R.id.btnlogin);
        btnsignup = (Button) findViewById(R.id.btnsignup2);
        btnforget = (Button) findViewById(R.id.btnforget);
        edtlogin1 = (EditText) findViewById(R.id.edtlogin1);
        edtpassword2 = (EditText) findViewById(R.id.edtpassword2);
        img1 = (ImageView) findViewById(R.id.img1);
        hide01 = (CheckBox) findViewById(R.id.hide01);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                String username = usernameWrapper.getEditText().getText().toString();
                String passwordwrapper = passwordWrapper.getEditText().getText().toString();

                if (!validateEmail(username)) {
                    usernameWrapper.setError("信箱格式不正确！");
                } else if (!validatePassword(passwordwrapper)) {
                    passwordWrapper.setError("密码错误！");
                } else {
                    usernameWrapper.setErrorEnabled(false);
                    passwordWrapper.setErrorEnabled(false);
                    doLogin();

                }
        }});
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity2.this,MainActivity.class);
                startActivity(intent);
            }
        });

        hide01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {       //眼睛隱藏程式碼64-73行
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (hide01.isChecked()) {
                    edtpassword2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    edtpassword2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btnforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity2.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    //放按钮在action bar上面，Toast测试按钮的可用性
    //注意：最重要的是要在menu上面放上所需要用的材料
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.help:
                Toast.makeText(MainActivity2.this,"help",Toast.LENGTH_LONG).show();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public boolean validatePassword(String password) {
        return password.length() > 5;
    }
    public void doLogin() {
        Intent intent = new Intent();
        intent.setClass(MainActivity2.this, MainActivity.class);
        startActivity(intent);
        // TODO: login procedure; not within the scope of this tutorial.
    }

}