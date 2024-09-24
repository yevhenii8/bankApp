package org.domahaiev.bankapp;

import org.domahaiev.bankapp.service.interf.ClientService;
import org.domahaiev.bankapp.service.interf.ManagerService;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAppApplication.class, args);

        //ManagerService managerService =  applicationContext.getBean(ManagerService.class);
        //ClientService clientService =  applicationContext.getBean(ClientService.class);

        //111

        //managerService.getManagerById("d0c2e8b1-3b4e-4e7a-9d63-9e6d9b3e1b12").getClients().forEach(client -> System.out.println("client id   " + client.getId()));
        //managerService.getManagerById("d0c2e8b1-3b4e-4e7a-9d63-9e6d9b3e1b12").getClients().forEach(client -> System.out.println("manager id  " + client.getManager().getId()));
        //managerService.deactivateManager("d0c2e8b1-3b4e-4e7a-9d63-9e6d9b3e1b12");


        //System.out.println(clientService.getClientById("9d5e6c3b-1d8e-4f5a-8d1c-3e7f4a1c9b3d").getManager().getId());
        //System.out.println(clientService.getClientById("f95a4c9a-3a5b-4c22-8b56-73b2a3e831df").getManager().getId());

        //managerService.getManagerById("a8e3d7f6-4d8b-4c2e-85b1-1a6c5f2d9c73").getClients().forEach(client -> System.out.println("client id after1   " + client.getId()));
        //managerService.getManagerById("4f2d9e8c-6b1a-4d7e-8a3b-9e5c7f1b2d4c").getClients().forEach(client -> System.out.println("client id after2   " + client.getId()));

        //222

        //System.out.println(clientService.getClientById("2b5c8e6a-4d2e-4d83-9c15-8e1a4a6b8d7f").getManager().getId());
        //managerService.getManagerById("9d6e5c8b-1a3f-4e2d-8c7b-5f9a2d3e6b1c").getClients().forEach(client -> System.out.println("client id   " + client.getId()));
        //clientService.deactivateClient("2b5c8e6a-4d2e-4d83-9c15-8e1a4a6b8d7f");
        //System.out.println("!!!");
        //managerService.getManagerById("9d6e5c8b-1a3f-4e2d-8c7b-5f9a2d3e6b1c").getClients().forEach(client -> System.out.println("client id   " + client.getId()));
        //System.out.println("!!!");


    }
}
