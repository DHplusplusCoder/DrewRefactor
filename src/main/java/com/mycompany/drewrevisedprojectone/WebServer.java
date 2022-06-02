package com.mycompany.drewrevisedprojectone;

import io.javalin.Javalin;


import com.mycompany.drewrevisedprojectone.Controller.AuthorizationController;
import com.mycompany.drewrevisedprojectone.Controller.ReimbursementController;
import com.mycompany.drewrevisedprojectone.Controller.UserController;
import static io.javalin.apibuilder.ApiBuilder.*;

public class WebServer {
    public void Initialize_Network(int Port_ID)
    {
        AuthorizationController AC = new AuthorizationController();
        ReimbursementController RC = new ReimbursementController();
        UserController UC = new UserController();
        
        Javalin app = Javalin.create().start(Port_ID);
   
        //Connect Javalin to my Controllers    
   app.routes(() ->{
       get("test", ctx->
       {
          ctx.result("I am alive");
       });
       get("login", ctx->
       {
           ctx.result("To log in, include your Username and Password in the URL.");
       });
       path("login", () ->{
           
          post(AC::Login); 
       });
       get("register", ctx->
       {
           ctx.result("To register, include your Username and Password in the URL.");
       });
       path("register", () ->{
          post(AC::Register); 
       });
       
       path("users", () ->{
          get(UC::HandleGetUsers);
          path("{id}", () ->{
          get(UC::HandleGetUsersByID); 
       });
       });
       
       
       
       
       
       path("reimbursements", () ->{
          get(RC::HandleGetReimbursements);
          post(RC::HandleSubmit);
          path("{id}", () ->{
          get(RC::HandleGetReimbursementsByID);
          put(RC::UpdateReimbursement);
       });
       });
   });
        
    }
   
}