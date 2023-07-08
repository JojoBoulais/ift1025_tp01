
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
public class Cour {

    private int numero;
    private String matiere;
    private int credits;

    private ArrayList<Sceance> sceances;

    public Cour(String matiere, int numero, int credits)
    {
        this.matiere = matiere;
        this.numero = numero;
        this.credits = credits;
    }

    public ArrayList<Sceance> getSceances() {
        return sceances;
    }

    public String getSigle()
    {
        return this.matiere + this.numero;
    }

    public int getCredits() {
        return credits;
    }

    public void setSceances(ArrayList<Sceance> sceances) {
        this.sceances = sceances;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void addSceance(Sceance sceance)
    {
        this.sceances.add(sceance);
    }

    public void addSceance(int date, LocalTime heureDebut,
                           LocalTime heureFin, String type)
    {
        Sceance sceance = new Sceance(this, date, heureDebut,
                                        heureFin, type);
        this.sceances.add(sceance);
    }

}
