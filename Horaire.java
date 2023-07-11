import java.util.Calendar;
import java.util.ArrayList;
import java.util.LinkedList;

public class Horaire {


    static String[] SCHOOL_DAYS = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"};
    static String[] SESSIONS = {"Hiver", "Ete", "Automne"};
    static int MAX_COURS = 10;
    static int MAX_CONFLITS = 3;
    static int MAX_CREDIT = 15;
    private String session= "";

    private ArrayList<Cour> cours = new ArrayList<Cour>();

    private ArrayList<Conflit> conflits;

    /**
     * Constructor de l'horaire
     *
     * @param session Session a mentionner parmis `Hiver` `Ete` et `Automne`.
     */
    public Horaire(String session) throws IllegalArgumentException
    {
        if (!session.equals(Horaire.SESSIONS[0]) && !session.equals(Horaire.SESSIONS[1]) &&
                !session.equals(Horaire.SESSIONS[2]))
        {
            throw new IllegalArgumentException("***ERROR: Horaire doit etre sois `Hiver`, `Ete` ou `Automne`***");
        }

        this.session = session;

    }

    /**
     * Getter attribut cours.
     *
     * @return attribut cours
     */
    public ArrayList<Cour> getCours() {
        return this.cours;
    }

    /**
     * Retourn attribut session.
     *
     * @return attribut session.
     */
    public String getSession()
    {
        return this.session;
    }

    /**
     * Setter attributs cour.
     *
     * @param cours ArrayList de cours.
     */
    public void setCours(ArrayList<Cour> cours) {
        this.cours = cours;
    }

    /**
     * Setter attribut sessions parmis `Hiver`, `Ete` et `Automne`
     *
     * @param session
     */

    public void setSession(String session)
    {
        for(String s : Horaire.SESSIONS)
        {
            if (s.equals(session))
            {
                this.session = session;
                return;
            }
        }

        return;
    }

    /**
     * Getter mois du debut de session selon l'attribut session.
     *
     * @return mois du debut de sessions.
     */
    public int getMoisDebutSession()
    {

     if (this.session.equals(Horaire.SESSIONS[0]))
     {
         return 1;
     } else if (this.session.equals(Horaire.SESSIONS[1])) {
         return 5;
     }else if (this.session.equals(Horaire.SESSIONS[2])) {
         return 9;
     }
        return -1;
    }

    /**
     * Getter mois du fin de session selon l'attribut session.
     *
     * @return mois du fin de sessions.
     */
    public int getMoisFinSession()
    {
        if (this.session.equals(Horaire.SESSIONS[0]))
        {
            return 4;
        } else if (this.session.equals(Horaire.SESSIONS[1])) {
            return 8;
        }else if (this.session.equals(Horaire.SESSIONS[2])) {
            return 12;
        }
        return -1;
    }

    // Modifier ajout de cour
    // On peut ajouter le meme cour car plusieurs horaire sont possible.

    /**
     * Ajoute des cours a l'attribut cours
     *
     * @param cours Cours a ajouter a l'attribut cours.
     */
    public void addCours(ArrayList<Cour> cours) {

        if (cours.size() + this.cours.size() > Horaire.MAX_COURS)
        {
            System.out.println("Impossible d'ajouter " + cours.size() + " cours a l'horaire\n" +
                    "Deja " + this.cours.size() + " cours a l'horaire.");
            return;
        }

        // Empeche d'ajouter deux fois le meme cour.
        for(Cour cour : cours)
        {
            this.addCours(cour);
        }
    }

    /**
     * Si un cour n'est pas deja present a l'horaire il est ajouter a l'attribut cours.
     *
     * @param cour Cours a ajouter a l'attribut cour.
     */
    public void addCours(Cour cour) {

        if (this.cours.size() == Horaire.MAX_COURS)
        {
            System.out.println("Impossible de mettre plus de 10 cours a l'horaire\n" +
                    "Deja " + this.cours.size() + " cours a l'horaire.");
            return;
        }

        Cour cour_trouver = this.trouverCour(cour.getSigle());
        if (cour_trouver == null)
        {
            this.cours.add(cour);
            return;
        }

        System.out.println("Cour deja present a l'horaire");
    }

    /**
     * Supprime un cour a l'horaire selon le sigle du cour.
     *
     * @param sigle Sigle du cour a supprimer de l'horaire.
     */
    public void supprimerCour(String sigle) {

        Cour cour = this.trouverCour(sigle);
        if (cour != null)
        {
            this.cours.remove(cour);
            return;
        }

        System.out.println("Cour non present a l'horaire");
    }

    /**
     * Trouver un cour a l'horaire selon le sigle du cour.
     *
     * @param sigle sigle du cour a trouver
     * @return Cour trouver ou null.
     */
    public Cour trouverCour(String sigle) {

        for (Cour cour : this.cours) {

            if (cour.getSigle().equals(sigle)) {
                return cour;
            }
        }

        return null;
    }

    /**
     * Getter nombre de credit selons les cour de l'horraire.
     *
     * @return nombre de credit de l'horaire.
     */
    public int getCredits()
    {
        int credit=0;

        for(Cour cour : this.cours)
        {
            credit += cour.getCredits();
        }

        return credit;

    }

    /**
     * Retourne les sceance d'un journee mentionner par les parametre d'entree.
     * (Les sceances sont retournee en ordre selon leure heure de debut.)
     *
     * @param mois Mois de la journee.
     * @param semaine semaine de la journee.
     * @param journee journee de la journee.
     *
     * @return Les sceances de la journee.
     */
    public LinkedList<Sceance> sceancesJournee(int mois, int semaine, String journee)
    {
        ArrayList<Sceance> sceances = new ArrayList<Sceance>();
        LinkedList<Sceance> sceancesSorted = new LinkedList<Sceance>();
        for (Cour cour : this.cours)
        {

            for(Sceance sceance : cour.getSceances())
            {

                if(sceance.getJourEcole().equals(journee)
                    && sceance.getMois() == mois
                    && sceance.getSemaine() == semaine)
                {
                    sceances.add(sceance);
                }
            }// Sceances

        } // Cours

        // Sorting Sceances

        for(Sceance sceance : sceances)
        {
            Sceance sc0;
            Sceance sc1;

            for(int i=0; i < sceances.size()-1; i++)
            {

                sc0 = sceances.get(i);
                sc1 = sceances.get(i+1);

                if(sc0.getHeureDebut().isBefore(sc1.getHeureDebut()) | sc0.getHeureDebut().equals(sc1.getHeureDebut()))
                {
                    continue;
                }
                else
                {
                    sceances.set(i, sc1);
                    sceances.set(i+1, sc0);
                }

            }

        } // end sorting

        for(Sceance sceance : sceances)
        {
            sceancesSorted.addLast(sceance);
        }

        return sceancesSorted;

    }

    /**
     * Calcul et retourne les conflits d'une journee mentionner par les parametres d'entree.
     *
     * @param mois Mois de la journee.
     * @param semaine Semaine de la journee.
     * @param journee Journee de la journee.
     *
     * @return Les conflit de la journee mentionner.
     */
    public LinkedList<Conflit> getConflits(int mois, int semaine, String journee)
    {

        LinkedList<Sceance> sceancesJournee = this.sceancesJournee(mois, semaine, journee);
        LinkedList<Conflit> conflits = new LinkedList<>();
        Conflit conflitActuel = null;
        Sceance sceanceAnterieur = null;
        for(Sceance sceance : sceancesJournee)
        {
            // Parce que les sceances sont deja classer par heure de debut
            // on regarde si le debut d'une sceance est avant la fin de
            // la sceance anterieur.

            if(sceanceAnterieur != null && sceance.getHeureDebut().isBefore(sceanceAnterieur.getHeureFin()))
            {
                    conflitActuel = new Conflit();
                    conflits.addLast(conflitActuel);
                    conflitActuel.setHeureDebut(sceance.getHeureDebut()); // good
                        conflitActuel.setHeureFin(sceanceAnterieur.getHeureFin());

                {
                        conflitActuel.setHeureFin(sceanceAnterieur.getHeureFin());
                    }
                // Plus que deux sceance peuvent etre dans un conflit
                //else {
                    //conflitActuel.setHeureFin(sceanceAnterieur.getHeureFin());
                //}

                // Ajoute premiere sceance causant le conflit
                if (conflitActuel.getSceances().size() == 0)
                {
                    conflitActuel.addSceance(sceanceAnterieur);
                }

                // Ajoute sceance actuelle
                conflitActuel.addSceance(sceance);

            }

            sceanceAnterieur = sceance;
        }

        return conflits;

    }

    /**
     * Retourne tout les conflit de la semaine mentionner par les paramettre d'entree.
     *
     * @param mois Mois de la semaine.
     * @param semaine Semaine de la semaine.
     *
     * @return Les conflits de la semaine.
     */
    public ArrayList<Conflit> getConflitSemaine(int mois, int semaine)
    {

        ArrayList<Conflit> conflitsSemaine = new ArrayList<Conflit>();

        for(String journee : Horaire.SCHOOL_DAYS)
        {
            LinkedList<Conflit> conflits = this.getConflits(mois, semaine, journee);

            for(Conflit conflit : conflits)
            {
                conflitsSemaine.add(conflit);
            }

        }

        return conflitsSemaine;

    }

    /**
     * Affichage de l'horaire d'une semaine mentionner par les parametre d'entree.
     * On affiche toutes les sceances pour chaque journee ainsi que les conflits
     * de la journee si il en exist.
     * Affiche un warning si il y a 3 conflits et plus et un Warning si le
     * nombre de credit pour l'horaire depasse 15 credit (Horaire surcharger.)
     *
     * @param mois Mois de la semaine a afficher.
     * @param semaine Semaine de la semaine a afficher.
     *
     * @return Tableau de l'horaire de la semaine en String.
     */
    public String printSemaine(int mois, int semaine)
    {
        String horaire = "";
        String emptyLine = "\n         |";

        for(String journee : Horaire.SCHOOL_DAYS)
        {
            String current_day ="";
            for(int i=0; i < 100; i++){current_day += "-";}
            current_day += "\n";
            int number_of_space = 8 - journee.length();

            String space = "";
            for(int i=0; i < number_of_space; i++){space += " ";}

            current_day += journee + space + " |";
            LinkedList<Sceance> sceances = this.sceancesJournee(mois, semaine, journee);
            LinkedList<Conflit> conflits = this.getConflits(mois, semaine, journee);
            int sceanceCounter = 0;
            // Sceance de la journee
            for(Sceance sceance : sceances)
            {
                sceanceCounter += 1;
                current_day += " |" + sceance.getCour().getSigle() + ": "
                        + sceance.getHeureDebut() + "h-" + sceance.getHeureFin() + "h| ";

                if (sceanceCounter == 3)
                {
                    current_day += emptyLine;
                    sceanceCounter = 0;
                }

            }

            // Conflit de la journee
            if (conflits.size() > 0)
            {
                current_day += emptyLine + emptyLine + " * Conflits : ";

                for (Conflit conflit : conflits) {

                    current_day += "| " + conflit.coursEnConflit() + conflit.getHeureDebut() + "h-" +
                            conflit.getHeureFin() + "h | ";
                }
            }

            horaire += current_day + "\n";

        }

        for(int i=0; i < 100; i++){horaire += "-";}

        horaire += this.getConflitWarning(mois, semaine);
        horaire += this.getCreditWarning();

        return horaire;

    }

    /**
     * Utilise la methode `printSemaine` de la premiere semaine de l'horaire
     * pour un apercu rapide de l'horaire de la sessions.
     *
     * @return Tableau de l'horaire de la semaine en String.
     */
    @Override
    public String toString() {
        // Print premiere semaine de cours (Sans examen)
        String horaire = this.printSemaine(this.getMoisDebutSession(), 1);
        return horaire;
    }

    /**
     * Genere un Warning si 3 conflit + pour une semaine mentionner par les parametre d'entree.
     *
     * @param mois Mois de la semaine.
     * @param semaine Semaine de la semaine.
     *
     * @return warning ou empty string.
     */
    public String getConflitWarning(int mois, int semaine )
    {
        String warning = "";
        if (this.getConflitSemaine(mois, semaine).size() >= Horaire.MAX_CONFLITS)
        {
            warning = "\n***ATTENTION 3 conflits et + a l'horaire***";
        }

        return warning;
    }

    /**
     * Genere un Warning si l'horaire contient plus de 15 credits.
     *
     * @return warning ou empty string.
     */
    public String getCreditWarning()
    {
        String warning = "";
        if (this.getCredits() > Horaire.MAX_CREDIT)
        {
            warning = "\n***ATTENTION + de 15 credits a l'horaire***";
        }
        return warning;
    }

}
