//javabin
package br.com.ifpe.oxefood.util.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@MappedSuperclass //
@EntityListeners(AuditingEntityListener.class) //preencher automaticamente atributos

public abstract class EntidadeAuditavel extends EntidadeNegocio { //subclasse de entidadenegocio (herda os atributos de entidadenegocio)
    
   @JsonIgnore //atributo não ser retornado nas consultas
   @Version
   private Long versao;

   @JsonIgnore
   @CreatedDate
   private LocalDate dataCriacao;

   @JsonIgnore
   @LastModifiedDate
   private LocalDate dataUltimaModificacao;

    @CreatedBy
    @ManyToOne
    @JoinColumn
    private Usuario criadoPor; // Id do usuário que o criou

    @LastModifiedBy
    @ManyToOne
    @JoinColumn
    private Usuario ultimaModificacaoPor; // Id do usuário que fez a última alteração

}
