package com.example.nearbuy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.ncorti.slidetoact.SlideToActView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
public class Splash extends AppCompatActivity {
    List<AuthUI.IdpConfig> providers;
    public static final int RC_SIGN_IN = 1;
    private static final String ANONYMOUS = "anonymous";
    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private String mUsername;

    @BindView(R.id.slider)
    SlideToActView slideToActView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        //init providers
        providers= Arrays.asList(

                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.EmailBuilder().build());

        showSignInOptions();

        ButterKnife.bind(this);
        slideToActView.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(SlideToActView slideToActView) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        });
    }
    private void showSignInOptions() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(), RC_SIGN_IN
        );
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== RC_SIGN_IN){
            if(resultCode== RESULT_OK){
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                Toast.makeText(this, "You are Signed in", Toast.LENGTH_SHORT).show();
            }
            else if(resultCode== RESULT_CANCELED){
                Toast.makeText(this, "Sign in Cancelled!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

}
