package utilities;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;

public class FileManager<T> {
    public Collection<T> read(String fileName, Serializer<T> serializer, Class<T> type) throws Exception {
        File file = new File(fileName);
        String content = FileUtils.readFileToString(file, "UTF-8");
        return serializer.deserialize(content, type);
    }

    public boolean write(String fileName, Collection<T> contacts, Serializer<T> serializer) throws Exception {
        String content = serializer.serialize(contacts);
        File file = new File(fileName);
        FileUtils.writeStringToFile(file, content, "UTF-8");
        return true;
    }
}
