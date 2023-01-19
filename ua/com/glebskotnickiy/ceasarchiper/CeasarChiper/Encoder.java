package ua.com.glebskotnickiy.ceasarchiper.CeasarChiper;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ua.com.glebskotnickiy.ceasarchiper.Alphabets.Alphabets.*;

public class Encoder {

    public void encodeFile(String filePath, int key) {
        FileService fileService = new FileService();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileService.markFileName(filePath, "(encoded)")))) {
            while (bufferedReader.ready()) {
                char symbol = (char) bufferedReader.read();
                bufferedWriter.write(encodeSymbol(symbol, key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private char encodeSymbol(char symbol, int key) {
        char result;
        if (Character.isUpperCase(symbol)) {
            symbol = Character.toLowerCase(symbol);
            result = Character.toUpperCase(getEncodedSymbol(symbol, key));
        } else if (Character.isLowerCase(symbol)) {
            result = getEncodedSymbol(symbol, key);
        } else result = symbol;
        return result;
    }

    private char getEncodedSymbol(char symbol, int key) {
        char result = 0;
        if (ENG_ALPHABET.contains(symbol)) {
            encode(ENG_ALPHABET, symbol, key);
        } else if (RU_ALPHABET.contains(symbol)) {
            encode(RU_ALPHABET, symbol, key);
        } else if (GRM_ALPHABET.contains(symbol)) {
            encode(GRM_ALPHABET, symbol, key);
        }
        return result;
    }

    private char encode(List<Character> alphabet, char symbol, int key) {
        List<Character> shiftedAlphabet = new ArrayList<>(alphabet);
        Collections.rotate(shiftedAlphabet, shiftedAlphabet.size() - key);
        int index = alphabet.indexOf(symbol);
        char result = shiftedAlphabet.get(index);
        return result;
    }
}