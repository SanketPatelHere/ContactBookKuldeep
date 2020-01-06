package com.example.sanket.contactbooksanket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ContactShowActivity extends AppCompatActivity {
    DataPojo dp;
    TextView tvName, tvPhone;
    ImageView imgvShow;
    String firstnameShow, secondnameShow, phoneShow;
    private boolean isDataChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_show);
        tvName = (TextView) findViewById(R.id.tvName);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        imgvShow = (ImageView) findViewById(R.id.imgvShow);

        Bundle data = getIntent().getExtras();
        dp = (DataPojo) data.getParcelable("data"); //whole data pass

        tvName.setText("Name = " + dp.getFirstname() + " " + dp.getSecondname());
        tvPhone.setText("Phone Number = " + dp.getPhone());
        imgvShow.setImageResource(R.drawable.admin);
        firstnameShow = dp.getFirstname();
        secondnameShow = dp.getSecondname();
        phoneShow = dp.getPhone();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ContactShowActivity.this, EditUserActivity.class);
                i.putExtra("data", dp);
                startActivityForResult(i, 1);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                if (data.getParcelableExtra("data") != null) {
                    isDataChanged = true;
                    dp = data.getParcelableExtra("data");
                    tvName.setText("Name = " + dp.getFirstname() + " " + dp.getSecondname());
                    tvPhone.setText("Phone Number = " + dp.getPhone());
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (isDataChanged) //true = data changed
            {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("data", dp);
            setResult(RESULT_OK, returnIntent);
            finish();
        } else //direct back
            {
            super.onBackPressed();
        }
    }
}
