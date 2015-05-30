public interface PQ<Key> {
    public void insert(Key v);
    public Key max();
    public Key delMax();
    public boolean isEmpty();
    public int size();
}