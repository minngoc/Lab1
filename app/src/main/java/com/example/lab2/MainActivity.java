package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button calculateBtn;
    EditText textName;
    EditText textSalary;
    ListView results;
    List<String> result = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculateBtn = findViewById(R.id.calculate_button);
        textName = findViewById(R.id.text_name);
        textSalary = findViewById(R.id.text_salary);
        results = findViewById(R.id.list_result);
        calculateBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        try {
            String fullName = textName.getText().toString();
            int grossSalary = Integer.parseInt(textSalary.getText().toString());
            PersonalSalary personalSalary = new PersonalSalary(fullName, grossSalary);
            result.add(personalSalary.getFullName() + " - Net salary: " + personalSalary.calculateNetSalary());
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,
                    android.R.id.text1, result);
            results.setAdapter(arrayAdapter);
        }catch (Exception e){
            System.out.println(e);
        }

    }
}