//define os endpoints de cliente
package br.com.ifpe.oxefood.api.cliente;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

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

import br.com.ifpe.oxefood.modelo.acesso.UsuarioService;
import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import br.com.ifpe.oxefood.modelo.cliente.ClienteService;
import br.com.ifpe.oxefood.modelo.cliente.EnderecoCliente;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController //api rest
@RequestMapping("/api/cliente") //url p acessar funções da classe desse controller
//http://localhost:5435/api/cliente =postg
@CrossOrigin //tipo cors, evita erro no react

//descreve o controlador p swagger:
@Tag(
    name = "API Cliente",
    description = "API responsável pelos servidos de cliente no sistema"
)

//

public class ClienteController {

   @Autowired //
   private ClienteService clienteService;

   @Autowired
    private UsuarioService usuarioService;

//descrição da função:
    @Operation(
       summary = "Serviço responsável por salvar um cliente no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por inserir um cliente no sistema."
   )

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody @Valid ClienteRequest clienteRequest, HttpServletRequest request) {

        Cliente cliente = clienteService.save(clienteRequest.build(), usuarioService.obterUsuarioLogado(request));
        return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
    }

   //RequestBody = o json vai vir no body da requisição
    
//listagem:

@Operation(
       summary = "Serviço responsável por listar os clientes no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por listar clientes no sistema."
   )
    @GetMapping 
    public List<Cliente> listarTodos() {
        return clienteService.listarTodos();
    }
@Operation(
       summary = "Serviço responsável por obter um cliente por id no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por listar clientes no sistema."
   )
    @GetMapping("/{id}")
    public Cliente obterPorID(@PathVariable Long id) {
        return clienteService.obterPorID(id);
    }

//update:
//(rota de alterar) passa tbm um json com os dados do cliente alterado
@Operation(
       summary = "Serviço responsável por atualizar os clientes por id no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por listar clientes no sistema."
   )
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable("id") Long id, 
            @RequestBody @Valid ClienteRequest clienteRequest, HttpServletRequest request) {

        clienteService.update(id, clienteRequest.build(), usuarioService.obterUsuarioLogado(request));
        return ResponseEntity.ok().build();
    }
//delete:
//pra ser invocado precisa fazer uma requisição tipo delete, passando o id na url
 //não vai retornar nenhum objeto(void)
 //pathvariable: tem q ser o mesmo do q está entre chaves
 @Operation(
       summary = "Serviço responsável por deletar os clientes por id no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por listar clientes no sistema."
   )
@DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {

       clienteService.delete(id);
       return ResponseEntity.ok().build();
   }

   //Endereço cliente:
   //criar endereço:
   @Operation(
       summary = "Serviço responsável por cadastrar um endereço por id do cliente no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por listar clientes no sistema."
   )
   @PostMapping("/endereco/{clienteId}")
   public ResponseEntity<EnderecoCliente> adicionarEnderecoCliente(@PathVariable("clienteId") Long clienteId, @RequestBody @Valid EnderecoClienteRequest request) {

       EnderecoCliente endereco = clienteService.adicionarEnderecoCliente(clienteId, request.build());
       return new ResponseEntity<EnderecoCliente>(endereco, HttpStatus.CREATED);
   }
  @Operation(
       summary = "Serviço responsável por atualizar um endereço por id do endereço no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por listar clientes no sistema."
   )
    @PutMapping("/endereco/{enderecoId}")
   public ResponseEntity<EnderecoCliente> atualizarEnderecoCliente(@PathVariable("enderecoId") Long enderecoId, @RequestBody EnderecoClienteRequest request) {

       EnderecoCliente endereco = clienteService.atualizarEnderecoCliente(enderecoId, request.build());
       return new ResponseEntity<EnderecoCliente>(endereco, HttpStatus.OK);
   }
     @Operation(
       summary = "Serviço responsável por deletar um endereço por id do endereço no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por listar clientes no sistema."
   )
@DeleteMapping("/endereco/{enderecoId}")
   public ResponseEntity<Void> removerEnderecoCliente(@PathVariable("enderecoId") Long enderecoId) {
       clienteService.removerEnderecoCliente(enderecoId);
       return ResponseEntity.noContent().build();
   }
  
}
