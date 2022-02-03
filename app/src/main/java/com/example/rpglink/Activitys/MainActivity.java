package com.example.rpglink.Activitys;


import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rpglink.R;
import com.example.rpglink.config.ConfigFirebase;
import com.example.rpglink.models.Usuario;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 2;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;
    private Usuario user;
    private Button entrar, googleLogin, facebookLogin;
    private GoogleSignInOptions gso;
    private GoogleSignInClient mGoogleSignInClient;
    private ActivityResultLauncher<Intent> googleLoginActivityLauncher;
    private CallbackManager callbackManager;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = ConfigFirebase.getFirebaseAutenticacao();
        database = FirebaseDatabase.getInstance();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        googleLoginActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                            try{
                                GoogleSignInAccount account = task.getResult(ApiException.class);
                                firebaseAuthWithGoogle(account.getIdToken());
                            }catch(Exception e){
                                Log.e("Google SignIn Activity",e.toString());
                            }
                        }else{
                            Log.e("Google SignIn Activity",String.valueOf(result.getResultCode()));
                        }
                    }
                });

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                firebaseAuthWithFacebook(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(getBaseContext(),"Login cancelado",Toast.LENGTH_SHORT);
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("FacebookException",error.toString());
            }
        });

        BottomNavigationView navView = findViewById(R.id.navView);
        navView.setItemIconTintList(null);

        Runnable slider = () -> Navigation.findNavController(findViewById(R.id.navController)).navigate(R.id.action_splashScreenFragment_to_slideBaseFragment);

        Runnable homeL = () -> Navigation.findNavController(findViewById(R.id.navController)).navigate(R.id.action_splashScreenFragment_to_homeLoginFragment);

        Handler handler = new Handler();
        if(auth.getCurrentUser() == null){
            handler.postDelayed(slider, 1);
        }else{
            handler.postDelayed(homeL, 1);
        }
    }
    public void abrirTelaCadastro(View view){
        Navigation.findNavController(findViewById(R.id.navController)).navigate(R.id.action_slideBaseFragment_to_cadastroFragment);
    }

    public void abrirMenuL(View view){
        Navigation.findNavController(findViewById(R.id.navController)).navigate(R.id.action_homeLoginFragment_to_menuHamburguerFragment);
    }

    public void googleLogin(View view){

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        googleLoginActivityLauncher.launch(signInIntent);

    }

    public void facebookLogin(View view){

        LoginManager.getInstance().logInWithReadPermissions(
                this,
                callbackManager,
                Arrays.asList("public_profile", "email")
        );

    }

    private void firebaseAuthWithGoogle(String idToken) {

        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        DatabaseReference userRef = database.getReference();
        user = new Usuario();
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            firebaseUser = auth.getCurrentUser();
                            user.setEmail(firebaseUser.getEmail());
                            user.setName(firebaseUser.getDisplayName());
                            user.setUserId(firebaseUser.getUid());

                            userRef.child("rpg-link").child("user").child(user.getUserId()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    if(task.isSuccessful() && task.getResult().getValue() != null){

                                        Log.d("firebase", String.valueOf(task.getResult().getValue()));

                                    }else{
                                        try{
                                            userRef.child("rpg-link").child("user").child(user.getUserId()).setValue(user);
                                            Log.d("firebase", "Usuário criado no banco de dados", task.getException());
                                        }catch (Exception e){
                                            Log.e("FirebaseDatabase",e.toString());
                                        }
                                    }
                                    Navigation.findNavController(findViewById(R.id.navController)).navigate(R.id.action_slideBaseFragment_to_homeLoginFragment);
                                }
                            });

                        } else {
                            // If sign in fails, display a message to the user.
                        }
                        // ...
                    }
                });

    }

    private void firebaseAuthWithFacebook(AccessToken token){

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        DatabaseReference userRef = database.getReference();
        user = new Usuario();

        auth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    //busca informações direto do firebase
                    firebaseUser = auth.getCurrentUser();
                    user.setEmail(firebaseUser.getEmail());
                    user.setName(firebaseUser.getDisplayName());
                    user.setUserId(firebaseUser.getUid());

                    //busca informações direto do facebook
                    GraphRequest graphRequest = GraphRequest.newMeRequest(token,  new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(@Nullable JSONObject jsonObject, @Nullable GraphResponse graphResponse) {

                            try{
                                if(jsonObject.has("birthday")){
                                    Log.d("Facebook Birthday info",jsonObject.getString("birthday"));
                                    user.setBirthday(parseDateToddMMyyyy(jsonObject.getString("birthday")));
                                }
                            }catch (Exception e){
                                Log.e("Facebook Birthday info",e.toString());
                            }

                        }
                    });

                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "birthday");
                    graphRequest.setParameters(parameters);

                    //thread pra fazer o código esperar a função de conversão de data terminar antes lançar o usuário no servidor
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            GraphResponse gResponse = graphRequest.executeAndWait();
                        }
                    });
                    t.start();
                    try{
                        t.join();
                    }catch (Exception e){
                        Log.e("graphrequest thread",e.toString());
                    }

                    //tenta achar usuário no sistema
                    userRef.child("rpg-link").child("user").child(user.getUserId()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if(task.isSuccessful() && task.getResult().getValue() != null){

                                Log.d("firebase", String.valueOf(userRef.child("rpg-link").child("user").child(user.getUserId()).child("name").get()));

                            }else{
                                try{
                                    userRef.child("rpg-link").child("user").child(user.getUserId()).setValue(user);
                                    Log.d("firebase", "Usuário criado no banco de dados", task.getException());
                                }catch (Exception e){
                                    Log.e("FirebaseDatabase",e.toString());
                                }
                            }
                            Navigation.findNavController(findViewById(R.id.navController)).navigate(R.id.action_slideBaseFragment_to_homeLoginFragment);
                        }
                    });
                }else{
                    Log.println(Log.VERBOSE,"MyActivity",task.getException().toString());
                }
            }
        });

    }

    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "MM/dd/yyyy";
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

}