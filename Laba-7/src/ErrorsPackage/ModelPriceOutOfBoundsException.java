package ErrorsPackage;

public class ModelPriceOutOfBoundsException extends RuntimeException {
    public ModelPriceOutOfBoundsException(String msg) {
        super(msg+" Указана неправильная цена!!!");
    }
}