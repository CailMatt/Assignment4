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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditUserFragment extends Fragment {
    private static final String ARG_USER = "ARG_USER";
    private User mUser;

    public EditUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param user The user object passed from the main activity.
     * @return A new instance of fragment EditUserFragment.
     */
    public static EditUserFragment newInstance(User user) {
        EditUserFragment fragment = new EditUserFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUser = (User) getArguments().getSerializable(ARG_USER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // References to EditTexts and RadioGroup
        EditText editName = view.findViewById(R.id.editName);
        EditText editEmail = view.findViewById(R.id.editEmail);
        RadioGroup roleGroup = view.findViewById(R.id.radioGroupEdit);

        // Update the name and email EditTexts to match the passed User object
        editName.setText(mUser.getName());
        editEmail.setText(mUser.getEmail());

        // Assign the role of the passed User object to role
        String role = mUser.getRole();

        // If else statements will determine which RadioButton needs to be checked
        if (role.equalsIgnoreCase("student")) {
            roleGroup.check(R.id.student);
        }
        else if (role.equalsIgnoreCase("employee")) {
            roleGroup.check(R.id.employee);
        }
        else {
            roleGroup.check(R.id.other);
        }

        view.findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the entered name from the EditText
                EditText nameText = view.findViewById(R.id.editName);
                String name = nameText.getText().toString();

                // Retrieve the entered email from the EditText
                EditText emailText = view.findViewById(R.id.editEmail);
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
                    mListener.updateProfileFragment(user);
                }
            }
        });

        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the cancel button is clicked, call popBackProfileFragment to display the previous ProfileFragment
                mListener.popBackProfileFragment();
            }
        });
    }
    EditUserListener mListener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (EditUserListener) context;
    }

    public interface EditUserListener {
        void updateProfileFragment(User user);
        void popBackProfileFragment();
    }
}