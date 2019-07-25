package chapter3.section4;
import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.StdOut;

// Because we know that the key is int and the value is String,
// so I didn't write it in generic way.

public class Exercise1 {
    private int N;
    private int M;
    private SequentialSearchST[] st;
    private int hash(int k) {
        return 11 * k % M;
    }

    public Exercise1(int capacity) {
        this.M = capacity;
        st = new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    public void put(int key, String val) {
        st[hash(key)].put(key, val);
    }

    public String get(int key) {
        return (String) st[hash(key)].get(key);
    }

    public static void main(String[] args) {
        Exercise1 hashTable = new Exercise1(10);
        hashTable.put(0,"E");
        hashTable.put(1,"A");
        hashTable.put(2,"S");
        hashTable.put(3,"Y");
        hashTable.put(4,"Q");
        hashTable.put(5,"U");
        hashTable.put(6,"T");
        hashTable.put(7,"I");
        hashTable.put(8,"O");
        hashTable.put(9,"N");

        StdOut.println(hashTable.get(5));
    }
}