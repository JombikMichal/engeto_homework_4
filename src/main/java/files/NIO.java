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

//        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//
//            File tempFile = File.createTempFile("prefix", ".tmp");
//            tempFile.deleteOnExit();
//
//            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
//
//            String line = br.readLine();
//            while (line != null) {
//                bw.write(line + " prd ");
//                line = br.readLine();
//            }
//            Files.move(Paths.get(tempFile.getPath()),Paths.get(file.getPath()), StandardCopyOption.REPLACE_EXISTING);
//            System.out.println(file.getName());
//            System.out.println(tempFile.getName());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
        try {
            File file1 = File.createTempFile("prefix",".tmp");
            PrintWriter pw = new PrintWriter(new FileWriter(file1,true));
            pw.write("example");
            BufferedReader br = new BufferedReader(new FileReader(file1));
            System.out.println(br.readLine());

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
