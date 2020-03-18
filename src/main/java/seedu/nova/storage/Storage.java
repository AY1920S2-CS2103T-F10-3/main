package seedu.nova.storage;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import seedu.nova.model.Model;
import seedu.nova.model.ModelManager;

import java.io.*;

public class Storage implements JsonStorage<Model> {
    private static final String DEFAULT_PATH = "./nova.json";
    File originFl;

    public Storage() {
        this(DEFAULT_PATH);
    }

    public Storage(String path) {
        this.originFl = new File(path);
    }

    public void setPath(String url) {
        this.originFl = new File(url);
    }

    public boolean fileExists() {
        return this.originFl.isFile();
    }

    public boolean createFile() throws IOException {
        return new File(this.originFl.getParent()).mkdirs() && this.originFl.createNewFile();
    }

    public Model readFromJson() throws IOException, ParseException {
        JSONObject jo = (JSONObject) new JSONParser().parse(new FileReader(this.originFl));
        return ModelManager.parseFromJson(jo);
    }

    public void saveJsonParsable(Model t) throws IOException {
        JSONObject jo = t.toJsonObject();
        PrintWriter pw = new PrintWriter(this.originFl);
        pw.write(jo.toJSONString());

        pw.flush();
        pw.close();
    }
}
