package com.example.langlearn;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginTabFragment extends Fragment {
    EditText email;
    EditText pass;
    Button login;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login_fragment,container,false);
        email = root.findViewById(R.id.email_et);
        pass = root.findViewById(R.id.pass_et);
        login = root.findViewById(R.id.button);
        progressBar = root.findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        email.setTranslationY(300);
        email.setAlpha(0);
        email.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        pass.setTranslationY(300);
        pass.setAlpha(0);
        pass.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String semail = email.getText().toString().trim();
                String spass = pass.getText().toString().trim();
                if(TextUtils.isEmpty(semail)){
                    email.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(spass)){
                    pass.setError("Password is required");
                    return;
                }
                if(spass.length()<6){
                    pass.setError("Password Should >= 6 Characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                //authentication
                fAuth.signInWithEmailAndPassword(semail,spass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getActivity(), "Logged in Successfully.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getContext(),LangSelect.class));
                        }
                        else{
                            Toast.makeText(getActivity(), "Error."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
        return root;
    }
    public static LoginTabFragment newInstance() {
        LoginTabFragment f = new LoginTabFragment();

        return f;
    }
}
