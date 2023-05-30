package com.example.mobile_labs_20521669;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class SignInActivity extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private EditText userNameText;
    private EditText passwordText;
    private Button btnLogin;
    private TextView signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);

        userNameText= findViewById(R.id.inputUserName);
        passwordText= findViewById(R.id.inputPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        signUp=findViewById(R.id.textViewSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = userNameText.getText().toString();
                String password = passwordText.getText().toString();

                if(userName.isEmpty()||password.isEmpty()){
                    Toast.makeText(SignInActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
                else{
                    CollectionReference collectionReference = firebaseFirestore.collection("user");
                    collectionReference.get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    queryDocumentSnapshots.forEach(queryDocumentSnapshot -> {
                                        String username = queryDocumentSnapshot.getString("user_name");
                                        String password = queryDocumentSnapshot.getString("password");
                                        if( userName.compareTo(username) == 0 && password.equals(password)){
                                            Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                                            startActivity(intent);
                                        }
                                        else{
                                            Toast.makeText(SignInActivity.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignInActivity.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
