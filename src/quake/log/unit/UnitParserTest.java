package quake.log.unit;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import quake.log.parser.Parser;

public class UnitParserTest {
	
	Parser p;
	
	@Before
	public void iniciarParser(){
		p = new Parser();
	}

	@Test
	public void testIniciarKillRate() throws IOException {
		//Testa se o hashMap killRate foi instanciado e está vazio
		Assert.assertNotNull(p.getKillRate());
		Assert.assertTrue(p.getKillRate().isEmpty());
		
		//Inicia o KillRate
		p.iniciarKillRate("raw/games.log");
		
		//Verifica se o KillRate foi preenchido
		Assert.assertFalse(p.getKillRate().isEmpty());
		
		//Verifica se os valores do Hash killrate iniciaram em 0
		for(String nomeJogador : p.getKillRate().keySet()){
			Assert.assertSame(0, p.getKillRate().get(nomeJogador));
		}
		
	}

	@Test
	public void testAtualizarKills() {
		fail("Not yet implemented");
	}

	@Test
	public void testContagemDeMortos() throws IOException {
		//Chama o teste anterior, pois o killRate não pode ser nulo
		//Inicia os valores do Hash killrate com 0
		this.testIniciarKillRate();
		
		//Como todos os valores são 0 é esperado que sua soma seja 0
		Assert.assertEquals(0, p.contagemDeMortos());
		
		//Inicia contagem de Kill baseado no arquivo games.log
		p.atualizarKills("raw/games.log");
		
		//Variável que guadará a soma das kills
		int soma = 0;
		//percorrendo o hash e somando a variável
		for(String chave : p.getKillRate().keySet()){
			soma += p.getKillRate().get(chave);
		}
		
		//Comparando o valor da variavel soma com o retorno do método
		//contagem de mortos
		Assert.assertEquals(soma, p.contagemDeMortos());
	}

}
