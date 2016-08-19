package org.ahu.edu.bigdatalab.sum.controller;

import com.jfinal.core.Controller;
import org.ahu.edu.bigdatalab.sum.model.Note;

import java.util.List;

public class SysNoteController extends Controller {

    public void index(){
        List<Note> noteListInMonth = Note.DAO.findInMonth();
        List<Note> noteListMonthAgo = Note.DAO.findMonthAgo();
        setAttr("noteListInMonth", noteListInMonth);
        setAttr("noteListMonthAgo", noteListMonthAgo);
        renderJsp("sys-note.jsp");
    }

    public void view(){
        String id = getPara("id");
        Note note = Note.DAO.findById(id);
        setAttr("note", note);
        renderJsp("sys-note-view.jsp");
    }
}
