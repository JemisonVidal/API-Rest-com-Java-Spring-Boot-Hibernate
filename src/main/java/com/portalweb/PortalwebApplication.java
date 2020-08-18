package com.portalweb;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.portalweb.domain.Categoria;
import com.portalweb.domain.Cidade;
import com.portalweb.domain.Cliente;
import com.portalweb.domain.Endereco;
import com.portalweb.domain.Estado;
import com.portalweb.domain.Produto;
import com.portalweb.domain.enums.TipoCliente;
import com.portalweb.repositories.CategoriaRepository;
import com.portalweb.repositories.CidadeRepository;
import com.portalweb.repositories.ClienteRepository;
import com.portalweb.repositories.EnderecoRepository;
import com.portalweb.repositories.EstadoRepository;
import com.portalweb.repositories.ProdutoRepository;

@SpringBootApplication
public class PortalwebApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(PortalwebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		Estado est1 = new Estado(null, "Minas");
		Estado est2 = new Estado(null, "SP");

		Cidade c1 = new Cidade(null, "Uberlância", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);

		Cliente cli1 = new Cliente(null, "Jemison", "jemison.santoos@gmail.com", "0000000", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("11111", "22222"));

		Endereco e1 = new Endereco(null, "Rua flores", "300", "apto 1", "jardim", "121212121", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Urano", "83", "apto 1", "jardim", "121212121", cli1, c2);

		cli1.getEndereco().addAll(Arrays.asList(e1, e2));

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

	}
}
