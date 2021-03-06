package com.company;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Type Lancer jeu.
 */
import static java.lang.Integer.parseInt;

public class LancerJeu {
    static String name = "";
    public static String choixordi;

    /**
     * Sélection mode menu.
     */
    public static void menuSelectionMode() {
        /**
         * Affiche le menu qui contient le mode jeu :
         * 1. Joueur contre machine
         * 2. Joueur 1 contre Joueur 2
         * 3. Revenir au menu principal
         */
        System.out.println("""
                ***********************************************************
                ****************** Menu selection de mode *****************
                ***********************************************************
                Pour jouer contre la machine tapez -> 1
                Pour jouer contre un autre joueur tapez -> 2
                ***********************************************************
                Pour revenir au menu principal tapez -> 3
                ***********************************************************
                """);

        String choixJouer = "";
        choixJouer = Main.scan.nextLine();
        switch (choixJouer) {
            case "1" -> {
                System.out.println("""
                ***********************************************************
                                 Vous êtes dans le mode:
                                  Joueur contre machine
                ***********************************************************
                """);
                System.out.println("Entrez votre prénom :");
                name = Main.scan.nextLine();

                int manche = 1;
                for (int i = 0; i < 3; i++) {
                    System.out.println("\nManche : " + manche);

                    // Chiffres
                    System.out.println("Etape CHIFFRES");
                    System.out.println("""
                            ***********************************************************
                            ********************** Instructions ***********************
                            ***********************************************************
                            Vous devez choisir parmi 3 rangs
                            Chaque rang contient des plaques numérotées
                            Après le choix de rang, une plaque sera séléctionnée 
                            3 plaques seront aussi séléctionnées par l'IA
                            ***********************************************************
                            - Rang 1 : 20 plaques numérotées de 1 à 10
                            - Rang 2 : 2 plaques de 25 et 2 plaques de 50
                            - Rang 3 : 2 plaques de 75 et 2 plaques de 100
                            ***********************************************************
                            """);

                    List<Integer> listePlaque = new ArrayList<>();
                    SaisieUtilisateur.raffraichirRang();
                    for (int j = 0; j < 3; j++) {
                        System.out.println("Dans quel rang piochez-vous, " + name + " ?");
                        String choix = Main.scan.next();

                        while (!(choix.equals("1") || choix.equals("2") || choix.equals("3"))) {
                            System.out.println("Vous ne pouvez choisir que le rang 1, 2 ou 3.");
                            choix = Main.scan.next();
                        }

                        listePlaque.add(SaisieUtilisateur.saisieRang(choix));
                        choixordi = (int) (Math.random()*(3)+1)+"";
                        System.out.println("La machine à choisit le rang " + choixordi + ".");
                        listePlaque.add(SaisieUtilisateur.saisieRang(choixordi));
                    }

                    String objectif = (int) (Math.random()*(999-101)+101)+"";
                    System.out.println("\nLe chiffre que vous devez attendre avec vos calculs est : " + objectif);
                    System.out.println("\nLes chiffes dont vous disposez pour effectuer ce calcule sont : ");
                    System.out.println(listePlaque);
                    System.out.println("\nVous avez 60 secondes pour réflechir!");
                    Chrono.chrono(60);
                    String calcul = TempsJoueur.tempsJoueurReponseChiffre();
                    RechercheCompte.toutOrdre(listePlaque,objectif);
                    boolean calculcorrect = Verification.verificateurReponseCalcul(listePlaque,calcul);
                    if (calculcorrect){
                        System.out.println("Votre calcul est correct");
                        Verification.scoreJoueur1 += parseInt(RechercheCompte.objectif)-Verification.resultat;
                    } else {
                        System.out.println("Votre calcul est erroné");
                    }

                    // Lettres
                    System.out.println("\nEtape LETTRES");
                    ArrayList<String> listMots = RecupMots.extraireMots();
                    ArrayList<String> tablettre = Lettre.partieLettreUnJoueur();
                    System.out.println("\nVous avez 60 secondes pour réflechir!");
                    Chrono.chrono(60);
                    String reponseUtilisateur = TempsJoueur.tempsJoueurReponseLettre();
                    Boolean verifValiditeReponse = Verification.verificateurReponseMot(listMots,tablettre,reponseUtilisateur);

                    Verification.verificateurReponseMot(listMots,tablettre,reponseUtilisateur);
                    String reponseMachine = RechercheMotLong.motLong(listMots,tablettre);

                    if (verifValiditeReponse){
                        Verification.jouerGagnantMancheLettre(reponseUtilisateur,reponseMachine);
                    } else {
                        System.out.println("L'IA a trouvé le mot " + reponseMachine);
                        Verification.scoreJoueur2 += reponseMachine.length();
                        System.out.println("Votre réponse n'étant point valide l'IA gagne cette étape");
                        System.out.println("L'IA a "+Verification.scoreJoueur2+" points.");
                    }

                    manche +=1;
                }
            }
            case "2" -> {
                System.out.println("""
                ***********************************************************
                                 Vous êtes dans le mode:
                                  Joueur contre joueur
                ***********************************************************
                """);
                System.out.println("Entrez un prénom pour le joueur 1 :");
                String name1 = Main.scan.nextLine();
                System.out.println("Entrez un prénom pour le joueur 2 :");
                String name2 = Main.scan.nextLine();

                int manche = 1;
                for (int i = 0; i < 3; i++) {
                    System.out.println("\nManche : " + manche);

                    // Chiffres
                    System.out.println("Etape CHIFFRES");
                    System.out.println("""
                            ***********************************************************
                            ********************** Instructions ***********************
                            ***********************************************************
                            Vous devez choisir parmi 3 rangs
                            Chaque rang contient des plaques numérotées
                            Après le choix de rang, une plaque sera séléctionnée 
                            ***********************************************************
                            - Rang 1 : 20 plaques numérotées de 1 à 10
                            - Rang 2 : 2 plaques de 25 et 2 plaques de 50
                            - Rang 3 : 2 plaques de 75 et 2 plaques de 100
                            ***********************************************************
                            Lors de la saisie de votre réponse,
                            vous devez saisir l'esemble d'operations mathematiques
                            (addition, sustraction, multiplication et division
                            ***********************************************************
                            """);

                    List<Integer> listePlaque = new ArrayList<>();
                    SaisieUtilisateur.raffraichirRang();
                    for (int j = 0; j < 3; j++) {
                        System.out.println("Dans quel rang piochez-vous, " + name1 + " ?");
                        String choix1 = Main.scan.next();

                        while (!(choix1.equals("1") || choix1.equals("2") || choix1.equals("3"))) {
                            System.out.println("Vous ne pouvez choisir que le rang 1, 2 ou 3.");
                            choix1 = Main.scan.next();
                        }

                        listePlaque.add(SaisieUtilisateur.saisieRang(choix1));
                        System.out.println("Dans quel rang piochez-vous, " + name2 + " ?");
                        String choix2 = Main.scan.next();

                        while (!(choix2.equals("1") || choix2.equals("2") || choix2.equals("3"))) {
                            System.out.println("Vous ne pouvez choisir que le rang 1, 2 ou 3.");
                            choix2 = Main.scan.next();
                        }

                        listePlaque.add(SaisieUtilisateur.saisieRang(choix2));
                    }

                    String objectif = (int) (Math.random()*(999-101)+101)+"";
                    System.out.println("\nLe chiffre que vous devez attendre avec vos calculs est : " + objectif);
                    System.out.println("\nLes chiffes dont vous disposez pour effectuer ce calcule sont : ");
                    System.out.println(listePlaque + "\n");
                    System.out.println("\nVous avez 60 secondes pour réflechir!");
                    Chrono.chrono(60);
                    System.out.println("Votre réponse " + name1 + " :");
                    String calcul1 = TempsJoueur.tempsJoueurReponseChiffre();
                    boolean calculcorrect1 = Verification.verificateurReponseCalcul(listePlaque,calcul1);

                    if (calculcorrect1){
                        System.out.println("Votre calcul est correct");
                        Verification.scoreJoueur1 += parseInt(RechercheCompte.objectif)-Verification.resultat;
                    } else {
                        System.out.println("Votre calcul est erroné");
                    }
                    System.out.println("Votre réponse " + name2 + " :");
                    String calcul2 = TempsJoueur.tempsJoueurReponseChiffre();
                    boolean calculcorrect2 = Verification.verificateurReponseCalcul(listePlaque,calcul2);
                    if (calculcorrect2) {
                        System.out.println("Votre calcul est correct");
                        Verification.scoreJoueur1 += parseInt(RechercheCompte.objectif)-Verification.resultat;
                    } else {
                        System.out.println("Votre calcul est erroné");
                    }

                    // Lettres
                    System.out.println("\nEtape LETTRES");
                    ArrayList<String> listMots = RecupMots.extraireMots();
                    ArrayList<String> tablettre = Lettre.partieLettreDeuxJoueur(name1, name2);
                    System.out.println("\nVous avez 60 secondes pour réflechir!");
                    Chrono.chrono(60);
                    TempsJoueur.tempsJoueurReponseLettre();
                    System.out.println("Joueur1, veuillez saisir votre mot: ");
                    String reponseUtilisateur1 = TempsJoueur.tempsJoueurReponseLettre();
                    System.out.println("Joueur2, veuillez saisir votre mot: ");
                    String reponseUtilisateur2 = TempsJoueur.tempsJoueurReponseLettre();
                    Boolean verifValiditeReponse1 = Verification.verificateurReponseMot(listMots,tablettre,reponseUtilisateur1);
                    Boolean verifValiditeReponse2 = Verification.verificateurReponseMot(listMots,tablettre,reponseUtilisateur2);

                    if (verifValiditeReponse1 && reponseUtilisateur1.equals(reponseUtilisateur2)) {
                        verifValiditeReponse2 = true;
                    }

                    if (verifValiditeReponse1 && verifValiditeReponse2) {
                        if (reponseUtilisateur1.length() > reponseUtilisateur2.length()) {
                            System.out.println("Joueur 1 possède le mot le plus long : " + reponseUtilisateur1);
                            Verification.scoreJoueur1+= reponseUtilisateur1.length();
                        } else if (reponseUtilisateur1.length() < reponseUtilisateur2.length()){
                            System.out.println("Joueur 2 possède le mot le plus long : " + reponseUtilisateur2);
                            Verification.scoreJoueur2+= reponseUtilisateur2.length();
                        } else {
                            if (reponseUtilisateur1=="" && reponseUtilisateur2=="") {
                                System.out.println("Joueur 1 et Joueur 2 n'ont entré aucune réponse");
                            }else {
                                System.out.println("Égalité vos mots sont de mêmes longueur");
                            }
                        }

                    } else if (verifValiditeReponse1 == false && verifValiditeReponse2 == false) {
                        System.out.println("Vous avez tous les deux entré des mots inexistans");
                    } else if (verifValiditeReponse1 && verifValiditeReponse2 == false) {
                        System.out.println("Jouer2 a rentré un mot inexistant,donc Joueur1 remporte cette étape.");
                        Verification.scoreJoueur1+= reponseUtilisateur1.length();
                    } else {
                        System.out.println("Jouer1 a rentré un mot inexistant,donc Joueur2 remporte cette étape.");
                        Verification.scoreJoueur2+= reponseUtilisateur2.length();
                    }
                }
            }
            case "3" -> {
            }
            default -> {
                System.err.println("Option non reconnue");
                menuSelectionMode();
            }
        }
    }
}