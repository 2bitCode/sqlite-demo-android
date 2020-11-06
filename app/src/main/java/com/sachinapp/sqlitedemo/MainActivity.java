package com.sachinapp.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_add, btn_viewAll;
    EditText et_name, et_age;
    Switch sw_active;
    ListView lv_data;
    ArrayAdapter arrayAdapter;
    DbHandler dbHandler;
    SearchView sv_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        btn_viewAll = findViewById(R.id.btn_viewAll);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        sw_active = findViewById(R.id.sw_active);
        lv_data = findViewById(R.id.lv_data);
        sv_search = findViewById(R.id.sv_search);

        dbHandler = new DbHandler(MainActivity.this);
        lv_data.setAdapter(new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dbHandler.fetchAllData()));

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerModel customerModel;

                try
                {
                    customerModel = new CustomerModel(-1, et_name.getText().toString(), Integer.parseInt(et_age.getText().toString()), sw_active.isChecked());
                  //  Toast.makeText(MainActivity.this, customerModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    customerModel = new CustomerModel(-1, "error", 0, false);
                   // Toast.makeText(MainActivity.this, customerModel.toString(), Toast.LENGTH_SHORT).show();
                }

                DbHandler dbHandler1 = new DbHandler(MainActivity.this);
                boolean success = dbHandler1.addOne(customerModel);
                lv_data.setAdapter(new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dbHandler1.fetchAllData()));
                Toast.makeText(MainActivity.this, success == true ? "Added Successfully" : "Error Occurred", Toast.LENGTH_SHORT).show();
            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                DbHandler dbhandler = new DbHandler(MainActivity.this);
                List<CustomerModel> allData = dbhandler.fetchAllData();

                arrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, allData);

                lv_data.setAdapter(arrayAdapter);
            }
        });

        lv_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CustomerModel clickedCustomer = (CustomerModel) adapterView.getItemAtPosition(i);
                dbHandler.deleteOne(clickedCustomer);
                Toast.makeText(MainActivity.this, "Deleted" + clickedCustomer.toString(), Toast.LENGTH_SHORT).show();
                lv_data.setAdapter(new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dbHandler.fetchAllData()));
            }
        });
    }
}