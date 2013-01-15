package quake.log.scripts;
import java.io.IOException;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import quake.log.parser.Parser;
import quake.log.util.RankingSorted;


public class App {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		Parser p = new Parser();
		
		System.out.println("...Iniciando Log...");
		p.lerLog();
		System.out.println("...Atualizando Contagem de Kills...");
		p.atualizarKills();
		
		System.out.println("\nGame:");
		System.out.println("\nTotal de Mortos: "+p.contagemDeMortos());
		System.out.println("\nJogadores: "+p.getKillRate().keySet());
		System.out.println("\nRanking de Kills: ");
		for(String nomeJogador : p.getKillRate().keySet()){
			System.out.println(nomeJogador+": "+p.getKillRate().get(nomeJogador)+" kills");
		}
		
	}

}
