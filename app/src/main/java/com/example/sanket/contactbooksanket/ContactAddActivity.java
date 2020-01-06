package com.example.sanket.contactbooksanket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ContactAddActivity extends AppCompatActivity {  //for add record
    EditText etFirstName2, etSecondName2, etPhone2;
    ImageButton btnCancel, btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_add);
        etFirstName2 = (EditText) findViewById(R.id.etFirstName2);
        etSecondName2 = (EditText) findViewById(R.id.etSecondName2);
        etPhone2 = (EditText) findViewById(R.id.etPhone2);
        btnCancel = (ImageButton) findViewById(R.id.btnCancel);
        btnEdit = (ImageButton) findViewById(R.id.btnEdit);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEmployee();

            }
        });


    }


    public void addEmployee() {
        String firstname = etFirstName2.getText().toString().trim();
        String secondname = etSecondName2.getText().toString().trim();
        String phone = etPhone2.getText().toString().trim();


        if (firstname.isEmpty()) {
            etFirstName2.setError("Name can't be empty");
            etFirstName2.requestFocus();
            return;
        }
        if (secondname.isEmpty()) {
            etSecondName2.setError("Salary can't be empty");
            etSecondName2.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            etPhone2.setError("Salary can't be empty");
            etPhone2.requestFocus();
            return;
        }
        if (phone.length() != 10) {
            etPhone2.setError("Enter valid phone number - 0-10");
            etPhone2.requestFocus();
            return;
        }
        Toast.makeText(this, "Contact added successfully", Toast.LENGTH_SHORT).show();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("data", new DataPojo(firstname, secondname, phone));
        setResult(RESULT_OK, returnIntent);
        finish();

    }


}
