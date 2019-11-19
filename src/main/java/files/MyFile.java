package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class MyFile {
    private Path path;
    private String title;
    private File file;

    public MyFile(String url) {
        this.path = Paths.get(url);
        this.file = new File(this.path.toUri());
    }

    public boolean existFile(){
        return file.exists();
    }

    public String findOutType(){
        if(file.isDirectory()){
            return FileType.IS_DIRECTORY.toString();
        }else if (file.isFile()){
            return FileType.IS_FILE.toString();
        }else {
            throw new RuntimeException();
        }
    }

    public void printFileContent(){
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String content;
            while ((content = br.readLine()) != null){
                System.out.println(content);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyFile myFile = (MyFile) o;
        return Objects.equals(path, myFile.path) &&
                Objects.equals(title, myFile.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, title);
    }
}
