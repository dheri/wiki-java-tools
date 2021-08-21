package app;


import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class IOHandler {
    private static final String FILE_NAME = "downloadableFile.txt";


    public static void writeToFile(List<String> fileList) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME)) {
            for (String filename : fileList) {
                fileOutputStream.write(filename.getBytes(StandardCharsets.UTF_8));
                fileOutputStream.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            // woppsie
        }
    }

    public static String[] readFromFile() {


        ArrayList<String> files = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(FILE_NAME))) {

            stream.forEach(s -> files.add(s));
        } catch (IOException e) {
            // ouch
        }
        return files.toArray(new String[0]);

    }

    public static boolean fileIsCommented(String fileName) {
        fileName = fileName.trim();
        if (fileName.startsWith("//") || fileName.startsWith("#"))
            return true;
        return false;
    }
}
