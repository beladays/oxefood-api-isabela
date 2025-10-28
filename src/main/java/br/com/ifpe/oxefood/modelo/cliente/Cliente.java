//especificar atributos e tabela cliente, classe estrutura
package br.com.ifpe.oxefood.modelo.cliente;
 
import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.SQLRestriction;

import br.com.ifpe.oxefood.modelo.acesso.Usuario;
import br.com.ifpe.oxefood.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
import org.hibernate.annotations.FetchMode;

//anotações do JPA (serve p mapear atributos no banco):
@Entity //classifica a classe como entidade
@Table(name = "Cliente") //determinar q sera criado uma tabela com esse nome
@SQLRestriction("habilitado = true") //serve pra acresentar uma clausula de filtro, e pode fazer uma exclusão lógica

//anotações do lombok
@Builder //intanciar objetos
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Cliente extends EntidadeAuditavel  {

   @OneToOne //relaçãod e cliente e usuário (1 cliente poderá ter 1 usuário)
   @JoinColumn(nullable = false)
   private Usuario usuario;

   @OneToMany(mappedBy = "cliente", orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
   private List<EnderecoCliente> enderecos;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;
  
   @Column (nullable = false, length = 100) //acresenta no BANCO uma validação (if) 
   //tem q informar o nome e max 100
   private String nome;

   @Column
   private LocalDate dataNascimento;

   @Column (unique = true) //n permite q tenha 2 clientes nesse banco com o mesmo cpf
   private String cpf;

   @Column
   private String foneCelular;

   @Column
   private String foneFixo;


}
