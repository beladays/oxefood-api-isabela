package br.com.ifpe.oxefood.modelo.categoriaProduto;
import org.hibernate.annotations.SQLRestriction;
import br.com.ifpe.oxefood.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//anotações do JPA:
@Entity //classifica a classe como entidade
@Table(name = "CategoriaProduto") //determinar q sera criado uma tabela com esse nome
@SQLRestriction("habilitado = true") //serve pra acresentar uma clausula de filtro, e pode fazer uma exclusão lógica

//anotações do lombok
@Builder //intanciar objetos
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CategoriaProduto extends EntidadeAuditavel {

    @Column
    private String descricao;
    
}
