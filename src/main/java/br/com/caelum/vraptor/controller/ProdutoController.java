package br.com.caelum.vraptor.controller;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.dao.ProdutoDao;
import br.com.caelum.vraptor.model.Produto;
import br.com.caelum.vraptor.util.JPAUtil;
import br.com.caelum.vraptor.view.Results;

@Controller
public class ProdutoController {

	
	private final Result result;
	
	@Inject
	public ProdutoController(Result result) {
		
		this.result = result;
	}
	
	public ProdutoController() {
		
		this(null);
	}

	@Path("/")
	public void inicio() {
		
	}
	
	@Get
	public void sobre() {
		
	}
	
	@Get
	public void lista() {
		EntityManager em = JPAUtil.criaEntityManager();
		ProdutoDao dao = new ProdutoDao(em);
		
		result.include("produtoList", dao.lista());
	}

	@Get
	public void listaXML() {
		EntityManager em = JPAUtil.criaEntityManager();
		ProdutoDao dao = new ProdutoDao(em);
		
		result.use(Results.xml()).from(dao.lista()).serialize();
	}
	
	@Get
	public void listaJSON() {
		EntityManager em = JPAUtil.criaEntityManager();
		ProdutoDao dao = new ProdutoDao(em);
		
		result.use(Results.json()).from(dao.lista()).serialize();
	}
	
	@Get
	public void formulario() {
		
	}
	
	@Post
	public void adiciona(Produto produto) {
		EntityManager em =  JPAUtil.criaEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		em.getTransaction().begin();
		produtoDao.adiciona(produto);
		em.getTransaction().commit();
		
		result.include("mensagem", "Produto adicionado com sucesso!");
		// result.forwardTo(this).lista(); // não muda a url, fica adicionando novo produto
		result.redirectTo(this).lista();
	}
	
	@Get
	public void remove(Produto produto){
	    EntityManager em =  JPAUtil.criaEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
	    em.getTransaction().begin();
	    produtoDao.remove(produto);
	    em.getTransaction().commit();
	    result.include("mensagem", "Produto deletado com sucesso!");
		// result.forwardTo(this).lista(); // não muda a url, fica adicionando novo produto
		result.redirectTo(this).lista();
	}
	
}
