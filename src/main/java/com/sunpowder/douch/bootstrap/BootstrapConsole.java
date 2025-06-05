package com.sunpowder.douch.bootstrap;

import java.util.Scanner;

public class BootstrapConsole {
    private final Scanner scanner = new Scanner(System.in);
    public String readLine() { return scanner.nextLine(); }
    public void print(String msg) { System.out.print(msg); }
    public void println(String msg) { System.out.println(msg); }
}
