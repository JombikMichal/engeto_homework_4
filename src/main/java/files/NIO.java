package files;

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

    public static void replaceWord(File file, String original, String newWord){
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {

            //create a temp file
            File temp = File.createTempFile("prefix",".tmp");

            //write it
            BufferedWriter bw = new BufferedWriter(new FileWriter(temp));

            String line = br.readLine();
            while (line != null){
                bw.write(line.replaceAll(original,newWord));
                bw.newLine();
                line = br.readLine();
            }
            bw.close();
            Files.move(Paths.get(temp.getPath()),Paths.get(file.getPath()), StandardCopyOption.REPLACE_EXISTING);

        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
