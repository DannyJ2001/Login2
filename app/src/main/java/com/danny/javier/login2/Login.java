package com.danny.javier.login2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText txtGmailLog;
    EditText txtContrasenaLog;

    Button botonLog;

    ProgressBar progressBar;

    FirebaseAuth mAuth;

    TextView textView;


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth= FirebaseAuth.getInstance();

        txtGmailLog = findViewById(R.id.txtGmailLogin);
        txtContrasenaLog = findViewById(R.id.txtContrasenaLogin);
        botonLog = findViewById(R.id.buttonLogin);
        textView = findViewById(R.id.registerNow);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Registro.class);
                startActivity(intent);
                finish();
            }
        });

        botonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email, contra;

                email = String.valueOf(txtGmailLog.getText());
                contra = String.valueOf(txtContrasenaLog.getText());

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this, "Enter email",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(contra)){
                    Toast.makeText(Login.this, "Enter contrasena",Toast.LENGTH_SHORT).show();
                    return;
                }


                mAuth.signInWithEmailAndPassword(email, contra)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
Intent intent = new Intent(getApplicationContext(),MainActivity.class);
startActivity(intent);
finish();
                                } else {
                                   Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });

    }
}