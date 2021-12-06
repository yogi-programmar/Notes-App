package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNotesActivity extends AppCompatActivity {
    EditText title;
    EditText  description;
    Button addnote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnotes);
        title=findViewById(R.id.edttitle);
    description=findViewById(R.id.edtdescr);
    addnote=findViewById(R.id.addnotes);
        addnote.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!TextUtils.isEmpty(title.getText().toString())&&!TextUtils.isEmpty(description.getText().toString())){
                DatabaseClass db =new DatabaseClass(AddNotesActivity.this);
                db.addNotes(title.getText().toString(),
                        description.getText().toString());
                Intent intent= new Intent (AddNotesActivity.this,MainActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(AddNotesActivity.this, "Both Field Requireds", Toast.LENGTH_SHORT).show();
            }

        }
    });


}
}