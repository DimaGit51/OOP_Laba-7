package Package;

import ErrorsPackage.DuplicateModelNameException;
import ErrorsPackage.ModelPriceOutOfBoundsException;
import ErrorsPackage.NoSuchModelNameException;
import java.util.Random;

import java.util.HashMap;

public class Scooter implements transport_vehicle{

    //поле типа String, хранящее марку скутера
    //ОБЩАЯ ПЕРЕМЕННАЯ brand
    private String brand;

    //класс Скутер хранит массив Моделей
    private HashMap<String, Double> ModelMass;

    //метод для получения марки автомобиля
    //ОБЩИЙ МЕТОД getBrand()
    public String getBrand()
    {
        return brand;
    }

    //метод для модификации марки скутера
    //ОБЩИЙ МЕТОД setBrand(String value)
    public void setBrand(String value)
    {
        brand = value;
    }

    public Scooter(String brand, int n)
    {
        this.brand=brand;
        ModelMass = new HashMap<>(n);
        Random rnd = new Random();
        for(int i=0; i<n; i++)
        {
            ModelMass.put("model" + Integer.toString(i), (double)rnd.nextInt(1000));

        }
    }
    public int getLenModelMass()
    {
        return ModelMass.size();
    }

    public String[] getModelNameMass()
    {
        String[] modelNames = new String[ModelMass.size()];
        int i=0;
        for (String key: ModelMass.keySet())
        {
            modelNames[i]=key;
            i++;
        }
        return modelNames;
    }

    public double[] getModelPriceMass()
    {
        double[] modelPrices = new double[ModelMass.size()];
        int i=0;
        for (double value: ModelMass.values())
        {
            modelPrices[i]=value;
            i++;
        }
        return modelPrices;
    }

    public double getPriceByName(String name) throws NoSuchModelNameException
    {
        if(!ModelMass.containsKey(name)) throw new NoSuchModelNameException(name);
        return ModelMass.get(name);
    }

    public void setPriceByName(String name, double price) throws NoSuchModelNameException
    {
        if(price<0) throw  new ModelPriceOutOfBoundsException(name);
        if(!ModelMass.containsKey(name)) throw new NoSuchModelNameException(name);
        else ModelMass.replace(name,price);
    }

    public void addNamePriceModel(String name,double price) throws DuplicateModelNameException
    {
        if(price<0) throw new ModelPriceOutOfBoundsException(name);
        for(int i=0; i<ModelMass.size();i++)
        {
            if(ModelMass.containsKey(name)) throw new DuplicateModelNameException(name);
        }
        ModelMass.put(name,price);
    }
    public void delNamePriceModel(String name) throws NoSuchModelNameException
    {
        if(!ModelMass.containsKey(name)) throw new NoSuchModelNameException(name);
        ModelMass.remove(name);
    }
    public void changeNameModel(String name,String _name)  throws DuplicateModelNameException, NoSuchModelNameException
    {
        if(name.equals(_name)) throw new DuplicateModelNameException(name);
        if(ModelMass.containsKey(name))
        {
            if(ModelMass.containsKey(_name))
            {
                throw new DuplicateModelNameException(_name);
            }
            else
            {
                double price = ModelMass.get(name);
                ModelMass.remove(name);
                ModelMass.put(_name,price);
            }
        }
        else
        {
            throw new NoSuchModelNameException(name);
        }
    }
}
