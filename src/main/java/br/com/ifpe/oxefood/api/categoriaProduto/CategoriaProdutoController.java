package br.com.ifpe.oxefood.api.categoriaProduto;
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


import br.com.ifpe.oxefood.modelo.categoriaProduto.CategoriaProduto;
import br.com.ifpe.oxefood.modelo.categoriaProduto.CategoriaProdutoService;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;


@RestController //api rest
@RequestMapping("/api/categoriaProduto") //url p acessar funções da classe desse controller
//http://localhost:5435/api/cliente =postg
@CrossOrigin //tipo cors, evita erro no react


public class CategoriaProdutoController {
     @Autowired //
   private CategoriaProdutoService categoriaProdutoService;
  @Operation(
       summary = "Serviço responsável por cadastrar uma categoria de produto no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por listar clientes no sistema."
   )
   @PostMapping
   public ResponseEntity<CategoriaProduto> save(@RequestBody CategoriaProdutoRequest request) {
//RequestBody = o json vai vir no body da requisição
       CategoriaProduto categoriaProduto = categoriaProdutoService.save(request.build());
       return new ResponseEntity<CategoriaProduto>(categoriaProduto, HttpStatus.CREATED);
   }
   
//listagem:
  @Operation(
       summary = "Serviço responsável por listar todas as categorias de produto no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por listar clientes no sistema."
   )
    @GetMapping 
    public List<CategoriaProduto> listarTodos() {
        return categoriaProdutoService.listarTodos();
    }
  @Operation(
       summary = "Serviço responsável por listar uma categoria de produto por id do cliente no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por listar clientes no sistema."
   )
    @GetMapping("/{id}")
    public CategoriaProduto obterPorID(@PathVariable Long id) {
        return categoriaProdutoService.obterPorID(id);
    }

//update:
//(rota de alterar) passa tbm um json com os dados do cliente alterado
  @Operation(
       summary = "Serviço responsável por alterar uma categoria de produto por id no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por listar clientes no sistema."
   )
@PutMapping("/{id}")
 public ResponseEntity<CategoriaProduto> update(@PathVariable("id") Long id, @RequestBody CategoriaProdutoRequest request) {

       categoriaProdutoService.update(id, request.build());
       return ResponseEntity.ok().build();
 }
//delete:
//pra ser invocado precisa fazer uma requisição tipo delete, passando o id na url
 //não vai retornar nenhum objeto(void)
 //pathvariable: tem q ser o mesmo do q está entre chaves

   @Operation(
       summary = "Serviço responsável por deletar uma categoria de produto por id do cliente no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por listar clientes no sistema."
   )
@DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {

       categoriaProdutoService.delete(id);
       return ResponseEntity.ok().build();
   }

}
