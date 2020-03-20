package seedu.nova.model.event;

import seedu.nova.model.common.Copyable;

public class EventDetails implements Copyable<EventDetails> {
    final String name;
    String desc;
    String venue;
    String note;

    public EventDetails(String name) {
        this(name, null, null, null);
    }

    public EventDetails(String name, String venue) {
        this(name, null, venue, null);
    }

    private EventDetails(String name, String desc, String venue, String note) {
        this.name = name;
        this.desc = desc;
        this.venue = venue;
        this.note = note;
    }

    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getVenue() {
        return this.venue;
    }

    public String getNote() {
        return this.note;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setNote(String note) {
        this.note = note;
    }

    private String genField(String attr, String val) {
        if(val == null) {
            return "";
        } else {
            return String.format("%s: %s\n", attr, val);
        }
    }

    @Override
    public String toString() {
        return genField("Name", this.name) +
                genField("Description", this.desc) +
                genField("Venue", this.venue) +
                genField("Note", this.note);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof EventDetails) {
            EventDetails e = (EventDetails) obj;
            return this.name.equals(e.name);
        } else {
            return super.equals(obj);
        }
    }

    @Override
    public EventDetails getCopy() {
        return new EventDetails(this.name, this.desc, this.venue, this.note);
    }
}
