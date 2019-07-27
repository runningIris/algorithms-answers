package chapter3.section4;

import edu.princeton.cs.algs4.StdOut;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LinearProbingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;
    private int n; // the current number of key-value pair
    private int m; // capacity of the LinearProbingHashST
    private Key[] keys;
    private Value[] vals;

    public LinearProbingHashST(){
        this(INIT_CAPACITY);
    }
    public LinearProbingHashST(int capacity) {
        m = capacity;
        n = 0;
        keys = (Key[]) new Object[m];
        vals = (Value[]) new Object[m];
    }
    public int size() {
        return n;
    }
    public boolean isEmpty() {
        return n == 0;
    }
    public boolean contains(Key key) {
        return get(key) != null;
    }
    private int hash(Key key) {
        return (key.hashCode() & 0x7ffffff) % m;
    }
    private void resize(int capacity) {
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<Key, Value>(capacity);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], vals[i]);
            }
        }

        keys = temp.keys;
        vals = temp.vals;
        m = temp.m;
    }
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("no argument key in method put");

        if (val == null) {
            delete(key);
            return;
        }

        if (m < n * 2) {
            resize(m * 2);
        }

        int i;

        // overwrite
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }

        // repeated hash
        while (keys[i] != null) {
            i++;
        }

        keys[i] = key;
        vals[i] = val;

        StdOut.println("put: " + hash(key) + " " + key + " " + val);

        n++;

    }
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("no argument in method get()");
        }

        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (!keys[i].equals(key)) {
                return vals[i];
            }

        }

        return null;
    }
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("no argument in method delete()");
        }
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                vals[i] = null;
                n--;
            } else {
                put(keys[i], vals[i]);
            }
        }
    }
    public Iterable<Key> keys() {
        List<Key> all = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                all.add(keys[i]);
            }
        }
        return all;
    }
    // integrity check - don't check after each put() because
    // integrity not maintained during a delete()
    private boolean check() {
        if (m < 2 * n) {
            System.err.println("Hash table size m = " + m + "; array size n = " + n);
            return false;
        }
        for (int i = 0; i < m; i++) {
            if (keys[i] == null) continue;
            else if (get(keys[i]) != vals[i]) {
                System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; vals[i] = " + vals[i]);
                return false;
            }
        }
        return true;
    }
    public String toString() {
        String str = "";
        for (int i = 0; i < m; i++) {
            if (keys[i] == null) continue;
            str += (keys[i] + ": " + vals[i] + "\n");
        }
        return str;
    }
    public static void main(String[] args) {
        LinearProbingHashST things = new LinearProbingHashST<String, String>(10);
        things.put("fruit", "strawberry");
        things.put("car", "rolls-royce");
        things.put("computer", "macbook air");
        things.put("planet", "mars");
        things.put("rapper", "Nicki Manaj");
        things.put("makeup", "foundation");
        StdOut.println(things.toString());

    }
}
