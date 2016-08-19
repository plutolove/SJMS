package com.example.controller;

import com.example.dao.JobDao;
import com.example.dao.NoteDao;
import com.example.model.Job;
import com.example.model.Note;
import com.jfinal.core.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sb on 16-7-22.
 */
public class SysNoteController extends Controller {

    public void index(){
        List<Note> noteListInMonth = NoteDao.findInMonth();
        List<Note> noteListMonthAgo = NoteDao.findMonthAgo();
        setAttr("noteListInMonth", noteListInMonth);
        setAttr("noteListMonthAgo", noteListMonthAgo);
        render("sys-note.jsp");
    }

    public void view(){
        String id = getPara("id");
        Note note = Note.Dao.findById(id);
        setAttr("note", note);
        render("sys-note-view.jsp");
    }
}
