package br.com.oficina;

import java.util.Scanner;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        String answer;


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
                    System.out.println("Cadastro atendimento");


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

                    System.out.print("Relato do cliente: ");
                    String complaint = keyboard.nextLine();


                    Customer customer = new Customer(name, phone);
                    customers.add(customer);

                    Vehicle vehicle = new Vehicle(licensePlate, brand, model, year, customer);
                    vehicles.add(vehicle);

                    WorkOrder workOrder = new WorkOrder(nextWorkOrderNumber, vehicle, complaint);
                    workOrders.add(workOrder);
                    nextWorkOrderNumber ++;


                    System.out.println("Deseja cadastrar outro cliente ? (S/N) ");
                    answer = keyboard.nextLine();

                } while (answer.equalsIgnoreCase("s"));

                break;


            case 2:
                System.out.println("Consultar ordens de serviço");
                System.out.println("1 - Listar todas ");
                System.out.println("2 - Buscar por numero da ordem");
                System.out.println("3 - Buscar por nome cliente");
                System.out.println("4 - Menu inicial");

                int consultaOption = keyboard.nextInt();
                keyboard.nextLine();

                switch (consultaOption){

                    case 1:
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

                    case 2:
                        String answerFindNumber;


                        do {
                            System.out.println("Numero da ordem: ");
                            int orderNumber = keyboard.nextInt();
                            keyboard.nextLine();

                            boolean orderFound = false;

                            for (WorkOrder registeredWorkOrder : workOrders) {
                                if (registeredWorkOrder.getNumber() == orderNumber) {
                                    System.out.println("Ordem encontrada");

                                    Vehicle registeredVehicle = registeredWorkOrder.getVehicle();
                                    System.out.println("Proprietário: " + registeredVehicle.getOwner().getName());
                                    System.out.println("Telefone: " + registeredVehicle.getOwner().getPhone());
                                    System.out.println("Marca: " + registeredVehicle.getBrand());
                                    System.out.println("Modelo: " + registeredVehicle.getModel());
                                    System.out.println("Placa: " + registeredVehicle.getLicensePlate());
                                    System.out.println("Ano: " + registeredVehicle.getYear());
                                    System.out.println("Relato: " + registeredWorkOrder.getComplaint());
                                    System.out.println("Status: " + registeredWorkOrder.getStatus());
                                    System.out.println("Número da ordem: " + registeredWorkOrder.getNumber());

                                    orderFound = true;
                                    break;
                                }


                            }
                            if (!orderFound) {
                                System.out.println("Ordem não encontrada.");
                            }

                            System.out.println("Deseja buscar outra ordem ? (S/N)");
                            answerFindNumber = keyboard.nextLine();


                        }while(answerFindNumber.equalsIgnoreCase("s"));
                        break;


                    case 3:

                        String answerNewSearch;

                        do {
                            System.out.print("Digite o nome do cliente: ");

                            boolean customerFound = false;

                            String nameCustomerSearch = keyboard.nextLine();

                            for (WorkOrder registeredWorkOrder : workOrders) {
                                Vehicle registeredVehicle = registeredWorkOrder.getVehicle();
                                String registeredCustomerName = registeredVehicle.getOwner().getName();

                                if (registeredCustomerName.equalsIgnoreCase(nameCustomerSearch)) {
                                    System.out.println("Nome: " + registeredCustomerName);
                                    System.out.println("Número da ordem: " + registeredWorkOrder.getNumber());
                                    System.out.println("Veículo: " + registeredVehicle.getModel());
                                    System.out.println("Relato: " + registeredWorkOrder.getComplaint());
                                    System.out.println("Status: " + registeredWorkOrder.getStatus());
                                    System.out.println();

                                    customerFound = true;

                                }

                            }
                            if (!customerFound) {
                                System.out.println("Nenhuma ordem encontrada para esse cliente.");
                            }

                            System.out.println("Deseja fazer outra busca ? (S/N)");
                            answerNewSearch = keyboard.nextLine();


                        }while (answerNewSearch.equalsIgnoreCase("S"));
                        break;

                    case 4:
                        System.out.println("Voltando ao menu inicial.");
                        break;

                    default:
                        System.out.println("Opção inválida");


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
                        System.out.println("1 - Em diagnóstico");
                        System.out.println("2 - Aguardando aprovação");
                        System.out.println("3 - Aprovada");
                        System.out.println("4 - Orçamento recusado");
                        System.out.println("5 - Em execução");
                        System.out.println("6 - Concluída");

                        int statusOption = keyboard.nextInt();
                        keyboard.nextLine();

                        String newStatus = null;

                        switch (statusOption){

                            case 1:
                                newStatus = "Em diagnóstico";
                                break;

                            case 2:
                                newStatus = "Aguardando aprovação";
                                break;

                            case 3:
                                newStatus = "Aprovada";
                                break;

                            case 4:
                                newStatus = "Orçamento recusado";
                                break;

                            case 5:
                                newStatus = "Em execução";
                                break;

                            case 6:
                                newStatus = "Concluída";
                                break;

                            default:
                                System.out.println("Opção inválida, status não alterado");
                        }

                        if (newStatus!=null){
                            registeredWorkOrder.setStatus(newStatus);
                            System.out.println("Status alterado para: " +newStatus);
                        }




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