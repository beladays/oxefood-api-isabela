package br.com.ifpe.oxefood.api.categoriaProduto;
import br.com.ifpe.oxefood.modelo.categoriaProduto.CategoriaProduto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data //gerar get e set dos atributos das classes
@Builder //so funciona se no cliente.java tiver o builder tbm
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaProdutoRequest {

     private String descricao;

     public CategoriaProduto build() { 

       return CategoriaProduto.builder()
           .descricao(descricao)
           .build();
           
   }
}
