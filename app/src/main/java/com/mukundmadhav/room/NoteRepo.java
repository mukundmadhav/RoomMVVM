package com.mukundmadhav.room;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class NoteRepo {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepo(Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note) {
        new InsertNodeAsyncTask(noteDao).execute(note);
    }

    public void update(Note note) {
        new UpdateNodeAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note) {
        new DeleteNodeAsyncTask(noteDao).execute(note);
    }

    public void deleteAll() {
        new DeleteAllNodeAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    private static class InsertNodeAsyncTask extends AsyncTask<Note,Void,Void> {
        private NoteDao noteDao;

        public InsertNodeAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNodeAsyncTask extends AsyncTask<Note,Void,Void> {
        private NoteDao noteDao;

        public UpdateNodeAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNodeAsyncTask extends AsyncTask<Note,Void,Void> {
        private NoteDao noteDao;

        public DeleteNodeAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNodeAsyncTask extends AsyncTask<Void,Void,Void> {
        private NoteDao noteDao;

        public DeleteAllNodeAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAll();
            return null;
        }
    }

}
