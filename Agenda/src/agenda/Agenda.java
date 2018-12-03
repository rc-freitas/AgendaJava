/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.Collections;

/*
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
*/

/**
 *
 * @author aluno
 */
public class Agenda implements Serializable{
    
    private String nome;
    private String descricao;
    private ArrayList<Evento> eventos = new ArrayList();
    
    
    public Agenda(){}
    
    public Agenda(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }
    
    public Agenda(String nome){
        this.nome = nome;
        this.descricao = "";
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public boolean addEvento(Evento evento){
        boolean adicionado = this.eventos.add(evento);
        Collections.sort(this.eventos, new SortEventos());
        return adicionado;
    }
    
    public void setEventos(ArrayList<Evento> eventos){
        this.eventos = eventos;
    }
    
    public void getAllEventos(){
        for( Evento e : this.eventos){
            System.out.println("Evento: " + e.getNome());
            String tipo = "evento";
            if (e.isDiaInteiro()){
                tipo = "dia inteiro";
            }
            System.out.println("Tipo: " + tipo);
            System.out.println("Descrição: " + e.getDescricao());
            System.out.println("De: " + e.getData() + " às " + e.getHoraInicial());
            System.out.println("Até: " + e.getData() + " às " + e.getHoraFinal());
            System.out.println("--------------------------------------");
        }
    }
    
    public boolean removeEvento(Evento evento){
        try{
            boolean removido = this.eventos.remove(evento);
            Collections.sort(this.eventos, new SortEventos());
            return removido;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /*
    public boolean salvarAgenda(){
        boolean salvo = false;
        try {
            FileOutputStream outFile = new FileOutputStream("./agenda.dat");
            ObjectOutputStream s = new ObjectOutputStream(outFile);
            s.writeObject(this);
            s.close();
            System.out.println("Agenda salva com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ocorreu um erro durante o salvamento.");
        }
        return salvo;
    }
    */   
}
