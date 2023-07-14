import java.time.LocalTime;
import java.lang.IllegalArgumentException;

public class Sceance {

    static String[] SCEANCE_TYPES = {"Theorique", "Pratiques"};
    static String SCEANCE_EXAMEN = "Examen";
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private Cours cours;
    private int mois;
    private int semaine;
    private String jourEcole;
    private String type;

    public Sceance(Cours cours) {
    this.cours = cours;
    }

    public Sceance(Cours cours,
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
        } else if (!type.equalsIgnoreCase(Sceance.SCEANCE_TYPES[0]) && !type.equalsIgnoreCase(Sceance.SCEANCE_TYPES[1])
                    && !type.equalsIgnoreCase(Sceance.SCEANCE_EXAMEN)) {
            throw new IllegalArgumentException("***ERROR: type doit etre sois Theorique ou Pratique ou Examen***");
        } else if (!jourEcole.equalsIgnoreCase(Horaire.SCHOOL_DAYS[0]) && !jourEcole.equalsIgnoreCase(Horaire.SCHOOL_DAYS[1]) &&
                    !jourEcole.equalsIgnoreCase(Horaire.SCHOOL_DAYS[2]) && !jourEcole.equalsIgnoreCase(Horaire.SCHOOL_DAYS[3]) &&
                    !jourEcole.equalsIgnoreCase(Horaire.SCHOOL_DAYS[4]))
        {
            throw new IllegalArgumentException("***ERROR: jourEcole doit etre une journee de la semaine.***");
        }

        this.jourEcole = jourEcole;
     this.cours = cours;
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
     * Getter attribut cours.
     *
     * @return attribut cours
     */
    public Cours getCours() {
        return this.cours;
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
     * Setter attribut cours.
     *
     * @param cours Cours a setter.
     */
    public void setCours(Cours cours) {
        this.cours = cours;
    }

    /**
     * Setter attribut cours.
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
     * @param heureDebut Heure a setter.
     */
    public void setHeureDebut(LocalTime heureDebut) throws IllegalArgumentException
    {
        if (!(this.heureFin == null) && this.heureFin.isBefore(heureDebut))
        {
            throw new IllegalArgumentException("***ERROR: heureDebut dois etre avant heureFin***");
        }

        this.heureDebut = heureDebut;
    }

    /**
     * Setter attribut heureFin.
     *
     * @param heureFin Heure a setter.
     */
    public void setHeureFin(LocalTime heureFin) throws IllegalArgumentException
    {

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
