package com.example.sanket.contactbooksanket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditUserActivity extends AppCompatActivity {
    EditText etFirstName3, etSecondName3, etPhone3;
    Button btnEdit3, btnCancel3, btnDelete3;
    DataPojo dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        etFirstName3 = (EditText) findViewById(R.id.etFirstName3);
        etSecondName3 = (EditText) findViewById(R.id.etSecondName3);
        etPhone3 = (EditText) findViewById(R.id.etPhone3);
        btnCancel3 = (Button) findViewById(R.id.btnCancel3);
        btnEdit3 = (Button) findViewById(R.id.btnEdit3);
        btnDelete3 = (Button) findViewById(R.id.btnDelete3);
        dp = (DataPojo) getIntent().getParcelableExtra("data"); //whole data pass
        etFirstName3.setText(dp.getFirstname());
        etFirstName3.setSelection(etFirstName3.getText().length());  //place cursor at end
        etSecondName3.setText(dp.getSecondname());
        etPhone3.setText(dp.getPhone());

        btnEdit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstname = etFirstName3.getText().toString().trim();
                String secondname = etSecondName3.getText().toString().trim();
                String phone = etPhone3.getText().toString().trim();

                if (firstname.isEmpty()) {
                    etFirstName3.setError("Firstname can't be empty");
                    etFirstName3.requestFocus();
                    return;
                }
                if (secondname.isEmpty()) {
                    etSecondName3.setError("Secondname can't be empty");
                    etSecondName3.requestFocus();
                    return;
                }
                if (phone.isEmpty()) {
                    etPhone3.setError("Phone can't be empty");
                    etPhone3.requestFocus();
                    return;
                }
                if (phone.length() != 10) {
                    etPhone3.setError("Enter valid phone number - 0-10");
                    etPhone3.requestFocus();
                    return;
                }

                dp.setFirstname(firstname);
                dp.setSecondname(secondname);
                dp.setPhone(phone);

                Toast.makeText(EditUserActivity.this, "User Updated", Toast.LENGTH_SHORT).show();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("data", dp);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
        btnCancel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnDelete3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("data", dp);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
    }


}
