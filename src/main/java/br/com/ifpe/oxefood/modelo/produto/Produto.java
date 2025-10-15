//especificar atributos e tabela produto, classe estrutura
package br.com.ifpe.oxefood.modelo.produto;

import org.hibernate.annotations.SQLRestriction;

import br.com.ifpe.oxefood.modelo.categoriaProduto.CategoriaProduto;
import br.com.ifpe.oxefood.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//anotações do JPA:

@Entity //classifica a classe como entidade
@Table(name = "Produto") //determinar q sera criado uma tabela com esse nome
@SQLRestriction("habilitado = true") //serve pra acresentar uma clausula de filtro, e pode fazer uma exclusão lógica

//anotações do lombok
@Builder //intanciar objetos
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Produto extends EntidadeAuditavel  {
  
   @ManyToOne
   private CategoriaProduto categoria;

   @Column (nullable = false)
   private String codigo;

   @Column (nullable = false)
   private String titulo;

   @Column
   private String descricao;

   private Double valorUnitario;

   @Column
   private Integer tempoEntregaMinimo;

   @Column
   private Integer tempoEntregaMaximo;


}
