//define os endpoints de cliente
package br.com.ifpe.oxefood.api.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefood.modelo.produto.Produto;
import br.com.ifpe.oxefood.modelo.produto.ProdutoService;


@RestController //api rest
@RequestMapping("/api/produto") //url p acessar funções da classe desse controller
//http://localhost:5435/api/produto =postman
@CrossOrigin

public class ProdutoController {

   @Autowired //
   private ProdutoService produtoService;

   @PostMapping
   public ResponseEntity<Produto> save(@RequestBody ProdutoRequest request) {
//RequestBody = o json vai vir no body da requisição
       Produto produto = produtoService.save(request.build());
       return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);
   }
//listagem:
    @GetMapping 
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Produto obterPorID(@PathVariable Long id) {
        return produtoService.obterPorID(id);
    }

//update:
//(rota de alterar) passa tbm um json com os dados do cliente alterado
@PutMapping("/{id}")
 public ResponseEntity<Produto> update(@PathVariable("id") Long id, @RequestBody ProdutoRequest request) {

       produtoService.update(id, request.build());
       return ResponseEntity.ok().build();
 }

//delete:
//pra ser invocado precisa fazer uma requisição tipo delete, passando o id na url
 //não vai retornar nenhum objeto(void)
 //pathvariable: tem q ser o mesmo do q está entre chaves
@DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {

       produtoService.delete(id);
       return ResponseEntity.ok().build();
   }

    
}
