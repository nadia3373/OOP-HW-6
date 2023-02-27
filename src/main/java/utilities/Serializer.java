package utilities;

import java.util.Collection;

public abstract class Serializer<T> {
    public abstract Collection<T> deserialize(String input, Class<T> type) throws Exception;
    public abstract String serialize(Collection<T> tasks) throws Exception;
}
