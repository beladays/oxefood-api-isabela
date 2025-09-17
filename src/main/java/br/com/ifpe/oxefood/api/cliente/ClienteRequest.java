//serve pro spring utilizar p converter o json. Transporte de dados
//não possui anotações JPA
package br.com.ifpe.oxefood.api.cliente;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data //gerar get e set dos atributos das classes
@Builder //so funciona se no cliente.java tiver o builder tbm
@NoArgsConstructor
@AllArgsConstructor

public class ClienteRequest {

   private String nome;

   @JsonFormat(pattern = "dd/MM/yyyy") //pra avisar p java q o formato de data é esse
   private LocalDate dataNascimento;

   private String cpf;

   private String foneCelular;

   private String foneFixo;

   //instanciar:
   public Cliente build() { 

       return Cliente.builder()
           .nome(nome)
           .dataNascimento(dataNascimento)
           .cpf(cpf)
           .foneCelular(foneCelular)
           .foneFixo(foneFixo)
           .build();
   }

    
}
