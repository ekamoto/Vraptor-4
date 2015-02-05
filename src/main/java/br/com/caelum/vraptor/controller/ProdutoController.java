package br.com.caelum.vraptor.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.dao.ProdutoDao;
import br.com.caelum.vraptor.model.Produto;
import br.com.caelum.vraptor.simplemail.Mailer;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;

@Controller
public class ProdutoController {

	
	private final Result result;
	private final ProdutoDao dao;
	private final Validator validator;
	private Mailer mailer;
	
	@Inject
	public ProdutoController(Result result, ProdutoDao dao, Validator validator, Mailer mailer) {
		
		this.result = result;
		this.dao = dao;
		this.validator = validator;
		this.mailer = mailer;
	}
	
	public ProdutoController() {
		
		this(null, null, null, null);
	}

	@Path("/")
	public void inicio() {
		
	}
	
	@Get
	public void sobre() {
		
	}
	
	@Get
	public void lista() {
		
		result.include("produtoList", dao.lista());
	}

	@Get
	public void listaXML() {
		
		result.use(Results.xml()).from(dao.lista()).serialize();
	}
	
	@Get
	public void listaJSON() {
		
		result.use(Results.json()).from(dao.lista()).serialize();
	}
	
	@Get
	public void formulario() {
		
	}
	
	@Post
	public void adiciona(@Valid Produto produto) {
		
//		EntityManager em =  JPAUtil.criaEntityManager();
//		ProdutoDao produtoDao = new ProdutoDao(em);
//		
//		em.getTransaction().begin();
//		produtoDao.adiciona(produto);
//		em.getTransaction().commit();

// Foi substituido por @Valid
//		validator.check(produto.getQuantidade() > 0, new I18nMessage("produto.quantidade", "quantidade.negativa"));
		
		validator.onErrorForwardTo(this).formulario();
		
		dao.adiciona(produto);
		
		result.include("mensagem", "Produto adicionado com sucesso!");
		// result.forwardTo(this).lista(); // não muda a url, fica adicionando novo produto
		result.redirectTo(this).lista();
	}
	
	@Get
	public void remove(Produto produto){
	    
	    dao.remove(produto);
	    result.include("mensagem", "Produto deletado com sucesso!");
		// result.forwardTo(this).lista(); // não muda a url, fica adicionando novo produto
		result.redirectTo(this).lista();
	}
	
	@Get
	public void enviaPedidoDeNovosItens(Produto produto) throws EmailException {
		Email email = new SimpleEmail();
		email.setSubject("Precisamos de mais estoque");
		email.setMsg("O produto" +produto.getNome()+" está em falta no estoque");
		email.addTo("ekamoto.leandro@gmail.com");
		mailer.send(email);
		result.redirectTo(this).lista();
	}
}
