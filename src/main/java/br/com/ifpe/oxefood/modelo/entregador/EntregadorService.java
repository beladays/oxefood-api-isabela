package br.com.ifpe.oxefood.modelo.entregador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class EntregadorService {

   @Autowired //injetar automaticamente uma dependencia no atributo se precisar
   private EntregadorRepository repository;

   @Transactional //criar um bloco transacional, ou vai executar tudo ou não executa nada
   public Entregador save(Entregador entregador) {

       entregador.setHabilitado(Boolean.TRUE);
       return repository.save(entregador); //cadastra no banco
   }

    
}
