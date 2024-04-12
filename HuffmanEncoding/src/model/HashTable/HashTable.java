package model.HashTable;

/**
 * A custom HashTable class to store key-value pairs using linked lists for collision resolution.
 * Supports generic types for keys (K) and values (V).
 * 
 * @author Adam Fehse
 */
public class HashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 17;  // Default capacity of the hash table
    private Entry<K, V>[] table;  // Array of Entry's (buckets)
    private int size;  // Number of key-value pairs stored in the hash table
    
    /**
     * Inner class: representing a key-value pair (entry) in the hash table.
     */
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;  // Reference to the next entry in case of collision

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    /**
     * Constructs a new HashTable with the default capacity.
     */
    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a new HashTable with the specified capacity.
     * 
     * @param capacity The initial capacity of the hash table
     */
    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        this.table = new Entry[capacity];
        this.size = 0;
    }
    
    /**
     * Retrieves the value associated with the specified key from the hash table.
     * 
     * @param key The key whose associated value is to be retrieved
     * @return The value associated with the specified key, or null if the key is not found
     */
    public V get(K key) {
        int index = hash(key);
        Entry<K, V> entry = findEntry(key, index);
        return (entry != null) ? entry.value : null;
    }

    /**
     * Returns the value associated with the specified key in the hash table.
     * If the key is not found, returns the specified default value.
     * 
     * @param key The key whose associated value is to be retrieved
     * @param defaultValue The default value to return if the key is not found
     * @return value to which the specified key is mapped, or defaultValue if this map 
     * contains no mapping for the key
     */
    public V getOrDefault(K key, V defaultValue) {
        int index = hash(key);
        Entry<K, V> entry = findEntry(key, index);
        return (entry != null) ? entry.value : defaultValue;
    }

    /**
     * Associates the specified value with the specified key in the hash table.
     * 
     * @param key The key with which the specified value is to be associated
     * @param value The value to be associated with the specified key
     */
    public void put(K key, V value) {
        int index = hash(key);
        Entry<K, V> entry = findEntry(key, index);
        if (entry != null) {
            entry.value = value;  // Update existing entry
        } else {
            // Key not found, create a new entry and add it to the bucket
            Entry<K, V> newEntry = new Entry<K, V>(key, value);
            newEntry.next = table[index];  // Insert at the beginning of the linked list
            table[index] = newEntry;
            size++;
        }
    }

    /**
     * Computes the hash code for the specified key.
     * 
     * @param key The key for which to compute the hash code
     * @return The hash code computed for the key
     */
    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    /**
     * Finds and returns the entry associated with the specified key in the specified bucket index.
     * 
     * @param key The key to search for
     * @param index The bucket index
     * @return The entry associated with the key, or null if the key is not found in the bucket
     */
    private Entry<K, V> findEntry(K key, int index) {
        Entry<K, V> entry = table[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry;
            }
            entry = entry.next;
        }
        return null;  // Key not found in the bucket
    }

    /**
     * Returns the current size (number of key-value pairs) of the hash table.
     * 
     * @return The size of the hash table
     */
    public int size() {
        return size;
    }

    /**
     * Retrieves the value associated with the specified character key from the hash table.
     * 
     * @param c The character key whose associated value is to be retrieved
     * @return The value associated with the specified character key, or null if the key is not found
     */
    @SuppressWarnings("unchecked")
	public V get(char c) {
        // Convert the char c to a Character object
        Character key = Character.valueOf(c);

        // Use the existing get(K key) method to retrieve the value
        return get((K) key); // Cast the Character key to type K and call get(K key)
    }


	
	/**
     * Returns the set of keys stored in the hash table as an array.
     * 
     * @return An array containing all the keys stored in the hash table
     */
	public Character[] keySet() {
	    Character[] keys = new Character[size];  // Create an array to store keys
	    int index = 0;

	    // Traverse each bucket and collect keys
	    for (Entry<K, V> entry : table) {
	        while (entry != null) {
	            // Ensure that the key is a Character before assigning
	            if (entry.key instanceof Character) {
	                keys[index++] = (Character) entry.key;
	            }
	            entry = entry.next;
	        }
	    }

	    return keys;
	}
	
	@Override
	public String toString() {
		String result = "";
		
		for (char key : keySet()) {
			result += String.format("%c : %d\n", key, get(key));
		}
		
		return result;
	}

}
