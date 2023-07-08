import java.util.ArrayList;
public class Conflit {

    private ArrayList<Sceance> sceances =  new ArrayList<Sceance>();

    private int duree;

    private int heureDebut; // heure debut du conflit

    private int heureFin; //heure fin du conflit

    private String journee;

    public ArrayList<Sceance> getSceances() {
        return sceances;
    }

    public int getDuree() {
        return duree;
    }

    public int getHeureDebut() {
        return heureDebut;
    }

    public int getHeureFin() {
        return heureFin;
    }

    public String getJournee() {
        return journee;
    }

    public void setSceances(ArrayList<Sceance> sceances) {
        this.sceances = sceances;
    }

    public void addSceance(Sceance sceance)
    {
        this.sceances.add(sceance);
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setHeureDebut(int heureDebut) {
        this.heureDebut = heureDebut;
    }

    public void setHeureFin(int heureFin) {
        this.heureFin = heureFin;
    }

    public void setJournee(String journee) {
        this.journee = journee;
    }

    public String coursEnConflit()
    {
        String conflits = "";

        for(Sceance sceance : this.sceances)
        {
            conflits += sceance.getCour().getSigle() + " ";
        }

        return conflits;

    }

}
