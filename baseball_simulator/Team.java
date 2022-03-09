import java.util.ArrayList;

public class Team {
    String name;
    int year;
    ArrayList<Player> roster;
    ArrayList<Player> bullpen;

    public Team() {
        this.roster = new ArrayList<Player>();
        this.bullpen = new ArrayList<Player>();
    }

    public void setInfo(String info) {
        this.year = Integer.parseInt(info.substring(4, 8));
        this.name = info.substring(9, (info.length() - 5));
    }

    public void addPlayer(Player player) {
        this.roster.add(player);
        if (player.position.equals("P")) {
            bullpen.add(player);
        }
    }

    public void zeroOut() {
        for (Player player : roster) {
            player.clearGameStats();
        }
    }

    public Player getPlayer(String name) {
        Player result = roster.get(0);
        for (Player player : roster) {
            if (player.name.equals(name)) {
                result = player;
            }
        }
        return result;
    }

    public void setAverages() {
        for (Player player : roster) {
            player.setAvg();
        }
    }

    public String printRoster() {
        String result = ""+year+" "+name+"\n";
        result += "ROSTER:\n";
        for (Player player : roster) {
            result += player+" ("+player.position+")\n";
        }
        return result;
    }

    public String toString () {
        return ""+name+" ("+year+")";
    }
}
