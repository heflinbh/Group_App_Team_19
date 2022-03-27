package edu.neu.a7stickittoem_team19;

public class PlayerRep {
    public String name;
    public String score;
//    public String datePlayed;

    public PlayerRep() {}

    public PlayerRep(String name, String score) {
        this.name = name;
        this.score = score;
//        this.datePlayed = date;
    }

    public PlayerRep(Player p) {
        this.name = p.name;
        this.score = String.valueOf(p.score);
    }
}
