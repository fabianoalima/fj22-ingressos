package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;
import junit.framework.Assert;

public class DescontoTest {
	
	private Lugar lugar;
	private Sala sala;
	private Filme filme;
	private Sessao sessao;
	
	@Before
	public void preparaDescontos() {
		
		this.lugar = new Lugar("1", 1);
		this.sala = new Sala("Eldorado - IMAX", new BigDecimal("20.5"));
		this.filme = new Filme("Rogue One", Duration.ofMinutes(120),
						"SCI-FI", new BigDecimal("12"));
		this.sessao = new Sessao(LocalTime.parse("10:00:00"), this.filme, this.sala);
	}
	
	@Test
	public void naoDeveConcederDescontoParaIngressoNormal() {
		
		Ingresso ingresso = new Ingresso(this.sessao, TipoDeIngresso.INTEIRO, lugar);		
		BigDecimal precoEsperado = new BigDecimal("32.50");		
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}
	
	@Test
	public void deveConcederDescontoDe30PorCentoParaIngressoDeClienteDeBanco() {
		
		Ingresso ingresso = new Ingresso(this.sessao, TipoDeIngresso.BANCO, lugar);		
		BigDecimal precoEsperado = new BigDecimal("22.75");		
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}
	
	@Test
	public void deveConcederDescontoDe50PorCentoParaIngressoDeEstudante() {
		
		Ingresso ingresso = new Ingresso(this.sessao, TipoDeIngresso.ESTUDANTE, lugar);
		BigDecimal precoEsperado = new BigDecimal("16.25");
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}
}
