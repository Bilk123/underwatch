package com.underwatch.game.desktop;

/**
 * Created by sam on 7/10/17.
 */
public class Test {
    public static void main(String[] args) {
        byte a = 0b101;
        byte b = 0b000;
        a |= b;
        a &= 0b11;
        System.out.println(Integer.toBinaryString(a));
    }
}
