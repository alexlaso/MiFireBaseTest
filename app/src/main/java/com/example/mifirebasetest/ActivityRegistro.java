package com.example.mifirebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityRegistro extends AppCompatActivity {

    EditText user, pW;
    Button registrar;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        user = findViewById(R.id.editTextNombre);
        pW = findViewById(R.id.editTextPassword);
        registrar = findViewById(R.id.btnRegistrar);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!user.getText().toString().isEmpty() && !pW.getText().toString().isEmpty()){
                    registroUsuario(user.getText().toString(), pW.getText().toString());
                }else {
                    Toast.makeText(ActivityRegistro.this, "Los campos deben estar escritos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void registroUsuario(String user, String pass){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ActivityRegistro.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                }
            else{
                Toast.makeText(ActivityRegistro.this, "Registro fallido", Toast.LENGTH_SHORT).show();
            }}
        });
    }
}