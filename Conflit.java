import java.util.ArrayList;
import java.time.LocalTime;
public class Conflit {

    private ArrayList<Sceance> sceances =  new ArrayList<Sceance>();

    private LocalTime heureDebut; // heure debut du conflit

    private LocalTime heureFin; //heure fin du conflit

    private String journee;

    /**
     * Getter attribut sceances
     *
     * @return attribut sceances
     */
    public ArrayList<Sceance> getSceances() {
        return this.sceances;
    }

    /**
     * Getter attribut heureDebut
     *
     * @return attribut heureDebut
     */
    public LocalTime getHeureDebut() {
        return this.heureDebut;
    }

    /**
     * Getter attribut heureFin
     *
     * @return attribut heureFin
     */
    public LocalTime getHeureFin() {
        return this.heureFin;
    }

    /**
     * Getter attribut journee
     *
     * @return attribut journee
     */
    public String getJournee() {
        return this.journee;
    }

    /**
     * Setter attribut sceances
     *
     */
    public void setSceances(ArrayList<Sceance> sceances) {
        this.sceances = sceances;
    }

    /**
     * Ajoute une sceance a l'attribut sceances
     *
     * @param sceance Sceance a ajouter.
     */
    public void addSceance(Sceance sceance)
    {
        this.sceances.add(sceance);
    }


    /**
     * Setter heureDebut
     *
     * @param time Heure a setter. (LocalTime)
     */
    public void setHeureDebut(LocalTime time) throws IllegalArgumentException {

        if (!(this.heureDebut == null) && time.isBefore(this.heureDebut))
        {
            throw new IllegalArgumentException("heureDebut dois etre avant heureFin");
        }

        this.heureDebut = time;
    }


    /**
     * Setter heureFin avec LocalTime
     *
     * @param time Time to set.(LocalTime)
     */
    public void setHeureFin(LocalTime time) {

        if (!(this.heureFin == null) && this.heureFin.isBefore(time))
        {
            throw new IllegalArgumentException("heureDebut dois etre avant heureFin");
        }

        this.heureFin = time;
    }

    public void setJournee(String journee) {
        this.journee = journee;
    }

    /**
     * Retourne les cours en conflits.
     *
     * @return cours en conflits.
     */
    public String coursEnConflit()
    {
        String conflits = "";

        for(Sceance sceance : this.sceances)
        {
            conflits += sceance.getCours().getSigle() + " ";
        }

        return conflits;

    }

}
