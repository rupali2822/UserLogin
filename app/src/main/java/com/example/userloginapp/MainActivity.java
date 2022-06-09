package com.example.userloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txt_view;
EditText username,password;
Button Register,Login;

public DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       DatabaseHelper databaseHelper=new DatabaseHelper(this);
        username=findViewById(R.id.et_username);
        password=findViewById(R.id.et_password);
        Register=findViewById(R.id.btn_register);
        Login=findViewById(R.id.btn_login);
        txt_view=findViewById(R.id.txt_view);



        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getName=username.getText().toString();
                String getPassword=password.getText().toString();

                boolean register;
                if (databaseHelper.registerUser(new Data(getName, getPassword))) register = true;
                else register = false;

                 if(getName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Required all feilds", Toast.LENGTH_SHORT).show();
                   }else if(register = false){
                     Toast.makeText(MainActivity.this, "Cannot register", Toast.LENGTH_SHORT).show();
                 }
                 else if(register=true) {
                     Toast.makeText(MainActivity.this, "data registered", Toast.LENGTH_SHORT).show();
                     txt_view.setText("Sign Up");
                     Register.setVisibility(View.INVISIBLE);
                     username.getText().clear();
                     password.getText().clear();


                 }
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getName=username.getText().toString();
                String getPassword=password.getText().toString();


               boolean login=true;
               login = databaseHelper.loginUser(new Data(getName,getPassword));
               if(login) {
                   Toast.makeText(MainActivity.this, "Log in", Toast.LENGTH_SHORT).show();
                   Intent i=new Intent(MainActivity.this,WelcomeActivity.class);
                   MainActivity.this.startActivity(i);
               }
               else {
                   Toast.makeText(MainActivity.this, "login Failed", Toast.LENGTH_SHORT).show();
               }
//                if(login=true){
//                   Toast.makeText(MainActivity.this, "Log in", Toast.LENGTH_SHORT).show();
//               }else {
//                    Toast.makeText(MainActivity.this, "not login", Toast.LENGTH_SHORT).show();
//                }




            }
        });


    }
}