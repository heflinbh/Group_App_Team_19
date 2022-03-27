package edu.neu.a7stickittoem_team19;

public class Player {
    public String name;
    public int score;
//    public String datePlayed;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
//        this.datePlayed = date;
    }

//    public Player(PlayerRep p) {
//        this.name = p.name;
//        this.score = Integer.valueOf(p.score);
//    }

    public void addPoint() {
        this.score++;
    }
}
