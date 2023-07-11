import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        // CREATIONS DE COURS:
        //
        //
        Horaire horaire;

        try {
            horaire = new Horaire(Horaire.SESSIONS[0]);
        } catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        } finally {
            horaire = new Horaire(Horaire.SESSIONS[0]);
        }

        Cour ift1025 = new Cour("IFT", 1025, 3,
                horaire.getMoisDebutSession(), horaire.getMoisFinSession());
        ift1025.addSceances("Lundi",
                LocalTime.of(10,0),
                LocalTime.of(12,0),
                Sceance.SCEANCE_TYPES[0]);
        ift1025.addSceances("Mercredi",
                LocalTime.of(10,0),
                LocalTime.of(12,0),
                Sceance.SCEANCE_TYPES[1]);
        Cour ift1015 = new Cour("IFT", 1015, 3,
                horaire.getMoisDebutSession(), horaire.getMoisFinSession());
        ift1015.addSceances("Lundi",
                LocalTime.of(11,0),
                LocalTime.of(13,0),
                Sceance.SCEANCE_TYPES[0]);
        Cour ift1005 = new Cour("IFT", 1005, 3,
                horaire.getMoisDebutSession(), horaire.getMoisFinSession());
        ift1005.addSceances("Lundi",
                LocalTime.of(13,0),
                LocalTime.of(15,0),
                Sceance.SCEANCE_TYPES[1]);
        Cour ift2015 = new Cour("IFT", 2015, 3,
                horaire.getMoisDebutSession(), horaire.getMoisFinSession());
        ift2015.addSceances("Lundi",
                LocalTime.of(15,0),
                LocalTime.of(16,0),
                Sceance.SCEANCE_TYPES[1]);
        Cour ift2035 = new Cour("IFT", 2035, 3,
                horaire.getMoisDebutSession(), horaire.getMoisFinSession());
        ift2035.addSceances("Lundi",
                LocalTime.of(16,30),
                LocalTime.of(18,30),
                Sceance.SCEANCE_TYPES[1]);
        Cour ift1215 = new Cour("IFT", 1215, 3,
                horaire.getMoisDebutSession(), horaire.getMoisFinSession());
        ift1215.addSceances("Mardi",
                LocalTime.of(8,30),
                LocalTime.of(11,30),
                Sceance.SCEANCE_TYPES[1]);
        Cour ift1227 = new Cour("IFT", 1227, 3,
                horaire.getMoisDebutSession(), horaire.getMoisFinSession());
        ift1227.addSceances("Mardi",
                LocalTime.of(12,30),
                LocalTime.of(14,30),
                Sceance.SCEANCE_TYPES[1]);
        Cour ift2245 = new Cour("IFT", 2245, 3,
                horaire.getMoisDebutSession(), horaire.getMoisFinSession());
        ift2245.addSceances("Jeudi",
                LocalTime.of(12,0),
                LocalTime.of(16,0),
                Sceance.SCEANCE_TYPES[1]);
        Cour ift2255 = new Cour("IFT", 2255, 3,
                horaire.getMoisDebutSession(), horaire.getMoisFinSession());
        ift2255.addSceances("Jeudi",
                LocalTime.of(10,0),
                LocalTime.of(13,0),
                Sceance.SCEANCE_TYPES[1]);
        Cour ift1065 = new Cour("IFT", 1065, 3,
                horaire.getMoisDebutSession(), horaire.getMoisFinSession());
        ift1065.addSceances("Vendredi",
                LocalTime.of(9,0),
                LocalTime.of(13,0),
                Sceance.SCEANCE_TYPES[1]);
        Cour ift1575 = new Cour("IFT", 1575, 3,
                horaire.getMoisDebutSession(), horaire.getMoisFinSession());
        ift1575.addSceances("Vendredi",
                LocalTime.of(13,0),
                LocalTime.of(16,0),
                Sceance.SCEANCE_TYPES[1]);


        // UNIT TESTS
        //
        //
        System.out.println("\n\n\n\n--------------UNIT TEST---------------\n\n\n\n");

        // EXCEPTIONS
        //

        System.out.println("\n---| Verification EXCEPTIONS |---\n");

        // Test ajout sceances heureFin avant heureDebut
        ift1025.addSceances("Lundi", LocalTime.of(13,0),
                LocalTime.of(9,0), Sceance.SCEANCE_TYPES[1]);

        // Test jourEcole invalide
        ift1025.addSceances("TOTO", LocalTime.of(8,0),
                LocalTime.of(9,0), Sceance.SCEANCE_TYPES[1]);

        // Test type invalide
        ift1025.addSceances("Lundi", LocalTime.of(8,0),
                LocalTime.of(9,0), "TOTO");
        // horaire invalide
        try {
            Horaire testHoraire = new Horaire("TOTO");
        } catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }


        // Verification attributs Cour
        //
        System.out.println("\n---| Verification attributs Cour |---\n");
        if (ift1025.getSceances().size() == 2*4*4)
        {
            System.out.println("ift1025 contient 32 sceances\n");
        }

        System.out.println("Retrait d'une sceance (Toute les instances)");
        ift1025.enleverSceance("Lundi", LocalTime.of(10,0));
        if (ift1025.getSceances().size() == 4*4)
        {
            System.out.println("ift1025 contient 16 sceances\n");
        }
        System.out.println("(Rajout de la sceance pour test suivants)\n");
        ift1025.addSceances("Lundi",
                LocalTime.of(10,0),
                LocalTime.of(12,0),
                Sceance.SCEANCE_TYPES[0]);

        System.out.println("Retrait d'une INSTANCE d'une sceance");
        ift1025.enleverInstanceSceance(1,1,"Lundi",LocalTime.of(10,0));
        if (ift1025.getSceances().size() == 2*4*4 - 1)
        {
            System.out.println("ift1025 contient 31 sceances\n");
        }
        System.out.println("(Rajout de l'INSTANCE de la sceance pour test suivants)\n");
        ift1025.addInstanceSceance("Lundi",
                LocalTime.of(10, 0),
                LocalTime.of(12, 0),
                Sceance.SCEANCE_TYPES[0],
                1,1);
        if (ift1025.getSceances().size() == 2*4*4)
        {
            System.out.println("ift1025 contient 32 sceances\n");
        }

        System.out.println("Ajout Sceance Examen\n");
        ift1025.addSceanceExamen("Mercredi",
                LocalTime.of(13,0),
                LocalTime.of(15,0),
                2,4);

        Sceance examen = ift1025.getSceances(2,4,"Mercredi", LocalTime.of(13,0)).get(0);
        if (ift1025.getSceances().size() == 2*4*4 + 1 &&
                examen.getType().equals("Examen")
        )
        {
            System.out.println("Examen ... Mois: 2 Semaine: 4 Mercredi\n");
        }

        if (ift1025.getSigle().equals("IFT1025"))
        {
            System.out.println("Sigle ift1025 est \"IFT1025\"");
        }
        if (ift1025.getCredits() == 3)
        {
            System.out.println("ift1025 contient 3 credits\n");
        }

        System.out.println("\nTest getInstanceSceance\n");
        Sceance instanceTrouver = ift1005.getInstanceSceance(1,1,
                "Lundi", LocalTime.of(13,0));
        if (instanceTrouver != null)
        {
            System.out.println("\nInstance de sceance trouver.\n");
        }


        // Verification attributs Sceance
        //
        System.out.println("\n---| Verification attributs Sceance |---\n");
        if (ift1025.getSceances().get(0).getHeureDebut().getHour() == 10 &&
                ift1025.getSceances().get(0).getHeureDebut().getMinute() == 0)
        {
            System.out.println("Premiere sceance de ift1025 debut a 10h00");
        }
        if (ift1025.getSceances().get(0).getHeureFin().getHour() == 12 &&
                ift1025.getSceances().get(0).getHeureFin().getMinute() == 0)
        {
            System.out.println("Premiere sceance de ift1025 termine a 12h00");
        }
        if (ift1025.getSceances().get(0).getType().equals("Theorique"))
        {
            System.out.println("Premiere sceance de ift1025 est Theorique\n");
        }
        Sceance premiereInstanceSceance = ift1025.getSceances(1,1,"Lundi", LocalTime.of(10, 0)).get(0);
        if (premiereInstanceSceance.getMois() == 1 &&
                premiereInstanceSceance.getSemaine() == 1 &&
                premiereInstanceSceance.getJourEcole().equals("Lundi"))
        {
            System.out.println("Moment de premiere instance de la premiere sceance de ift1025 est... Mois: " + premiereInstanceSceance.getMois() +
                    " Semaine: " + premiereInstanceSceance.getSemaine() + " Journee: " + premiereInstanceSceance.getJourEcole() );
        }

        // Verification attributs Horaire
        //
        System.out.println("\n---| Verification attributs Horaire |---\n");
        ArrayList<Cour> cours = new ArrayList<Cour>();

        // Ajout de 11 cour (10 maximum)
        cours.add(ift1025);
        cours.add(ift1015);
        cours.add(ift1005);
        cours.add(ift2015);
        cours.add(ift2035);
        cours.add(ift1215);
        cours.add(ift1227);
        cours.add(ift2245);
        cours.add(ift2255);
        cours.add(ift1065);
        cours.add(ift1575);
        horaire.addCours(cours);

        if (horaire.getCours().size() == 0)
        {
            System.out.println("On ne peut ajouter plus de 10 Cours a l'horaire.");
        }

        // Ajout de 10 Cours
        cours.remove(10);
        System.out.println("(Retrait d'un cour)");
        horaire.addCours(cours);
        if (horaire.getCours().size() == 10)
        {
            System.out.println("Ajout de 10 Cours reussi.");
        }


        System.out.println("\nTest supprimerCour");

        horaire.supprimerCour("IFT1025");
        if (horaire.getCours().size() == 9)
        {
            System.out.println("IFT1025 Supprimer");
        }
        System.out.println("\n(Rajout de ift1025)");
        horaire.addCours(ift1025);


        System.out.println("\nTest trouverCour");
        Cour ift1005trouver = horaire.trouverCour("IFT1025");
        if (ift1005trouver.getSigle().equals("IFT1025"))
        {
            System.out.println("\n IFT1025 Trouver a l'horaire");
        }

        System.out.println("\nTest sceancesJournee");
        LinkedList<Sceance> sceancesJournee = horaire.sceancesJournee(1,1,"Lundi");
        if (sceancesJournee.size() == 5)
        {
            System.out.println("\n 5 Sceance lundi premiere semaine, premier mois");
        }

        // Conflits Horaire
        //
        System.out.println("\n---| Verification Conflits Horaire|---\n");

        if (horaire.getConflitSemaine(1,1).size() == 2)
        {
            System.out.println("Actuellement 2 conflits");
            System.out.println("Warning de conflit :" +
                    horaire.getConflitWarning(1,1));
        }

        System.out.println("\n(Occasionne volontairement un troisieme conflit)\n");
        Sceance instanceAModifier = ift1005.getInstanceSceance(1,1,
                                    "Lundi", LocalTime.of(13,0));
        instanceAModifier.setHeureDebut(12, 0);
        if (horaire.getConflitSemaine(1,1).size() == 3)
        {
            System.out.println("Actuellement 3 conflits");
            System.out.println("Warning de conflit :" +
                    horaire.getConflitWarning(1,1));
        }

        instanceAModifier.setHeureDebut(12, 0);

        System.out.println("Test getConflits");
        if (horaire.getConflits(1,1, "Lundi").size() == 2)
        {
            System.out.println("2 Conflit Lundi premier mois deuxieme semaine");
        }


        System.out.println("\nWarning de credit: ");
        if (!horaire.getCreditWarning().equals(""))
        {
            System.out.println(horaire.getCreditWarning());
        }

        System.out.println("\n\nEXEMPLE AFFICHAGE D'UNE SEMAINE (MOIS: 1 SEMAINE: 1) (3 Conflits)\n");

        System.out.println(horaire.printSemaine(1, 1));


        System.out.println("\n\nEXEMPLE AFFICHAGE D'UNE SEMAINE (MOIS: 2 SEMAINE: 1) (2 Conflits)\n");

        System.out.println(horaire.printSemaine(2, 1));


        System.out.println("\n\nEXEMPLE AFFICHAGE D'UNE SEMAINE (MOIS: 2 SEMAINE: 4) (1 Examen)\n");

        System.out.println(horaire.printSemaine(2, 4));

        // IMPLEMENTER TRAVAIL ABDEL
        // FAIRE RAPPORT
        // DISTANCE ENTRE EXAMENS... a voir
    }
}
