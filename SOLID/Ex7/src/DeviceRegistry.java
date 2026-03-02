import java.util.*;

public class DeviceRegistry {
    private final List<Object> devices = new ArrayList<>();

    public void add(Object d) {
        devices.add(d);
    }

    @SuppressWarnings("unchecked")
    public <T> T getFirstOfType(Class<T> type) {
        for (Object d : devices) {
            if (type.isInstance(d))
                return (T) d;
        }
        throw new IllegalStateException("Missing: " + type.getSimpleName());
    }

    public Object getFirstByName(String simpleName) {
        for (Object d : devices) {
            if (d.getClass().getSimpleName().equals(simpleName))
                return d;
        }
        throw new IllegalStateException("Missing: " + simpleName);
    }
}
