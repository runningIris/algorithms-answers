package chapter3.section4;

import edu.princeton.cs.algs4.StdOut;

public class SeperateChainingLiteHashST<Key, Value> {
    private int n;
    private int m;
    private Node[] st;

    private static class Node {
        private Object key;
        private Object val;
        private Node next;

        public Node(Object key, Object val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public SeperateChainingLiteHashST() {
        this(997);
    }
    public SeperateChainingLiteHashST(int capacity) {
        this.m = capacity;
        this.n = 0;
        st = new Node[m];
    }
    private int hash(Key key) {
        return (key.hashCode() & 0x7ffffff) % m;
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
    public Value get(Key key) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return (Value) x.val;
            }
        }
        return null;
    }
    public void put(Key key, Value val) {
        if (val == null) {
            delete(key);
            return;
        }

        int i = hash(key);

        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
            }
        }

        // Q: why the next node is st[i] ???
        // A: Because the st[i] is still null
        // so the expression equals to new Node(key, val, null)
        st[i] = new Node(key, val, st[i]);
        n++;
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("no argument in delete() method");
        }

        int i = hash(key);

        if (st[i] == null) {
            return;
        }

        if (st[i].key == key) {
            st[i] = null;
        }

        Node prev = st[i];
        for (Node x = st[i]; x != null; x = x.next) {
            if (x.key.equals(key)) {
                prev.next = x.next;
                n--;
                break;
            }

        }
    }

    public static void main(String[] args) {
        SeperateChainingLiteHashST things = new SeperateChainingLiteHashST(10);
        things.put("fruit", "strawberry");
        things.put("car", "rolls-royce");
        things.put("computer", "macbook air");
        things.put("planet", "mars");
        things.put("rapper", "Nicki Manaj");
        things.put("makeup", "foundation");
        things.delete("computer");
        for (int i = 0; i < things.m; i++) {
            Node x = things.st[i];
            while(x != null) {
                StdOut.println("Category: " + x.key + " " + x.val);
                x = x.next;
            }
        }
    }
}
