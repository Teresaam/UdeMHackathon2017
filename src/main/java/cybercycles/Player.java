/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cybercycles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author Tere
 */
public class Player {
    
    private int x;
    private int y;
    private String id;
    private String team;
    private String direction;
   
    
    
    public Player(JSONObject object) throws JSONException
    {
        x=object.getInt("x");
        y=object.getInt("y");
        id=object.getString("id");
        team=object.getString("team");
        direction=object.getString("direction");
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the team
     */
    public String getTeam() {
        return team;
    }

    /**
     * @return the direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Player{" + "x=" + x + ", y=" + y + ", id=" + id + ", team=" + team + ", direction=" + direction + '}';
    }
    
    
    
}
