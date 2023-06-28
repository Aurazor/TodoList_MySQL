package com.nikhilaukhaj;

import java.util.List;

public interface NotesInterface {
    boolean saveNote(Note note);
    List<Note> viewNoteForUser(Long userId);

}
