package ua.com.glebskotnickiy.ceasarchiper.CeasarChiper;

public class FileService {
    String markFileName(String filePath, String marker) {
        StringBuilder stringBuilder = new StringBuilder(filePath);
        stringBuilder.insert(filePath.length() - 4, marker);
        String newFilePath = stringBuilder.toString();
        return newFilePath.toString();
    }

}