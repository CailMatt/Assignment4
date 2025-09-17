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
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditUser extends AppCompatActivity {
    static public final String USER_KEY = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");

        Button cancel = findViewById(R.id.cancel);
        Button submit = findViewById(R.id.submit);
        EditText editName = findViewById(R.id.editName);
        EditText editEmail = findViewById(R.id.editEmail);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        assert user != null;
        final int[] selectId = {getResources().getIdentifier(user.getRole(), "id", getPackageName())};
        final RadioButton[] editRole = {findViewById(selectId[0])};

        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            View view = radioGroup.getChildAt(i);
            if (view instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) view;
                if (radioButton.getText().toString().equalsIgnoreCase(user.getRole())) {
                    radioButton.setChecked(true);
                    break;
                }
            }
        }

        editName.setText(user.getName());
        editEmail.setText(user.getEmail());

        cancel.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              finish();
          }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editName.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter your name.", Toast.LENGTH_SHORT).show();
                } else if (editEmail.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter your email.", Toast.LENGTH_SHORT).show();
                } else if (radioGroup.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getApplicationContext(), "Please select a role.", Toast.LENGTH_SHORT).show();
                } else {
                    user.setName(editName.getText().toString());
                    user.setEmail(editEmail.getText().toString());

                    editRole[0] = findViewById(radioGroup.getCheckedRadioButtonId());
                    user.setRole(editRole[0].getText().toString());

                    Intent intent = new Intent();
                    intent.putExtra("user", user);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}