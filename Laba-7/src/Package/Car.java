package Package;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;
import ErrorsPackage.*;

public class Car implements transport_vehicle{
    //поле типа String, хранящее марку автомобиля
    //ОБЩАЯ ПЕРЕМЕННАЯ brand
    //№1
    private String brand;


    //метод для получения марки автомобиля
    //ОБЩИЙ МЕТОД getBrand()
    //№2
    public String getBrand()
    {
        return brand;
    }

    //метод для модификации марки автомобиля
    //ОБЩИЙ МЕТОД setBrand(String value)
    //№3
    public void setBrand(String value)
    {
        brand = value;
    }


    //класс Автомобиль хранит массив Моделей
    //№4.3
    private Model[] ModelMass;


    //внутренний класс Модель
    //ОБЩИЙ КЛАСС Model
    //№4

    private class Model implements Serializable, Cloneable
    {
        //имеющий поля название модели (уникальное)
        //№4.1
        private String nameModel;
        private double priceModel;


        //метод для модификации значения названия модели,
        //ОБЩИЙ МЕТОД setNameModel(String value)
        //№5
        public void setNameModel(String value)
        {
            nameModel = value;
        }


        public String getNameModel(){
            return nameModel;
        }

        public void setPriceModel(double value){
            priceModel = value;
        }

        public double getPriceModel(){
            return priceModel;
        }

        //а также конструктор
        //ОБЩИЙ КОНСТРУКТОР Model
        //№4.2
        Model(String modelName, double modelPrice){
            if(modelPrice<0) {  throw new ModelPriceOutOfBoundsException(modelName);}
            nameModel = modelName;
            priceModel = modelPrice;
        }
        public Object clone() throws CloneNotSupportedException
        {
            return super.clone();
        }
    }


    //а также конструктор
    //(класс Автомобиль хранит массив Моделей)
    public Car (String mark, int len)
    {
        //Конструктор класса должен принимать в
        //качестве параметров значение Марки автомобиля,
        //размер массива Моделей и заполнять массив Моделями
        //с уникальными названиями и необязательно уникальными ценами.
        brand = mark;
        ModelMass = new Model[len];
        Random rnd = new Random();
        double countPrice = 0;
        for(int i=0; i<len; i++){
            countPrice+=1000;
            ModelMass[i] = new Model("model" + Integer.toString(i), (double) countPrice);
        }
    }


    //метод, возвращающий массив названий всех моделей,
    //ОБЩИЙ МЕТОД getModelNameMass()
    //№6
    public String[] getModelNameMass(){
        String[] arr = new String[ModelMass.length];
        for(int i=0; i<ModelMass.length; i++){
            arr[i] = ModelMass[i].getNameModel();
        }
        return arr;
    }
    public double[] getModelPriceMass() {
        double[] arr = new double[ModelMass.length];
        for(int i = 0; i < ModelMass.length; ++i) {
            arr[i] = ModelMass[i].getPriceModel();
        }
        return arr;
    }

    //метод для получения значения цены модели по её названию,
    //ОБЩИЙ МЕТОД getPriceByName(String name)
    //№7
    public double getPriceByName(String name) throws NoSuchModelNameException{
        for(int i=0; i<ModelMass.length; i++){
            if(ModelMass[i].getNameModel().equals(name)){//equals
                return ModelMass[i].priceModel;
            }
        }
        throw new NoSuchModelNameException(name);
    }


    //метод для модификации значения цены модели по её названию,
    //ОБЩИЙ МЕТОД setPriceByName(String name, double value)
    //№8
    public void setPriceByName(String name, double value) throws NoSuchModelNameException{
        boolean log = false;
        if(value>0) {
            for (int i = 0; i < ModelMass.length; i++) {
                if (ModelMass[i].getNameModel().equals(name)) {
                    ModelMass[i].setPriceModel(value);
                    log = true;
                    break;
                }
            }
        }
        else {
            throw new ModelPriceOutOfBoundsException(name);
        }
        if(!log) throw new NoSuchModelNameException(name);
    }


    //метод, возвращающий массив значений цен моделей,
    //ОБЩИЙ МЕТОД getPriceMass()
    //№9



    //метод добавления названия модели и её цены (путем создания нового массива Моделей), использовать метод Arrays.copyOf(),
    //ОБЩИЙ МЕТОД addNamePriceModel(String name, double value)
    //№10
    public void addNamePriceModel(String name, double value) throws DuplicateModelNameException{
        if(value>0) {
            int sz = ModelMass.length;
            for (int i = 0; i < ModelMass.length; i++) {
                if (ModelMass[i].getNameModel().equals(name)) throw new DuplicateModelNameException(name);
            }
            ModelMass = Arrays.copyOf(ModelMass, ModelMass.length + 1);
            ModelMass[sz] = new Model(name, value);
        }
        else{
            throw new ModelPriceOutOfBoundsException(name);
        }
    }


    //метод удаления модели по заданному имени, использовать методы System.arraycopy, Arrays.copyOf(),
    //ОБЩИЙ МЕТОД delNamePriceModel(String name, double value)
    //№11
    public void delNamePriceModel(String name) throws NoSuchModelNameException{
        int i;

        for(i = 0; i < ModelMass.length; i++) {
            if (ModelMass[i].getNameModel().equals(name)) break;
        }
        if(i == ModelMass.length) throw new NoSuchModelNameException(name);
        else {
            System.arraycopy(ModelMass, i + 1, ModelMass, i, ModelMass.length - i - 1);
            ModelMass = Arrays.copyOf(ModelMass, ModelMass.length - 1);
        }
    }

    //String,String
    public void changeNameModel(String name, String _name) throws NoSuchModelNameException, DuplicateModelNameException {
        int n = -1;
        for (int i = 0; i < ModelMass.length; i++) {
            if (ModelMass[i].getNameModel().equals(name)) {
                n = i;
                break;
            }
            else if (ModelMass[i].getNameModel().equals(_name)) throw new DuplicateModelNameException(_name);
        }
        if (n == -1) throw new NoSuchModelNameException(name);
        ModelMass[n].setNameModel(_name);
    }



    //метод для получения размера массива Моделей.
    //ОБЩИЙ МЕТОД getLenModelMass()
    //№12
    public int getLenModelMass(){
        return ModelMass.length;
    }


    public String toString()
    {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("Марка: "+getBrand()+'\n');
        strBuff.append("Количество моделей: "+getLenModelMass()+'\n');
        strBuff.append("Список моделей: "+'\n');
        for(int i=0; i<getLenModelMass(); i++)
        {
            strBuff.append("Название модели: "+ModelMass[i].getNameModel());
            strBuff.append(", ");
            strBuff.append("Цена модели: " +ModelMass[i].getPriceModel());
            strBuff.append("\n");
        }
        return strBuff.toString();
    }

    public boolean equals(Object obj)
    {
        if(obj == null)
        {
            return false;
        }
        if(obj == this)
        {
            return true;
        }
        if(obj instanceof Car)
        {
            Car car =(Car) obj;
            if(!car.getBrand().equals(getBrand()))
            {
                return false;
            }
            if(car.getLenModelMass()!=getLenModelMass())
            {
                return false;
            }
            for(int i=0; i<getLenModelMass();i++)
            {
                if(!car.ModelMass[i].getNameModel().equals(ModelMass[i].getNameModel()))
                {
                    return false;
                }
                if(car.ModelMass[i].getPriceModel()!=ModelMass[i].getPriceModel())
                {
                    return false;
                }
            }
            return true;

        }
        return false;
    }

    public int hashCode()
    {
        int hash = brand.hashCode();
        for(int i=0;i<getLenModelMass();i++)
        {
            hash += ModelMass[i].getNameModel().hashCode();
            hash += ModelMass[i].getPriceModel();
        }
        return hash;
    }

    public Object clone() throws CloneNotSupportedException
    {
        Car cloned = (Car) super.clone();
        cloned.ModelMass = ModelMass.clone();
        for (int i = 0; i < getLenModelMass(); i++)
        {
            cloned.ModelMass[i] = (Model) ModelMass[i].clone();
        }
        return cloned;
    }

}
