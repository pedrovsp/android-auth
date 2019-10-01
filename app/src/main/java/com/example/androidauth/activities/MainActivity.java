package com.example.androidauth.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidauth.R;
import com.example.androidauth.models.User;
import com.example.androidauth.models.UserToken;
import com.example.androidauth.services.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TextView mail, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mail = findViewById(R.id.user);
        password = findViewById(R.id.password);
    }

    public void login(View view) {
        String mail = this.mail.getText().toString();
        String senha = this.password.getText().toString();

        User user = new User(mail, senha);

        RetrofitService.getService().auth(user).enqueue(new Callback<UserToken>() {
            @Override
            public void onResponse(Call<UserToken> call, Response<UserToken> response) {
                Log.d(TAG, "onResponse: "+response.body().getToken());
                SharedPreferences sp = getSharedPreferences("user", 0);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("token",response.body().getToken());
                editor.apply();
                startActivity(new Intent(MainActivity.this, ProductsActivity.class));
            }

            @Override
            public void onFailure(Call<UserToken> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
                Toast.makeText(MainActivity.this, "Erro. Tente novamente!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
