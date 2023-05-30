package com.example.mobile_labs_20521669;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity{
    private Button signInBtn;
    private EditText textFullName;
    private EditText textPhone;
    private EditText textUserName;
    private EditText textPassword;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    @SuppressLint({"MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        signInBtn = findViewById(R.id.btn_sign_in);
        textFullName = findViewById(R.id.input_full_name);
        textPhone = findViewById(R.id.input_phone);
        textUserName = findViewById(R.id.input_user_name);
        textPassword = findViewById(R.id.input_password);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            //create user
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
    }

    public void createUser(){
        String fullName = this.textFullName.getText().toString();
        String phone = this.textPhone.getText().toString() ;
        String userName = this.textUserName.getText().toString();
        String password = this.textPassword.getText().toString();
        if(TextUtils.isEmpty(fullName)){
            Toast.makeText(this,"Invalid full name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Invalid phone number", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userName)){
            Toast.makeText(this,"Invalid user name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Invalid password", Toast.LENGTH_SHORT).show();
            return;
        }

        CollectionReference dbUser = db.collection("user");
        User user = new User(userName, fullName, password, phone);
        dbUser.add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(SignUpActivity.this, "Sign up successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(SignUpActivity.this, "Fail to sign up \n" + e, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
