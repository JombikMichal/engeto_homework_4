package files;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public final class NIO {

    private NIO() {
    }

    ;

    public static boolean existFile(File file) {
        return file.exists();
    }

    /**
     * Relative path?
     */
    public static boolean existFile(Path path) {
        File file = new File(path.toUri());
        return file.exists();
    }

    public static String pathType(File file) {
        if (file.isDirectory()) {
            return FileType.IS_DIRECTORY.toString();
        } else if (file.isFile()) {
            return FileType.IS_FILE.toString();
        } else {
            throw new RuntimeException();
        }
    }

    public static void deleteFile(File file) {
        try {
            if (existFile(file)) {
                file.delete();
                System.out.println(String.format("File %s was deleted!", file.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printFileContent(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeInFile(File file, String expression) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(file, true))) {
            printWriter.append(expression + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void replaceWord(@NotNull File file, @NotNull String regex, @NotNull String replacement) throws IOException {
        //create a temp file
        File temp = File.createTempFile("prefix", ".tmp");

        try (BufferedReader br = new BufferedReader(new FileReader(file)); BufferedWriter bw = new BufferedWriter(new FileWriter(temp))) {
            String line = br.readLine();
            while (line != null) {
                bw.write(line.replaceAll(regex, replacement));
                bw.newLine();
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Files.move(Paths.get(temp.getPath()), Paths.get(file.getPath()), StandardCopyOption.REPLACE_EXISTING);
    }

    private static void caesarCipher(File file, int shift) throws IOException {

        //create a temp file
        File temp = File.createTempFile("prefix", ".tmp");

        try (BufferedReader br = new BufferedReader(new FileReader(file)); BufferedWriter bw = new BufferedWriter(new FileWriter(temp))) {
            char[] arr = null;
            String line = br.readLine();
            while (line != null) {
                arr = line.toCharArray();
                for (int i = 0; i < arr.length; i++) {
                    if (Character.isUpperCase(arr[i])) {
                        arr[i] = (char) (((int) arr[i] + shift - 65) % 26 + 65);
                    } else {
                        arr[i] = (char) (((int) arr[i] + shift - 97) % 26 + 97);
                    }
                }
                bw.write(String.valueOf(arr));
                bw.newLine();
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Files.move(Paths.get(temp.getPath()), Paths.get(file.getPath()), StandardCopyOption.REPLACE_EXISTING);
    }

    public static void encode(File file, char shift) throws IOException {
        caesarCipher(file, Character.getNumericValue(shift));
    }

    public static void decode(File file, char shift) throws IOException {
        caesarCipher(file, (Character.getNumericValue(shift) * -1));
    }


}
