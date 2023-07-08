import java.time.LocalTime;
import java.time.LocalDate;

public class Sceance {


    static String[] COUR_TYPES = {"théorique", "pratiques", "examen"};

    private LocalTime heureDebut;
    private LocalTime heureFin;
    private Cour cour;
    private String jourEcole;
    private String type;

    public Sceance(Cour cour) {
    this.cour = cour;
    }


    public Sceance(Cour cour,
                   int journeeSemaine,
                   LocalTime heureDebut,
                   LocalTime heureFin,
                   String type)
    {
     this.cour = cour;
     this.heureDebut = heureDebut;
     this.heureFin = heureFin;
     this.type = type;
    }

    public String getJourEcole() {
        return this.jourEcole;
    }

    public LocalTime getHeureDebut() {
        return this.heureDebut;
    }

    public LocalTime getHeureFin() {
        return this.heureFin;
    }

    public String getType() {
        return this.type;
    }

    public Cour getCour() {
        return this.cour;
    }

    public void setCour(Cour cour) {
        this.cour = cour;
    }

    public void setJourEcole(String jourEcole) {
        this.jourEcole = jourEcole;
    }

    public void setHeureDebut(int heure, int minute)
    {
        this.heureDebut = LocalTime.of(heure, minute);
    }

    public void setHeureFin(int heure, int minute)
    {
        this.heureFin = LocalTime.of(heure, minute);
    }

    public void setType(String type) {
        this.type = type;
    }

}
