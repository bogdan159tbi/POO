package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int n;
        int[][] m1 = new int[10][10];
        int[][] m2 = new int[10][10];
        Scanner s = new Scanner(System.in);
        n = s.nextInt();
        for(int i = 0; i < n; ++i)
            for(int j =0 ; j < n; ++j)
                m1[i][j] = s.nextInt();

        for(int i = 0; i < n; ++i)
            for(int j =0 ; j < n; ++j){
                m2[i][j] = s.nextInt();
                m2[i][j] += m1[i][j];
            }
        for(int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(m2[i][j] + " ");
            }
            System.out.println();
        }
    }
}
