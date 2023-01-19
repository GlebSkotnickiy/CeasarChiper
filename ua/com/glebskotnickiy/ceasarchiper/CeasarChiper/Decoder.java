package ua.com.glebskotnickiy.ceasarchiper.CeasarChiper;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ua.com.glebskotnickiy.ceasarchiper.Alphabets.Alphabets.*;
import static ua.com.glebskotnickiy.ceasarchiper.Alphabets.Alphabets.GRM_ALPHABET;

public class Decoder {
    public void decodeFile(String filePath, int key) {
        FileService fileService = new FileService();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileService.markFileName(filePath, "(decoded)")))) {
            while (bufferedReader.ready()) {
                char symbol = (char) bufferedReader.read();
                bufferedWriter.write(decodeSymbol(symbol, key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private char decodeSymbol(char symbol, int key) {
        char result;
        if (Character.isUpperCase(symbol)) {
            symbol = Character.toLowerCase(symbol);
            result = Character.toUpperCase(getDecodedSymbol(symbol, key));
        } else if (Character.isLowerCase(symbol)) {
            result = getDecodedSymbol(symbol, key);
        } else result = symbol;
        return result;
    }

    private char getDecodedSymbol(char symbol, int key) {
        char result = 0;
        if (ENG_ALPHABET.contains(symbol)) {
            decode(ENG_ALPHABET, symbol, key);
        } else if (RU_ALPHABET.contains(symbol)) {
            decode(RU_ALPHABET, symbol, key);
        } else if (GRM_ALPHABET.contains(symbol)) {
            decode(GRM_ALPHABET, symbol, key);
        }
        return result;
    }

    private char decode(List<Character> alphabet, char symbol, int key) {
        List<Character> shiftedAlphabet = new ArrayList<>(alphabet);
        Collections.rotate(shiftedAlphabet, shiftedAlphabet.size() - key);
        int index = alphabet.indexOf(symbol);
        char result = shiftedAlphabet.get(index);
        return result;
    }
}