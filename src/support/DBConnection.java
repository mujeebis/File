package support;

import java.sql.*;
public class DBConnection {

	public static Connection DBConnect() throws SQLException
	
    {
        Connection con=null;
        try{
         Class.forName("com.mysql.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/file","root","admin");
        
        }
        catch(Exception er)
        {
            con.close();
        }
         return con;
    }

}
