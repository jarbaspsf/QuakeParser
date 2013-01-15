package quake.log.unit;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import quake.log.parser.Parser;

public class UnitParserTest {
	
	Parser p;
	
	/**
	 * Como padrão o parser irá tratar do arquivo games.log
	 * encontrado na pasta raw.
	 * @throws IOException, se o arquivo não foi encontrado
	 * @author jarbas 
	 */
	@Before
	public void iniciarParser() throws IOException{
		p = new Parser("raw/games.log");
	}
	/**
	 * Verifica a inicializaçao do Hash
	 * e se ele foi iniciado com os jogadores
	 * @throws IOException, se o arquivo não pode ser encontrado
	 * @author jarbas 
	 */
	@Test
	public void testIniciarKillRate() throws IOException {
		//Testa se o hashMap killRate foi instanciado 
		//e sua contagem inical ocorreu
		Assert.assertNotNull(p.getKillRate());
		
		//Verifica se os valores do Hash killrate iniciaram em 0
		//e se há jogadores (key)
		for(String nomeJogador : p.getKillRate().keySet()){
			Assert.assertSame(0, p.getKillRate().get(nomeJogador));
		}
		
	}
	/**
	 * Verifica se a atualização de kills funciona da seguinte maneira:
	 * se o jogador mata alguem ~> +1 kill,
	 * se o jogador se suicida ~> +0 kill
	 * e se um evento do jogo mata um jogador ~> -1 kill
	 * @throws IOException, se o arquivo não pode ser encontrado
	 * @author jarbas
	 */
	@Test
	public void testAtualizarKills() throws IOException {
		/*Carrega log teste
		 * neste log temos que:
		 * o jogador Mal matou Isgalamido
		 * o jogador Dono da Bola se suicidou
		 * o jogador isgalamido se suicidou duas vezes
		 * um evento do sistema (world) matou isgalamido 
		 */
		p = new Parser("raw/teste.log");
		
		//Atualiza as Kills
		p.atualizarKills();
		
		//Guarda as kills do jogador Mal
		int killJogadorMal = p.getKillRate().get(" Mal ");
		//Guarda as kills do jogador Dono da Bola
		int killJogadorDonoDaBola = p.getKillRate().get(" Dono da Bola ");
		//Guarda as kills do jogador Isgalamido
		int killJogadorIsgalamido = p.getKillRate().get(" Isgalamido ");
		
		// 1 kill
		Assert.assertEquals(1, killJogadorMal);
		// 1 Suicidio
		Assert.assertEquals(0, killJogadorDonoDaBola);
		// 2 Suicidios, 1 morte por um evento de sistema
		Assert.assertEquals(0, killJogadorIsgalamido);
		
		/*
		 * Carregando log teste2
		 * neste log temos que:
		 * o jogador mal se suicidou
		 * o jogador Mal matou isgalamido 3 vezes
		 * o jogador isgalamido matou Mal 14 vezes
		 * o jogador Dono da bola matou Isgalamido 7 vezes
		 * 3 eventos do sistema mataram o jogador Dono da bola
		 * 2 eventos do sistema mataram o jogador Isgalamdio
		 */
		p = new Parser("raw/teste2.log");
		
		//Atualiza as Kills
		p.atualizarKills();
				
		//Guarda as kills do jogador Mal
		killJogadorMal = p.getKillRate().get(" Mal ");
		//Guarda as kills do jogador Dono da Bola
		killJogadorDonoDaBola = p.getKillRate().get(" Dono da Bola ");
		//Guarda as kills do jogador Isgalamido
		killJogadorIsgalamido = p.getKillRate().get(" Isgalamido ");
				
		// 1 suicidio, 3 kills = 3
		Assert.assertEquals(3, killJogadorMal);
		// 7 Kills, 3 mortes por eventos de sistema = 4
		Assert.assertEquals(4, killJogadorDonoDaBola);
		// 14 Kills, 2 mortes por eventos de sistema = 12
		Assert.assertEquals(12, killJogadorIsgalamido);
		
		/*
		 * Carregando log teste3
		 * neste log temos:
		 * o jogador Isgalamido morreu 20 vezes por eventos de sistema
		 * o jogador Isgalamido matou Mal 2 vezes 
		 * o jogador Isgalamido morreu por eventos de sistema
		 */
		p = new Parser("raw/teste3.log");
		
		//Atualiza as Kills
		p.atualizarKills();
				
		//Guarda as kills do jogador Isgalamido
		killJogadorIsgalamido = p.getKillRate().get(" Isgalamido ");
		
		//20 mortes por eventos de sistema, 
		//2 kills, 1 morte por evento de sistema = 1
		Assert.assertEquals(1, killJogadorIsgalamido);
		
	}
	/**
	 * Verifica a contagem de mortos totais de um jogo
	 * @throws IOException
	 * @author jarbas
	 */
	@Test
	public void testContagemDeMortos() throws IOException {
		//Testa se o hashMap killRate foi instanciado 
		//e sua contagem inical ocorreu
		Assert.assertNotNull(p.getKillRate());
		Assert.assertFalse(p.getKillRate().isEmpty());
		
		//Como todos os valores são 0 é esperado que sua soma seja 0
		Assert.assertEquals(0, p.contagemDeMortos());
		
		//Inicia contagem de Kill baseado no arquivo games.log
		p.atualizarKills();
		
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
	
	/**
	 * Verifica a espera da exceção quando um caminho inexistente 
	 * é passado
	 * @throws IOException
	 * @author jarbas
	 */
	@Test(expected = IOException.class)
	public void testCaminhoArquivoErrado() throws IOException{
		p = new Parser("caminho/errado");
	}

}
