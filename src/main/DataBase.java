package main;

import java.sql.*;
///////pentru a putea folisi functiile de la baza de date
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.* ;

public class DataBase {


    ///////////////FUNCTIA DE INSERT
//INSERARE IN BAZA DE DATE
    public static void insertB(String nume_fisier, String nume_tabela, double playTime, int Life, int pozx, int pozY, int Map) {
        Connection c = null;
        Statement stmt = null;


        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:D:\\CTI\\AN 2\\SEM 2\\PAOO\\Proiect\\Game\\data.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String sql = "INSERT INTO " + nume_tabela + "( Time, Life, PozX, PozY, Map ) " + "VALUES (" + playTime + "," + Life + "," + pozx + "," + pozY + "," + Map + ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.out.println("Eroare la insert!!!");
            e.printStackTrace();
            // Optional rollback in case of error
            try {
                if (c != null) {
                    c.rollback();

                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }

            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }




    //////////FUNCTIA DE GET
//  EXTRAGERE CEA MAI RECENTA VALOARE DIN BAZA DE DATE
    public static int getB(String nume_fisier, String nume_tabela) {
        Connection c = null;
        Statement stmt = null;
        int value = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:D:\\CTI\\AN 2\\SEM 2\\PAOO\\Proiect\\Game\\data.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM " + nume_tabela + ";");
            while (rs.next()) {
                value = rs.getInt("Time");
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.out.println("Eroare la get!!!");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return value;
    }




    public static int Descending(String nume_fisier, String nume_tabela, int x) {
        int value = 0;
        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:D:\\CTI\\AN 2\\SEM 2\\PAOO\\Proiect\\Game\\data.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String sql = "SELECT * FROM " + nume_tabela + " ORDER BY playTime ASC ";
            rs = stmt.executeQuery(sql);

            // Iterează prin rezultatele obținute
            for (int i = 0; i < x; i++) {
                if (rs.next()) {
                    value = rs.getInt("playTime");
                } else {
                    // Dacă nu sunt suficiente rânduri în rezultatul interogării
                    break;
                }
            }

            c.commit();
        } catch (Exception e) {
            System.out.println("Eroare la descending!!!");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
            try {
                if (c != null) {
                    c.rollback();
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            // Închide resursele în blocul finally
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return value;
    }
}


