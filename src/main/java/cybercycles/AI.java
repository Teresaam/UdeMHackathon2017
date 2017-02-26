package cybercycles;

import java.util.Arrays;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AI {

    
    /* Configuration */
    public final String ROOM = "3";
    public final String TEAM = "3";

    /* Déplacement de l'A.I. */
    public final static char[] directions = {'u', 'l', 'd', 'r'};
    public char direction;

    Random random = new Random();
    
    Player [] p = new Player [4];

    boolean [] [] isOccupied;//false = empty
    
    int me;
    /**
     * 
     * @param d the direction
     * @return true if you can go in that direction
     */
    public boolean isPossible(char d) {
        Player play = p[me-1];
        
        switch (d) {
            case 'u':
                if (play.getY() - 1 < 0) {
                    return false;
                }
                return !isOccupied[play.getY() - 1][play.getX()];
                
                case 'd':
                    if (play.getY() + 1 >= isOccupied.length) {
                        return false;
                    }
                return !isOccupied[play.getY() + 1][play.getX()];
                
                case 'l':
                    if (play.getX() - 1 < 0) 
                    { return false;
                    }
                return !isOccupied[play.getY()][play.getX() - 1];
                case 'r':
                    if (play.getX() + 1 >=isOccupied[0].length) {
                        return false;
                    }
                return !isOccupied[play.getY()][play.getX() + 1];
                           
            default:
                throw new AssertionError();
        }
    }
    
    private int freeSpaces(int y, int x) {
        int free = 0;
        
        // above
        try {
            if (!isOccupied[y-1][x]) {
                free++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return -100000;
        }
        
        //below
        try {
            if (!isOccupied[y+1][x]) {
                free++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return -100000;
        }
        
        //left
        try {
            if (!isOccupied[y][x-1]) {
                free++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return -100000;
        }
        
        //right
        try {
            if (!isOccupied[y][x+1]) {
                free++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return -100000;
        }
        
        return free;
        
    }
    
    private int possibilities(char c) {
        Player play = p[me-1];
        if (!isPossible(c)) {
            return -100000;
        }
        
        
        switch (c) {
            case 'u':
                return freeSpaces(play.getY()-1, play.getX());
            case 'd':
                return freeSpaces(play.getY()+1, play.getX());
            case 'l':
                return freeSpaces(play.getY(), play.getX()-1);
            case 'r':
                return freeSpaces(play.getY(), play.getX()+1);
            default:
                throw new AssertionError();
        }
    }
    
    
    /**
     * Fonction appelée en début de partie.
     *
     * @param config Configuration de la grille de jeu
     * @throws org.json.JSONException
     */
    public void start(JSONObject config) throws JSONException {
        me = Integer.parseInt(config.getString("me"));
        JSONArray a = config.getJSONArray("players");
        System.out.println("Joueurs : " + config.getJSONArray("players"));
        
        int widthGrill = config.getInt("w");
        int heigthGrill = config.getInt("h");
        
        for (int i = 0; i < a.length(); i++) {
            p[i] = new Player(a.getJSONObject(i));
            System.out.println(p[i]);
        }
        
        isOccupied = new boolean [config.getInt("h")] [config.getInt("w")];
        
        System.out.println(Arrays.deepToString(isOccupied));
        System.out.println("Obstacles : " + config.getJSONArray("obstacles"));

        System.out.print("Taille de la grille : ");
        System.out.println(config.getInt("w") + " x " + config.getInt("h"));

        System.out.println("Votre identifiant : " + config.getString("me"));
        
        for (int k = 0; k < config.getJSONArray("obstacles").length(); k++) {
            int x = config.getJSONArray("obstacles").getJSONObject(k).getInt("x");//coordinates 
            int y = config.getJSONArray("obstacles").getJSONObject(k).getInt("y");//coordinates
            int w = config.getJSONArray("obstacles").getJSONObject(k).getInt("w");
            int h = config.getJSONArray("obstacles").getJSONObject(k).getInt("h");
                
            if (y<0)
            {
                h = h +y;
                y=0;
            }
            
            else if (x<0)
            {
                w = w+ x;
                x = 0;
            }
            
            else if (x > widthGrill )
            {
                continue;
            }
            
            else if (y > heigthGrill )
            {
                continue;
            }
            System.out.printf("x: %d w: %d y: %d h: %d\n", x, w, y, h);

            for (int row = y; row < y+h; row++) {//
                for (int col = x; col<x+w; col++) {
                    isOccupied[row][col]=true;
                }
                }
                    
            } ///finding obstacle 
           //initial 
        for (int i = 0; i < config.getJSONArray("players").length(); i++) {
            isOccupied[config.getJSONArray("players").getJSONObject(i).getInt("y")][config.getJSONArray("players").getJSONObject(i).getInt("x")]= true;
        }
        
        
        for (int i = 0; i < isOccupied.length; i++) {//isOccupied.length is the h of the grid
            for (int j = 0; j < isOccupied[i].length; j++) {//x-axis
                if (isOccupied[i][j]) { System.out.println(" row "+i + " col " + j);}
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
        
      
        
        
        
        
        
        // saves opponent's moves
        for (int i = 0; i < prevMoves.length(); i++) {
            JSONObject prevMove = prevMoves.getJSONObject(i);

            int id=Integer.parseInt(prevMove.getString("id"));
            char direccion=prevMove.getString("direction").charAt(0);
                switch (direccion) {
                    case 'u':
                        p[id-1].setY(p[id-1].getY()-1);
                        break;
                    case 'd':
                        p[id-1].setY(p[id-1].getY()+1);
                        break;
                    case 'l':
                        p[id-1].setX(p[id-1].getX()-1);
                        break;
                    case 'r':
                        p[id-1].setX(p[id-1].getX()+1);
                        break;
                    default:
                        throw new AssertionError();
                }
            isOccupied[p[id-1].getY()][p[id-1].getX()] = true;

            


            System.out.print(prevMove + " ");
        
        }

        
        
        System.out.print("\n");
        
        
        
         int max=0;
         char c ='u';
        for (char i : directions ) {
            if (isPossible(i))
            {
               int actual=possibilities(i);
                if(actual>max)
                {
                    max=actual;
                    c=i;
                }
            }
                
            
        }
        
        // Choisis une direction au hasard
      //  direction = directions[random.nextInt(directions.length)];
       
        
        System.out.println("Mouvement choisi : " + direction);

        return c;
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
