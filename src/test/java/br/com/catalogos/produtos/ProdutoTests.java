package br.com.catalogos.produtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.catalogos.produtos.exceptions.EntidadeNaoEncontradaException;
import br.com.catalogos.produtos.models.Produto;
import br.com.catalogos.produtos.models.dto.ProdutoDTO;
import br.com.catalogos.produtos.models.dto.converter.ProdutoDTOConverter;
import br.com.catalogos.produtos.repository.ProdutoRepository;
import br.com.catalogos.produtos.services.produto.ProdutoService;
import br.com.catalogos.produtos.services.produto.ProdutoServiceImpl;

@ExtendWith(MockitoExtension.class)
class ProdutoTests {

	@Mock
	private ProdutoService pService;

	@Mock
	private ProdutoRepository pRepository;

	@Mock
	private ProdutoDTOConverter pDTOConverter;

	@BeforeEach
	public void iniciar() {
		pService = new ProdutoServiceImpl(pRepository, pDTOConverter);
	}

	@Test
	void deveriaRetornarNenhumRegistroLocalizado() {
		Optional<Produto> pOptional = Optional.ofNullable(null);
		when(this.pRepository.findById(Long.MAX_VALUE)).thenReturn(pOptional);

		EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class,
				() -> this.pService.buscar(Long.MAX_VALUE));

		assertTrue(exception.getMessage().contains("Nenhum registro localizado."));
	}

	@Test
	void deveriaRetornarUmProdutoValido() {
		Produto produto = new Produto();
		produto.setId(1L);
		Optional<Produto> pOptional = Optional.of(produto);
		when(this.pRepository.findById(Long.MAX_VALUE)).thenReturn(pOptional);
		ProdutoDTO dto = pDTOConverter.convert(pOptional.get());
		ProdutoDTO dtoTeste = pService.buscar(Long.MAX_VALUE);

		assertEquals(dto, dtoTeste);
	}
}
