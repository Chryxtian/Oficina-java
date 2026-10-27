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

        int nextWorkOrderNumber = 1;
        int option;

    do {
        System.out.println("1 - Cadastrar cliente");
        System.out.println("2 - Consultar ordem de serviço");
        System.out.println("3 - Atualizar ordem de serviço");
        System.out.println("0 - Sair");
        option = keyboard.nextInt();
        keyboard.nextLine();

        switch (option) {
            case 1:


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
                    String licensePlate = keyboard.nextLine();

                    System.out.print("Ano do veículo: ");
                    int year = keyboard.nextInt();

                    keyboard.nextLine();

                    System.out.println("Relato do cliente: ");
                    String complaint = keyboard.nextLine();


                    Customer customer = new Customer(name, phone);
                    customers.add(customer);

                    Vehicle vehicle = new Vehicle(licensePlate, brand, model, year, customer);
                    vehicles.add(vehicle);

                    WorkOrder workOrder = new WorkOrder(nextWorkOrderNumber, vehicle, complaint);
                    workOrders.add(workOrder);
                    nextWorkOrderNumber ++;


                    System.out.println("Deseja cadastrar outro cliente ? (s/n) ");
                    awnser = keyboard.nextLine();

                } while (awnser.equalsIgnoreCase("s"));

                break;


            case 2:
                System.out.println("Consultar ordens de seviço");

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
                    System.out.println("Número da ordem: " +registeredWorkOrder.getNumber());
                    System.out.println(" ");

                }

                break;

            case 3:
                System.out.println("Digite o número da ordem ");
                int selectedOrderNumber = keyboard.nextInt();
                keyboard.nextLine();

                boolean orderFound = false;


                for (WorkOrder registeredWorkOrder : workOrders){
                    if (registeredWorkOrder.getNumber() == selectedOrderNumber){
                        System.out.println("Ordem localizada. Status atual: " +registeredWorkOrder.getStatus());

                        System.out.println("Digite o novo status ");
                        String newStatus = keyboard.nextLine();

                        registeredWorkOrder.setStatus(newStatus);
                        System.out.println("Status atualizado.");

                        orderFound = true;

                        break;
                    }
                }
                if (!orderFound){
                    System.out.println("Ordem não encontrada.");

                }
                break;

            case 0:
                System.out.println("Encerrando o programa");
                break;

            default:
                System.out.println("Opção inválida");


        }

    }while(option!= 0);









    }
}