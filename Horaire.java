import java.util.Calendar;
import java.util.ArrayList;
import java.util.LinkedList;

public class Horaire {


    static String[] SCHOOL_DAYS = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"};

    private Calendar calendar;

    private ArrayList<Cour> cours;

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

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public void setCours(ArrayList<Cour> cours) {
        this.cours = cours;
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


    @Override
    public String toString() {

    String horaire = "";


    for(String journee : Horaire.SCHOOL_DAYS)
    {
        String current_day = journee + " :";
        LinkedList<Sceance> sceances = this.sceancesJournee(journee);

        for(Sceance sceance : sceances)
        {

            current_day += " *" + sceance.getCour().getSigle()
                    + sceance.getHeureDebut() + "-" + sceance.getHeureFin() + "* ";

        }

        horaire += current_day + "\n\n";

    }

    return horaire;

    }


}
