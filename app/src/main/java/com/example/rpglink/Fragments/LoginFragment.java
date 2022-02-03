package com.example.rpglink.Fragments;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.facebook.CallbackManager;


import com.example.rpglink.R;
import com.example.rpglink.config.ConfigFirebase;
import com.example.rpglink.models.Usuario;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {

    private static final int RC_SIGN_IN = 1726;
    private TextInputEditText login;
    private TextInputLayout loginIL;
    private TextInputEditText senha;
    private TextInputLayout senhaIL;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private Button entrar, googleLogin, facebookLogin;
    private GoogleSignInOptions gso;
    private GoogleSignInClient mGoogleSignInClient;
    private ActivityResultLauncher<Intent> googleLoginActivityLauncher;
    private CallbackManager callbackManager;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.login_fragment, container, false);

        auth = ConfigFirebase.getFirebaseAutenticacao();

        login = view.findViewById(R.id.LoginEditText);
        loginIL = view.findViewById(R.id.LoginInputLayout);
        senha = view.findViewById(R.id.passwordEditText);
        senhaIL = view.findViewById(R.id.passwordInputLayout);
        entrar = view.findViewById(R.id.entrarBtn);
        googleLogin = view.findViewById(R.id.googleBtn);
        facebookLogin = view.findViewById(R.id.facebookBtn);

        //desabilita o text hint quando a caixa de texto de login está ativa
        login.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b == true){
                    loginIL.setHintTextColor(ColorStateList.valueOf(Color.TRANSPARENT));
                    if(login != null){
                        loginIL.setHintTextColor(ColorStateList.valueOf(Color.TRANSPARENT));
                    }
                }
            }
        });

        //desabilita o text hint quando a caixa de texto de senha está ativa
        senha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b == true){
                    senhaIL.setHintTextColor(ColorStateList.valueOf(Color.TRANSPARENT));
                    if(senha.getText().toString() != null){
                        senhaIL.setHintTextColor(ColorStateList.valueOf(Color.TRANSPARENT));
                    }
                }
            }
        });

        entrar.setOnClickListener(v -> Login());
        return view;
    }

    public void Login (){

        String loginS = login.getText().toString();
        String senhaS = senha.getText().toString();

        if(!loginS.isEmpty()) {
            if (!senhaS.isEmpty()) {

                Usuario user = new Usuario();
                user.setEmail(loginS);
                user.setPassword(senhaS);

                auth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Navigation.findNavController(getActivity().findViewById(R.id.navController)).navigate(R.id.action_slideBaseFragment_to_homeLoginFragment);

                                }else {
                                    try {
                                        throw task.getException();
                                    } catch (FirebaseAuthInvalidUserException e) {
                                        Toast.makeText(getContext(), "O email digitado não está cadastrado", Toast.LENGTH_SHORT).show();
                                    }catch(FirebaseAuthInvalidCredentialsException e) {
                                        Toast.makeText(getContext(),"A senha digitada não corresponde ao email",Toast.LENGTH_SHORT).show();
                                    }catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
            }else{
                Toast.makeText(getContext(), "Digite uma senha", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getContext(), "Digite um Email ou nome de usuário", Toast.LENGTH_SHORT).show();
        }
    }
}