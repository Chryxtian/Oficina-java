package br.com.oficina;

public class Vehicle {
    private String licensePlate;
    private String brand;
    private String model;
    private int year;

    private Customer owner;


        public Vehicle (String licensePlate, String brand, String model, int year, Customer owner){

            this.licensePlate = licensePlate;
            this.brand = brand;
            this.model = model;
            this.year = year;
            this.owner = owner;
        }


        public String getLicensePlate(){
            return licensePlate;
        }

        public String getBrand(){
            return brand;
        }

        public String getModel(){
            return model;
        }

        public int getYear(){
            return year;
        }

    public Customer getOwner() {
        return owner;
    }
}
