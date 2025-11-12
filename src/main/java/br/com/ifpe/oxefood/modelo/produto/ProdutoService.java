package br.com.ifpe.oxefood.modelo.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.ifpe.oxefood.util.Util;
import br.com.ifpe.oxefood.util.exception.ProdutoException;
import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

   @Autowired //injetar automaticamente uma dependencia no atributo se precisar
   private ProdutoRepository repository;

   @Transactional //criar um bloco transacional, ou vai executar tudo ou não executa nada
   public Produto save(Produto produto) {
    //excessão:
   if (produto.getValorUnitario() < 20 ) {
	    throw new ProdutoException(ProdutoException.MSG_VALOR_MINIMO_PRODUTO);
	}
    if (produto.getValorUnitario() > 100 ) {
	    throw new ProdutoException(ProdutoException.MSG_VALOR_MINIMO_PRODUTO);
	}
    //
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
      produto.setCategoria(produtoAlterado.getCategoria());
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
  
  @Transactional
   public void delete(Long id) {

       Produto produto = repository.findById(id).get();
       produto.setHabilitado(Boolean.FALSE);

       repository.save(produto);
   }

  public List<Produto> filtrar(String codigo, String titulo, Long idCategoria) {

        List<Produto> listaProdutos = repository.findAll();

        if ((codigo != null && !"".equals(codigo)) &&
            (titulo == null || "".equals(titulo)) &&
            (idCategoria == null)) {

                listaProdutos = repository.consultarPorCodigo(codigo);

        } else if (
            (codigo == null || "".equals(codigo)) &&
            (titulo != null && !"".equals(titulo)) &&
            (idCategoria == null)) {

                listaProdutos = repository.findByTituloContainingIgnoreCaseOrderByTituloAsc(titulo);
        
        } else if (
            (codigo == null || "".equals(codigo)) &&
            (titulo == null || "".equals(titulo)) &&
            (idCategoria != null)) {

                listaProdutos = repository.consultarPorCategoria(idCategoria);

        } else if (
            (codigo == null || "".equals(codigo)) &&
            (titulo != null && !"".equals(titulo)) &&
            (idCategoria != null)) {

                listaProdutos = repository.consultarPorTituloECategoria(titulo, idCategoria); 
        }

        return listaProdutos;
    }

//salvar imagem 
@Transactional
public Produto saveImage(Long id, MultipartFile imagem) {

Produto produto = obterPorID(id); //consulta o produto por id

     String imagemUpada = Util.fazerUploadImagem(imagem); //chama o metodo fazer upload img

     if (imagemUpada != null) {
     	produto.setImagem(imagemUpada);
     }

     return save(produto);
}


}

