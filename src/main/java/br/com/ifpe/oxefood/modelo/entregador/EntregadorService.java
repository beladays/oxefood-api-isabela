package br.com.ifpe.oxefood.modelo.entregador;

import java.util.List;

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
//listagem controller:
    public List<Entregador> listarTodos() {
  
        return repository.findAll(); //SELECT * FROM Entregador
    }

    public Entregador obterPorID(Long id) {

        return repository.findById(id).get(); //SELECT * FROM Entregador Where id = ? 
    }

    //update:
//a partir do id q recebeu, consulta o cliente no banco e altera 
  @Transactional
   public void update(Long id, Entregador entregadorAlterado) {

      Entregador entregador = repository.findById(id).get();
      entregador.setNome(entregadorAlterado.getNome());
      entregador.setCpf(entregadorAlterado.getCpf());
      entregador.setRg(entregadorAlterado.getRg());
      entregador.setDataNascimento(entregadorAlterado.getDataNascimento());
      entregador.setCpf(entregadorAlterado.getCpf());
      entregador.setFoneCelular(entregadorAlterado.getFoneCelular());
      entregador.setFoneFixo(entregadorAlterado.getFoneFixo());
	  entregador.setQtdEntregasRealizadas(entregadorAlterado.getQtdEntregasRealizadas());
      entregador.setEnderecoRua(entregadorAlterado.getEnderecoRua());
      entregador.setEnderecoComplemento(entregadorAlterado.getEnderecoComplemento());
      entregador.setEnderecoNumero(entregadorAlterado.getEnderecoNumero());
      entregador.setEnderecoBairro(entregadorAlterado.getEnderecoBairro());
      entregador.setEnderecoCidade(entregadorAlterado.getEnderecoCidade());
      entregador.setEnderecoCep(entregadorAlterado.getEnderecoCep());
      entregador.setEnderecoUf(entregadorAlterado.getEnderecoUf());
      entregador.setAtivo(entregadorAlterado.getAtivo());
      //sem id: update
     //com id:insert
      repository.save(entregador);
  }
  
//delete:
  //remoção lógica
  //sethabilitado: pra ver se está vivo ou não 
  //save = inclui(sem id) ou altera(com id)
  @Transactional
   public void delete(Long id) {

       Entregador entregador = repository.findById(id).get();
       entregador.setHabilitado(Boolean.FALSE);

       repository.save(entregador);
   }



    
}
