// file: SymbolTable.java
// author: Bob Muller
// date: March 12, 2012
//
// The API for the symbol table used in the Huffman Coding Algorithm.
//
import java.util.*;

public interface SymbolTable<Key, Value> {

    public void write(BinaryOut outputFile);
    public boolean containsKey(Object key);
    public Value get(Key key);
    public boolean isEmpty();
    public Object put(Key key, Value value);
    public int size(); 
    public Set<Key> keySet();
    public Collection<Value> values();
    public String toString();
}