package jdbcassignmnet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Projectjdbc{
    public static void main(String[] args) throws SQLException,ClassNotFoundException{
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
           
        	Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:9501/XE", "system", "rps@123");
            System.out.println("Connection is successful.");
           
            st = con.createStatement();

            st.executeUpdate("create table product3(id number primary key,name varchar(10),price number)");
            st.executeUpdate("insert into product3 values(1,'bag',9999)");
            st.executeUpdate("insert into product3 values(2,'book',199)");
            st.executeUpdate("insert into product3 values(3,'table',299)");
           
        
            st.executeUpdate("update product3 set price =2000 where id=2");
            st.executeUpdate("delete from product3 where id=3");

            System.out.println("Table created, data inserted, updated, and deleted.");

            rs = st.executeQuery("select * from product3");
            while (rs.next()) {
                System.out.println(rs.getString(1) + "  " + rs.getString(2) + "   " + rs.getString(3));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
         
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
