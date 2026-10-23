package br.com.oficina;

import java.util.Scanner;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        String awnser;


        System.out.println("-----Sistema da oficina-----");

        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        ArrayList<WorkOrder> workOrders = new ArrayList<>();





        do {
            System.out.println("Cadastro de cliente");


            System.out.print("Nome: ");
            String name = keyboard.nextLine();

            System.out.print("Telefone: ");
            String phone = keyboard.nextLine();

            System.out.print("Marca do veículo: ");
            String brand = keyboard.nextLine();

            System.out.print("Modelo do veículo: ");
            String model = keyboard.nextLine();

            System.out.print("Placa do veículo: ");
            String licensePlate  = keyboard.nextLine();

            System.out.print("Ano do veículo: ");
            int year = keyboard.nextInt();

            keyboard.nextLine();

            System.out.println("Relato do cliente: ");
            String complaint = keyboard.nextLine();




            Customer customer = new Customer(name, phone);
            customers.add(customer);

            Vehicle vehicle = new Vehicle(licensePlate, brand, model, year, customer);
            vehicles.add(vehicle);

            WorkOrder workOrder = new WorkOrder(vehicle, complaint);
            workOrders.add(workOrder);


            System.out.println("Deseja cadastrar outro cliente ? (s/n) ");
            awnser = keyboard.nextLine();

        }while (awnser.equalsIgnoreCase("s"));





        for (WorkOrder registeredWorkOrder : workOrders) {
            Vehicle registeredVehicle = registeredWorkOrder.getVehicle();



            System.out.println("Proprietário: " +registeredVehicle.getOwner().getName());
            System.out.println("Telefone: " +registeredVehicle.getOwner().getPhone());
            System.out.println("Marca do veículo: " +registeredVehicle.getBrand());
            System.out.println("Modelo do veículo: " +registeredVehicle.getModel());
            System.out.println("Placa do veículo: " +registeredVehicle.getLicensePlate());
            System.out.println("Ano do veículo: " +registeredVehicle.getYear());
            System.out.println("Relato do cliente: " +registeredWorkOrder.getComplaint());
            System.out.println("Status da ordem: " +registeredWorkOrder.getStatus());
            System.out.println(" ");

        }




    }
}