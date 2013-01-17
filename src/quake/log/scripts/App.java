package quake.log.scripts;

import java.io.IOException;
import org.codehaus.jettison.json.JSONObject;
import quake.log.parser.Parser;

public class App {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {
		try {
			Parser p = new Parser("raw/games.log");
			p.atualizarKills();
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("total_kills", p.contagemDeMortos());
			jsonObject.put("players", p.getKillRate().keySet());
			jsonObject.put("Kills", p.getKillRate());
			jsonObject.put("kills_by_means", p.getMeios_de_morte());
			
			JSONObject jsonFinal = new JSONObject();
			jsonFinal.put("game", jsonObject);
			
			System.out.println(jsonFinal.toString());

		} catch (IOException e) {
			System.out.println("Caminho do arquivo incorreto");
		}catch(ArrayIndexOutOfBoundsException a){
			System.out.println("Arquivo de log fora do padrão");
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
