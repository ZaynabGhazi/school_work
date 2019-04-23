import java.util.ArrayList;
public class DoubleHashMap<K,V> extends AbstractHashMap<K,V>{
    private MapEntry<K,V>[] table;        // a fixed array of entries (all initially null)
    private MapEntry<K,V> DEFUNCT = new MapEntry<>(null, null);
    // provide same constructors as base class
    /** Creates a hash table with capacity 17 and prime factor 109345121. */
    public DoubleHashMap() { super(); }
    
    /** Creates a hash table with given capacity and prime factor 109345121. */
    public DoubleHashMap(int cap) { super(cap); }

    /** Creates a hash table with the given capacity and prime factor. */
    public DoubleHashMap(int cap, int p) { super(cap, p); }
    
    /** Creates an empty table having length equal to current capacity. */
    @Override
    @SuppressWarnings({"unchecked"})
    protected void createTable() {
	table = (MapEntry<K,V>[]) new MapEntry[capacity];   // safe cast
    }

    /** Returns true if location is either empty or the "defunct" sentinel. */
    private boolean isAvailable(int j) {
	return (table[j] == null || table[j] == DEFUNCT);
    }
    
    /**
     * Searches for an entry with key equal to k (which is known to have
     * hash value h), returning the index at which it was found, or
     * returning -(a+1) where a is the index of the first empty or
     * available slot that can be used to store a new such entry.
     *
     * @param h the precalculated hash value of the given key
     * @param k the key
     * @return index of found entry or if not found, value -(a+1) where a is index of first available slot
     */
    private int findSlot(int h, K k) {
	//set # of probes for this insertion to 0
	probes=0;
	int avail = -1;                               // no slot available (thus far)
	int j = h;                                    // index while scanning table
	do {
	    //increment # of probes 
	    probes++;
	    if (isAvailable(j)) {                       // may be either empty or defunct
		if (avail == -1) avail = j;               // this is the first available slot!
		if (table[j] == null) break;              // if empty, search fails immediately
	    } else if (table[j].getKey().equals(k))
		return j;                             // successful  match
	    //double-probe by using second hashcode function
	    j = (j+hash2(k)) % capacity;
	} while (j!=h);                             // stop if we return to the start
	return -(avail + 1);                          // search has failed
    }
    //initialise prime constant for hashmap
    private static final int PRIME =13 ;
    /**
     * hashes key into second value for double probing
     * @param key to be hashed for 2nd time
     * @return 2nd hash integer of the key
     */
    private int hash2(K i){
	return PRIME-(Math.abs(i.hashCode())%PRIME);
    }
    
    /**
     * Returns value associated with key k in bucket with hash value h.
     * If no such entry exists, returns null.
     * @param h  the hash value of the relevant bucket
     * @param k  the key of interest
     * @return   associate value (or null, if no such entry)
   */
    @Override
    protected V bucketGet(int h, K k) {
	int j = findSlot(h, k);
	if (j < 0) return null;                   // no match found
    return table[j].getValue();
    }
    
  /**
   * Associates key k with value v in bucket with hash value h, returning
   * the previously associated value, if any.
   * @param h  the hash value of the relevant bucket
   * @param k  the key of interest
   * @param v  the value to be associated
   * @return   previous value associated with k (or null, if no such entry)
   */
    @Override
    protected V bucketPut(int h, K k, V v) {
	int j = findSlot(h, k);
	if (j >= 0)                               // this key has an existing entry
	    return table[j].setValue(v);
	table[-(j+1)] = new MapEntry<>(k, v);     // convert to proper index
	n++;
	return null;
    }
    
    /**
     * Removes entry having key k from bucket with hash value h, returning
     * the previously associated value, if found.
     * @param h  the hash value of the relevant bucket
     * @param k  the key of interest
     * @return   previous value associated with k (or null, if no such entry)
     */
  @Override
  protected V bucketRemove(int h, K k) {
      int j = findSlot(h, k);
      if (j < 0) return null;                   // nothing to remove
      V answer = table[j].getValue();
      table[j] = DEFUNCT;                       // mark this slot as deactivated
      n--;
      return answer;
  }
    
    /**
     * Returns an iterable collection of all key-value entries of the map.
     *
     * @return iterable collection of the map's entries
     */
  @Override
  public Iterable<Entry<K,V>> entrySet() {
      ArrayList<Entry<K,V>> buffer = new ArrayList<>();
      for (int h=0; h < capacity; h++)
	  if (!isAvailable(h)) buffer.add(table[h]);
      return buffer;
  }
    //initialize public instance variable to store number of probes per insertion
    public int probes;
   
}
