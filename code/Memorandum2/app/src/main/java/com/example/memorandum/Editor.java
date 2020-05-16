package com.example.memorandum;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;

public class Editor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        //工具条
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //向上按钮
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);//打开新页面
        startActivity(intent);
        //super.onBackPressed();
    }

    public void onClickDone(View view){
        CharSequence textSequence = "保存成功!";
        int duration = Toast.LENGTH_SHORT;

        EditText titleView = (EditText) findViewById(R.id.edit_title);
        EditText textView = (EditText) findViewById(R.id.edit_text);
        Spinner typeView = (Spinner) findViewById(R.id.spinner);
        String title = titleView.getText().toString();
        String text = textView.getText().toString();
        String typeName = String.valueOf(typeView.getSelectedItem());
        Note.notes.add(new Note(title, text, typeName));
        if(typeName.equals("学习")){
            Note.notesStu.add(new Note(title, text, typeName));
        }
        else if (typeName.equals("生活")){
            Note.notesLife.add(new Note(title, text, typeName));
        }
        else if (typeName.equals("其他")){
            Note.notesOther.add(new Note(title, text, typeName));
        }
        textView.setText(null);
        titleView.setText(null);


        //Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("name",1);
        //startActivity(intent);

        Toast toast = Toast.makeText(this, textSequence, duration);
        toast.show();
    }
}
