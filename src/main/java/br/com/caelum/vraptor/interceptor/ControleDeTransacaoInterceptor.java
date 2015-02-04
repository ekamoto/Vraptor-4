package br.com.caelum.vraptor.interceptor;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.vraptor.AfterCall;
import br.com.caelum.vraptor.BeforeCall;
import br.com.caelum.vraptor.Intercepts;

@Intercepts(after=AutorizadorInterceptor.class)
public class ControleDeTransacaoInterceptor {

	@Inject private EntityManager em;
	
	@BeforeCall
	public void before() {
		em.getTransaction().begin();
	}
	
	@AfterCall
	public void after() {
		em.getTransaction().commit();
	}
}
