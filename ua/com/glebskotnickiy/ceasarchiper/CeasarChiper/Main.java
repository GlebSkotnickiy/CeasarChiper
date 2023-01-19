package ua.com.glebskotnickiy.ceasarchiper.CeasarChiper;

public class Main {
    public static void main(String[] args) {

        CliApplication cliApplication = new CliApplication(args);
        String command = cliApplication.getCommand();
        String filePath = cliApplication.getFilePath();

        if ("encode".equals(command)) {
            Encoder encoder = new Encoder();
            int key = cliApplication.getKey();
            encoder.encodeFile(filePath, key);
        } else if ("decode".equals(command)) {
            Decoder decoder = new Decoder();
            int key = cliApplication.getKey();
            decoder.decodeFile(filePath, key);
        }
    }
}