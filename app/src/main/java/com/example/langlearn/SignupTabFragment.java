package com.example.langlearn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class SignupTabFragment extends Fragment {
    EditText email;
    EditText pass;
    EditText number;
    Button signup;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.signup_fragment,container,false);
        email = root.findViewById(R.id.email_et);
        pass = root.findViewById(R.id.pass_et);
        number = root.findViewById(R.id.phone);
        signup = root.findViewById(R.id.button);
        return root;
    }
    public static SignupTabFragment newInstance(String text) {
        SignupTabFragment f = new SignupTabFragment();
//        Bundle b = new Bundle();
//        b.putString("msg", text);
//        f.setArguments(b);
        return f;
    }
}

