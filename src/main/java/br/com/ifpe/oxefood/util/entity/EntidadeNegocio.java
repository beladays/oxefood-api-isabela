package br.com.ifpe.oxefood.util.entity;

import java.io.Serializable;

import lombok.EqualsAndHashCode; // método equals == compara duas variáveis, HashCode == 
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = { "id" })  
public abstract class EntidadeNegocio implements Serializable { //Serializable == interface

    private Long id;

    private Boolean habilitado;
    
}
