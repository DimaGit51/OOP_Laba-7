package Package;

import ErrorsPackage.DuplicateModelNameException;
import ErrorsPackage.NoSuchModelNameException;
import ErrorsPackage.ModelPriceOutOfBoundsException ;

import java.io.Serializable;

public interface transport_vehicle extends Serializable, Cloneable {
    String getBrand();
    void setBrand(String value);
    int getLenModelMass();
    String[] getModelNameMass();
    double[] getModelPriceMass();
    double getPriceByName(String name) throws NoSuchModelNameException;
    void setPriceByName(String name, double value) throws NoSuchModelNameException;
    void addNamePriceModel(String name, double value) throws DuplicateModelNameException, ModelPriceOutOfBoundsException;
    void delNamePriceModel(String modelName) throws NoSuchModelNameException;
    void changeNameModel(String name, String _name) throws NoSuchModelNameException, DuplicateModelNameException;
//    Object clone() throws CloneNotSupportedException;
//    int hashCode();
//    boolean equals(Object obj);
//    String toString();
}
