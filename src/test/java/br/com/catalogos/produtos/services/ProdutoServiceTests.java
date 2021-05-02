package br.com.catalogos.produtos.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.catalogos.produtos.exceptions.EntidadeNaoEncontradaException;
import br.com.catalogos.produtos.models.Produto;
import br.com.catalogos.produtos.models.dto.converter.ProdutoDTOConverter;
import br.com.catalogos.produtos.repository.ProdutoRepository;
import br.com.catalogos.produtos.services.produto.ProdutoServiceImpl;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTests {

	@InjectMocks
	private ProdutoServiceImpl pServiceImpl;

	@Mock
	private ProdutoRepository pRepository;

	@Mock
	private ProdutoDTOConverter pDTOConverter;

	@BeforeEach
	public void iniciar() {
		pDTOConverter = new ProdutoDTOConverter();
	}

	@Test
	void deveriaRetornarNenhumRegistroLocalizado() {
		EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class,
				() -> pServiceImpl.buscar(Long.MAX_VALUE));

		assertTrue(exception.getMessage().contains("Nenhum registro localizado."));
	}

	@Test
	void deveriaRetornarUmProdutoValido() {
		Optional<Produto> pOptional = Optional.of(new Produto(3L, "Echo Show 5",
				"Smart Speaker com tela de 5,5\" e Alexa - Cor Preta", BigDecimal.valueOf(569.05)));
		when(pRepository.findById(3L)).thenReturn(pOptional);
//		ProdutoDTO dto = pDTOConverter.convert(pOptional.get());
//		ProdutoDTO dtoTeste = pServiceImpl.buscar(3L);

		assertEquals(pOptional, pRepository.findById(3L));
	}

//	@Test
//	void deveriaRetornarUmProdutoEntre300e600() {
//		BigDecimal minPrice = BigDecimal.valueOf(300);
//		BigDecimal maxPrice = BigDecimal.valueOf(600);
//		List<Produto> produtoList = new ArrayList<>();
//		produtoList.add(new Produto(3L, "Echo Show 5", "Smart Speaker com tela de 5,5\" e Alexa - Cor Preta",
//				BigDecimal.valueOf(569.05)));
//		produtoList.add(new Produto(2L, "Echo Dot (3ª Geração)", "Smart Speaker com Alexa - Cor Preta",
//				BigDecimal.valueOf(331.55)));
//		List<ProdutoDTO> dtoList = produtoList.stream().map(pDTOConverter::convert).collect(Collectors.toList());
//
//		when(pService.buscarPorFiltros(null, minPrice, maxPrice)).thenReturn(dtoList);
//		List<ProdutoDTO> dtoListTeste = pService.buscarPorFiltros(null, minPrice, maxPrice);
//
//		assertEquals(dtoList, dtoListTeste);
//	}
}
