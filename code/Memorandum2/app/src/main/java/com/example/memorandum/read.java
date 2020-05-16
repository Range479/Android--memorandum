package com.example.memorandum;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class read extends AppCompatActivity {

    public static final String EXTRA_NOTE_ID = "noteId";
    private ShareActionProvider shareActionProvider;
    public String NoteText;
    public String NoteTitle;
    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        type= (Integer)getIntent().getExtras().get("type");
        int noteId = (Integer)getIntent().getExtras().get(EXTRA_NOTE_ID);
        switch(type){
            case 0:
                NoteTitle = Note.notes.get(noteId).getTitle();
                NoteText = Note.notes.get(noteId).getText();
                break;
            case 1:
                NoteTitle = Note.notesStu.get(noteId).getTitle();
                NoteText = Note.notesStu.get(noteId).getText();
                break;
            case 2:
                NoteTitle = Note.notesLife.get(noteId).getTitle();
                NoteText = Note.notesLife.get(noteId).getText();
                break;
            case 3:
                NoteTitle = Note.notesOther.get(noteId).getTitle();
                NoteText = Note.notesOther.get(noteId).getText();
                break;
        }

        TextView textView = (TextView)findViewById(R.id.read_title);
        textView.setText(NoteTitle);
        TextView textView2 = (TextView)findViewById(R.id.read_text);
        textView2.setText(NoteText);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        int noteId = (Integer)getIntent().getExtras().get(EXTRA_NOTE_ID);
        Note notes = Note.notes.get(noteId);
        getMenuInflater().inflate(R.menu.menu_read, menu);
        MenuItem menuItem = menu.findItem(R.id.read_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent(NoteText);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int noteId = (Integer)getIntent().getExtras().get(EXTRA_NOTE_ID);
        switch (item.getItemId()){//得到动作的ID
            case R.id.dele:
                switch(type){
                    case 0:
                        if(Note.notes.get(noteId).getType().equals("学习")){
                            for(int i = 0; i < Note.notesStu.size(); i++){
                                if(Note.notesStu.get(i).getTitle().equals(Note.notes.get(noteId).getTitle())
                                    && Note.notesStu.get(i).getText().equals(Note.notes.get(noteId).getText())
                                    && Note.notesStu.get(i).getType().equals(Note.notes.get(noteId).getType())){
                                Note.notesStu.remove(i);
                                break;
                                }
                            }
                        }
                        else if(Note.notes.get(noteId).getType().equals("生活")){
                            for(int i = 0; i < Note.notesLife.size(); i++){
                                if(Note.notesLife.get(i).getTitle().equals(Note.notes.get(noteId).getTitle())
                                        && Note.notesLife.get(i).getText().equals(Note.notes.get(noteId).getText())
                                        && Note.notesLife.get(i).getType().equals(Note.notes.get(noteId).getType())){
                                    Note.notesLife.remove(i);
                                    break;
                                }
                            }
                        }
                        else if(Note.notes.get(noteId).getType().equals("其他")){
                            for(int i = 0; i < Note.notesOther.size(); i++){
                                if(Note.notesOther.get(i).getTitle().equals(Note.notes.get(noteId).getTitle())
                                        && Note.notesOther.get(i).getText().equals(Note.notes.get(noteId).getText())
                                        && Note.notesOther.get(i).getType().equals(Note.notes.get(noteId).getType())){
                                    Note.notesOther.remove(i);
                                    break;
                                }
                            }
                        }
                        Note.notes.remove(noteId);
                        break;
                    case 1:
                        for(int i = 0; i < Note.notes.size(); i++){
                            if(Note.notes.get(i).getTitle().equals(Note.notesStu.get(noteId).getTitle())
                                    && Note.notes.get(i).getText().equals(Note.notesStu.get(noteId).getText())
                                    && Note.notes.get(i).getType().equals(Note.notesStu.get(noteId).getType())){
                                Note.notes.remove(i);
                                break;
                            }
                        }
                        Note.notesStu.remove(noteId);
                        break;
                    case 2:
                        for(int i = 0; i < Note.notes.size(); i++){
                        if(Note.notes.get(i).getTitle().equals(Note.notesLife.get(noteId).getTitle())
                                && Note.notes.get(i).getText().equals(Note.notesLife.get(noteId).getText())
                                && Note.notes.get(i).getType().equals(Note.notesLife.get(noteId).getType())){
                            Note.notes.remove(i);
                            break;
                        }
                    }
                        Note.notesLife.remove(noteId);
                        break;
                    case 3:
                        for(int i = 0; i < Note.notes.size(); i++){
                            if(Note.notes.get(i).getTitle().equals(Note.notesOther.get(noteId).getTitle())
                                    && Note.notes.get(i).getText().equals(Note.notesOther.get(noteId).getText())
                                    && Note.notes.get(i).getType().equals(Note.notesOther.get(noteId).getType())){
                                Note.notes.remove(i);
                                break;
                            }
                        }
                        Note.notesOther.remove(noteId);
                        break;
                }

                CharSequence textS = "删除成功！";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(this, textS, duration);
                toast.show();
                return true;//返回true表示已经处理了所单击的动作源
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setShareActionIntent(String text){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }
}
