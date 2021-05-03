package br.com.catalogos.produtos.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.catalogos.produtos.exceptions.EntidadeNaoEncontradaException;
import br.com.catalogos.produtos.models.Produto;
import br.com.catalogos.produtos.models.dto.ProdutoDTO;
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

	@Test
	void deveriaRetornarNenhumRegistroLocalizado() {
		EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class,
				() -> pServiceImpl.buscar(Long.MIN_VALUE));

		assertTrue(exception.getMessage().contains("Nenhum registro localizado."));
	}

	@Test
	void deveriaRetornarUmProdutoValido() {
		Optional<Produto> pOptional = Optional.of(new Produto(3L, "Echo Show 5",
				"Smart Speaker com tela de 5,5\" e Alexa - Cor Preta", BigDecimal.valueOf(569.05)));
		when(pRepository.findById(Mockito.anyLong())).thenReturn(pOptional);
		ProdutoDTO dto = this.convert(pOptional.get());

		when(pDTOConverter.convert(pOptional.get())).thenReturn(dto);
		ProdutoDTO dtoTeste = pServiceImpl.buscar(Mockito.anyLong());

		assertEquals(dto, dtoTeste);
	}

	@Test
	void deveriaDeletarUmProdutoValido() {
		ProdutoDTO pDTO = ProdutoDTO.builder().build();
		Produto expectedDeletedProduto = new Produto();

		when(pRepository.findById(pDTO.getId())).thenReturn(Optional.of(expectedDeletedProduto));
		doNothing().when(pRepository).deleteById(pDTO.getId());

		pServiceImpl.deletarPorId(pDTO.getId());

		verify(pRepository, times(1)).findById(pDTO.getId());
		verify(pRepository, times(1)).deleteById(pDTO.getId());
	}

	private ProdutoDTO convert(Produto produto) {
		return ProdutoDTO.builder().id(produto.getId()).name(produto.getName()).description(produto.getDescription())
				.price(produto.getPrice()).build();
	}
}
