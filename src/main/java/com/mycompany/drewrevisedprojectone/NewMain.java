/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.drewrevisedprojectone;

/**
 *
 * @author drew
 */
import com.mycompany.drewrevisedprojectone.Controller.SQLInterface;
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        WebServer WS = new WebServer();
        WS.Initialize_Network(5000);
        SQLInterface S = new SQLInterface();

        
        System.out.println("Employee and Financial Records Backend CLI");
        System.out.println("Created by Drew Harley");
        System.out.println("Type the number that corresponds to your choice");
        System.out.println("---1--- Employee Record Backend");
        System.out.println("---2--- Financial Record Backend");
    }
}
