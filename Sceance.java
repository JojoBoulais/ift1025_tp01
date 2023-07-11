import java.time.LocalTime;
import java.lang.IllegalArgumentException;

public class Sceance {

    static String[] SCEANCE_TYPES = {"Theorique", "Pratiques"};
    static String SCEANCE_EXAMEN = "Examen";
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private Cour cour;
    private int mois;
    private int semaine;
    private String jourEcole;
    private String type;

    public Sceance(Cour cour) {
    this.cour = cour;
    }


    public Sceance(Cour cour,
                   String jourEcole,
                   LocalTime heureDebut,
                   LocalTime heureFin,
                   String type,
                   int mois,
                   int semaine) throws IllegalArgumentException
    {
        if(heureFin.isBefore(heureDebut))
        {
            throw new IllegalArgumentException("***ERROR: heureDebut dois etre avant heureFin***");
        } else if (!type.equals(Sceance.SCEANCE_TYPES[0]) && !type.equals(Sceance.SCEANCE_TYPES[1])
                    && !type.equals(Sceance.SCEANCE_EXAMEN)) {
            throw new IllegalArgumentException("***ERROR: type doit etre sois Theorique ou Pratique ou Examen***");
        } else if (!jourEcole.equals(Horaire.SCHOOL_DAYS[0]) && !jourEcole.equals(Horaire.SCHOOL_DAYS[1]) &&
                    !jourEcole.equals(Horaire.SCHOOL_DAYS[2]) && !jourEcole.equals(Horaire.SCHOOL_DAYS[3]) &&
                    !jourEcole.equals(Horaire.SCHOOL_DAYS[4]))
        {
            throw new IllegalArgumentException("***ERROR: jourEcole doit etre une journee de la semaine.***");
        }

        this.jourEcole = jourEcole;
     this.cour = cour;
     this.heureDebut = heureDebut;
     this.heureFin = heureFin;
     this.type = type;
     this.mois = mois;
     this.semaine = semaine;
    }

    /**
     * Getter attribut jourEcole.
     *
     * @return attribut jourEcole
     */
    public String getJourEcole() {
        return this.jourEcole;
    }

    /**
     * Getter attribut heureDebut.
     *
     * @return attribut heureDebut
     */
    public LocalTime getHeureDebut() {
        return this.heureDebut;
    }

    /**
     * Getter attribut heureFin.
     *
     * @return attribut heureFin
     */
    public LocalTime getHeureFin() {
        return this.heureFin;
    }

    /**
     * Getter attribut type.
     *
     * @return attribut type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Getter attribut cour.
     *
     * @return attribut cour
     */
    public Cour getCour() {
        return this.cour;
    }

    /**
     * Getter attribut mois.
     *
     * @return attribut mois
     */
    public int getMois()
    {
        return this.mois;
    }

    /**
     * Getter attribut semaine.
     *
     * @return attribut semaine
     */
    public int getSemaine()
    {
        return this.semaine;
    }

    /**
     * Setter attribut mois.
     *
     * @param mois Mois a setter.
     */
    public void setMois(int mois)
    {
    this.mois = mois;
    }

    /**
     * Setter attribut semaine.
     *
     * @param semaine Semaine a setter.
     */
    public void setSemaine(int semaine)
    {
        this.semaine = semaine;
    }

    /**
     * Setter attribut cour.
     *
     * @param cour Cour a setter.
     */
    public void setCour(Cour cour) {
        this.cour = cour;
    }

    /**
     * Setter attribut cour.
     *
     * @param jourEcole JourEcole a setter.
     */
    public void setJourEcole(String jourEcole) throws IllegalArgumentException {

        if (!jourEcole.equals(Horaire.SCHOOL_DAYS[0]) && !jourEcole.equals(Horaire.SCHOOL_DAYS[1]) &&
                !jourEcole.equals(Horaire.SCHOOL_DAYS[2]) && !jourEcole.equals(Horaire.SCHOOL_DAYS[3]) &&
                !jourEcole.equals(Horaire.SCHOOL_DAYS[4]))
        {
            throw new IllegalArgumentException("***ERROR: jourEcole doit etre une journee de la semaine.***");
        }


        this.jourEcole = jourEcole;
    }

    /**
     * Setter attribut heureDebut.
     *
     * @param heure Heure a setter.
     * @param minute Minute a setter.
     */
    public void setHeureDebut(int heure, int minute) throws IllegalArgumentException
    {
        LocalTime heureDebut = LocalTime.of(heure, minute);
        if (!(this.heureFin == null) && this.heureFin.isBefore(heureDebut))
        {
            throw new IllegalArgumentException("***ERROR: heureDebut dois etre avant heureFin***");
        }

        this.heureDebut = heureDebut;
    }

    /**
     * Setter attribut heureFin.
     *
     * @param heure Heure a setter.
     * @param minute Minute a setter.
     */
    public void setHeureFin(int heure, int minute) throws IllegalArgumentException
    {

        LocalTime heureFin = LocalTime.of(heure, minute);
        if (!(this.heureDebut == null) && heureFin.isBefore(this.heureDebut))
        {
            throw new IllegalArgumentException("***ERROR: heureDebut dois etre avant heureFin***");
        }

        this.heureFin = heureFin;
    }

    /**
     * Setter attribut type.
     *
     * @param type Type de sceance a setter.
     */
    public void setType(String type) throws IllegalArgumentException {

        if (!type.equals(Sceance.SCEANCE_TYPES[0]) && !type.equals(Sceance.SCEANCE_TYPES[1])) {
            throw new IllegalArgumentException("***ERROR: type doit etre sois Theorique ou Pratique***");
        }

        this.type = type;
    }

}
