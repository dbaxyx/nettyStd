package com.nettyInaction.code.part1;

/**
 * Created by dba on 2017/4/17.
 */
public class Data {
    private int n;
    private int m;

    @java.lang.Override
    public java.lang.String toString() {
        return "Data{" +
                "n=" + n +
                ", m=" + m +
                '}';
    }

    public Data(int n, int m) {
        this.n = n;
        this.m = m;
    }
}
