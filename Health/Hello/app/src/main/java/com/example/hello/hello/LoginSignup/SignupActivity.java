package com.example.hello.hello.LoginSignup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.hello.hello.R;
import com.example.hello.hello.dashboardActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {

    private EditText email_id,password;
    private Button sign_up;
    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email_id = findViewById(R.id.email_id);
        password = findViewById(R.id.password_id);
        sign_up = findViewById(R.id.sign_up_button);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


    }

    public void Register(View view)
    {
        String email = email_id.getText().toString();
        String pass = password.getText().toString();
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(SignupActivity.this,"Please Enter The username",Toast.LENGTH_LONG).show();
            return;
        }else{
            UserDetails.setEmailId(email);
        }

        if(TextUtils.isEmpty(pass))
        {
            Toast.makeText(SignupActivity.this,"Please Enter The Password",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registering User");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isComplete())
                {
                    Toast.makeText(SignupActivity.this,"Registered Successfully!!",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SignupActivity.this,RegisterActivity.class));
                }

                else
                {
                    Toast.makeText(SignupActivity.this,"Registeration Failed!!",Toast.LENGTH_LONG).show();
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
            startActivity(new Intent(SignupActivity.this,dashboardActivity.class));
        }
    }
}
