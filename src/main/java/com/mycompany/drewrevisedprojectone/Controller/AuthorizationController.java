/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.drewrevisedprojectone.Controller;

/**
 *
 * @author drew
 */

import io.javalin.http.Context;
import io.javalin.http.HttpCode;


import com.mycompany.drewrevisedprojectone.Datatype.User;

import java.util.Objects;

public class AuthorizationController {
    public void Login(Context ctx)
    {
     //String username = ctx.formParam("username");
     //String password = ctx.formParam("password");
        String username = "u";
     String password = "p";
     if(Objects.equals(username, "") || Objects.equals(password, ""))
     {
         ctx.status(HttpCode.BAD_REQUEST);
         ctx.result("Invalid Username and Password");
     }
     else
     {
         Authorizer A = new Authorizer();
         System.out.println(username);
         System.out.println(password);
         int UserExists = A.LoginA(username, password);
         if(UserExists == 1)
         {
             User U = new User();
             ctx.status(HttpCode.ACCEPTED);
             ctx.header("Access-Control-Expose-Headers");
             ctx.header("Current-User", ""+U.GetUserID());
             //ctx.result(toString(U.GetRole()));
         }
     }
    }
    public void Register(Context ctx)
    {
        try
        {
            
            
            Authorizer A = new Authorizer();
            
            int ID = A.RegisterA(ctx);
            if(ID == 0)
            {
                ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
                ctx.result("Error during registration");
            }
            else
            {
                ctx.status(HttpCode.CREATED);
                ctx.result("You have been registered");
            }
            
        }
        catch(Exception E)
        {
            ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
            if(!E.getMessage().isEmpty())
            {
                ctx.result(E.getMessage());
            }
        }
    }
 
}

        
        
        
        
        
