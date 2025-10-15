//serve pro spring utilizar p converter o json. Transporte de dados
//não possui anotações JPA

package br.com.ifpe.oxefood.api.produto;


import br.com.ifpe.oxefood.modelo.produto.Produto;
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

public class ProdutoRequest {

   @NotNull(message = "O código é de preenchimento obrigatório")
   @NotEmpty(message = "O código é de preenchimento obrigatório")
   private String codigo;
   
   @NotNull(message = "O título é de preenchimento obrigatório")
   @NotEmpty(message = "O título é de preenchimento obrigatório")
   private String titulo;
   
   private Double valorUnitario;

   private Long idCategoria;

   private String descricao;

   private Integer tempoEntregaMinimo;

   private Integer tempoEntregaMaximo;

   //instanciar:
   public Produto build() { 

       return Produto.builder()
           .codigo(codigo)
           .titulo(titulo)
           .descricao(descricao)
           .valorUnitario(valorUnitario)
           .tempoEntregaMinimo(tempoEntregaMinimo)
           .tempoEntregaMaximo(tempoEntregaMaximo)
           .build();
   }

    
}
