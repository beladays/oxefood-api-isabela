//serve pro spring utilizar p converter o json. Transporte de dados
//não possui anotações JPA
package br.com.ifpe.oxefood.api.cliente;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data //gerar get e set dos atributos das classes
@Builder //so funciona se no cliente.java tiver o builder tbm
@NoArgsConstructor
@AllArgsConstructor

public class ClienteRequest {
    
   @NotNull(message = "O Nome é de preenchimento obrigatório")
   @NotEmpty(message = "O Nome é de preenchimento obrigatório")
   @Length(max = 100, message = "O Nome deverá ter no máximo {max} caracteres") //verifica tamanho max de uma string


   private String nome;

   @JsonFormat(pattern = "dd/MM/yyyy") //pra avisar p java q o formato de data é esse
   private LocalDate dataNascimento;

   @NotBlank(message = "O CPF é de preenchimento obrigatório")
   @CPF
   private String cpf;

   @Length(min = 8, max = 20, message = "O campo Fone tem que ter entre {min} e {max} caracteres")
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
