package com.example.hbl;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.concurrent.Executor;


public class home extends Fragment {

    Executor executor;
    BiometricPrompt biometricPrompt;
    BiometricPrompt.PromptInfo promptInfo;

    EditText mEmail,mPassword;
    SharedPreferences ref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Switch bioMetrics;


        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        Button signupButton = (Button) view.findViewById(R.id.button);
        Button loginButton = (Button) view.findViewById(R.id.button2);

        mEmail = (EditText) view.findViewById(R.id.editTextTextPersonName2);
        mPassword = (EditText) view.findViewById(R.id.editTextTextPassword);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sEmail = ref.getString("email","");
                String sPassword = ref.getString("password","");

                if(sEmail.equals(mEmail.getText().toString()) && sPassword.equals(mPassword.getText().toString())) {
                    Intent intent = new Intent(getActivity(), Signup.class);
                    startActivity(intent);
                }

            else{

                Toast.makeText(view.getContext(),"Invalid User", Toast.LENGTH_SHORT).show();

            }


            }
        });


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Signup.class);
                startActivity(intent);
            }
        });
        bioMetrics = view.findViewById(R.id.switch1);
        executor = ContextCompat.getMainExecutor(this.getContext());
        biometricPrompt = new BiometricPrompt(this.getActivity(), executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getContext(), errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                loadFragment(new Login());
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getContext(),"FAILURE",Toast.LENGTH_LONG).show();
            }
        });
        promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Biometric Authentication")
                .setSubtitle("Login using fingerprint").setNegativeButtonText("Cancel").build();
        bioMetrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                biometricPrompt.authenticate(promptInfo);
            }
        });


        return view;
    }
    public void loadFragment(Fragment frag){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container,frag);
        ft.commit();
    }


}