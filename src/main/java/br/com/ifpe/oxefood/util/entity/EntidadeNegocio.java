package br.com.ifpe.oxefood.util.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode; // método equals == compara duas variáveis, HashCode == 
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = { "id" })  
@MappedSuperclass
public abstract class EntidadeNegocio implements Serializable { //Serializable == interface

    @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE) //regra utilizada p preencher o id. SEQUENCE: variavel q controla o valor e incrementa
   private Long id;

   @JsonIgnore
   @Column
   private Boolean habilitado;

    
}
