package ErrorsPackage;
//переделать объявляемы и необявля
public class NoSuchModelNameException extends Exception{
    public NoSuchModelNameException(String msg) {
        super(msg+" Не найден в списке!!!");
    }
}
