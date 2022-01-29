package org.briarheart.algorithm.util;

public class Misc {
    private Misc() {
        //no instance
    }

    public static void swap(Object[] a, int i, int j) {
        if (i == j) {
            return;
        }

        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void swap(int[] a, int i, int j) {
        if (i == j) {
            return;
        }

        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void swap(long[] a, int i, int j) {
        if (i == j) {
            return;
        }

        long tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void swap(double[] a, int i, int j) {
        if (i == j) {
            return;
        }

        double tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
