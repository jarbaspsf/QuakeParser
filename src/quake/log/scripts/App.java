package quake.log.scripts;
import java.io.IOException;

import quake.log.parser.Parser;


public class App {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		Parser p = new Parser("raw/games.log");
		
		System.out.println("...Iniciando Log...");
		System.out.println("...Atualizando Contagem de Kills...");
		p.atualizarKills();
		System.out.println("\nGame:");
		System.out.println("\nTotal de Mortos: "+p.contagemDeMortos());
		System.out.println("\nJogadores: "+p.getKillRate().keySet());
		System.out.println("\nRanking de Kills: ");
		
		for(String nomeJogador : p.getKillRate().keySet()){
			System.out.println(nomeJogador+": "+p.getKillRate().get(nomeJogador)+" kills");
		}
		
		System.out.println("\nContagem de meios de Kill:");
		for(String nomeMeioMorte : p.getMeios_de_morte().keySet()){
			System.out.println(nomeMeioMorte+": "+p.getMeios_de_morte().get(nomeMeioMorte));
		}
		
	}

}
