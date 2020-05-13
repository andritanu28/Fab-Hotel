package com.example.fabhotels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fabhotels.Database.Data;
import com.example.fabhotels.Database.Member;

import java.lang.reflect.Array;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText txtFullName, txtEmail, txtPassword, txtPhone;
    Button btnRegister;

    public int validateEmail(String email) {
        int jmlAdd = 0;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                jmlAdd++;
            }
        }
        if (jmlAdd == 1) {
            return 0;
            //true
        }
        else {
            return 1;
            //false
        }
    }

    public static boolean checkString(String s) {
        char c;
        boolean upperCase = false;
        boolean lowerCase = false;
        boolean numberr = false;

        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);

            if (Character.isDigit(c)) {
                numberr = true;
            }
            else if (Character.isUpperCase(c)) {
                upperCase = true;
            }
            else if (Character.isLowerCase(c)) {
                lowerCase = true;
            }
            if (numberr == true && upperCase == true && lowerCase == true) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkPhone(String p) {
        try {
            Double.parseDouble(p);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtFullName = findViewById(R.id.register_editTxt_fName);
        txtEmail = findViewById(R.id.register_editTxt_Email);
        txtPassword = findViewById(R.id.register_editTxt_Password);
        txtPhone = findViewById(R.id.register_editTxt_Phone);
        btnRegister = findViewById(R.id.register_Btn_Register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtFullName.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this,"Name must be filled", Toast.LENGTH_SHORT).show();
                }
                else if (!txtFullName.getText().toString().contains(" ")) {
                    Toast.makeText(RegisterActivity.this,"Name at least 2 words", Toast.LENGTH_SHORT).show();
                }
                else if (txtEmail.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this,"Email must be filled", Toast.LENGTH_SHORT).show();
                }
                else if (!txtEmail.getText().toString().contains(".") || !txtEmail.getText().toString().contains("@")){
                    Toast.makeText(RegisterActivity.this, "Email must be contain @ and .", Toast.LENGTH_SHORT).show();
                }
                else if (txtEmail.getText().toString().contains("@.") || txtEmail.getText().toString().contains(".@")) {
                    Toast.makeText(RegisterActivity.this, "Character . and @ can't be on each other side", Toast.LENGTH_SHORT).show();
                }
                else if (validateEmail(txtEmail.getText().toString()) == 1) {
                    Toast.makeText(RegisterActivity.this, "Email must be contains 1 @", Toast.LENGTH_SHORT).show();
                }
                else if (txtPassword.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Password must be filled", Toast.LENGTH_SHORT).show();
                }
                else if (txtPassword.getText().toString().length() < 5) {
                    Toast.makeText(RegisterActivity.this, "Password minimum 5 character", Toast.LENGTH_SHORT).show();
                }
                else if (checkString(txtPassword.getText().toString()) == false) {
                    Toast.makeText(RegisterActivity.this, "Password must be contain 1 letter uppercase, 1 lowercase, and number", Toast.LENGTH_SHORT).show();
                }
                else if (txtPhone.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Phone number must be filled", Toast.LENGTH_SHORT).show();
                }
                else if (!txtPhone.getText().toString().startsWith("+62")) {
                    Toast.makeText(RegisterActivity.this, "Phone number must be start with +62", Toast.LENGTH_SHORT).show();
                }
                else if (checkPhone(txtPhone.getText().toString().substring(1, txtPhone.length())) == false) {
                    Toast.makeText(RegisterActivity.this, "Phone number must be numeric", Toast.LENGTH_SHORT).show();
                }
                else {
                    Member member = new Member();
                    member.MemberId = Data.DBmember.lastElement().MemberId+1;
                    member.Email = txtEmail.getText().toString();
                    member.fName = txtFullName.getText().toString();
                    member.Password = txtPassword.getText().toString();
                    member.Phone = txtPhone.getText().toString();

                    Data.DBmember.add(member);

                    Toast.makeText(RegisterActivity.this, "SIGN UP SUCCESS", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
