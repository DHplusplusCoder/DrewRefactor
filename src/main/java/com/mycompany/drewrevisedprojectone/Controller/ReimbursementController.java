/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.drewrevisedprojectone.Controller;

import com.mycompany.drewrevisedprojectone.Datatype.Status;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author drew
 */

public class ReimbursementController {
    public void HandleGetReimbursements(Context ctx)
    {
        
    }
    public void HandleSubmit(Context ctx) throws SQLException
    {
        DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
        Connection C = DriverManager.getConnection("jdbc:sqlserver://0.0.0.0\\sql1:1433;databaseName=EmploAndFinan;trustServerCertificate=true;user=sa;password=P@SSWORD123");
        Statement S = C.createStatement();
  
              String ID = ctx.formParam("id");
    String Author = ctx.formParam("author");
    String Resolver = ctx.formParam("resolver");
    String Description = ctx.formParam("description");
    String Type = ctx.formParam("type");
    String Status = ctx.formParam("status");
    String Amount = ctx.formParam("amount");
        ResultSet RS = S.executeQuery("INSERT INTO dbo.ers_reimbursements VALUES (" + ID + "," + Author+","+ Resolver+","+ Description+","+ Type+","+ Status+","+Amount+")");
  
    }
    public void HandleGetReimbursementsByID(Context ctx)
    {
        
    }
    public void HandleGetReimbursementsByStatus(Context ctx)
    {
        try
        {
            String statusParam = ctx.queryParam("status");
            Status status = Status.valueOf(statusParam);
            if(status == Status.Pending)
            {
                ctx.status(HttpCode.OK);
                //show reimbursements
            }
            else
            {
                ctx.status(HttpCode.OK);
                //show reims
            }
        }
        catch(Exception E)
        {
            ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
            E.printStackTrace();
            
        }
    }
    public void UpdateReimbursement(Context ctx)
    {
        String ReimbursementUser = ctx.header("Current-User");
        String NewReimbursementStatus = ctx.formParam("newreimbursementstatus");
        String ReimbursementID = ctx.formParam("reimbursementid");
        ctx.status(HttpCode.ACCEPTED);
    }
}

