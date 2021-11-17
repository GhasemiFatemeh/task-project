package common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RamDB {
    public static Map<Object, Object> cachedDB = new ConcurrentHashMap<>();
    private RamDB(){};
}
