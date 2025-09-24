package br.com.ifpe.oxefood.modelo.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class ClienteService {

   @Autowired //injetar automaticamente uma dependencia no atributo se precisar
   private ClienteRepository repository;

   @Transactional //criar um bloco transacional, ou vai executar tudo ou não executa nada
   public Cliente save(Cliente cliente) {

       cliente.setHabilitado(Boolean.TRUE);
       return repository.save(cliente); //cadastra no banco
   }

//listagem controller:
    public List<Cliente> listarTodos() {
  
        return repository.findAll(); //SELECT * FROM Cliente
    }

    public Cliente obterPorID(Long id) {

        return repository.findById(id).get(); //SELECT * FROM Ciente Where id = ? 
    }
//update:
//a partir do id q recebeu, consulta o cliente no banco e altera 
  @Transactional
   public void update(Long id, Cliente clienteAlterado) {

      Cliente cliente = repository.findById(id).get();
      cliente.setNome(clienteAlterado.getNome());
      cliente.setDataNascimento(clienteAlterado.getDataNascimento());
      cliente.setCpf(clienteAlterado.getCpf());
      cliente.setFoneCelular(clienteAlterado.getFoneCelular());
      cliente.setFoneFixo(clienteAlterado.getFoneFixo());
	  
      //sem id: update
     //com id:insert
      repository.save(cliente);
  }
  


    
}
