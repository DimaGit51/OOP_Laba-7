package Package;

import ErrorsPackage.DuplicateModelNameException;
import ErrorsPackage.ModelPriceOutOfBoundsException;
import ErrorsPackage.NoSuchModelNameException;

import java.util.LinkedList;
import java.util.Random;
public class Moped implements transport_vehicle{
    private String brand;
    private LinkedList<Model> ModelMass;
    public Moped(String brand, int n)
    {
        this.brand=brand;
        ModelMass=new LinkedList<>();
        Random rnd = new Random();

        for(int i=0;i<n;i++)
        {
            Model newModel=new Model("model" + Integer.toString(i), (double)rnd.nextInt(1000));
            ModelMass.addLast(newModel);
        }
    }
    private class Model
    {
        private String nameModel;
        private double priceModel;
        public Model(String name, double value)
        {
            nameModel=name;
            priceModel=value;
        }
        public String getNameModel()
        {
            return nameModel;
        }
        public void setNameModel(String nameModel)
        {
            this.nameModel=nameModel;
        }
        public double getPriceModel()
        {
            return priceModel;
        }
        public void setPriceModel(double price)
        {
            this.priceModel = price;
        }
    }
    public String getBrand()
    {
        return brand;
    }
    public void setBrand(String value)
    {
        brand = value;
    }
    public int getLenModelMass()
    {
        return ModelMass.size();
    }
    public String[] getModelNameMass()
    {
        String [] modelNames= new String[ModelMass.size()];
        for(int i=0;i<ModelMass.size();i++)
        {
            modelNames[i]=ModelMass.get(i).getNameModel();
        }
        return modelNames;
    }
    public double[] getModelPriceMass()
    {
        double[] modelPrices =new double[ModelMass.size()];
        for(int i=0;i<ModelMass.size();i++)
        {
            modelPrices[i]=ModelMass.get(i).getPriceModel();
        }
        return modelPrices;
    }
    public double getPriceByName(String name) throws NoSuchModelNameException
    {
        for(int i=0;i<ModelMass.size();i++)
        {
            if( ModelMass.get(i).getNameModel().equals(name))
            {
                return ModelMass.get(i).getPriceModel();
            }
            if(i==ModelMass.size()) throw new NoSuchModelNameException(name);
        }
        return 0;
    }
    public void setPriceByName(String name, double price) throws NoSuchModelNameException
    {
        if(price<0) throw new ModelPriceOutOfBoundsException(name);
        int j=0;
        while (!ModelMass.get(j).getNameModel().equals(name) && j!=ModelMass.size())
        {
            j++;
        }
        if(j==ModelMass.size()) {throw new NoSuchModelNameException(name);}
        for(int i=0; i<ModelMass.size();i++)
        {
            if(ModelMass.get(i).getNameModel().equals(name))
            {
                ModelMass.get(i).priceModel = price;
            }
        }
    }
    public void addNamePriceModel(String name, double price) throws DuplicateModelNameException
    {
        if(price<0) throw new ModelPriceOutOfBoundsException(name);
        for(int i=0;i<ModelMass.size();i++) {
            if (ModelMass.get(i).getNameModel().equals(name)) {
                throw new DuplicateModelNameException(name);
            }
        }
        Model newModel=new Model(name,price);
        ModelMass.addLast(newModel);
    }

    public void delNamePriceModel(String name) throws NoSuchModelNameException
    {
        int j=0;
        while (j<ModelMass.size() && !ModelMass.get(j).getNameModel().equals(name))
        {
            j++;
        }
        if(j == ModelMass.size()) throw new NoSuchModelNameException(name);
        ModelMass.remove(j);
    }
    public void changeNameModel(String name, String name_) throws DuplicateModelNameException, NoSuchModelNameException
    {
        for(int i=0;i<ModelMass.size();i++)
        {
            if(ModelMass.get(i).getNameModel().equals(name_)) throw new DuplicateModelNameException(name_);
        }
        boolean flag=false;
        for(int i=0;i<ModelMass.size();i++)
        {
            if(ModelMass.get(i).getNameModel().equals(name))
            {
                ModelMass.get(i).setNameModel(name_);
                flag=true;
            }
        }
        if(!flag) throw new NoSuchModelNameException(name);
    }
}
