package br.com.oficina;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
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
        System.out.println("4 - Registrar aviso ao cliente");
        System.out.println("0 - Sair");

        while (!keyboard.hasNextInt()){
            System.out.println ("Entrada inválida, digite um número do menu.");
            keyboard.nextLine();

        }

        option = keyboard.nextInt();
        keyboard.nextLine();

        switch (option) {
            case 1:


                do {
                    System.out.println("Cadastrar atendimento");


                    System.out.print("Nome: ");
                    String name = keyboard.nextLine();

                    System.out.print("Telefone: ");
                    String phone = keyboard.nextLine();

                    while (!phone.matches("[0-9]+") || phone.length() != 11 && phone.length() != 10){
                        System.out.println("Apenas números são válidos");
                        phone = keyboard.nextLine();

                    }

                    System.out.print("Marca do veículo: ");
                    String brand = keyboard.nextLine();

                    System.out.print("Modelo do veículo: ");
                    String model = keyboard.nextLine();

                    System.out.print("Placa do veículo: ");
                    String licensePlate = keyboard.nextLine().trim().replace("-","");

                    while (licensePlate.length() != 7){
                        System.out.println("A placa deve ter 7 caracteres, digite novamente: ");
                        licensePlate = keyboard.nextLine().trim().replace("-","");
                    }


                    System.out.print("Ano do veículo: ");

                    while (!keyboard.hasNextInt()){
                        System.out.println("Apenas números são válidos.");
                        keyboard.nextLine();
                    }
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

                    System.out.println("Cadastro concluído.");
                    System.out.println("Número da ordem: " +workOrder.getNumber());
                    System.out.println("Status atual: " +workOrder.getStatus());

                    nextWorkOrderNumber ++;

                    System.out.println("Deseja cadastrar outro cliente ? (S/N) ");
                    answer = keyboard.nextLine();

                } while (answer.equalsIgnoreCase("s"));

                break;


            case 2:

                int consultaOption;

                do {

                    System.out.println("Consultar ordens de serviço");
                    System.out.println("1 - Listar todas ");
                    System.out.println("2 - Buscar por numero da ordem");
                    System.out.println("3 - Buscar por nome cliente");
                    System.out.println("4 - Menu inicial");

                    consultaOption = keyboard.nextInt();
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

                                if (registeredWorkOrder.getDiagnosis()!= null){
                                    System.out.println("Diagnóstico: " +registeredWorkOrder.getDiagnosis());
                                }

                                if (registeredWorkOrder.getService()!=null){
                                    System.out.println("Serviço recomendando: " +registeredWorkOrder.getService());
                                }

                                if (registeredWorkOrder.getPartsNeeded()!=null){
                                    System.out.println("Peças necessárias: " +registeredWorkOrder.getPartsNeeded());
                                }

                                if (registeredWorkOrder.getPrice()!=null){
                                    System.out.println("Valor do orçamento: R$ "+registeredWorkOrder.getPrice());
                                }

                                System.out.println(" ");


                            }

                            System.out.println("Deseja fazer outra busca? (S/N): ");
                            String answerListAgain = keyboard.nextLine();

                                if (answerListAgain.equalsIgnoreCase("n")){
                                    consultaOption = 4;
                                }





                        break;

                    case 2:
                        String answerFindNumber;


                        do {
                            System.out.println("Numero da ordem: ");

                            while (!keyboard.hasNextInt()){
                                System.out.println("Entrada inválida. Digite o número da ordem.");
                                keyboard.nextLine();

                            }

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

                                    if (registeredWorkOrder.getDiagnosis()!= null){
                                        System.out.println("Diagnóstico: " +registeredWorkOrder.getDiagnosis());
                                    }

                                    if (registeredWorkOrder.getService()!=null)
                                        System.out.println("Serviço recomendado: " +registeredWorkOrder.getService());


                                    if (registeredWorkOrder.getPartsNeeded()!=null){
                                        System.out.println("Peças necessárias: " +registeredWorkOrder.getPartsNeeded());
                                    }

                                    if (registeredWorkOrder.getPrice()!=null){
                                        System.out.println("Valor do orçamento: R$ "+registeredWorkOrder.getPrice());
                                    }

                                    System.out.print(" ");

                                    orderFound = true;
                                    break;
                                }


                            }
                            if (!orderFound) {
                                System.out.println("Ordem não encontrada.");
                            }

                            System.out.println("Deseja buscar outra ordem ? (S/N)");
                            answerFindNumber = keyboard.nextLine();

                                if (answerFindNumber.equalsIgnoreCase("n")){
                                    consultaOption = 4;
                                }


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

                                    if (registeredWorkOrder.getDiagnosis()!= null){
                                        System.out.println("Diagnóstico: " +registeredWorkOrder.getDiagnosis());
                                    }

                                    if (registeredWorkOrder.getService()!=null){
                                        System.out.println("Serviço recomendado: " +registeredWorkOrder.getService());
                                    }

                                    if (registeredWorkOrder.getPartsNeeded()!=null){
                                        System.out.println("Peças necessárias: " +registeredWorkOrder.getPartsNeeded());
                                    }

                                    if (registeredWorkOrder.getPrice()!=null){
                                        System.out.println("Valor do orçamento: R$ "+registeredWorkOrder.getPrice());
                                    }

                                    System.out.print(" ");

                                    customerFound = true;

                                }

                            }
                            if (!customerFound) {
                                System.out.println("Nenhuma ordem encontrada para esse cliente.");
                            }

                            System.out.println("Deseja fazer outra busca ? (S/N)");
                            answerNewSearch = keyboard.nextLine();

                                if (answerNewSearch.equalsIgnoreCase("n")){
                                    consultaOption = 4;
                                }


                        }while (answerNewSearch.equalsIgnoreCase("S"));
                        break;

                    case 4:
                        System.out.println("Voltando ao menu inicial.");
                        break;

                    default:
                        System.out.println("Opção inválida");

                    }
                }while (consultaOption!= 4);

                break;



            case 3:

                String answerUpdate;

              do {

                  System.out.println("Digite o número da ordem ");

                  while (!keyboard.hasNextInt()){
                      System.out.println("Entrada inválida. Digite o número da ordem.");
                      keyboard.nextLine();
                  }
                  int selectedOrderNumber = keyboard.nextInt();
                  keyboard.nextLine();

                  boolean orderFound = false;


                  for (WorkOrder registeredWorkOrder : workOrders) {
                      if (registeredWorkOrder.getNumber() == selectedOrderNumber) {
                          System.out.println("Ordem localizada. Status atual: " + registeredWorkOrder.getStatus());


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

                          switch (statusOption) {

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

                          if (newStatus != null) {

                              boolean missingDiagnosis =
                                      registeredWorkOrder.getDiagnosis() == null
                                              || registeredWorkOrder.getDiagnosis().isBlank();

                              boolean missingService =
                                      registeredWorkOrder.getService() == null
                                              || registeredWorkOrder.getService().isBlank();

                              if (newStatus.equalsIgnoreCase("Aguardando aprovação")
                                    && (missingDiagnosis || missingService)){

                                  System.out.println("Status não alterado, informe diagnóstico e serviço necessário primeiro.");

                              }else if (
                                      newStatus.equalsIgnoreCase("Aguardando aprovação")
                                      && registeredWorkOrder.getStatus().equalsIgnoreCase("Aprovada")
                              ){
                                  System.out.println("O orçamento já foi aprovado, deseja revisá-lo? (S/N)");
                                  String revisionAnswer = keyboard.nextLine();

                                  if (revisionAnswer.equalsIgnoreCase("s")) {

                                      System.out.println("Novo valor do orçamento: ");
                                      BigDecimal revisedPrice = keyboard.nextBigDecimal();
                                      keyboard.nextLine();

                                      registeredWorkOrder.setPrice(revisedPrice);
                                      registeredWorkOrder.setStatus("Aguardando aprovação");

                                      System.out.println("Orçamento revisao, aguardando nova aprovação.");

                                  }else {
                                      System.out.println("Revisão cancelada, status permanece: " +registeredWorkOrder.getStatus());
                                  }





                              }else if (
                                      (newStatus.equalsIgnoreCase("Aprovada")
                                      || newStatus.equalsIgnoreCase("Orçamento recusado"))
                                              && !registeredWorkOrder.getStatus().equalsIgnoreCase("Aguardando aprovação"))
                                {
                                  System.out.println("A ordem precis estar com status 'aguardando aprovação' para registrar a decisão do cliente");

                                }else if (
                                      newStatus.equalsIgnoreCase("Em execução")
                                      && !registeredWorkOrder.getStatus().equalsIgnoreCase("Aprovada")

                              ){
                                  System.out.println("A ordem precisa estar aprovada para iniciar a execução.");

                              }else if (
                                      newStatus.equalsIgnoreCase("Concluída")
                                      && !registeredWorkOrder.getStatus().equalsIgnoreCase("Em execução")

                              ){
                                  System.out.println("A ordem precisa estar em execução para ser concluída.");




                              }else if (
                                      newStatus.equalsIgnoreCase("Aguardando aprovação")
                                      && !registeredWorkOrder.getStatus().equalsIgnoreCase("Em diagnóstico")

                              ){
                                  System.out.println("A ordem precisa estar em diagnóstico antes de ir para aprovação. ");

                              }else{

                                  registeredWorkOrder.setStatus(newStatus);
                                  System.out.println("Status alterado para: " +newStatus);

                                  if (newStatus.equalsIgnoreCase("Em diagnóstico")) {
                                      System.out.println("Descreva o diagnóstico: ");
                                      String diagnosis = keyboard.nextLine();
                                      registeredWorkOrder.setDiagnosis(diagnosis);

                                      System.out.println("Descreva o serviço recomendado: ");
                                      String service = keyboard.nextLine();
                                      registeredWorkOrder.setService(service);

                                      System.out.println("Peças necessárias: ");
                                      String partsNeeded = keyboard.nextLine();
                                      registeredWorkOrder.setPartsNeeded(partsNeeded);
                                  }

                                  if (newStatus.equalsIgnoreCase("Aguardando aprovação")) {
                                      System.out.print("Valor do orçamento: R$ ");
                                      BigDecimal price = keyboard.nextBigDecimal();
                                      keyboard.nextLine();

                                      registeredWorkOrder.setPrice(price);
                                  }
                              }
                          }


                          orderFound = true;

                          break;
                      }
                  }
                  if (!orderFound) {
                      System.out.println("Ordem não encontrada.");

                  }

                  System.out.println("Deseja atualizar uma ordem serviço? (S/N): ");
                  answerUpdate = keyboard.nextLine();


              }while (answerUpdate.equalsIgnoreCase("s"));
              break;



            case 4:
                System.out.println("Digite o número da ordem: ");
                int notificationOrderNumber = keyboard.nextInt();
                keyboard.nextLine();

                boolean notificationOrderFound = false;

                for (WorkOrder registeredWorkOrder : workOrders){
                    if (registeredWorkOrder.getNumber() == notificationOrderNumber){
                        notificationOrderFound = true;

                        if (registeredWorkOrder.getStatus().equalsIgnoreCase("Concluída")){
                            System.out.println("O cliente foi avisado sobre a conclusão? (S/N)");
                            String notificationAnswer = keyboard.nextLine();

                            if (notificationAnswer.equalsIgnoreCase("s")){
                                registeredWorkOrder.setCustomerNotified(true);
                                System.out.println("Aviso registrado como enviado.");

                            }else if (notificationAnswer.equalsIgnoreCase("n")){
                                System.out.println("Aviso pendente.");

                            }else {
                                System.out.println("Resposta inválida");
                            }


                        }else {
                            System.out.println("O aviso só pode ser enviado após a conclusão da ordem. Para alterar staus utilize a opção 3.");

                        }

                        break;
                    }
                }
                if (!notificationOrderFound){
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