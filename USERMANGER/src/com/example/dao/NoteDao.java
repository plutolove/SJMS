package com.example.dao;


import com.example.model.Note;

import java.util.List;

/**
 * Created by sb on 16-7-22.
 */
public class NoteDao {

    public static List<Note> findAll(){
        return Note.Dao.find("select * from note");
    }

    public static List<Note> findInMonth(){
        List<Note> noteList = Note.Dao.find("select * from note where date_format(time,'%Y-%m')=date_format(now(),'%Y-%m') order by time DESC");
        return noteList;
    }

    public static List<Note> findMonthAgo(){
        return Note.Dao.find("select * from note where date_format(time,'%Y-%m')<date_format(now(),'%Y-%m') order by time DESC");
    }

//    public static void main(String[] args){
//        List<Note> noteList = NoteDao.findAll();
//        System.out.print(noteList.toString());
//    }
}
