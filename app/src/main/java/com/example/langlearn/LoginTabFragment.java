package com.example.langlearn;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class LoginTabFragment extends Fragment {
    EditText email;
    EditText pass;
    Button login;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login_fragment,container,false);
        email = root.findViewById(R.id.email_et);
        pass = root.findViewById(R.id.pass_et);
        login = root.findViewById(R.id.button);
        return root;
    }
    public static LoginTabFragment newInstance(String text) {
        LoginTabFragment f = new LoginTabFragment();
//        Bundle b = new Bundle();
//        b.putString("msg", text);
//        f.setArguments(b);
        return f;
    }
}
