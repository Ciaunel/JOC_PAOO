package joc;

import java.sql.*;
public class DbConnection {
    public static Connection connect() {

        Connection con = null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:src\\resurse\\SaveGame\\save.db");
            System.out.println("Connected!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    public static void insert(int Maps, int PlayerX, int PlayerY, int Life) {
        Connection con = connect();
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO save(Maps, PlayerX, PlayerY, Life) VALUES(?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, Maps);
            ps.setInt(2, PlayerX);
            ps.setInt(3, PlayerY);
            ps.setInt(4, Life);
            ps.execute();
            System.out.println("Data has been inserted!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int[] readAllData() {
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int []v = new int[4];
        try {
            String sql = "SELECT * FROM save";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            v[0] = rs.getInt("Maps");
            v[1] = rs.getInt("PlayerX");
            v[2] = rs.getInt("PlayerY");
            v[3] = rs.getInt("Life");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return v;
    }

    public static void deleteRow(){
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        try{
            String sql = "delete from save";
            ps = con.prepareStatement(sql);
            ps.execute();

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

}