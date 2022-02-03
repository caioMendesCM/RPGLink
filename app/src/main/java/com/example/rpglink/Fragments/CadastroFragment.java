package com.example.rpglink.Fragments;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rpglink.R;
import com.example.rpglink.config.ConfigFirebase;
import com.example.rpglink.models.Usuario;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroFragment extends Fragment {

    private TextInputLayout nomeUsuario, email, senha, rSenha, nascimento;
    private TextInputEditText userEtxt, emailEtxt, senhaEtxt, rSenhaEtxt, nascimentoEtxt;
    private ImageView cadastro;
    private FirebaseAuth auth;
    private Usuario user;
    private SimpleMaskFormatter maskFormatter;
    private MaskTextWatcher maskData;

    public CadastroFragment() {
    }

    public static CadastroFragment newInstance(String param1, String param2) {
        CadastroFragment fragment = new CadastroFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_cadastro, container, false);

        maskFormatter = new SimpleMaskFormatter("NN/NN/NNNN");

        nomeUsuario     = view.findViewById(R.id.LoginCInputLayout);
        email           = view.findViewById(R.id.emailInputLayout);
        senha           = view.findViewById(R.id.passwordCInputLayout);
        rSenha          = view.findViewById(R.id.rPasswordCInputLayout);
        nascimento      = view.findViewById(R.id.nascimentoinputLayout);

        userEtxt        = view.findViewById(R.id.LoginCEditText);
        emailEtxt       = view.findViewById(R.id.emailEditText);
        senhaEtxt       = view.findViewById(R.id.passwordCEditText);
        rSenhaEtxt      = view.findViewById(R.id.rPasswordCEditText);
        nascimentoEtxt  = view.findViewById(R.id.nascimentoEditText);

        maskData = new MaskTextWatcher(nascimentoEtxt,maskFormatter);

        userEtxt.setOnFocusChangeListener((view1, b) -> {
            if(b = true){
                nomeUsuario.setHintTextColor(ColorStateList.valueOf(Color.TRANSPARENT));
            }
        });

        emailEtxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b = true){
                    email.setHintTextColor(ColorStateList.valueOf(Color.TRANSPARENT));
                }
            }
        });

        senhaEtxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b = true){
                    senha.setHintTextColor(ColorStateList.valueOf(Color.TRANSPARENT));
                }
            }
        });

        rSenhaEtxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b = true){
                    rSenha.setHintTextColor(ColorStateList.valueOf(Color.TRANSPARENT));
                }
            }
        });

        nascimentoEtxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b = true){
                    nascimento.setHintTextColor(ColorStateList.valueOf(Color.TRANSPARENT));
                }
            }
        });

        nascimentoEtxt.addTextChangedListener(maskData);

        cadastro = view.findViewById(R.id.cadastroBtn);

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nomeS        = userEtxt.getText().toString();
                String loginS       = emailEtxt.getText().toString();
                String senhaS       = senhaEtxt.getText().toString();
                String rSenhaS      = rSenhaEtxt.getText().toString();
                String nascimentoS  = nascimentoEtxt.getText().toString();

                if(!loginS.isEmpty()){
                    if(!senhaS.isEmpty()) {
                        if(senhaS != rSenhaS) {
                            if(!nomeS.isEmpty()){
                                if(!nascimentoS.isEmpty()) {


                                    user = new Usuario();
                                    user.setEmail(loginS);
                                    user.setPassword(senhaS);
                                    user.setName(nomeS);
                                    user.setBirthday(nascimentoS);

                                    auth = ConfigFirebase.getFirebaseAutenticacao();
                                    auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {

                                                Navigation.findNavController(getActivity().findViewById(R.id.navController)).navigate(R.id.action_cadastroFragment_to_homeCadastroFragment);

                                            } else {
                                                try {
                                                    throw task.getException();
                                                } catch (FirebaseAuthWeakPasswordException e) {
                                                    Toast.makeText(getContext(), "A senha digitada é muito fraca", Toast.LENGTH_SHORT).show();
                                                }catch(FirebaseAuthInvalidCredentialsException e) {
                                                    Toast.makeText(getContext(), "O email digitado é invalido", Toast.LENGTH_SHORT).show();
                                                } catch(FirebaseAuthUserCollisionException e) {
                                                    Toast.makeText(getContext(), "Este email ja esta cadastrado", Toast.LENGTH_SHORT).show();
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                            }
                                        });
                                }else{
                                    Toast.makeText(getContext(), "Preencha o campo do nascimento", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(getContext(), "Preencha o campo do nome", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getContext(), "As senhas digitadas não correspondem", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getContext(), "Digite uma senha", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Digite um Email ou nome de usuário", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}