import java.util.Calendar;
import java.util.ArrayList;
import java.util.LinkedList;

public class Horaire {


    static String[] SCHOOL_DAYS = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"};
    static String[] SESSIONS = {"Hiver", "Ete", "Automne"};

    private String session= "";
    private Calendar calendar;

    private ArrayList<Cour> cours = new ArrayList<Cour>();

    private ArrayList<Conflit> conflits;

    public Horaire() {
        this.calendar = Calendar.getInstance();
    }


    public Calendar getCalendar() {
        return this.calendar;
    }

    public ArrayList<Cour> getCours() {
        return cours;
    }

    public String getSession()
    {
        return this.session;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public void setCours(ArrayList<Cour> cours) {
        this.cours = cours;
    }


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


    public int getSessionStartMonth()
    {
        if (this.session.equals(Horaire.SESSIONS[0])) {
            return 0;
        } else if (this.session.equals(Horaire.SESSIONS[1])) {
            return 4;
        }else if (this.session.equals(Horaire.SESSIONS[2])) {
            return 8;
        }

        System.out.println("Pas de session setter");
        return -1;
    }

    static public int getSessionStartMonth(String session)
    {
        if (session.equals(Horaire.SESSIONS[0])) {
            return 0;
        } else if (session.equals(Horaire.SESSIONS[1])) {
            return 4;
        }else if (session.equals(Horaire.SESSIONS[2])) {
            return 8;
        }

        System.out.println("Pas de session setter");
        return -1;
    }


    // Modifier ajout de cour
    // On peut ajouter le meme cour car plusieurs horaire sont possible.

    /*
    Ajoute list de cour a l'horaire.
     */
    public void addCours(ArrayList<Cour> cours) {
        this.cours.addAll(cours);
    }

    /*
    Ajoute un cour a l'horaire.
     */
    public void addCours(Cour cour) {
        Cour cour_trouver = this.trouverCour(cour.getSigle());
        if (cour_trouver == null)
        {
            this.cours.add(cour);
            return;
        }

        System.out.println("Cour deja present a l'horaire");
    }

    public void supprimerCour(String sigle) {

        Cour cour = this.trouverCour(sigle);
        if (cour != null)
        {
            this.cours.remove(cour);
            return;
        }

        System.out.println("Cour non present a l'horaire");
    }

    public Cour trouverCour(String sigle) {

        Cour cour_trouver = null;

        for (Cour cour : this.cours) {

            if (cour.getSigle().equals(sigle)) {
                return cour;
            }


        }

        return null;


    }

    public LinkedList<Sceance> sceancesJournee(String journee)
    {
        ArrayList<Sceance> sceances = new ArrayList<Sceance>();
        LinkedList<Sceance> sceancesSorted = new LinkedList<Sceance>();
        int minHeureDebut = 0;
        for (Cour cour : this.cours)
        {

            for(Sceance sceance : cour.getSceances())
            {

                if(sceance.getJourEcole().equals(journee))
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

                if(sc0.getHeureDebut() < sc1.getHeureDebut() | sc0.getHeureDebut() == sc1.getHeureDebut())
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

    public int getCredits()
    {
        int credit=0;

        for(Cour cour : this.cours)
        {
            credit += cour.getCredits();
        }

        return credit

    }



    public LinkedList<Conflit> getConflits(String journee)
    {

        LinkedList<Sceance> sceancesJournee = this.sceancesJournee(journee);
        LinkedList<Conflit> conflits = new LinkedList<>();
        int debutConflit = 0;
        int finConflit = 0;
        Conflit conflitActuel = null;
        Sceance sceanceAnterieur = null;
        for(Sceance sceance : sceancesJournee)
        {
            // Parce que les sceances sont deja classer on regarde
            if((sceance.getHeureDebut() < finConflit)  |
                    (sceance.getHeureDebut() == debutConflit && sceance.getHeureFin() == finConflit) |
                    (sceance.getHeureDebut() < finConflit && sceance.getHeureFin() == finConflit))
            {
                if (conflitActuel == null) {
                    conflitActuel = new Conflit();
                    conflits.addLast(conflitActuel);
                    conflitActuel.setHeureDebut(sceance.getHeureDebut());
                    conflitActuel.setHeureFin(Math.min(sceance.getHeureFin(), finConflit));
                }
                // Plus que deux sceance peuvent etre dans un conflit
                else {
                    if (sceanceAnterieur.getHeureFin() == sceance.getHeureFin())
                    {
                        conflitActuel.setHeureFin(sceanceAnterieur.getHeureFin());
                    }
                    else {
                        conflitActuel.setHeureFin(Math.min(sceance.getHeureFin(), finConflit));
                    }
                }

                if (conflitActuel.getSceances().size() == 0)
                {
                    conflitActuel.addSceance(sceanceAnterieur);
                }
                conflitActuel.addSceance(sceance);
                debutConflit = conflitActuel.getHeureDebut();
                finConflit = conflitActuel.getHeureFin();

            } else {
                debutConflit = sceance.getHeureDebut();
                finConflit = sceance.getHeureFin();
                conflitActuel = null;
            }

            sceanceAnterieur = sceance;
        }

        return conflits;

    }


    @Override
    public String toString() {

    String horaire = "";


    for(String journee : Horaire.SCHOOL_DAYS)
    {
        String current_day ="";
        for(int i=0; i < 200; i++){current_day += "-";}
        current_day += "\n";
        int number_of_space = 8 - journee.length();

        String space = "";
        for(int i=0; i < number_of_space; i++){space += " ";}

        current_day += journee + space + " |";
        LinkedList<Sceance> sceances = this.sceancesJournee(journee);
        LinkedList<Conflit> conflits = this.getConflits(journee);

        // Sceance de la journee
        for(Sceance sceance : sceances)
        {

            current_day += " |" + sceance.getCour().getSigle() + ": "
                    + sceance.getHeureDebut() + "h-" + sceance.getHeureFin() + "h| ";

        }

        // Conflit de la journee
        if (conflits.size() > 0)
        {
            current_day += " * Conflits : ";

            for (Conflit conflit : conflits) {

                current_day += "| " + conflit.coursEnConflit() + conflit.getHeureDebut() + "h-" +
                        conflit.getHeureFin() + "h | ";
            }
        }

        horaire += current_day + "\n";

    }

        for(int i=0; i < 200; i++){horaire += "-";}

    return horaire;

    }

}
