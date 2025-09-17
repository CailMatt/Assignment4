package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateUser extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
/*
        EditText inputName = findViewById(R.id.enterName);
        EditText inputEmail = findViewById(R.id.enterEmail);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Button next = findViewById(R.id.next);



        final int[] selectId = {radioGroup.getCheckedRadioButtonId()};

        final RadioButton[] inputRole = {findViewById(selectId[0])};


        next.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   if(inputName.getText().toString().isEmpty()){
                       Toast.makeText(getApplicationContext(), "Please enter your name.", Toast.LENGTH_SHORT).show();
                   } else if (inputEmail.getText().toString().isEmpty()) {
                       Toast.makeText(getApplicationContext(), "Please enter your email.", Toast.LENGTH_SHORT).show();
                   } else if (radioGroup.getCheckedRadioButtonId() == -1){
                       Toast.makeText(getApplicationContext(), "Please select a role.", Toast.LENGTH_SHORT).show();
                   } else {
                       User user = new User();
                       user.setName(inputName.getText().toString());
                       user.setEmail(inputEmail.getText().toString());

                       inputRole[0] = findViewById(radioGroup.getCheckedRadioButtonId());
                       user.setRole(inputRole[0].getText().toString());

                       Intent intent = new Intent(CreateUser.this, Profile.class);
                       intent.putExtra("user", user);
                       startActivity(intent);
                   }
               }
           });*/
    }

}