package br.com.ifpe.oxefood.modelo.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;

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

}
