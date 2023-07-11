
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Cour {

    private int numero;
    private String matiere;
    private int credits;
    private int moisDebut;
    private int moisFin;
    private ArrayList<Sceance> sceances = new ArrayList<Sceance>();

    private ArrayList<Sceance> sceancesExamen = new ArrayList<Sceance>();

    /**
     * Constructeur
     *
     * @param matiere Matiere (Trois lettre)
     * @param numero Numero du cour (4 chiffres)
     * @param credits Nombre du credit pour le cour.
     * @param moisDebut Numero de mois de debut du cour.
     * @param moisFin Numero de mois de fin du cour.
     */
    public Cour(String matiere, int numero, int credits, int moisDebut,
                int moisFin)
    {
        this.matiere = matiere;
        this.numero = numero;
        this.credits = credits;
        this.moisDebut = moisDebut;
        this.moisFin = moisFin;
    }

    /**
     * Getter attribut sceances
     *
     * @return attribut sceances.
     */
    public ArrayList<Sceance> getSceances() {
        return this.sceances;
    }

    /**
     * Overload getSceances. Les sceances concordant avec les parametres d'entree.
     *
     * @param mois mois de la sceance.
     * @param semaine semaine de la sceance.
     * @param jourEcole jour ecole de la sceance.
     * @param heureDebut heure debut de la sceance (LocalTime)
     *
     * @return Les sceances concordant avec les parametres d'entree
     */
    public ArrayList<Sceance> getSceances(int mois, int semaine, String jourEcole, LocalTime heureDebut)
    {
        ArrayList<Sceance> sceances = new ArrayList<Sceance>();

        for(Sceance sceance : this.sceances)
        {

            if (sceance.getMois() == mois
                    && sceance.getSemaine() == semaine
                    && sceance.getJourEcole().equals(jourEcole)
                    && sceance.getHeureDebut() == heureDebut)
            {
                sceances.add(sceance);
            }

        }

        return sceances;
    }

    /**
     * Return signe du cour (Matiere + Numero)
     *
     * @return (Matiere + Numero)
     */
    public String getSigle()
    {
        return this.matiere + this.numero;
    }

    /**
     * Getter attribut credits
     *
     * @return Retourne le nombre de credit du cour.
     */
    public int getCredits() {
        return this.credits;
    }

    /**
     * Getter attribut moisDebut
     *
     * @return Retourne attribut moisDebut.
     */
    public int getMoisDebut(){return this.moisDebut;}

    /**
     * Getter attribut moisFin
     *
     * @return Retourne attribut moisFin.
     */
    public int getMoisFin(){return this.moisFin;}

    /**
     * Setter attribut sceances.
     *
     * @param sceances Sceances a setter.
     */
    public void setSceances(ArrayList<Sceance> sceances) {
        this.sceances = sceances;
    }

    /**
     * Setter attribut credits
     *
     * @param credits Credits a setter.
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * Setter attribut moisDebut
     *
     * @param mois Mois a Setter.
     */
    public void setMoisDebut(int mois )
    {
        this.moisDebut = mois;
    }

    /**
     * Setter attribut moisFin
     *
     * @param mois Mois a Setter.
     */
    public void setMoisFin(int mois )
    {
        this.moisFin = mois;
    }

    /**
     * Setter pour attributs moisDebut et moisFin.
     *
     * @param debut Mois a Setter.
     * @param fin Mois a Setter.
     */
    public void setMoisDebutFin(int debut, int fin)
    {
        this.moisDebut = debut;
        this.moisFin = fin;
    }

    /**
     * Add une sceance a l'attribut sceances.
     *
     * @param sceance sceance a ajouter.
     */
    private void addSceance(Sceance sceance)
    {
        this.sceances.add(sceance);
    }

    /**
     * Ajoute une INSTANCE d'une sceance selon les parametres.
     *
     * @param jourEcole jourEcole pour la sceance.
     * @param heureDebut heureDebut pour la sceance.
     * @param heureFin heureFin pour la sceance.
     * @param type type pour la sceance.
     * @param mois mois pour la sceance.
     * @param semaine semaine pour la sceance.
     */
    public void addInstanceSceance(String jourEcole, LocalTime heureDebut,
                                    LocalTime heureFin, String type,
                                    int mois, int semaine)
    {
        Sceance sceance = new Sceance(this, jourEcole, heureDebut,
                heureFin, type, mois, semaine);

        this.sceances.add(sceance);
    }

    /**
     * Ajoute toutes les INSTANCES d'une sceance pour une session (4 mois)
     * a l'attribut sceances.
     *
     * @param jourEcole jourEcole pour les sceances.
     * @param heureDebut heureDebut pour les sceances.
     * @param heureFin heureFin pour les sceances.
     * @param type type pour les sceances.
     */
    public void addSceances(String jourEcole, LocalTime heureDebut,
                           LocalTime heureFin, String type)
    {
        try
        {
        // On assume que les mois ont outs 4 semaines sans trop entrer dans
        // les details.
        for(int mois = this.moisDebut; mois < this.moisFin+1; mois++) {
            for (int semaine = 1; semaine < 5; semaine++) {

                Sceance sceance = new Sceance(this, jourEcole, heureDebut,
                        heureFin, type, mois, semaine);

                this.sceances.add(sceance);
            }
        }
        } catch (IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
            }

    }

    /**
     * Enleve toute les INSTANCES des sceances concordant avec le jour et l'heure du debut
     * des sceances.
     *
     * @param jourEcole jourEcole pour les sceances.
     * @param heureDebut heureDebut pour les sceances.
     */
    public void enleverSceance(String jourEcole, LocalTime heureDebut)
    {

        ArrayList<Sceance> sceancesbckup = new ArrayList<Sceance>();

        for(Sceance sceance : this.sceances)
        {
            sceancesbckup.add(sceance);
        }


        for(Sceance sceance : this.sceances)
        {
            if (sceance.getJourEcole().equals(jourEcole) &&
            sceance.getHeureDebut() == heureDebut)
            {
                sceancesbckup.remove(sceance);
            }
        }

        this.sceances = sceancesbckup;

    }

    /**
     * Enleves l'INSTANCES de sceance mentionner par les parametres d'entree.
     *
     * @param mois mois pour la sceance a enlever.
     * @param semaine semaine pour la sceance a enlever.
     * @param jourEcole jourEcole pour la sceance a enlever.
     * @param heureDebut heureDebut pour la sceance a enlever.
     */
    public void enleverInstanceSceance(int mois, int semaine, String jourEcole, LocalTime heureDebut)
    {
        ArrayList<Sceance> sceancesbckup = new ArrayList<Sceance>();
        for(Sceance sceance : this.sceances)
        {
            sceancesbckup.add(sceance);
        }

        for(Sceance sceance : this.sceances)
        {

            if (sceance.getMois() == mois
                && sceance.getSemaine() == semaine
                && sceance.getJourEcole().equals(jourEcole)
                && sceance.getHeureDebut() == heureDebut)
            {
                sceancesbckup.remove(sceance);
            }

        }

        this.sceances = sceancesbckup;

    }

    /**
     * Ajoute une sceance de type Examen selon les parametres d'entree.
     *
     * @param jourEcole jourEcole pour la sceance.
     * @param heureDebut heureDebut pour la sceance.
     * @param heureFin heureFin pour la sceance.
     * @param mois mois pour la sceance.
     * @param semaine semaine pour la sceance.
     */
    public void addSceanceExamen(String jourEcole, LocalTime heureDebut,
                                 LocalTime heureFin, int mois, int semaine)
    {
        try {
            Sceance sceance = new Sceance(this, jourEcole, heureDebut,
                    heureFin, Sceance.SCEANCE_EXAMEN, mois, semaine);
            this.sceances.add(sceance);
        } catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Retourne une sceance selon les parametre d'entree.
     * (Souvent utilise pour trouver une sceance et la modifier)
     *
     * @param mois mois pour la sceance
     * @param semaine semaine pour la sceance
     * @param jourEcole jourEcole pour la sceance
     * @param heureDebut heureDebut pour la sceance
     *
     * @return La sceance trouver ou null si non trouver.
     */
    public Sceance getInstanceSceance(int mois, int semaine,String jourEcole, LocalTime heureDebut)
    {
        for (Sceance sceance : this.sceances)
        {
            if (sceance.getMois() == mois &&
            sceance.getSemaine() == semaine &&
            sceance.getJourEcole().equals(jourEcole) &&
            sceance.getHeureDebut() == heureDebut)
            {
                return sceance;
            }
        }
        return null;
    }
}
