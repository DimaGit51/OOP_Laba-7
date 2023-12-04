package ErrorsPackage;

public class DuplicateModelNameException extends Exception {
    public DuplicateModelNameException(String msg) {
        super(msg+" Уже есть в массиве!!!");
    }
}