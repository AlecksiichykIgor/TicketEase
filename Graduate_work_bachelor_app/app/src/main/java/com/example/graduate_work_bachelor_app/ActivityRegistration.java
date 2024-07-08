package com.example.graduate_work_bachelor_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graduate_work_bachelor_app.retrofit.RetrofitService;
import com.example.graduate_work_bachelor_app.retrofit.UserApi;
import com.example.graduate_work_bachelor_app.model.Client; // Подставьте путь к вашему классу Client

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ActivityRegistration extends AppCompatActivity {
    private TextView headerTitle;
    private EditText editTextPassword;
    private TextView showHidePass;
    private EditText editTextFirstname;
    private EditText editTextLastname;
    private EditText editTextPhone;
    private RetrofitService retrofitService;
    private UserApi userApi;
    private EditText editTextEmail;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        headerTitle = findViewById(R.id.headerTitle);
        editTextFirstname = findViewById(R.id.editTextFirstname);
        editTextLastname = findViewById(R.id.editTextLastname);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        showHidePass = findViewById(R.id.show_hide_Pass);
        editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        retrofitService = new RetrofitService();
        Retrofit retrofit = retrofitService.getRetrofit();
        userApi = retrofit.create(UserApi.class);

        showHidePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void togglePasswordVisibility() {
        if (editTextPassword.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            showHidePass.setBackground(ContextCompat.getDrawable(this, R.drawable.view)); // Фон для видимого состояния
        } else {
            // Пароль видим, скрываем его
            editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            showHidePass.setBackground(ContextCompat.getDrawable(this, R.drawable.hide)); // Фон для скрытого состояния
        }
        editTextPassword.setSelection(editTextPassword.getText().length());
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\+380\\d{9}$");
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        // Проверка на длину пароля (не менее 6 символов) и наличие хотя бы одной буквы
        return password.length() >= 6 && password.matches(".*[a-zA-Z]+.*");
    }

    private void registerUser() {
        String firstName = editTextFirstname.getText().toString();
        String lastName = editTextLastname.getText().toString();
        String phoneNumber = editTextPhone.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        // Проверяем валидность данных перед отправкой на сервер
        if (!isValidPhoneNumber(phoneNumber)) {
            // Неправильный формат номера телефона
            editTextPhone.setError("Неправильный формат номера телефона");
            return;
        }

        if (!isValidEmail(email)) {
            // Неправильный формат адреса электронной почты
            editTextEmail.setError("Неправильный адрес электронной почты");
            return;
        }

        if (!isValidPassword(password)) {
            // Пароль не соответствует требованиям
            editTextPassword.setError("Пароль должен содержать хотя бы одну букву и быть не менее 6 символов в длину");
            return;
        }

        // Создаем новый объект клиента
        Client client = new Client(firstName, lastName, phoneNumber, email, password);

        // Вызываем API для регистрации клиента
        Call<Void> call = userApi.registerUser(client);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Регистрация прошла успешно
                    Toast.makeText(ActivityRegistration.this, "Реєстрація успішна", Toast.LENGTH_SHORT).show();
                    editTextFirstname.setText("");
                    editTextLastname.setText("");
                    editTextPhone.setText("");
                    editTextEmail.setText("");
                    editTextPassword.setText("");
                    Intent intent = new Intent(ActivityRegistration.this, ActivityMainPageUser.class);
                    startActivity(intent);
                } else {
                    // Ошибка при регистрации
                    headerTitle.setText(response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Ошибка при обращении к серверу
                Toast.makeText(ActivityRegistration.this, "Ошибка при обращении к серверу: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                headerTitle.setText(t.getMessage());
            }
        });
    }
}
