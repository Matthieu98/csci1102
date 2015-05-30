// file: SymbolTableC.java
// author: Bob Muller
// date: March 19, 2012
// 
// The SymbolTable class defines a symbol table by composition (see Bloch Item 16)
// with the HashMap class.
//
import java.util.*;

public class SymbolTableC<Key, Value> implements SymbolTable<Key, Value> {

    private Map<Key, Value> st;

    // This constructor is used in the Huff program.
    //
    public SymbolTableC() {
 st = new HashMap<Key, Value>();
    }

    // This constructor is used to reconstitute the symbol table from the
    // information in the zip file.
    //
    public SymbolTableC(BinaryIn inputFile) {

 this.st = new HashMap<Key, Value>();

 int size = inputFile.readInt();

 for(int i = 0; i < size; i++) {

     char c = inputFile.readChar();
     int frequency = inputFile.readInt();

     Value v = (Value) new STValueC(frequency);
     Key key = (Key) new Integer((int) c);
     
     st.put(key, v);
 }
    }

    public void write(BinaryOut outputFile) {

 outputFile.write(this.size());
 
 Set<Key> keys = this.keySet();

 for(Key key : keys) {
     STValue v = (STValue) this.get(key);
     int frequency = v.getFrequency();
     outputFile.write((char) ((Integer)key).intValue());
     outputFile.write(frequency);
 }
    }

    public boolean containsKey(Object key) { return st.containsKey(key); }
    public Value get(Key key) { return st.get(key); }
    public boolean isEmpty() { return st.isEmpty(); }
    public Object  put(Key key, Value value) { return st.put(key, value); }
    public int size() { return st.size(); }
    public Set<Key> keySet() { return st.keySet(); }
    public Collection<Value> values() { return st.values(); }
    public String toString() { return st.toString(); }
}