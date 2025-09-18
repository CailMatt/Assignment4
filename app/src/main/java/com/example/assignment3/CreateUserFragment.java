package com.example.assignment3;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CreateUserFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.next3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the entered name from the EditText, assign to name variable
                EditText nameText = view.findViewById(R.id.enterName3);
                String name = nameText.getText().toString();

                // Retrieve the entered email from the EditText, assign to email variable
                EditText emailText = view.findViewById(R.id.enterEmail3);
                String email = emailText.getText().toString();

                // Locate the RadioGroup
                RadioGroup roleGroup = view.findViewById(R.id.radioGroupEdit);

                // Determine the id of the selected radio button and store this id in selection
                int selection = roleGroup.getCheckedRadioButtonId();

                // Find the selected RadioButton
                RadioButton roleButton = view.findViewById(selection);

                // If name, email or role are left empty display a toast telling them to enter a value
                if (name.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter a name", Toast.LENGTH_SHORT).show();
                } else if (email.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter an email", Toast.LENGTH_SHORT).show();
                }
                else if (roleButton == null) {
                    Toast.makeText(getContext(), "Please select a role", Toast.LENGTH_SHORT).show();
                }
                else {
                    // If button is selected, Store the value of the selected button in role
                    String role = roleButton.getText().toString().trim();
                    // Create new User object with entered information
                    User user = new User(name, email, role);

                    // Pass new User object to MainActivity through setProfileFragment
                    mListener.setProfileFragment(user);
                }

            }
        });
    }

    CreateUserListener mListener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (CreateUserListener) context;
    }

    public interface CreateUserListener {
        void setProfileFragment(User user);
    }
}