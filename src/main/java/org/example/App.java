package org.example;

import org.example.command.CommandManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        String file = "D:\\WORK\\Projects\\IdeaProjects\\EDU\\OrderBook\\src\\input.txt";

        List<String> commands = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while (bufferedReader.ready()) {
                commands.add(bufferedReader.readLine());
            }
        }

        CommandManager commandManager = new CommandManager(commands);
    }
}
