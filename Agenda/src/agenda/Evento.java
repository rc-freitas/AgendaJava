/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;
import java.io.Serializable;
import java.time.*;
//import java.util.*;

/**
 *
 * @author aluno
 */
public class Evento implements Serializable{
    
    private int id;
    
    private String nome;
    private String descricao;
    
    private Boolean dia_inteiro;

    private LocalDate data;
    private LocalTime hora_inicial;
    private LocalTime hora_final;
    
    
    public Evento(){
        throw new RuntimeException("É preciso definir tipo, data, horários");
    }
    
    public Evento(int id, String nome, String descricao,  Boolean dia_inteiro, 
            String data, String hora_inicial,  String hora_final){
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dia_inteiro = dia_inteiro;
        
        this.data = LocalDate.parse(data);
        
        LocalTime hora_i = LocalTime.parse(hora_inicial);
        LocalTime hora_f = LocalTime.parse(hora_final);
        
        if (hora_f.isAfter(hora_i)){
            this.hora_inicial = LocalTime.parse(hora_inicial);
            this.hora_final = LocalTime.parse(hora_final);
        } else {
            throw new IllegalArgumentException("O horário de termino do evento não pode ser enterior ao horario de início.");
        }
    }
    
    public Evento(String nome, String descricao,  Boolean dia_inteiro, 
            String data, String hora_inicial,  String hora_final){
        this.nome = nome;
        this.descricao = descricao;
        this.dia_inteiro = dia_inteiro;
        
        this.data = LocalDate.parse(data);
        
        LocalTime hora_i = LocalTime.parse(hora_inicial);
        LocalTime hora_f = LocalTime.parse(hora_final);
        
        if (hora_f.isAfter(hora_i)){
            this.hora_inicial = LocalTime.parse(hora_inicial);
            this.hora_final = LocalTime.parse(hora_final);
        } else {
            throw new IllegalArgumentException("O horário de termino do evento não pode ser enterior ao horario de início.");
        }
    }
        
    public Evento(String nome, String descricao,  String data, 
            String hora_inicial,  String hora_final){
        this.nome = nome;
        this.descricao = descricao;
        this.dia_inteiro = false;
        
        this.data = LocalDate.parse(data);
        
        LocalTime hora_i = LocalTime.parse(hora_inicial);
        LocalTime hora_f = LocalTime.parse(hora_final);
        
        if (hora_f.isAfter(hora_i)){
            this.hora_inicial = LocalTime.parse(hora_inicial);
            this.hora_final = LocalTime.parse(hora_final);
        } else {
            throw new IllegalArgumentException("O horário de termino do evento não pode ser enterior ao horario de início.");
        }
    }
    
    public Evento(String nome, String data, String hora_inicial, String hora_final){
        this.nome = nome;
        this.dia_inteiro = false;
        
        this.data = LocalDate.parse(data);
        
        LocalTime hora_i = LocalTime.parse(hora_inicial);
        LocalTime hora_f = LocalTime.parse(hora_final);
        
        if (hora_f.isAfter(hora_i)){
            this.hora_inicial = LocalTime.parse(hora_inicial);
            this.hora_final = LocalTime.parse(hora_final);
        } else {
            throw new IllegalArgumentException("O horário de termino do evento não pode ser enterior ao horario de início.");
        }
    }
    
    public Evento(String nome, String descricao,  String data){
        this.nome = nome;
        this.descricao = descricao;
        this.dia_inteiro = true;
        
        this.data = LocalDate.parse(data);
        
        this.hora_inicial = LocalTime.of(0,0);
        this.hora_final = LocalTime.of(23,59,59);
    }
    
    public Evento(String nome,  String data){
        this.nome = nome;
        this.dia_inteiro = true;
        
        this.data = LocalDate.parse(data);
        
        this.hora_inicial = LocalTime.of(0,0);
        this.hora_final = LocalTime.of(23,59,59);
    }
    
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public boolean getDiaInteiro(){
        return this.dia_inteiro;
    }
            
    public void setDiaInteiro(){
        if (!this.dia_inteiro){
            this.hora_inicial = LocalTime.of(0,0);
            this.hora_final = LocalTime.of(23,59,59);
            this.dia_inteiro = true;
        }
    }
    
    public void setEvento(String hora_inicial, String hora_final){
        
        if (this.dia_inteiro){
            setHora(hora_inicial, hora_final);
        }
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    public void setData(String data){
        this.data = LocalDate.parse(data);
    }
    
    public void setHora(String hora_inicial, String hora_final){
        LocalTime hora_i = LocalTime.parse(hora_inicial);
        LocalTime hora_f = LocalTime.parse(hora_final);
        
        if (hora_f.isAfter(hora_i)){
            this.hora_inicial = LocalTime.parse(hora_inicial);
            this.hora_final = LocalTime.parse(hora_final);
        } else {
            throw new IllegalArgumentException("O horário de termino do evento não pode ser enterior ao horario de início.");
        }
    }
    
    public void setHoraInicial(String hora_inicial){
        this.hora_inicial = LocalTime.parse(hora_inicial);
        
        LocalTime hora_i = LocalTime.parse(hora_inicial);
        
        if (this.hora_final.isAfter(hora_i)){
            this.hora_inicial = LocalTime.parse(hora_inicial);
        } else {
            throw new IllegalArgumentException("O horário de termino do evento não pode ser enterior ao horario de início.");
        }
    }
    
    public void setHoraFinal(String hora_final){
        this.hora_final = LocalTime.parse(hora_final);
        
        LocalTime hora_f = LocalTime.parse(hora_final);
        
        if (hora_f.isAfter(this.hora_inicial)){
            this.hora_final = LocalTime.parse(hora_final);
        } else {
            throw new IllegalArgumentException("O horário de termino do evento não pode ser enterior ao horario de início.");
        }
    }
    
    public int getId(){
        return this.id;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public Boolean isDiaInteiro(){
        return this.dia_inteiro;
    }
    
    public LocalDate getData(){
        return this.data;
    }
   
    public LocalTime getHoraInicial(){
        return this.hora_inicial;
    }
    
    public LocalTime getHoraFinal(){
        return this.hora_final;
    }
    
    public Long getDuracao(){
        Long duracao = Duration.between(this.hora_inicial, this.hora_final).toHours();
        return duracao;
    }
}



