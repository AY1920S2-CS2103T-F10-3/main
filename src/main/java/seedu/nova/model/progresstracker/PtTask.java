package seedu.nova.model.progresstracker;

import seedu.nova.model.note.NotesList;

/**
 * Represents a project task
 */
public class PtTask {
    private TaskDesc taskDesc;
    private NotesList notesList;
    private PtWeek ptWeek;

    public PtTask(TaskDesc taskDesc, int num) {
        this.taskDesc = taskDesc;
        ptWeek = new PtWeek(num);
        notesList = new NotesList();
    }

    public TaskDesc getTaskDesc() {
        return taskDesc;
    }

    public NotesList getNotesList() {
        return notesList;
    }

    public PtWeek getPtWeek() {
        return ptWeek;
    }
}
