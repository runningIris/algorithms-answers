package chapter3.section4;

import java.util.ArrayList;
import java.util.List;

// Question:
// Suppose we wish to repeatedly search a linked list of length N elements,
// each of which contains a very long string key.
// How might we take advantage of the hash value
// when searching the list for an element with a given key?

public class WebExercise1 {
    private int[] hashes;
    private String[] list;
    private int n;

    public WebExercise1(String[] list) {
        this.n = n;
        this.hashes = new int[n];
        this.list = list;
        for (int i = 0; i < n; i++) {
            hashes[i] = list[i].hashCode();
        }
    }

    public boolean search(String key) {
        int hash = key.hashCode();

        for(int i = 0; i < n; i++) {
            if (hash == hashes[i] && key.equals(hashes[i])) {
                return true;
            }
        }

        return false;
    }
}
