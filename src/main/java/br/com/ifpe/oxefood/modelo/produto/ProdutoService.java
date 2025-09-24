package br.com.ifpe.oxefood.modelo.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

   @Autowired //injetar automaticamente uma dependencia no atributo se precisar
   private ProdutoRepository repository;

   @Transactional //criar um bloco transacional, ou vai executar tudo ou não executa nada
   public Produto save(Produto produto) {

       produto.setHabilitado(Boolean.TRUE);
       return repository.save(produto); //cadastra no banco
   }

    //listagem controller:
    public List<Produto> listarTodos() {
  
        return repository.findAll(); //SELECT * FROM Produto
    }

    public Produto obterPorID(Long id) {

        return repository.findById(id).get(); //SELECT * FROM Produto Where id = ? 
    }

    //update:
//a partir do id q recebeu, consulta o cliente no banco e altera 
  @Transactional
   public void update(Long id, Produto produtoAlterado) {

      Produto produto = repository.findById(id).get();
      produto.setCodigo(produtoAlterado.getCodigo());
      produto.setTitulo(produtoAlterado.getTitulo());
      produto.setDescricao(produtoAlterado.getDescricao());
      produto.setValorUnitario(produtoAlterado.getValorUnitario());
      produto.setTempoEntregaMaximo(produtoAlterado.getTempoEntregaMaximo());
	  produto.setTempoEntregaMinimo(produtoAlterado.getTempoEntregaMinimo());

      //sem id: update
     //com id:insert
      repository.save(produto);
  }
  


}
