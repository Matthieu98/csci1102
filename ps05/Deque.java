public interface Deque<Item> {

          public int size();
          public boolean isEmpty();
          public String toString();
          public void pushLeft(Item item);
          public void pushRight(Item item);
          public Item popLeft();
          public Item popRight();
          }