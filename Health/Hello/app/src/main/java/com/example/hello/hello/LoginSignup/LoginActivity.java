package com.example.hello.hello.LoginSignup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hello.hello.R;
import com.example.hello.hello.dashboardActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText email_id,password;
    private Button login;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_id = findViewById(R.id.email_id);
        password = findViewById(R.id.password_id);
        login = findViewById(R.id.login_button_id);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }

    public void Login()
    {
        String email = email_id.getText().toString();
        String pass = password.getText().toString();
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(LoginActivity.this,"Please Enter The username",Toast.LENGTH_LONG).show();
        }else{
            UserDetails.setEmailId(email);
        }

        if(TextUtils.isEmpty(pass))
        {
            Toast.makeText(LoginActivity.this,"Please Enter The Password",Toast.LENGTH_LONG).show();
        }

        progressDialog.setMessage("Logging In");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(LoginActivity.this,"Logged in Successfully!!",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this,dashboardActivity.class));
                }

                else
                {
                    Toast.makeText(LoginActivity.this,"Login Failed!! Check email and password",Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null)
        {
            startActivity(new Intent(LoginActivity.this,dashboardActivity.class));
        }
    }

}
