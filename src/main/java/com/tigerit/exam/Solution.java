package com.tigerit.exam;

import static com.tigerit.exam.IO.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

/**
 * All of your application logic should be placed inside this class. Remember we
 * will load your application from our custom container. You may add private
 * method inside this class but, make sure your application's execution points
 * start from inside run method.
 */
public class Solution implements Runnable {

    static String[] str = new String[100];
    static String[][] column = new String[100][100];
    static int[][][] table = new int[100][100][100];
    static HashMap<String, Integer> map = new HashMap<String, Integer>();
    static HashMap<String, Integer> map1 = new HashMap<String, Integer>();
    static int[] row = new int[100];
    static int[] col = new int[100];
    static int a, b;

    static void find_id(String s1) {
        int i = 0;
        int id = 0;

        String temp = new String();
        String st = new String();

        for (i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == '.') {
                id = map1.get(temp);
                break;
            }
            temp = temp + s1.charAt(i);
        }

        for (int j = i + 1; j < s1.length(); j++) {
            st = st + s1.charAt(j);
        }

        for (i = 0; i < col[id]; i++) {

            if (column[id][i].equals(st)) {
                b = i;
                a = id;
                break;
            }
        }

    }

     public static String toLowerCase(String inputString) {
       String result = "";
       for (int i = 0; i < inputString.length(); i++) {
           char currentChar = inputString.charAt(i);
           char currentCharToLowerCase = Character.toLowerCase(currentChar);
           result = result + currentCharToLowerCase;
       }
       return result;
   }

    @Override
    public void run() {
        // your application entry point

        // sample input process
        // String string = readLine();
        // Integer integer = readLineAsInteger();
        //String c1 = readLine();
        Integer test = readLineAsInteger();
        for (int t = 1; t <= test; t++) {

            Integer n = readLineAsInteger();
            map.clear();
            int[][] new_table = new int[100][100];
            int[][] new_table2 = new int[100][100];
            ///For Input 
            for (int i = 0; i < n; i++) {

                str[i] = readLine();
                str[i] = toLowerCase(str[i]);
                map.put(str[i], i);
                String str2 = readLine();
                String[] splitted3 = str2.split("[' ']");
                row[i] = Integer.parseInt(splitted3[0]);
                col[i] = Integer.parseInt(splitted3[1]);
                String str3 = readLine();
                String[] splitted1 = str3.split("[' ']");


                int l = 0;
                for (String split : splitted1) {
                    // System.out.println(split);
                    column[i][l] = split;
                    column[i][l]=toLowerCase(column[i][l]);
                    l += 1;
                
               

                }

                for (int j = 0; j < row[i]; j++) {
                    String str4 = readLine();
                    String[] splitte2 = str4.split("[' ']");
                    l = 0;

                    for (String split : splitte2) {
                        // System.out.println(split);

                        table[i][j][l] = Integer.parseInt(split);
                        l++;
                    }
                }
            }
            int q;
            q = readLineAsInteger();
            String query;
            System.out.println("Test: " + t);
            for (int m = 1; m <= q; m++) {
                String[] current = new String[100];

                map1.clear();

                query = readLine();
//            System.out.println("===> query: " + query;
                int t1 = 0, found = 0;
                String[] splitted = query.split("[' ',]");
                for (String split : splitted) {
                    if (split.equals("Select") || split.equals("select") || split.equals("SELECT")) {
                        continue;
                    }
                    if (split.equals(",") || split.equals(" ") || split.equals("=")) {
                        continue;
                    }
                    if (split.length() == 0) {
                        continue;
                    }
                    if (split.equals("*")) {
                        found = 1;

                        break;
                    }
                    current[t1] = split;
                    t1 = t1 + 1;
                }

                String query1 = readLine();
                //System.out.println("query1: " + query1);
                String[] second_part = query1.split("[' ',]");
                int sec_part = -1, check = 0;
                for (String split : second_part) {
                    check++;
                    if (check == 1) {
                        continue;
                    }
                    if (split.equals(",") || split.equals(" ") || split.equals("=") || split.equals("from") || split.equals("FROM")) {
                        continue;
                    }
                    if (split.length() > 0) {

                        if (sec_part == -1) {
                            // printLine(split);

                            sec_part = map.get(split);
                            // printLine(sec_part);
                            map1.put(split, sec_part);
                        } else {
                            map1.put(split, sec_part);
                        }
                    }

                }
                String query2 = readLine();
                // System.out.println(" query2: " + query2);
                int th_part = -1;
                check = 0;
                String[] third_part = query2.split("[' ',]");
                for (String split : third_part) {
                    check++;
                    if (check == 1) {
                        continue;
                    }
                    if (split.equals(",") || split.equals(" ") || split.equals("=") || split.equals("join") || split.equals("JOIN")) {
                        continue;
                    }
                    if (split.length() > 0) {

                        if (th_part == -1) {

                            //printLine(th_part);
                            //printLine(split);
                            th_part = map.get(split);
                            //printLine(th_part);

                            map1.put(split, th_part);
                        } else {
                            map1.put(split, th_part);
                        }
                    }

                }

                String query3 = readLine();

                //System.out.println("query3: " + query3); 
                String[] list = query3.split("[' ',=]");
                check = 0;
                int flag = 0;
                int[] ara1 = new int[10];
                int[] ara2 = new int[10];

                for (String split : list) {
                    check++;
                    if (check == 1) {
                        continue;
                    }
                    if (split.equals(",") || split.equals(" ") || split.equals("=") || split.equals("on") || split.equals("ON")) {
                        continue;
                    }
                    if (split.length() > 0) {

                        find_id(split);
                        ara1[flag] = a;
                        ara2[flag] = b;
                        flag = flag ^ 1;
                    }
                }
                String c = readLine();

                int visited[] = new int[500];
                int p1 = ara1[0];
                int p3 = ara2[0];
                int p2 = ara1[1];
                int p4 = ara2[1];
                int r = 0;

                for (int i = 0; i < row[p1]; i++) {
                    for (int j = 0; j < row[p2]; j++) {
                        if (table[p1][i][p3] == table[p2][j][p4]) {

                            for (int x = 0; x < col[p1]; x++) {
                                new_table[r][x] = table[p1][i][x];
                            }
                            for (int x = 0; x < col[p2]; x++) {
                                int p5 = x + col[p1];
                                new_table[r][p5] = table[p2][j][x];
                            }
                            r = r + 1;
                        }
                    }
                }

                if (found != 0) {

                    for (int i = 0; i < col[p1]; i++) {
                        System.out.print(column[p1][i] + " ");
                    }
                    for (int i = 0; i < col[p2]; i++) {
                        System.out.print(column[p2][i] + " ");
                    }
                    System.out.println();
                    for (int i = 0; i < r; i++) {
                        int check1 = 0;
                        for (int j = i + 1; j < r; j++) {
                            for (int k = 0; k < col[p1] + col[p2]; k++) {
                                if (new_table[i][k] < new_table[j][k]) {
                                    break;
                                }
                                if (new_table[i][k] > new_table[j][k]) {
                                    check1 = 1;
                                    break;
                                }

                            }
                            if (check1 == 0) {
                                continue;
                            }
                            for (int p = 0; p < col[p1] + col[p1]; p++) {
                                int t2 = new_table[p][i];
                                new_table[i][p] = new_table[j][p];
                                new_table[j][p] = t2;
                            }
                        }
                    }
                    for (int i = 0; i < r; i++) {
                        for (int j = 0; j < col[p1] + col[p2]; j++) {
                            System.out.print(new_table[i][j] + " ");
                        }
                        System.out.println();
                    }

                    System.out.println();

                    continue;
                }

                
                int mn = 0;
                for (int i = 0; i < t1; i++) {
                    find_id(current[i]);

                    System.out.print(column[a][b] + " ");
                    int ans = b;
                    if (a != p1) {
                        ans = ans + col[p1];
                    }
                    for (int j = 0; j < r; j++) {
                        new_table2[j][mn] = new_table[j][ans];
                    }
                    mn = mn + 1;

                }
                System.out.println();
                for (int i = 0; i < r; i++) {
                    int check2 = 0;
                    for (int j = i + 1; j < r; j++) {
                        for (int k = 0; k < mn; k++) {
                            if (new_table2[i][k] < new_table2[j][k]) {
                                break;
                            }
                            if (new_table2[i][k] > new_table2[j][k]) {
                                check2 = 1;
                                break;
                            }

                        }
                        if (check2 == 0) {
                            continue;
                        }
                        for (int p = 0; p < mn; p++) {
                            int t2 = new_table2[p][i];
                            new_table2[i][p] = new_table2[j][p];
                            new_table2[j][p] = t2;
                        }
                    }
                }
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < mn; j++) {
                        System.out.print(new_table2[i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println();

            }
        }

    }
}
