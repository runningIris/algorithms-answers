package chapter3.section4;

import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

// 3.4.2 - enhanced SeperateChainingHashST
// 3.4.3 - give each value the N number at the moment they are inserted
public class Exercise2<Key, Value> {
    private int N;
    private int M;
    private SequentialSearchST<Key, Node>[] st;

    public Exercise2() {
        this(997);
    }
    public Exercise2(int capacity) {
        N = 0;
        M = capacity;
        st = new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<Key, Node>();
        }
    }

    private static class Node<Value>{
        private Value value;
        private int num;
        public Node(Value value, int num) {
            this.value = value;
            this.num = num;
        }
    }

    private SequentialSearchST[] test() {
        return st;
    }

    private int hash(Key key) {
        return key.hashCode() & 0x7ffffff % M;
    }

    public void put(Key key, Value val) {

        st[hash(key)].put(key, new Node<Value>(val, N));
        N++;
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument of delete() is null");
        }

        SequentialSearchST seq = st[hash(key)];

        if (seq.contains(key)) {
            seq.delete(key);
            N--;
        }
    }

    public Value get(Key key) {
        Node<Value> current = st[hash(key)].get(key);
        return current.value;
    }

    public Node get(Key key, Boolean isGettingNum) {
        Node<Value> current = st[hash(key)].get(key);
        return current;
    }

    public Iterable<Key> keys() {
        List<Key> keys = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            for(Key key: st[i].keys()){
                keys.add(key);
            }
        }
        return keys;
    }


    public static void main(String[] args) {

        Exercise2 e2 = new Exercise2<String, String>();

        e2.put("car", "rolls-royce");
        e2.put("animal", "cat");
        e2.put("fruit", "strawberry");
        StdOut.println(e2.get("fruit"));
        Node<String> animal = e2.get("animal", true);
        StdOut.println(animal.value + " " + animal.num);
    }
}
