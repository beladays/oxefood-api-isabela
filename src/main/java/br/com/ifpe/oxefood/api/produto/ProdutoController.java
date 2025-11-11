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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefood.modelo.categoriaProduto.CategoriaProdutoService;
import br.com.ifpe.oxefood.modelo.produto.Produto;
import br.com.ifpe.oxefood.modelo.produto.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;


@RestController //api rest
@RequestMapping("/api/produto") //url p acessar funções da classe desse controller
//http://localhost:5435/api/produto =postman
@CrossOrigin

public class ProdutoController {

   @Autowired //
   private ProdutoService produtoService;

   @Autowired
   private CategoriaProdutoService categoriaProdutoService;

@Operation(
       summary = "Serviço responsável por cadastrar um produto no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por listar clientes no sistema."
   )
   @PostMapping
   public ResponseEntity<Produto> save(@RequestBody @Valid ProdutoRequest request) {
//RequestBody = o json vai vir no body da requisição
       Produto produtoNovo = request.build();
       produtoNovo.setCategoria(categoriaProdutoService.obterPorID(request.getIdCategoria()));
       Produto produto = produtoService.save(produtoNovo);

       return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);

   }
//listagem:
@Operation(
       summary = "Serviço responsável por listar todos os produtos no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por listar clientes no sistema."
   )
    @GetMapping 
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }
@Operation(
       summary = "Serviço responsável por listar um produto por id no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por listar clientes no sistema."
   )
    @GetMapping("/{id}")
    public Produto obterPorID(@PathVariable Long id) {
        return produtoService.obterPorID(id);
    }

//update:
//(rota de alterar) passa tbm um json com os dados do cliente alterado

@Operation(
       summary = "Serviço responsável por atualizar um produto por id no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por listar clientes no sistema."
   )
@PutMapping("/{id}")
 public ResponseEntity<Produto> update(@PathVariable("id") Long id, @RequestBody @Valid ProdutoRequest  request) {
       Produto produto = request.build();
       produto.setCategoria(categoriaProdutoService.obterPorID(request.getIdCategoria()));
       produtoService.update(id, produto);

       return ResponseEntity.ok().build();
 }

//delete:
//pra ser invocado precisa fazer uma requisição tipo delete, passando o id na url
 //não vai retornar nenhum objeto(void)
 //pathvariable: tem q ser o mesmo do q está entre chaves

 @Operation(
       summary = "Serviço responsável por deletar um produto por id no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por listar clientes no sistema."
   )
@DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {

       produtoService.delete(id);
       return ResponseEntity.ok().build();
   }

 //filtrar:
 @Operation(
       summary = "Serviço responsável por filtrar os produtos.",
       description = "Exemplo de descrição de um endpoint responsável por listar clientes no sistema."
   )
 @PostMapping("/filtrar")
   public List<Produto> filtrar(
           @RequestParam(value = "codigo", required = false) String codigo,
           @RequestParam(value = "titulo", required = false) String titulo,
           @RequestParam(value = "idCategoria", required = false) Long idCategoria) {

       return produtoService.filtrar(codigo, titulo, idCategoria);
   }
   
}
