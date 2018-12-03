/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.*;
import agenda.*;

/**
 *
 * @author Aluno
 */
public class SQLiteDataBase {
    
    private Connection connect(String file_path) {
        Connection connection = null;
        try {
            String url = "jdbc:sqlite:" + file_path;
            // conexão
            connection = DriverManager.getConnection(url);
            
            System.out.println("Conexão com SQLite estabelecida.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        return connection;
    }
    
    public static void createNewDatabase(String file_path) {
 
        String url = "jdbc:sqlite:" + file_path;
 
        try (Connection connection = DriverManager.getConnection(url)) {
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("O nome do driver é " + meta.getDriverName());
                System.out.println("Um novo banco de dados foi criado.");
                //Cria a tabela AGENDAS
                try (Statement stmt = connection.createStatement()) {
                    String sql = "CREATE TABLE agendas ("
                            + "id INTEGER PRIMARY KEY, "
                            + "nome TEXT NOT NULL, "
                            + "descricao TEXT);";
                    stmt.execute(sql);
                    System.out.println("A tabela agendas foi criada.");
                    stmt.close();
                }catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                //Cria a tabela EVENTOS
                try (Statement stmt = connection.createStatement()) {
                    String sql = "CREATE TABLE eventos ("
                            + "id INTEGER PRIMARY KEY, "
                            + "agenda_id INTEGER, "
                            + "nome TEXT NOT NULL, "
                            + "descricao TEXT, "
                            + "dia_inteiro INTEGER NOT NULL, "
                            + "data TEXT NOT NULL, "
                            + "hora_inicial TEXT NOT NULL, "
                            + "hora_final TEXT NOT NULL, "
                            + "FOREIGN KEY (agenda_id) REFERENCES agendas (id) "
                            + "ON DELETE CASCADE ON UPDATE NO ACTION);";
                    stmt.execute(sql);
                    System.out.println("A tabela eventos foi criada.");
                    stmt.close();
                }catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                    
                connection.close();
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void createNewDatabase(String file_path, String fileName) {    
        String full_file_path = file_path + fileName;
        createNewDatabase(full_file_path);
    }
      
    /**
     *
     * @param db_path
     * @param agenda_id
     * @return
     */
    public ArrayList allEventos(String db_path, int agenda_id){
        Connection connection;
        PreparedStatement pstmt;
        ResultSet result;
        
        String sql = "SELECT * FROM eventos WHERE agenda_id = ?;";
        
        ArrayList<Evento> eventos = new ArrayList();
        
        try{
            connection = this.connect(db_path);
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, agenda_id);
            result = pstmt.executeQuery();
            
            while (result.next()){
                int id = result.getInt("id");
                String nome = result.getString("nome");
                String descricao = result.getString("descricao");
                boolean dia_inteiro = (result.getInt("dia_inteiro")==1);
                String data = result.getString("data");
                String hora_inicial = result.getString("hora_inicial");
                String hora_final = result.getString("hora_final");
                
                Evento e = new Evento(id, nome, descricao, dia_inteiro, 
                        data, hora_inicial, hora_final);
                
                eventos.add(e);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return eventos;
        
    }
    
    public ArrayList getEventosData(String db_path, int agenda_id, String data){
        Connection connection;
        PreparedStatement pstmt;
        ResultSet result;
        
        String sql = "SELECT * FROM eventos WHERE agenda_id = ? AND data = ?;";
        
        ArrayList<Evento> eventos = new ArrayList();
        try{
            connection = this.connect(db_path);
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, agenda_id);
            pstmt.setString(2, data);
            result = pstmt.executeQuery();
            
            while (result.next()){
                int id = result.getInt("id");
                String nome = result.getString("nome");
                String descricao = result.getString("descricao");
                boolean dia_inteiro = (result.getInt("dia_inteiro")==1);
                //String data = result.getString("data");
                String hora_inicial = result.getString("hora_inicial");
                String hora_final = result.getString("hora_final");
                
                Evento e = new Evento(id, nome, descricao, dia_inteiro, 
                        data, hora_inicial, hora_final);
                
                eventos.add(e);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        Collections.sort(eventos, new SortEventos());
        return eventos;
        
    }
    
    public void saveEvento(String db_path, Evento evento, int agenda_id){
        Connection connection;
        PreparedStatement pstmt;
        
        String sql;
        
        if (evento.getId()==0){
             sql = "INSERT INTO eventos "
                + "(agenda_id, "
                + "nome, "
                + "descricao, "
                + "dia_inteiro, "
                + "data, "
                + "hora_inicial, "
                + "hora_final) "
                + "VALUES(?,?,?,?,?,?,?)";    
        } else {
            sql = "UPDATE eventos SET agenda_id=?, "
                    + "nome=?, "
                    + "descricao=?, "
                    + "dia_inteiro=?, "
                    + "data=?, "
                    + "hora_inicial=?, "
                    + "hora_final=? "
                    + "WHERE id=?";
        }
          
        try{
            connection = this.connect(db_path);
            pstmt = connection.prepareStatement(sql);
            
            pstmt.setInt(1, agenda_id);
            pstmt.setString(2, evento.getNome());
            pstmt.setString(3, evento.getDescricao());
            pstmt.setInt(4, evento.getDiaInteiro()? 1 : 0);
            pstmt.setString(5, evento.getData().toString());
            pstmt.setString(6, evento.getHoraInicial().toString());
            pstmt.setString(7, evento.getHoraFinal().toString());
            if (evento.getId()!=0){pstmt.setInt(8, evento.getId());}
            
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteEvento(String db_path, int id) {
        Connection connection;
        PreparedStatement pstmt;
        
        String sql = "DELETE FROM eventos WHERE id = ?";
 
        try{
            connection = this.connect(db_path);
            pstmt = connection.prepareStatement(sql);
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void insertAgenda(String db_path, Agenda agenda){
        Connection connection;
        PreparedStatement pstmt;
        
        String sql = "INSERT INTO agendas (nome, descricao) VALUES (?,?)";
          
        try{
            connection = this.connect(db_path);
            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, agenda.getNome());
            pstmt.setString(2, agenda.getDescricao());
            
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void printAllAgendas(String db_path){
        Connection connection;
        Statement stmt;
        ResultSet result;
        
        String sql = "SELECT * FROM agendas;";
        
        try{
            connection = this.connect(db_path);
            stmt = connection.createStatement();
            result = stmt.executeQuery(sql);
            
            System.out.println("id      nome        descricao");
            while (result.next()){
                int id = result.getInt("id");
                String nome = result.getString("nome");
                String descricao = result.getString("descricao");
                
                System.out.println(id + "   " + nome + "   " + descricao);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
}
