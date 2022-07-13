package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String mode = scan("-mode", args).equals("") ? "enc" : scan("-mode", args);
        String key = scan("-key", args).equals("") ? "0" : scan("-key", args);
        String data = scan("-data", args);
        String in = scan("-in", args);
        String out = scan("-out", args);
        String alg = scan("-alg", args).equals("") ? "shift" : scan("-alg", args);
        int n = Integer.parseInt(key);
        if (data.equals("")) {
            if (in.equals("")) {
                if (out.equals("")) {
                    //data, in and out are empty
                    System.out.println(mode.equals("enc") ? encrypt(data, n, alg) : decrypt(data, n, alg));
                } else {
                    //data and in are empty, out is full - create a new empty file
                    File file = new File(out);
                }
            } else if (out.equals("")) {
                //data is empty, in is full, out is empty - read from file, print on console
                readFilePrintConsole(in, mode, n, alg);
            } else {
                //data is empty, in is full, out is full - read from file, print on file
                readFilePrintFile(in, out, mode, n, alg);
            }
        } else if (in.equals("")) {
            if (out.equals("")) {
                //data is full, in is empty, out is empty - read from data, print on console
                System.out.println(mode.equals("enc") ? encrypt(data, n, alg) : decrypt(data, n, alg));
            } else {
                //data is full, in is empty, out is full - read from data, print on file
                readDataPrintFile(data, out, mode, n, alg);
            }
        } else if (out.equals("")) {
            //data is full, in is full, out is empty - read from data, print on console
            System.out.println(mode.equals("enc") ? encrypt(data, n, alg) : decrypt(data, n, alg));
        } else {
            //everything is full - read from data, print on file
            readDataPrintFile(data, out, mode, n, alg);
        }
    }

    public static String encrypt(String data, int n, String alg) {
        StringBuilder s = new StringBuilder("");
        if (alg.equals("unicode")) {
            for (int i = 0; i < data.length(); i++) {
                char c = data.charAt(i);
                s.append((char) (c + n));
            }
        } else {
            //caesar algorithm
            final int z_code = 122;
            final int a_code = 97;
            final int Z_CODE = 90;
            final int A_CODE = 65;
            List<String> alpha = List.of("abcdefghijklmnopqrstuvwxyz".split(""));
            List<String> alphaCapital = List.of("abcdefghijklmnopqrstuvwxyz".toUpperCase().split(""));
            for (int i = 0; i < data.length(); i++) {
                char c = data.charAt(i);
                s.append(alpha.contains(c + "") ? (c + n) > z_code ? (char) (a_code + (n - (z_code - c)) - 1) : (char) (c + n) : alphaCapital.contains(c + "") ? (c + n) > Z_CODE ? (char) (A_CODE + (n - (Z_CODE - c)) - 1) : (char) (c + n) : data.charAt(i));
            }
        }
        return s.toString();
    }

    public static String decrypt(String data, int n, String alg) {
        StringBuilder s = new StringBuilder("");
        if (alg.equals("unicode")) {
            for (int i = 0; i < data.length(); i++) {
                char c = data.charAt(i);
                s.append((char) (c - n));
            }
        } else {
            //caesar algorithm
            final int z_code = 122;
            final int a_code = 97;
            final int Z_CODE = 90;
            final int A_CODE = 65;
            List<String> alpha = List.of("abcdefghijklmnopqrstuvwxyz".split(""));
            List<String> alphaCapital = List.of("abcdefghijklmnopqrstuvwxyz".toUpperCase().split(""));
            for (int i = 0; i < data.length(); i++) {
                char c = data.charAt(i);
                s.append(alpha.contains(c + "") ? (c - n) < a_code ? (char) (z_code - (n - (c - a_code)) + 1) : (char) (c - n) : alphaCapital.contains(c + "") ? (c - n) < A_CODE ? (char) (Z_CODE - (n - (c - Z_CODE)) + 1) : (char) (c - n) : data.charAt(i));
            }
        }
        return s.toString();
    }

    public static String scan(String s, String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (s.equals(args[i])) {
                return args[i + 1];
            }
        }
        return "";
    }

    public static void readFilePrintConsole(String in, String mode, int n, String alg) {
        File file = new File(in);
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                String data = sc.nextLine();
                System.out.println(mode.equals("enc") ? encrypt(data, n, alg) : decrypt(data, n, alg));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error, input file not found");
        }
    }

    public static void readFilePrintFile(String in, String out, String mode, int n, String alg) {
        try (FileWriter fileOut = new FileWriter(out)) {
            File fileIn = new File(in);
            Scanner sc = new Scanner(fileIn);
            while (sc.hasNext()) {
                String data = sc.nextLine();
                fileOut.write(mode.equals("enc") ? encrypt(data, n, alg) : decrypt(data, n, alg));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error, input file not found");
        } catch (IOException e) {
            System.out.println("Error, unable to write to output file");
        }
    }

    public static void readDataPrintFile(String data, String out, String mode, int n, String alg) {
        try (FileWriter fileOut = new FileWriter(out)) {
            fileOut.write(mode.equals("enc") ? encrypt(data, n, alg) : decrypt(data, n, alg));
        } catch (IOException e) {
            System.out.println("Error, unable to write to output file");
        }
    }
}
