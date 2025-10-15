//serve pro spring utilizar p converter o json. Transporte de dados
//não possui anotações JPA
package br.com.ifpe.oxefood.api.entregador;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.entregador.Entregador;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data //gerar get e set dos atributos das classes
@Builder //so funciona se no entregador.java tiver o builder tbm
@NoArgsConstructor
@AllArgsConstructor

public class EntregadorRequest {

   @NotNull(message = "O Nome é de preenchimento obrigatório")
   @NotEmpty(message = "O Nome é de preenchimento obrigatório")
   private String nome;

   private String cpf;

   private String rg;

   @JsonFormat(pattern = "dd/MM/yyyy") //pra avisar p java q o formato de data é esse
   private LocalDate dataNascimento;

   private String foneCelular;

   private String foneFixo;

   private Integer qtdEntregasRealizadas;

   private Double valorFrete;

   @NotNull(message = "A rua é de preenchimento obrigatório")
   @NotEmpty(message = "A rua é de preenchimento obrigatório")
   private String enderecoRua;

   private String enderecoComplemento;

   private String enderecoNumero;

   @NotNull(message = "O Bairro é de preenchimento obrigatório")
   @NotEmpty(message = "O Bairro é de preenchimento obrigatório")
   private String enderecoBairro;

   private String enderecoCidade;

   private String enderecoCep;

   private String enderecoUf;

   private Boolean ativo;

   //instanciar:
   public Entregador build() { 

       return Entregador.builder()
           .nome(nome)
           .cpf(cpf)
           .rg(rg)
           .dataNascimento(dataNascimento)
           .foneCelular(foneCelular)
           .foneFixo(foneFixo)
           .qtdEntregasRealizadas(qtdEntregasRealizadas)
           .valorFrete(valorFrete)
           .enderecoRua(enderecoRua)
           .enderecoComplemento(enderecoComplemento)
           .enderecoNumero(enderecoNumero)
           .enderecoBairro(enderecoBairro)
           .enderecoCidade(enderecoCidade)
           .enderecoCep(enderecoCep)
           .enderecoUf(enderecoUf)
           .ativo(ativo)
           .build();
   }

    
}
