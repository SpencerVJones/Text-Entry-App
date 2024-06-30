// Spencer Jones
// MDV3832-0 - 062024
// MainActivity.java

package com.example.jonesspencer_ce08;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_EDIT_TEXT = "edit_text";
    private static final String KEY_STRING_LIST = "string_list";

    private EditText editText;
    private Button submitButton;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> stringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        submitButton = findViewById(R.id.submitButton);
        listView = findViewById(R.id.listView);

        stringList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringList);
        listView.setAdapter(adapter);

        if (savedInstanceState != null) {
            editText.setText(savedInstanceState.getString(KEY_EDIT_TEXT));
            stringList.addAll(savedInstanceState.getStringArrayList(KEY_STRING_LIST));
            adapter.notifyDataSetChanged();
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString().trim();
                if (input.length() > 0) {
                    stringList.add(input);
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                } else {
                    Toast.makeText(MainActivity.this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_EDIT_TEXT, editText.getText().toString());
        outState.putStringArrayList(KEY_STRING_LIST, stringList);
    }
}