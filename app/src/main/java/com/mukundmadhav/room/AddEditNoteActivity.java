package com.mukundmadhav.room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "com.mukundmadhav.room.EXTRA_TITLE";
    public static final String EXTRA_DESC = "com.mukundmadhav.room.EXTRA_DESC";
    public static final String EXTRA_PRI = "com.mukundmadhav.room.EXTRA_PRI";
    public static final String EXTRA_ID = "com.mukundmadhav.room.EXTRA_ID";

    private EditText edit_title, edit_desc;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        edit_desc = findViewById(R.id.edit_text_desc);
        edit_title = findViewById(R.id.edit_text_title);
        numberPicker = findViewById(R.id.number_picker_prioity);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);


        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            edit_title.setText(intent.getStringExtra(EXTRA_TITLE));
            edit_desc.setText(intent.getStringExtra(EXTRA_DESC));
            numberPicker.setValue(intent.getIntExtra(EXTRA_PRI, 1));
        } else
            setTitle("Add Note");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sav_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote() {
        String title = edit_title.getText().toString();
        String desc = edit_desc.getText().toString();
        int priority = numberPicker.getValue();

        if (title.trim().isEmpty() || desc.trim().isEmpty()) {
            Toast.makeText(this, "Please insert values", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESC, desc);
        data.putExtra(EXTRA_PRI, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);

        if (id != -1)
            data.putExtra(EXTRA_ID,id);

        setResult(RESULT_OK, data);

        finish();

    }
}
