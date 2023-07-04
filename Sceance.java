import java.util.Calendar;

public class Sceance {


    static String[] COUR_TYPES = {"théorique", "pratiques"};

    private Cour cour;

    private int duree;

    private String getJourEcole;

    private int heureDebut;

    private int heureFin;

    private String type;

    public Sceance() {

    }

    public static String[] getCourTypes() {
        return COUR_TYPES;
    }

    public int getDuree() {
        return duree;
    }

    public String getJourEcole() {
        return this.getJourEcole;
    }

    public int getHeureDebut() {
        return heureDebut;
    }

    public int getHeureFin() {
        return heureFin;
    }

    public String getType() {
        return type;
    }

    public Cour getCour() {
        return cour;
    }
}
