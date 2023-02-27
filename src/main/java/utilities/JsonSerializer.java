package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Collection;

public class JsonSerializer<T> extends Serializer<T> {
    private ObjectMapper objectMapper;

    public JsonSerializer() {
        objectMapper = new ObjectMapper();
    }

    @Override
    public Collection<T> deserialize(String input, Class<T> type) throws Exception {
        return objectMapper.readValue(input, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, type));
    }

    @Override
    public String serialize(Collection<T> objects) throws Exception {
        return objectMapper.writeValueAsString(objects);
    }
}

