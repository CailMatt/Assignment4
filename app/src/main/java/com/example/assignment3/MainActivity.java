package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements MainFragment.MainListener, CreateUserFragment.CreateUserListener,
        ProfileFragment.ProfileListener, EditUserFragment.EditUserListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.containerView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // When app first starts, load MainFragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.containerView, new MainFragment())
                .commit();
    }

    @Override
    public void loadUserFragment() {
        // When the start button is clicked on the MainFragment, replace with CreateUserFragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerView, new CreateUserFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void setProfileFragment(User user) {
        // When the next button is clicked on the CreateUserFragment, replace with ProfileFragment
        // and pass newly created User object to be display in ProfileFragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerView, ProfileFragment.newInstance(user), "profile-fragment")
                .addToBackStack(null)
                .commit();
    }
    @Override
    public void setEditUserFragment(User user) {
        // When the update button is clicked on the ProfileFragment, replace ProfileFragment
        // with EditUserFragment and pass user object to be displayed
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerView, EditUserFragment.newInstance(user))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void updateProfileFragment(User user) {
        // Find the ProfileFragment by its tag "profile-fragment"
        ProfileFragment profileFragment = (ProfileFragment) getSupportFragmentManager().findFragmentByTag("profile-fragment");

        if (profileFragment != null) {
            // Pass the updated User object to ProfileFragment
            profileFragment.setmUser(user);

            // Pop back stack to display the ProfileFragment with the updated User information
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void popBackProfileFragment() {
        // When the cancel button on the EditUserFragment is clicked, pop back stack to display the previous ProfileFragment
        getSupportFragmentManager().popBackStack();
    }
}