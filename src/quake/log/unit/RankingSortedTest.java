package quake.log.unit;

import java.util.HashMap;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class RankingSortedTest {

	HashMap<String, Integer> killRate;
	
	/**
	 * Instancia um HashMap para ser utilizado nos testes
	 */
	@Before
	public void iniciar() {
		killRate = new HashMap<String, Integer>();
	}
	
	/**
	 * Verifica se o hashMap
	 * esta sendo ordenado em 
	 * ordem decresente corretamente.
	 * @author jarbas
	 */
	
	@Test
	public void ordenarRankingTeste() {
		// Populando KillRate
		killRate.put("Player1", 45);
		killRate.put("Player2", 68);
		killRate.put("Player3", 91);
		killRate.put("Player4", 12);
		killRate.put("Player5", 0);
		killRate.put("Player6", 120);

		// Array que irá guardar os valores
		int numeros[] = new int[killRate.size()];
		int cont = 0;

		for (String nome : killRate.keySet()) {
			numeros[cont] = killRate.get(nome);
			cont++;
		}

		// Verifica que não está em ordem crescente
		Assert.assertFalse(verificarOrdemCrescente(numeros));

		// Ordena o HashMap
		HashMap<String, Integer> hashOrdenado = RankingSorted
				.ordenarRanking(killRate);

		cont = 0;

		for (String nome : hashOrdenado.keySet()) {
			numeros[cont] = hashOrdenado.get(nome);
			cont++;
		}

		// Verifica que está em ordem crescente
		Assert.assertTrue(verificarOrdemCrescente(numeros));

	}

	/**
	 * Verifica se um array está em ordem decrescente
	 * @param array que será testado
	 * @return true se estiver ordenado decrescentemente
	 */
	private boolean verificarOrdemCrescente(int array[]) {
		int cursor = array[0];

		for (int i = 1; i < array.length; i++) {
			if (cursor >= array[i]) {
				cursor = array[i];
			} else {
				return false;
			}
		}

		return true;

	}

}
