package cybercycles;

import java.util.Arrays;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AI {

    /* Configuration */
    public final String ROOM = "3";
    public final String TEAM = "2";

    /* Déplacement de l'A.I. */
    public final char[] directions = {'u', 'l', 'd', 'r'};
    public char direction;

    Random random = new Random();
    
    Player p1, p2, p3, p4;
    Player [] p ={p1, p2, p3, p4};

    boolean [] [] isOccupied;//false = empty
    
    
    /**
     * Fonction appelée en début de partie.
     *
     * @param config Configuration de la grille de jeu
     * @throws org.json.JSONException
     */
    public void start(JSONObject config) throws JSONException {
        JSONArray a = config.getJSONArray("players");
        System.out.println("Joueurs : " + config.getJSONArray("players"));
        
        for (int i = 0; i < a.length(); i++) {
            p[i] = new Player(a.getJSONObject(i));
            System.out.println(p[i]);
        }
        
        isOccupied = new boolean [config.getInt("w")-1] [config.getInt("h")-1];
        
        System.out.println(Arrays.deepToString(isOccupied));
        System.out.println("Obstacles : " + config.getJSONArray("obstacles"));

        System.out.print("Taille de la grille : ");
        System.out.println(config.getInt("w") + " x " + config.getInt("h"));

        System.out.println("Votre identifiant : " + config.getString("me"));
        
        for (int i = 0; i < isOccupied.length; i++) {//isOccupied.length is the w of the grid
            for (int j = 0; j < isOccupied[i].length; j++) {
                if (isOccupied[i][j]) { System.out.println(i + ", " + j);}
            }
            
        }
    }

    /**
     * Fonction appelée à chaque tour de jeu.
     *
     * @param prevMoves Mouvements précédents des joueurs
     * @return Mouvement à effectuer
     * @throws org.json.JSONException
     */
    public char next(JSONArray prevMoves) throws JSONException {
        System.out.print("Mouvements précdents : ");

        for (int i = 0; i < prevMoves.length(); i++) {
            JSONObject prevMove = prevMoves.getJSONObject(i);
            System.out.print(prevMove + " ");
        }

        System.out.print("\n");

        // Choisis une direction au hasard
        direction = directions[random.nextInt(directions.length)];
        System.out.println("Mouvement choisi : " + direction);

        return direction;
    }

    /**
     * Fonction appelée en fin de partie.
     *
     * @param winnerID ID de l'équipe gagnante
     */
    public void end(String winnerID) {
        System.out.println("Équipe gagnante : " + winnerID);
    }
}
