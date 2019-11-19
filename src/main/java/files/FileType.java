package files;

public enum FileType {
    IS_DIRECTORY, IS_FILE;


    @Override
    public String toString() {
        switch (this){
            case IS_FILE:
                return "This is FILE!";
            case IS_DIRECTORY:
                return "This is DIRECTORY!";
                default:
                    throw new RuntimeException();
        }
    }
}
