package com.example.blackjack.cli;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleGameIO implements GameIO {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void println(String message) {
        System.out.println(message);
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }
}
