package br.com.oficina;

import java.util.Scanner;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        String awnser;


        System.out.println("-----Sistema da oficina-----");
        ArrayList<Customer> customers = new ArrayList<>();

        do {
            System.out.println("Cadastro de cliente");


            System.out.print("Nome: ");
            String name = keyboard.nextLine();

            System.out.print("Telefone: ");
            String phone = keyboard.nextLine();

            Customer customer = new Customer(name, phone);
            customers.add(customer);

            System.out.println("Deseja cadastrar outro cliente ? (s/n) ");
            awnser = keyboard.nextLine();

        }while (awnser.equalsIgnoreCase("s"));


        for (Customer registeredCustomer : customers){

            System.out.println("Nome cadastrado: " + registeredCustomer.getName());
            System.out.println("Telefone cadastrado: " +registeredCustomer.getPhone());
            System.out.println();
    }}
}