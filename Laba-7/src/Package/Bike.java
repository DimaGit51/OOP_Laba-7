package Package;


import ErrorsPackage.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Bike implements transport_vehicle{
    {
        lastModified = System.currentTimeMillis();
    }
    private int SizeMass;
    private Model head = new Model();
    {
        head.prev = head;
        head.next = head;
    }
    private transient long lastModified;//blok init

    public long getLastModified() {
        return lastModified;
    }
    public int getLenModelMass()
    {
        return SizeMass;
    }

    //поле типа String, хранящее марку автомобиля
    //ОБЩАЯ ПЕРЕМЕННАЯ brand
    //№1
    private String brand;


    //метод для получения марки автомобиля
    //ОБЩИЙ МЕТОД getBrand()
    //№2
    public String getBrand(){
        return brand;
    }


    //метод для модификации марки автомобиля
    //ОБЩИЙ МЕТОД setBrand(String value)
    //№3
    public void setBrand(String value){
        brand = value;
    }


    //внутренний класс Модель
    //ОБЩИЙ КЛАСС Model
    //№4
    private class Model implements Serializable, Cloneable {
        private String nameModel = null;
        private double priceModel = Double.NaN;
        private Model prev = null;
        private Model next = null;

        //метод для модификации значения названия модели,
        public void setNameModel(String value){
            nameModel = value;
        }
        public String getNameModel(){
            return nameModel;
        }
        public double getPriceModel(){
            return priceModel;
        }

        public void setPrice(double value){
            priceModel = value;
        }
        public double getPrice(){
            return priceModel;
        }

        public void setPrev(Model value){
            prev = value;
        }
        public Model getPrev(){
            return prev;
        }

        public void setNext(Model value){
            next = value;
        }
        public Model getNext(){
            return next;
        }
        public Model() {
        }
        public Model(String nM, double pM){
            if(pM<0) { throw new ModelPriceOutOfBoundsException(nM);}
            lastModified = System.currentTimeMillis()/1000;
            nameModel = nM;
            priceModel = pM;
        }
        public Object clone() throws CloneNotSupportedException
        {
            return super.clone();
        }
    }


    //метод, возвращающий массив названий всех моделей,
    //ОБЩИЙ МЕТОД getModelNameMass()
    //№6
    public String[] getModelNameMass(){
        String[] allNameModel = new String[SizeMass];
        Model mdHead = head;
        for(int i=0; i<SizeMass; i++){
            allNameModel[i] = mdHead.getNameModel();
            mdHead = mdHead.next;
        }
        return allNameModel;
    }

    public double[] getModelPriceMass(){
        double[] allNameModel = new double[SizeMass];
        Model mdHead = head;
        for(int i=0; i<SizeMass; i++){
            allNameModel[i] = mdHead.getPrice();
            mdHead = mdHead.next;
        }
        return allNameModel;
    }


    //метод для получения значения цены модели по её названию,
    //ОБЩИЙ МЕТОД getPriceByName(String name)
    //№7
    public double getPriceByName(String name) throws NoSuchModelNameException{
        Model p = head;
        if(p.nameModel.equals(name)) return p.getPrice();
        p = p.next;
        while ((p != head) && !p.nameModel.equals(name)) {p = p.next;}
        if (p == head) throw new NoSuchModelNameException(name);
        return p.getPrice();
    }


    //метод для модификации значения цены модели по её названию,
    //ОБЩИЙ МЕТОД setPriceByName(String name, double value)
    //№8
    public void setPriceByName(String name, double value) throws NoSuchModelNameException{
        boolean log = false;
        if(value>0) {
            Model mdHead = head;
            for (int i = 0; i < SizeMass; i++) {
                if (mdHead.getNameModel().equals(name)) {
                    mdHead.setPrice(value);
                    lastModified = System.currentTimeMillis();
                    log = true;
                    break;
                }
                mdHead = mdHead.getNext();
            }
        }
        else{
            throw new ModelPriceOutOfBoundsException(name);
        }
        if(!log) throw new NoSuchModelNameException(name);
    }


    //метод, возвращающий массив значений цен моделей,
    //ОБЩИЙ МЕТОД getPriceMass()
    //№9



    //ДОП МЕТОД "Логика существования"



    //метод добавления названия модели и её цены (путем создания нового массива Моделей), использовать метод Arrays.copyOf(),
    //ОБЩИЙ МЕТОД addNamePriceModel(String name, double value)
    //№10
    public void addNamePriceModel(String name, double modelPrice) throws DuplicateModelNameException{
        if(modelPrice>0) {
            Model p = head.next;
            while ((p != head)) {
                if (p.nameModel.equals(name)) throw new DuplicateModelNameException(name);
                p = p.next;
            }
            Model mdHead = new Model(name, modelPrice);
            if (SizeMass == 0) {
                head = mdHead;
                mdHead.next = mdHead;
                mdHead.prev = mdHead;
            } else {
                mdHead.prev = head.prev;
                mdHead.next = head;
                head.prev.next = mdHead;
                head.prev = mdHead;
            }
            lastModified = System.currentTimeMillis()/1000;
            SizeMass++;
        }
        else{
            throw new ModelPriceOutOfBoundsException(name);
        }
    }


    //метод удаления модели по заданному имени, использовать методы System.arraycopy, Arrays.copyOf(),
    //ОБЩИЙ МЕТОД delNamePriceModel(String name, double value)
    //№11
    public void delNamePriceModel(String name) throws NoSuchModelNameException{
        Model p = head.next;
        while ((p != head) && !p.nameModel.equals(name)) p = p.next;
        if (p == head) throw new NoSuchModelNameException(name);
        p.prev.next = p.next;
        p.next.prev = p.prev;
        SizeMass--;
        lastModified = System.currentTimeMillis();
    }
    public void changeNameModel(String name, String newName) throws NoSuchModelNameException, DuplicateModelNameException{
        Model p = head.next;
        Model q = head;
        while (p != head) {
            if (p.getNameModel().equals(name)) {
                q = p;
            } else if (p.nameModel.equals(newName)) {
                throw new DuplicateModelNameException(newName);
            }
            p = p.next;
        }

        if (q == head) throw new NoSuchModelNameException(name);
        q.nameModel = newName;
        lastModified = System.currentTimeMillis();
    }

    //Конструктор класса должен принимать в
    // качестве параметров значение Марки автомобиля,
    // размер массива Моделей и заполнять массив
    // Моделями с уникальными названиями и необязательно уникальными ценами.
    public Bike(String mark, int size){
        this.brand = mark;
        this.SizeMass = size;
        Random r = new Random();

        head = new Model("model0", (double) r.nextInt(1000));
        head.next = head;
        head.prev = head;

        for (int i = 1; i < size; i++) {
            Model p = new Model("model" + Integer.toString(i), (double) r.nextInt(1000));
            p.next = head;
            p.prev = head.prev;
            head.prev.next = p;
            head.prev = p;
        }
    }
    public String toString()
    {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("Марка: "+getBrand()+'\n');
        strBuff.append("Количество моделей: "+getLenModelMass()+'\n');
        strBuff.append("Список моделей: "+'\n');
        Model q=head;
        strBuff.append("Название модели: "+q.getNameModel());
        strBuff.append(", ");
        strBuff.append("Цена модели: " +q.getPriceModel());
        strBuff.append("\n");
        q=q.next;

        while(q!=head)
        {
            strBuff.append("Название модели: "+q.getNameModel());
            strBuff.append(", ");
            strBuff.append("Цена модели: " +q.getPriceModel());
            strBuff.append("\n");
            q=q.next;
        }
        return strBuff.toString();

    }

    public boolean equals(Object obj)
    {
        if(obj==null)
        {
            return false;
        }
        if(obj==this)
        {
            return true;
        }
        boolean flag = true;
        if(obj instanceof Bike)
        {
            Bike motoc =(Bike) obj;
            if(!motoc.getBrand().equals(getBrand()))
            {
                flag= false;
            }
            if(motoc.getLenModelMass()!=getLenModelMass())
            {
                flag= false;
            }
            String[] array = this.getModelNameMass();
            String[] array2 = motoc.getModelNameMass();
            double[] array3 = this.getModelPriceMass();
            double[] array4 = motoc.getModelPriceMass();
            for (int i=0;i<array.length;i++)
            {
                if(!array[i].equals((array2[i]))||array3[i]!=array4[i])
                {
                    flag= false;
                }
            }


        }
        return flag;
    }

    public int hashCode()
    {
        int result=brand.hashCode();
        Model q=head.next;
        while (q!=head)
        {
            result+=q.getNameModel().hashCode();
            result+=q.getPriceModel();
            q=q.next;
        }
        return result;
    }

    public Object clone() throws CloneNotSupportedException
    {
        Bike cloned = (Bike) super.clone();
        cloned.head = (Model) head.clone();
        Model q = head.next;
        Model p = cloned.head;
        while (q != head)
        {
            p.next = (Model) q.clone();
            p.next.prev = p;
            p = p.next;
            q = q.next;
        }
        p.next=cloned.head;
        cloned.head.prev = p;
        return cloned;
    }
}



