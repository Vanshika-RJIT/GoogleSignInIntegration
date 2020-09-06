package com.example.googlesigninintegration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private GoogleSignInClient mGoogleSignInClient;
    private Button signOut;
    private TextView name,id,email;
    private ImageView Photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        signOut= findViewById(R.id.sign_out_button);
        id= findViewById(R.id.id);
        email= findViewById(R.id.email);
        name= findViewById(R.id.name);
        Photo= findViewById(R.id.photo);
        name.setText(getIntent().getStringExtra("name"));
        String Id=getIntent().getStringExtra("id");
        id.setText(Id);
        String Email=getIntent().getStringExtra("email");
        email.setText(Email);
        String url=getIntent().getStringExtra("photo");
        Picasso.get().load(Uri.parse(url)).placeholder(R.drawable.common_google_signin_btn_icon_dark).into(Photo);



        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        findViewById(R.id.sign_out_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // ...
            case R.id.sign_out_button:
                signOut();
                break;
            // ...
        }

    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(ProfileActivity.this,MainActivity.class));
                        finish();

                    }
                });
    }
}