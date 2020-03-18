package seedu.nova.storage;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

public interface JsonStorage<T extends JsonParsable> {
    File originFl = null;

    public void setPath(String url);

    public boolean fileExists();

    public boolean createFile() throws IOException;

    public T readFromJson() throws IOException, ParseException;

    void saveJsonParsable(T t) throws IOException;

}
