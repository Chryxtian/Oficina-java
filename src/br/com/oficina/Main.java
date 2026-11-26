package br.com.oficina;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.ArrayList;
import java.sql.SQLException;

public class Main{

    private static String capitalizeFirstLetter(String value){
        String text = value.trim();

        if (text.isEmpty()){
            return text;
        }
        return text.substring(0, 1).toUpperCase()+ text.substring(1);

    }
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        String answer;


        System.out.println("-----SESH GARAGE-----");

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
        System.out.println("¸.•*´¨`*•.¸¸.•*´¨`*•.¸¸.•*´¨`*•.¸¸.•*´¨`*•.¸");

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

                    while (name.isBlank()){
                        System.out.println("O nome precisa ser informado, digite novamente: ");
                        name = keyboard.nextLine();

                    } name = capitalizeFirstLetter(name);

                    System.out.print("Telefone: ");
                    String phone = keyboard.nextLine();

                    while (!phone.matches("[0-9]+") || phone.length() != 11 && phone.length() != 10){
                        System.out.println("Apenas números são válidos e deve conter 10 (fixo) ou 11 (celular) dígitos..");
                        phone = keyboard.nextLine();

                    }

                    System.out.print("Marca do veículo: ");
                    String brand = keyboard.nextLine();

                    while (brand.isBlank()){
                        System.out.println("A marca do veículo deve ser informada, digite novamente: ");
                        brand = keyboard.nextLine();

                    } brand = capitalizeFirstLetter(brand);

                    System.out.print("Modelo do veículo: ");
                    String model = keyboard.nextLine();

                    while (model.isBlank()){
                        System.out.println("O modelo do veículo deve ser informado, digite novamente: ");
                        model = keyboard.nextLine();

                    } model = capitalizeFirstLetter(model);


                    System.out.print("Placa do veículo: ");
                    String licensePlate = keyboard.nextLine().trim().replace("-","").toUpperCase();

                    String oldPlate = "[A-Z]{3}[0-9]{4} "; // 3 letras e 4 números
                    String mercosulPlate = "[A-Z]{3}[0-9][A-Z0-9][0-9]{2}"; // placa mercosul

                    while (!licensePlate.matches(oldPlate) && !licensePlate.matches(mercosulPlate)){
                        System.out.println("Formato inválido, placa deve conter 7 caracteres.");
                        licensePlate = keyboard.nextLine().trim().replace("-","").toUpperCase();
                    }


                    System.out.print("Ano do veículo: ");
                    String yearInput = keyboard.nextLine();

                    while (!yearInput.matches("\\d{4}")){
                        System.out.println("O ano deve conter 4 dígitos.");
                        yearInput = keyboard.nextLine();
                    }

                    int year = Integer.parseInt(yearInput);





                    System.out.print("Relato do cliente: ");
                    String complaint = keyboard.nextLine();

                    while (complaint.isBlank()){
                        System.out.println("Relato precisa ser informado, digite novamente: " );
                        complaint = keyboard.nextLine();
                    }


                    Customer customer = new Customer(name, phone);

                    int customerId;
                    try {
                        customerId = new CustomerDAO().save(customer);
                        System.out.println("Cadastro do cliente salvo no banco com ID: " +customerId);
                        customers.add(customer);
                    }catch (SQLException e){
                        System.out.println("Não foi possível salvar o cadastro do cliente no banco.");
                        e.printStackTrace();
                        break;
                    }


                    Vehicle vehicle = new Vehicle(licensePlate, brand, model, year, customer);

                    int vehicleId;
                    try {
                        vehicleId = new VehicleDAO().save(vehicle,customerId);
                        System.out.println("Veículo salvo com o ID: " +vehicleId);
                        vehicles.add(vehicle);
                    }catch (SQLException e){
                        System.out.println("Não foi possível salvar o veículo no banco.");
                        e.printStackTrace();
                        break;
                    }

                    WorkOrder workOrder = new WorkOrder(nextWorkOrderNumber, vehicle, complaint);

                     int workOrderId;
                     try {
                         workOrderId = new WorkOrderDAO().save(vehicleId, workOrder.getComplaint(),workOrder.getStatus());

                         workOrder.setNumber(workOrderId);
                         workOrders.add(workOrder);

                         System.out.println("Ordem salva com o ID: " + workOrderId);

                     }catch (SQLException e){
                         System.out.println("Não foi possível salvar a ordem no banco.");
                         e.printStackTrace();
                         break;
                     }

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

                    while (!keyboard.hasNextInt()){
                        System.out.println("Entrada inválida, escolha uma das opções disponiveis.");
                        keyboard.nextLine();
                    }

                    consultaOption = keyboard.nextInt();
                    keyboard.nextLine();

                    switch (consultaOption){

                    case 1:

                        if (workOrders.isEmpty()){
                            System.out.println("Nenhuma ordem cadastrada.");
                        }else {


                            for (WorkOrder registeredWorkOrder : workOrders) {
                                Vehicle registeredVehicle = registeredWorkOrder.getVehicle();


                                System.out.println("Proprietário: " + registeredVehicle.getOwner().getName());
                                System.out.println("Telefone: " + registeredVehicle.getOwner().getPhone());
                                System.out.println("Marca do veículo: " + registeredVehicle.getBrand());
                                System.out.println("Modelo do veículo: " + registeredVehicle.getModel());
                                System.out.println("Placa do veículo: " + registeredVehicle.getLicensePlate());
                                System.out.println("Ano do veículo: " + registeredVehicle.getYear());
                                System.out.println("Relato do cliente: " + registeredWorkOrder.getComplaint());
                                System.out.println("Status da ordem: " + registeredWorkOrder.getStatus());
                                System.out.println("Número da ordem: " + registeredWorkOrder.getNumber());

                                if (registeredWorkOrder.getDiagnosis() != null) {
                                    System.out.println("Diagnóstico: " + registeredWorkOrder.getDiagnosis());
                                }

                                if (registeredWorkOrder.getService() != null) {
                                    System.out.println("Serviço recomendando: " + registeredWorkOrder.getService());
                                }

                                if (registeredWorkOrder.getPartsNeeded() != null) {
                                    System.out.println("Peças necessárias: " + registeredWorkOrder.getPartsNeeded());
                                }

                                if (registeredWorkOrder.getPrice() != null) {
                                    System.out.println("Valor do orçamento: R$ " + registeredWorkOrder.getPrice());
                                }

                                System.out.println(" ");



                            }
                            System.out.println("Deseja fazer outra busca? (S/N): ");
                            String answerListAgain = keyboard.nextLine();

                            if (answerListAgain.equalsIgnoreCase("n")){
                                consultaOption = 4;
                            }
                        }


                        break;


                    case 2:
                        String answerFindNumber;

                        if (workOrders.isEmpty()){
                            System.out.println("Nenhuma ordem cadastrada.");
                        }else {


                        do {
                            System.out.println("Numero da ordem: ");

                            while (!keyboard.hasNextInt()) {
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

                                    if (registeredWorkOrder.getDiagnosis() != null) {
                                        System.out.println("Diagnóstico: " + registeredWorkOrder.getDiagnosis());
                                    }

                                    if (registeredWorkOrder.getService() != null)
                                        System.out.println("Serviço recomendado: " + registeredWorkOrder.getService());


                                    if (registeredWorkOrder.getPartsNeeded() != null) {
                                        System.out.println("Peças necessárias: " + registeredWorkOrder.getPartsNeeded());
                                    }

                                    if (registeredWorkOrder.getPrice() != null) {
                                        System.out.println("Valor do orçamento: R$ " + registeredWorkOrder.getPrice());
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

                                if (answerFindNumber.equalsIgnoreCase("n")) {
                                    consultaOption = 4;
                                }


                            }while (answerFindNumber.equalsIgnoreCase("s")) ;

                        }break;


                    case 3:

                        String answerNewSearch;

                        if (workOrders.isEmpty()){
                            System.out.println("Nenhuma ordem cadastrada.");
                        }else {

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

                                        if (registeredWorkOrder.getDiagnosis() != null) {
                                            System.out.println("Diagnóstico: " + registeredWorkOrder.getDiagnosis());
                                        }

                                        if (registeredWorkOrder.getService() != null) {
                                            System.out.println("Serviço recomendado: " + registeredWorkOrder.getService());
                                        }

                                        if (registeredWorkOrder.getPartsNeeded() != null) {
                                            System.out.println("Peças necessárias: " + registeredWorkOrder.getPartsNeeded());
                                        }

                                        if (registeredWorkOrder.getPrice() != null) {
                                            System.out.println("Valor do orçamento: R$ " + registeredWorkOrder.getPrice());
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

                                if (answerNewSearch.equalsIgnoreCase("n")) {
                                    consultaOption = 4;
                                }


                            } while (answerNewSearch.equalsIgnoreCase("S"));
                        } break;

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

                          while (!keyboard.hasNextInt()){
                              System.out.println("Entrada inválida, digite uma das opções disponiveis.");
                              keyboard.nextLine();
                          }

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
                                  System.out.println("Opção inválida, status não alterado.");
                          }

                          if (newStatus != null) {

                              String currentStatus = registeredWorkOrder.getStatus();

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

                                      BigDecimal revisedPrice;

                                      do {
                                          System.out.print("Novo valor do orçamento: R$ ");


                                      while (!keyboard.hasNextBigDecimal()) {
                                          System.out.println("Valor inválido, digite novamente: ");
                                          keyboard.nextLine();
                                          System.out.println("Novo valor do orçamento: R$ ");
                                      }

                                      revisedPrice = keyboard.nextBigDecimal();
                                      keyboard.nextLine();

                                      if (revisedPrice.compareTo( BigDecimal.ZERO) <= 0){
                                          System.out.println("O orçamento deve ser maior que zero. ");
                                      }

                                  }while (revisedPrice.compareTo(BigDecimal.ZERO) <= 0 );

                                      registeredWorkOrder.setPrice(revisedPrice);
                                      registeredWorkOrder.setStatus("Aguardando aprovação");

                                      System.out.println("Orçamento revisado, aguardando nova aprovação.");

                                  }else {
                                      System.out.println("Revisão cancelada, status permanece: " +registeredWorkOrder.getStatus());
                                  }





                              }else if (
                                      (newStatus.equalsIgnoreCase("Aprovada")
                                      || newStatus.equalsIgnoreCase("Orçamento recusado"))
                                              && !registeredWorkOrder.getStatus().equalsIgnoreCase("Aguardando aprovação"))
                                {
                                  System.out.println("A ordem precisa estar com status 'aguardando aprovação' para registrar a decisão do cliente.");

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




                              }else if (newStatus.equalsIgnoreCase(registeredWorkOrder.getStatus())){
                                  System.out.println("A ordem já está com esse status.");

                              }else if (
                                      newStatus.equalsIgnoreCase("Aguardando aprovação")
                                      && !registeredWorkOrder.getStatus().equalsIgnoreCase("Em diagnóstico")

                              ){
                                  System.out.println("A ordem precisa estar em diagnóstico antes de ir para aprovação. ");



                              }else if (
                                      newStatus.equalsIgnoreCase("Em diagnóstico")
                                        && !currentStatus.equalsIgnoreCase("Aberta")
                                        && !currentStatus.equalsIgnoreCase("Em diagnóstico")
                                        && !currentStatus.equalsIgnoreCase("Aguardando aprovação")
                              ){
                                  System.out.println("A ordem só pode ir para diagnóstico quando estiver aberta ou aguardando aprovação.");


                                } else{

                                  registeredWorkOrder.setStatus(newStatus);
                                  System.out.println("Status alterado para: " +newStatus);

                                  if (newStatus.equalsIgnoreCase("Em diagnóstico")) {
                                      registeredWorkOrder.setPrice(null);

                                      System.out.println("Descreva o diagnóstico: ");
                                      String diagnosis = keyboard.nextLine();
                                      registeredWorkOrder.setDiagnosis(diagnosis);

                                      System.out.println("Descreva o serviço recomendado: ");
                                      String service = keyboard.nextLine();
                                      registeredWorkOrder.setService(service);

                                      System.out.println("Peças necessárias: ");
                                      String partsNeeded = keyboard.nextLine();
                                      registeredWorkOrder.setPartsNeeded(partsNeeded);

                                      System.out.println("Diagnóstico registrado.");
                                  }

                                  if (newStatus.equalsIgnoreCase("Aguardando aprovação")) {

                                      BigDecimal price;

                                      do {
                                          System.out.println("Valor do orçamento: R$ ");

                                          while (!keyboard.hasNextBigDecimal()){
                                              System.out.println("Valor inválido, digite novamente: ");
                                              keyboard.nextLine();
                                              System.out.println("Valor do orçamento: R$ ");
                                          }

                                          price = keyboard.nextBigDecimal();
                                          keyboard.nextLine();

                                          if (price.compareTo(BigDecimal.ZERO) <= 0){
                                              System.out.println("O orçamento deve ser maior que zero.");
                                          }

                                      }while (price.compareTo(BigDecimal.ZERO) <= 0 );

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

                while (!keyboard.hasNextInt()){
                    System.out.println("Entrada inválida, digite um número de ordem.");
                    keyboard.nextLine();
                }

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
                            System.out.println("O aviso só pode ser enviado após a conclusão da ordem. Para alterar status utilize a opção 3.");

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