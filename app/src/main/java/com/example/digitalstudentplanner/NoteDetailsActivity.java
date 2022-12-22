package com.example.digitalstudentplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

public class NoteDetailsActivity extends AppCompatActivity {

    EditText noteContentTxt,noteTitleTxt;
    ImageButton saveNoteBtn;
    TextView pageTitleTextView;
    String title,content,docID;
    boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notedetails);

        noteContentTxt = findViewById(R.id.noteContentTxt);
        noteTitleTxt = findViewById(R.id.noteTitleTxt);
        saveNoteBtn = findViewById(R.id.saveNoteBtn);
        pageTitleTextView = findViewById(R.id.pageTitle);

        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        docID = getIntent().getStringExtra("docID");

        if (docID != null && !docID.isEmpty()){
            isEditMode = true;
        }

        noteContentTxt.setText(content);
        noteTitleTxt.setText(title);
        if (isEditMode){
            pageTitleTextView.setText("Edit your note");
        }


        saveNoteBtn.setOnClickListener((v -> saveNote()));
    }

    void saveNote(){
        String noteTitle = noteTitleTxt.getText().toString();
        String noteContent = noteContentTxt.getText().toString();
        if (noteTitle == null || noteTitle.isEmpty()){
            noteTitleTxt.setError("Title Required");
            return;
        }

        Note note = new Note();
        note.setTitle(noteTitle);
        note.setContent(noteContent);
        note.setTimestamp(Timestamp.now());

        saveNotetoFirebase(note);
    }

    void saveNotetoFirebase(Note note){
        DocumentReference documentReference;
        if (isEditMode){
            documentReference = Utility.getCollectionReferenceForNotes().document(docID);
        }else{
            documentReference = Utility.getCollectionReferenceForNotes().document();
        }

        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(NoteDetailsActivity.this, "Note Added Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(NoteDetailsActivity.this, "Adding Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

