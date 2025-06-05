package com.sunpowder.douch.core;

import java.util.Scanner;

public class ProxyConsole {
    private final Scanner scanner = new Scanner(System.in);
    public String readLine() { return scanner.nextLine(); }
    public void print(String msg) { System.out.print(msg); }
    public void println(String msg) { System.out.println(msg); }
}
