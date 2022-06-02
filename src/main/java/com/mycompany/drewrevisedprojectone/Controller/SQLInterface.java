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
public class SQLInterface {
    Connection C;
    public SQLInterface()
    {
        try
        {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
        C = DriverManager.getConnection("jdbc:sqlserver://0.0.0.0\\sql1:1433;databaseName=EmploAndFinan;trustServerCertificate=true;user=sa;password=P@SSWORD123");
    }
    catch(SQLException E)
    {
        E.printStackTrace();
    }
    }
    public int DoesUserExist(String Input) throws SQLException
    {
        Statement S = C.createStatement();
        ResultSet RS = S.executeQuery("IF EXISTS(SELECT * FROM dbo.ers_users WITH (NOLOCK) WHERE username = "+Input+")");
        if(RS.getBoolean(1))
        {
            return 1;
        } 
        else 
        {
            System.out.println("User did not exist");
            return 0;
        }
        
    }
}
