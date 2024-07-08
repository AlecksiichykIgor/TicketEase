package com.example.graduate_work_bachelor_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.graduate_work_bachelor_app.model.Client;
import com.example.graduate_work_bachelor_app.retrofit.RetrofitService;
import com.example.graduate_work_bachelor_app.retrofit.UserApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ActivityAuthorization extends AppCompatActivity {

    private EditText editTextPhone;
    private EditText editTextPassword;
    private Button loginButton;
    private Button registrationButton;
    private UserApi userApi;
    private RetrofitService retrofitService;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPassword = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.loginButton);
        registrationButton = findViewById(R.id.registrationButton);

        retrofitService = new RetrofitService();
        Retrofit retrofit = retrofitService.getRetrofit();
        userApi = retrofit.create(UserApi.class);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityAuthorization.this, ActivityRegistration.class);
                startActivity(intent);
            }
        });
    }

    public void openAdminAuthorization(View view){
        Intent intent = new Intent(this, ActivityAdminAuthorization.class);
        startActivity(intent);
    }

    private void loginUser() {
        String phoneNumber = editTextPhone.getText().toString();
        String password = editTextPassword.getText().toString();

        Client client = new Client();
        client.setPhoneNumber(phoneNumber);
        client.setPassword(password);

        Call<ResponseBody> call = userApi.login(client);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("phone_number", phoneNumber);
                    editor.apply();
                    Toast.makeText(ActivityAuthorization.this, "Автетифікація успішна", Toast.LENGTH_SHORT).show();
                    Intent adminIntent = new Intent(ActivityAuthorization.this, ActivityMainPageUser.class);
                    startActivity(adminIntent);
                } else if (response.code() == 401 || response.code() == 404) {
                    Toast.makeText(ActivityAuthorization.this, "Неверный пароль", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivityAuthorization.this, "Ошибка аутентификации", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ActivityAuthorization.this, "Ошибка подключения", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
