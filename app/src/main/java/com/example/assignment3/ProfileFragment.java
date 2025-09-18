package com.example.assignment3;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private static final String ARG_USER = "ARG_USER";
    private User mUser;

    // Function to set the ProfileFragment's mUser object to a user object
    public void setmUser(User user) {
        this.mUser = user;
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param user The user object passed to this fragment.
     * @return A new instance of fragment ProfileFragment.
     */
    public static ProfileFragment newInstance(User user) {
        ProfileFragment fragment = new ProfileFragment();
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Retrieve all TextViews for name, email and role
        TextView profileName = view.findViewById(R.id.profileName2);
        TextView profileEmail = view.findViewById(R.id.profileEmail3);
        TextView profileRole = view.findViewById(R.id.profileRole2);

        // Update TextView text value to represent User data
        profileName.setText(mUser.getName());
        profileEmail.setText(mUser.getEmail());
        profileRole.setText(mUser.getRole());

        // When the user clicks the update button, send the User object back to the main activity
        // to load the EditUser fragment with User data
        view.findViewById(R.id.update2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send User object back to MainActivity
                mListener.setEditUserFragment(mUser);
            }
        });
    }

    ProfileListener mListener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (ProfileListener) context;
    }

    public interface ProfileListener {
        void setEditUserFragment(User user);
    }
}