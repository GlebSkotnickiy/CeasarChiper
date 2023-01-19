package ua.com.glebskotnickiy.ceasarchiper.CeasarChiper;

import java.nio.file.Files;
import java.nio.file.Path;

public class CliApplication {
    private String[] args;

    public CliApplication(String[] args) {
        this.args = args;
    }

    public String getCommand() {
        String command = args[0].toLowerCase();
        if (!"encode".equals(command) && !"decode".equals(command) && !"bruteforce".equals(command)) {
            throw new IllegalArgumentException("This command does not exist.");
        }
        return command;
    }

    public String getFilePath() {
        String filePath = args[1];
        if (!Files.isRegularFile(Path.of(filePath))) {
            throw new IllegalArgumentException("File not found.");
        }
        return filePath;
    }

    public int getKey() {
        try {
            return Integer.parseInt(args[2]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Key not found, you must enter an integer number.");
        }
    }
}
