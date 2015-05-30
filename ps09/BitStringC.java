// file: BitStringC.java
// author: Bob Muller
// March 10, 2012
//
public class BitStringC implements BitString { 
 
    private int bits;
    private int count = 0;

    public BitStringC() { 
 this.bits = 0;
 this.count = 0;
    }

    public BitStringC(int bits, int size) {
 this.bits = bits;
 this.count = size;
    }

    public int getBits() { return bits; }
    public int getSize() { return count; }

    public String toString() {

 StringBuilder sb = new StringBuilder();

 int temp = this.bits;

 for(int i = 0; i < count; i++) {
     sb.append(temp % 2 == 0 ? "0" : "1");
     temp = temp / 2;
 }
 return sb.reverse().toString();
    }
}
