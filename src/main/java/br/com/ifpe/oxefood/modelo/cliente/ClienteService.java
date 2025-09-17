package br.com.ifpe.oxefood.modelo.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {

   @Autowired //injetar automaticamente uma dependencia no atributo se precisar
   private ClienteRepository repository;

   @Transactional //criar um bloco transacional, ou vai executar tudo ou não executa nada
   public Cliente save(Cliente cliente) {

       cliente.setHabilitado(Boolean.TRUE);
       return repository.save(cliente); //cadastra no banco
   }

    
}
