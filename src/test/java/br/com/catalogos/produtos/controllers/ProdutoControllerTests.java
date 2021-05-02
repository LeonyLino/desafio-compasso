package br.com.catalogos.produtos.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.catalogos.produtos.models.Produto;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProdutoControllerTests {

	@Autowired
	private MockMvc mockMvc;
	private URI uri;
	private ObjectMapper objectMapper;
	Produto produto;

	@BeforeEach
	public void iniciar() throws URISyntaxException {
		this.objectMapper = new ObjectMapper();
		this.uri = new URI("/products");
		this.produto = new Produto(null, "Monitor LG Gamer Editado",
				"UltraWide 25Pol IPS Full HD 1ms MBR 25UM58G Editado", BigDecimal.valueOf(902.40));
	}

	@Test
	void deveriaRetornarStatus201_QuandoCadastrarUmNovoProduto() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.content(objectMapper.writeValueAsString(
						new Produto(null, "Echo Google", "Produto criado de ultima hora", BigDecimal.valueOf(0.05))))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(201));
	}

	@Test
	void deveriaRetornarStatus400_QuandoCadastrarProdutoSemPreco() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.content(objectMapper
						.writeValueAsString(new Produto(null, "Echo Google", "Produto criado de ultima hora", null)))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(400));
	}

	@Test
	void deveriaRetornarStatus200EBodyDaRequisaoComIdDaURI_QuandoEditarUmProduto() throws Exception {
		Produto produtoEditadoMocadoTeste = new Produto(1L, "Monitor LG Gamer Editado",
				"UltraWide 25Pol IPS Full HD 1ms MBR 25UM58G Editado", BigDecimal.valueOf(902.40));

		mockMvc.perform(MockMvcRequestBuilders.put(uri.getPath().concat("/1"))
				.content(objectMapper.writeValueAsString(this.produto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200))
				.andExpect(content().string(objectMapper.writeValueAsString(produtoEditadoMocadoTeste)));
	}

	@Test
	void deveriaRetornarStatus404_QuandoEditarUmProduto() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put(uri.getPath().concat("/123"))
				.content(objectMapper.writeValueAsString(produto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(404));
	}

	@Test
	void deveriaRetonarStatus200_QuandoProcurarUmProdutoValido() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(uri.getPath().concat("/1"))).andExpect(status().is(200));
	}

	@Test
	void deveriaRetonarStatus404_QuandoProcurarUmProdutoInvalido() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(uri.getPath().concat("/123"))).andExpect(status().is(404));
		
	}
	
	@Test
	void deveriaRetonarStatus200_QuandoDeletarUmProdutoValido() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete(uri.getPath().concat("/1"))).andExpect(status().is(200));
	}

	@Test
	void deveriaRetonarStatus404_QuandoDeletarUmProdutoInvalido() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete(uri.getPath().concat("/123"))).andExpect(status().is(404));
		
	}
}
