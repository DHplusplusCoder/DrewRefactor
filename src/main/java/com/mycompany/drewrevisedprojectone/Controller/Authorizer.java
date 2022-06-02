/*
 * The MIT License
 *
 * Copyright 2022 Drew Harley.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.mycompany.drewrevisedprojectone.Controller;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author drew
 */
import com.mycompany.drewrevisedprojectone.Datatype.User;
import com.mycompany.drewrevisedprojectone.DAO.UserDao;
import io.javalin.http.Context;
public class Authorizer {
    public int LoginA(String Username, String Password)
    {

        try
        {
               DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
        Connection C = DriverManager.getConnection("jdbc:sqlserver://0.0.0.0\\sql1:1433;databaseName=EmploAndFinan;trustServerCertificate=true;user=sa;password=P@SSWORD123");
        
        
        Statement S = C.createStatement();
        ResultSet RS = S.executeQuery("SELECT * FROM dbo.ers_users WHERE password = " + Password);
            
            int UserExists = 0;
            String DatabasePassword = RS.getString("password"); //Get from Database
            String DatabaseUsername = RS.getString("username"); //Get from Database
            if(DatabaseUsername.equals(Username))
            {
                UserExists = 1;
            }
            
            if(UserExists == 1 && Password.equals(DatabasePassword))
            {
                System.out.print("You are logged in.");
                return 1;
                
            }
            else if(UserExists == 1 && !Password.equals(DatabasePassword))
            {
                System.out.println("Incorrect Password");
                return 0;
            }
            else
            {
                System.out.println("User does not exist.");
               return 0;
            }
               
        }
        catch(Exception E)
        {
            System.out.println("Unknown Error");
            E.printStackTrace();
            return 0;
            
        }
       
    }
       
    
    public int RegisterA(Context ctx) throws SQLException
    {
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");
        String ID = ctx.formParam("ID");
        String role = ctx.formParam("role");
        
           DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
        Connection C = DriverManager.getConnection("jdbc:sqlserver://0.0.0.0\\sql1:1433;databaseName=EmploAndFinan;trustServerCertificate=true;user=sa;password=P@SSWORD123");
        
        
        Statement S = C.createStatement();
        ResultSet RS = S.executeQuery("INSERT INTO dbo.ers_users VALUES (" + ID + "," + username + "," + password + "," + role+")");
        
        
        return 0;
  }
}
