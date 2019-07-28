package chapter3.section4;

import edu.princeton.cs.algs4.StdOut;

public class WebExercise3 {
    private int x, y, z;
    public WebExercise3(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int hash(){
        return (((x << 16) % 31 + y << 16) % 31 + z << 16) % 31;
    }

    public static void main(String[] args) {
        StdOut.println(new WebExercise3(3, 2, 1).hash());
        StdOut.println(new WebExercise3(1, 2, 3).hash());
        StdOut.println(new WebExercise3(3, 1, 2).hash());
    }
}
