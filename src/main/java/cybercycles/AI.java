package cybercycles;

import java.util.ArrayList;
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

    Player[] p = new Player[4];

    boolean[][] isOccupied;//false = empty

    int me;

    /**
     *
     * @param d the direction
     * @return true if you can go in that direction
     */
    
//    int possibilities (char ch) //change isPossible method
//    {
//        int poss =0;
//        boolean a = isPoss(p[me-1].getX()+1,p[me-1].getY()-1) ;
//        boolean b = isPoss(p[me-1].getX()-1,p[me-1].getY()-1) ;
//        boolean c = isPoss(p[me-1].getX(),p[me-1].getY()-2) ;
//        boolean d = isPoss(p[me-1].getX(),p[me-1].getY()+2) ;
//        boolean e = isPoss(p[me-1].getX()-2,p[me-1].getY()) ;
//        boolean f = isPoss(p[me-1].getX()+1,p[me-1].getY()+1) ;
//        boolean g = isPoss(p[me-1].getX()+2,p[me-1].getY()) ;
//        boolean h = isPoss(p[me-1].getX()-1,p[me-1].getY()+1) ;
//        
//        switch (ch)
//        {
//            case 'u':
//                if (a) {
//                    poss++;
//                }
//                if (b) {
//                    poss++;
//                }
//                if (c) {
//                    poss++;
//                }
//                break;
//            case 'd':
//                if (f) {
//                    poss++;
//                }
//                if (h) {
//                    poss++;
//                }
//                if (d) {
//                    poss++;
//                }
//                break;
//                
//            case 'r':
//                if (a) {
//                    poss++;
//                }
//                if (f) {
//                    poss++;
//                }
//                if (g) {
//                    poss++;
//                }
//                
//                break;
//                
//            case 'l':
//                if (b) {
//                    poss++;
//                }
//                if (h) {
//                    poss++;
//                }
//                if (e) {
//                    poss++;
//                }
//                break;
//        }
//        return poss;
//            
//    }
    
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
                    System.out.println(play);
                    if (play.getX() + 1 >=isOccupied[0].length) {
                        return false;
                    }
                return !isOccupied[play.getY()][play.getX() + 1];
                           
            default:
                throw new AssertionError();
        }
    }
//    public int possibility(int y, int x) {
//        int poss = 0;
//        if (y - 1 >= 0) {
//
//            if (!isOccupied[y - 1][x]) {
//                poss++;
//
//            }
//        }
//
//        if (y + 1 < isOccupied.length) {
//
//            if (!isOccupied[y + 1][x]) {
//                poss++;
//
//            }
//        }
//        if (x - 1 >= 0) {
//
//            if (!isOccupied[y][x - 1]) {
//                poss++;
//
//            }
//        }
//        if (x + 1 < isOccupied[0].length) {
//
//            if (!isOccupied[y][x + 1]) {
//                poss++;
//
//            }
//        }
//        return poss;
//    }
    
//    //meeee tam
//    public int getNextY(char c)
//    {
//        Player play = p[me-1];
//        int y = p[me-1].getY();
//        switch (c) {
//            case 'u':
//                y= y-1;
//                break;
//            case 'd':
//                y= y+1;
//                break;
//            case 'r':
//                y=y;
//                break;
//            case 'l':
//                y=y;
//                break;
//            default:
//                break;
//        }
//        
//        return y;
//    }
//    
//    public int getNextX(char c)
//    {
//        Player play = p[me-1];
//        int x=p[me-1].getX();
//        switch (c) {
//            case 'u':
//                x=x;
//                break;
//            case 'd':
//                x=x;
//                break;
//            case 'r':
//                x= x+1;
//                break;
//            case 'l':
//                x= x-1;
//                break;
//            default:
//                break;
//        }
//        
//        return x;
//    }
//    
//    public int possiNum ()
//    {
//        int possiM = 0;
//        int possiU = 0;
//        int possiD = 0;
//        int possiL = 0;
//        int possiR = 0;
//        for (int i = 0; i < 4; i++) {
//            char c=this.directions[i];
//        
//        
//        isPoss(getNextX(c),getNextY(c),c );
//        if (!false)
//        {
//            if (c =='u') {
//                  
//            }
//            else if (c =='d') {
//                 ++possiD;
//            }
//            else if (c =='l') {
//                 ++possiL;
//            }
//            else if (c =='r') {
//                 ++possiR;
//            }
//        }
//        possiM=possiU;
//            if (possiM < possiD) {
//                possiM=possiD;
//            }
//            else if (possiM< possiL)
//            {
//                possiM=possiL;
//            }
//            else if (possiM<possiR) {
//                possiM=possiR;
//            }
//        }
//        
//        return possiM;
//    }
    
    public boolean isPoss (int x, int y)//good maybe
    {
        
        if (x >=0 && x<isOccupied[0].length && y>=0 && y<isOccupied.length) {
            return !isOccupied[y][x];
        }
        
        else {return false;}
        
        
 
       
        
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
            p[Integer.parseInt(a.getJSONObject(i).getString("id"))-1] = new Player(a.getJSONObject(i));
            System.out.println(p[i]);
        }

        isOccupied = new boolean[config.getInt("h")][config.getInt("w")];

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

            if (y < 0) {
                h = h + y;
                y = 0;
            } else if (x < 0) {
                w = w + x;
                x = 0;
            } else if (x > widthGrill) {
                continue;
            } else if (y > heigthGrill) {
                continue;
            }
            System.out.printf("x: %d w: %d y: %d h: %d\n", x, w, y, h);

            for (int row = y; row < Math.min(y + h, isOccupied.length); row++) {//
                for (int col = x; col < Math.min(x + w, isOccupied[0].length); col++) {
                    isOccupied[row][col] = true;
                }
            }

        } ///finding obstacle 
        //initial 
        for (int i = 0; i < config.getJSONArray("players").length(); i++) {
            isOccupied[config.getJSONArray("players").getJSONObject(i).getInt("y")][config.getJSONArray("players").getJSONObject(i).getInt("x")] = true;
        }

        for (int i = 0; i < isOccupied.length; i++) {//isOccupied.length is the h of the grid
            for (int j = 0; j < isOccupied[i].length; j++) {//x-axis
                if (isOccupied[i][j]) {
                    System.out.println(" row " + i + " col " + j);
                }
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
            
            int id = Integer.parseInt(prevMove.getString("id"));
            char direccion = prevMove.getString("direction").charAt(0);
            System.out.println(id+ " " + direccion);
            switch (direccion) {
                case 'u':
                    p[id - 1].setY(p[id - 1].getY() - 1);
                    break;
                case 'd':
                    p[id - 1].setY(p[id - 1].getY() + 1);
                    break;
                case 'l':
                    p[id - 1].setX(p[id - 1].getX() - 1);
                    break;
                case 'r':
                    p[id - 1].setX(p[id - 1].getX() + 1);
                    break;
                default:
                    throw new AssertionError();
            }
            isOccupied[p[id - 1].getY()][p[id - 1].getX()] = true;

            System.out.print(prevMove + " ");

        }

        System.out.print("\n");

//       int[] listPoss = new int [4];
//        for (int i = 0; i < 4; i++) {
//            if (isPossible(directions[i])) {
//                listPoss[i] = possibilities (directions[i]);
//            }
//            else listPoss[i]=-1;
            
            
            
//        }
//        int max=0;
//        int maxIndex= 0;
//        
//        for (int i = 0; i < 4; i++) {
//            if (listPoss[i]>max) {
//                
//             max=   listPoss[i] ;
//             maxIndex = i;
//             
//            }
           // }
int index=0;
        for (int i = 0; i < 4; i++) {
            System.out.println("dir=" + directions[i] + ", " + isPossible(directions[i]));
            
            if (isPossible (directions[i])) 
                { 
                
                index=i;
                
            }
        }
         System.out.println(directions[index]);  
        return directions[index];

        // Choisis une direction au hasard
        //  direction = directions[random.nextInt(directions.length)];
       // System.out.println("Mouvement choisi : " + direction);
       // return directions[]//[maxIndex];

        
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
