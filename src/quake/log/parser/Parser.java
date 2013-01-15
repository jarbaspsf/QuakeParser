package quake.log.parser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Parser {

	// Hash que representa o killRate de um jogador no padrão Nome / Kill
	private HashMap<String, Integer> killRate = new HashMap<String, Integer>();

	public void iniciarKillRate(String caminhoArquivo) throws IOException {

		FileInputStream stream = new FileInputStream(caminhoArquivo);
		InputStreamReader sr = new InputStreamReader(stream);
		BufferedReader br = new BufferedReader(sr);

		String linha;

		// Iniciar contagem de kill
		while ((linha = br.readLine()) != null) {
			//Se a String linha contem a palavra "Killed"
			if (linha.contains("killed")) {
				//Separa a linha por :
				String s[] = linha.split(":");
				//Separa a linha por "killed" 
				//Por padrão do Log a terceira parte contem o nome do jogador
				//na posição 0
				String nomeJogadorAndMorto[] = s[3].split("killed");
				
				if (!nomeJogadorAndMorto[0].contains("world")) {
					killRate.put(nomeJogadorAndMorto[0], 0);
				}
			}
		}
		
		br.close();

	}
	
	/**
	 * @author jarbas 
	 * Método que atualiza o killRate de acordo com as seguintes regras:
	 * se um jogador matou outro jogador +1
	 * se um jogador cometeu suicidio +0
	 * se um jogador foi morto pelo "mundo" -1 
	 * @throws IOException
	 * Lança Execeção caso não encontre aquivo de log
	 */
	public void atualizarKills(String caminhoArquivo) throws IOException {

		FileInputStream stream = new FileInputStream(caminhoArquivo);
		InputStreamReader sr = new InputStreamReader(stream);
		BufferedReader br = new BufferedReader(sr);

		String linha;
		//Ler Arquivo de log
		while ((linha = br.readLine()) != null) {
			//Se a String linha contem a palavra "Killed"
			if (linha.contains("killed")) {
				//Separa a linha por :
				String s[] = linha.split(":");
				//Separa a linha por "killed" 
				//Por padrão do Log a terceira parte contem o nome do jogador
				//na posição 0
				String nomeJogadorAndMorto[] = s[3].split("killed");
				//pegamos o resltado do split separamos novamente por "by"
				//e agora temos na posição 0 o nome do JogadorMorto
				//e na posição 1 o metodo (Foguete, evento do sistema etc...)
				String nomeMortoAndMetodo[] = nomeJogadorAndMorto[1]
						.split("by");
				
				//Testa se o nome do jogador é world
				if (!nomeJogadorAndMorto[0].contains("world")) {
					//Testa se o jogador cometeu suicidio
					//se não é computada/adicionanda uma kill a ele
					if (!nomeJogadorAndMorto[0]
							.equalsIgnoreCase(nomeMortoAndMetodo[0])) {
						Integer kill = killRate.get(nomeJogadorAndMorto[0]);
						kill++;
						killRate.put(nomeJogadorAndMorto[0], kill);
					}
					//Se um evento do world matou o jogador
					//é retirada uma kill do mesmo
				}else {
						Integer kill = killRate.get(nomeMortoAndMetodo[0]);
						kill--;
						
						//Se a quantidade de kills for negativa
						//kill recebe 0
						if(kill < 0){
							kill = 0;
						}
						
						killRate.put(nomeMortoAndMetodo[0], kill);
					}
				}
		}
		
		br.close();
	}
	
	public long contagemDeMortos(){
		
		int somaKills = 0;
		
		for(Integer i : killRate.values()){
			somaKills += i;
		}
		
		return somaKills;
	}
	
	/*
	 * Gets And Sets
	 */
	public HashMap<String, Integer> getKillRate() {
		return killRate;
	}
	public void setKillRate(HashMap<String, Integer> killRate) {
		this.killRate = killRate;
	}
	
	

}
