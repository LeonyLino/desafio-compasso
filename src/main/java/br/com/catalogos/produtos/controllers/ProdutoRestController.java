package br.com.catalogos.produtos.controllers;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.catalogos.produtos.exceptions.EntidadeNaoEncontradaException;
import br.com.catalogos.produtos.models.Erro;
import br.com.catalogos.produtos.models.dto.ProdutoDTO;
import br.com.catalogos.produtos.services.produto.ProdutoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProdutoRestController {

	private final ProdutoService pService;

	@PostMapping
	public ResponseEntity<ProdutoDTO> salvar(@RequestBody @Valid ProdutoDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pService.salvar(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> editar(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
		try {
			dto.setId(id);
			return ResponseEntity.status(HttpStatus.OK).body(pService.editar(dto));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new Erro(HttpStatus.NOT_FOUND.value(), e.getMessage()));
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDTO> buscar(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(pService.buscar(id));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping
	public List<ProdutoDTO> listar() {
		return pService.listarTodos();
	}

	@GetMapping("/search")
	public List<ProdutoDTO> listarPorFiltros(@RequestParam(value = "q", required = false) String q,
			@RequestParam(value = "min_price", required = false) BigDecimal minPrice,
			@RequestParam(value = "max_price", required = false) BigDecimal maxPrice) {
		return pService.buscarPorFiltros(q, minPrice, maxPrice);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> deletar(@PathVariable Long id) {
		try {
			pService.deletarPorId(id);
			return ResponseEntity.ok("ok");
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
