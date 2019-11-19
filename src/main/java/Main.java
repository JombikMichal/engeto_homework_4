import files.MyFile;
import files.NIO;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        File file = new File("/Users/michaljombik/IdeaProjects/engeto_homework_4/src/main/java/files/repository/original");
        File file2 = new File("/Users/michaljombik/IdeaProjects/engeto_homework_4/src/main/java/files/repository/file_to_delete2");

        // #2 - napíšte program, ktorý zistí, či daný súbor existuje
        System.out.println("1.Absolute path: " + NIO.existFile(file));
        System.out.println("2. Relative path: " + NIO.existFile(Paths.get("./files/repository/original")));

        // #3 -> - napíšte program, ktorý zistí, či daná cesta je adresár alebo súbor
        System.out.println("#3 -> Type: " + NIO.pathType(file));

        // #4 -> - napíšte program, ktorý zmaže určitý súbor
        //NIO.deleteFile(file2);

        // #5 -> - prečítať obsah súboru a vypísať na konzolu - BufferedReader
        NIO.printFileContent(file);

        // #6 -> - napíšte program, ktorý spočíta počet riadkov v danom súbore

        // #7 -> - napíšte program, ktorý spočíta počet slov v danom súbore
       // NIO.writeInFile(file,"new word");
       // NIO.printFileContent(file);

        // #8 -> - prepísať slovo v súbore na iné
        NIO.replaceWord(file,"","");
      //  NIO.printFileContent(file);

    }
}
