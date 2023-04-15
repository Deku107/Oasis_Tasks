package Atm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JavaDatabase {
    public Connection connect_db(){
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/atm","postgres","Parrot@73");
            if(conn==null){
                System.out.println("NO");
            }
            else {
                System.out.println("Yess");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return conn;
    }

    public boolean check(int users,int pass){
        int pwds = 0;
        boolean ans = false;
        Statement statement;
        ResultSet rs = null;
        int uset = users;
        try {
            String query = "SELECT user_id,password FROM atm_users WHERE user_id ="+uset;
            statement= connect_db().createStatement();
            rs=statement.executeQuery(query);
            while(rs.next()){
                System.out.println(rs.getInt("user_id")+"  "+rs.getInt("password"));
                pwds = rs.getInt("password");
            }

            if(pass == pwds){
                ans = true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return ans;
    }

    public void hist(int users){

        Statement statement;
        ResultSet rs = null;
        int uset = users;
        try {
            String query = "SELECT * FROM vep_tf_hist";
            statement= connect_db().createStatement();
            rs=statement.executeQuery(query);
            while(rs.next()){
                if(rs.getInt("amounts_his")>0){
                    System.out.println("Credited : "+rs.getInt("amounts_his"));
                }
                else {
                    System.out.println("Debited : "+rs.getInt("amounts_his"));
                }
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void getsam(int users,int withdraw){
        int amount = 0;
        Statement statement;
        ResultSet rs = null;

        try {
            String query1 = "SELECT amount FROM atm_users WHERE user_id ="+users;
            statement= connect_db().createStatement();
            rs=statement.executeQuery(query1);
            while(rs.next()){
                amount = rs.getInt("amount");
            }

            String query2 = String.format("UPDATE atm_users SET amount = %d WHERE user_id = %d",amount-withdraw,users);
            statement= connect_db().createStatement();
            statement.executeUpdate(query2);

            String query3 = String.format("INSERT INTO vep_tf_hist VALUES (%d)",-withdraw);
            statement= connect_db().createStatement();
            statement.executeUpdate(query3);

            System.out.println("Sucessfully Done !");

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public int semsam(int users,int put){
        int amount = 0;
        Statement statement;
        ResultSet rs = null;

        try {
            String query1 = "SELECT amount FROM atm_users WHERE user_id ="+users;
            statement= connect_db().createStatement();
            rs=statement.executeQuery(query1);
            while(rs.next()){
                amount = rs.getInt("amount");
            }

            String query2 = String.format("UPDATE atm_users SET amount = %d WHERE user_id = %d",amount+put,users);
            statement= connect_db().createStatement();
            statement.executeUpdate(query2);

            String query3 = String.format("INSERT INTO vep_tf_hist VALUES (%d)",put);
            statement= connect_db().createStatement();
            statement.executeUpdate(query3);

            System.out.println("Sucessfully Done !");

        }catch (Exception e){
            System.out.println(e);
        }
        return amount;
    }


}
