package com.example.graduate_work_bachelor_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.graduate_work_bachelor_app.model.Client;
import com.example.graduate_work_bachelor_app.retrofit.AdminApi;
import com.example.graduate_work_bachelor_app.retrofit.RetrofitService;
import com.example.graduate_work_bachelor_app.retrofit.UserApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ActivityAdminAuthorization extends AppCompatActivity {
    private EditText editTextPhone;
    private EditText editTextPassword;
    private Button loginButton;
    private Button backButton;
    private AdminApi adminApi;
    private RetrofitService retrofitService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_authorization);
        editTextPhone = findViewById(R.id.adminPhone);
        editTextPassword = findViewById(R.id.adminPass);
        loginButton = findViewById(R.id.loginButton);
        backButton = findViewById(R.id.backButton);

        retrofitService = new RetrofitService();
        Retrofit retrofit = retrofitService.getRetrofit();
        adminApi = retrofit.create(AdminApi.class);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAdmin();
            }
        });
    }
    private void loginAdmin() {
        String phoneNumber = editTextPhone.getText().toString();
        String password = editTextPassword.getText().toString();

        Client client = new Client();
        client.setPhoneNumber(phoneNumber);
        client.setAdminPass(password);

        Call<ResponseBody> call = adminApi.adminLogin(client);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ActivityAdminAuthorization.this, "Автентифікація успішна", Toast.LENGTH_SHORT).show();
                    Intent adminIntent = new Intent(ActivityAdminAuthorization.this, ActivityMainPageAdmin.class);
                    startActivity(adminIntent);
                } else if (response.code() == 401 || response.code() == 404) {
                    Toast.makeText(ActivityAdminAuthorization.this, "Невірний номер телефона або пароль", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivityAdminAuthorization.this, "Помилка автентифікації", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ActivityAdminAuthorization.this, "Помилка підключення", Toast.LENGTH_SHORT).show();
            }
        });
    }
}