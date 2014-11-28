package tk.manf.util;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractFactory<K, V extends Identifiable<K>> {
    private final Map<K, V> handlers;
    private final String name;
    private final UnsafeFactory<K, V> unsafe;
    
    public AbstractFactory(String name) {
        this.handlers = new HashMap<>();
        this.name = name;
        this.unsafe = new UnsafeFactory<>(this);
    }
    
    protected V get(K k) {
        if (!handlers.containsKey(k)) {
            throw new IllegalArgumentException("Unknown " + name +  " '" + k + "'");
        }
        return handlers.get(k);
    }

    public UnsafeFactory<K, V> unsafe() {
        return unsafe;
    }
    
    public static class UnsafeFactory<K, V extends Identifiable<K>>  {
        private final AbstractFactory<K, V> parent;
        
        public UnsafeFactory(AbstractFactory<K, V> parent) {
            this.parent = parent;
        }
        
        public UnsafeFactory<K, V> register(V v) {
            parent.handlers.put(v.getIdent(), v);
            // Allow chaining
            return this;
        }
    }
}