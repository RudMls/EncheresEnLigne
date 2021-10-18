package app;

import app.dao.*;
import app.model.*;

import java.sql.Date;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Inscription
        Inscription();

        // Connexion
        //Connexion();


        OptionEnchereDao optionEnchereDao = new OptionEnchereDao();
        ArrayList<OptionEnchere> optionEncheres = optionEnchereDao.findAll();

        System.out.println(optionEncheres);

    }

    public static void Inscription(){
        Compte compte = new Compte();
        compte.setEmail("r.monlouis@gmail.com");
        compte.setMdp("pwd");
        compte.setRole(Role.MEMBRE);

        CompteDao compteDao = new CompteDao();
        if (!compteDao.find(compte.getEmail())) {

            compteDao.create(compte);
            Membre membre = new Membre(compte);
            membre.setNom("Monlouis");
            membre.setPrenom("Ruddy");
            membre.setDateNaissance(Date.valueOf("1997-08-05"));
            membre.setCodePostal("31300");
            membre.setAdressePostale("58 Avenue Etienne Billières");
            membre.setVille("Toulouse");
            membre.setPays("France");
            MembreDao membreDao = new MembreDao();
            membreDao.create(membre);
        } else {
            System.out.println("Le compte existe déja");
        }

    }
    
    public static void creerArticle() {
        Article article=new Article();
        article.setTitre("apple TV");
        article.setDescription("c'est un apple tv");
        article.setFraisPort(10f);
        article.setModeCloture("auto");
        java.util.Date date=new java.util.Date();
        article.setDateCloture(new java.sql.Date(date.getTime()));
        article.setMontantVente(700f);
        article.setPrixDepart(500f);
        article.setPrixReserve(550f);
        article.setRegionLivraison("Occitanie");
        article.setPrixAchatImmediat(750f);

        /*CategorieDao categorieDao=new CategorieDao();
        article.setCategorie(categorieDao.find(1));
        MembreDao membreDao1=new MembreDao();
        article.setVendeur(membreDao1.find(1));
        OptionEnchereDao optionEnchereDao=new OptionEnchereDao();
        article.setOption(optionEnchereDao.find(1));*/

        //       ArrayList<Categorie> rs=new ArrayList<>();
//       rs=categorieDao.findAllSousCategorie(1);
//       for (int i=0;i<rs.size();i++){
//           System.out.println(rs.get(i).getId());
//       }
//        Categorie c=categorieDao.find(1);
//        System.out.println(c.getLibelle());

        ArticleDao articleDao=new ArticleDao();
        articleDao.create(article);
    }

    public static void Connexion(){

        String email = "r.monlouis@gmail.com";
        String mdp = "pwd";
        CompteDao compteDao = new CompteDao();
        Compte compte = compteDao.find(email, mdp);

        if (compte != null) {
            switch (compte.getRole()) {
                case MEMBRE :
                    System.out.println("MEMBRE");
                    break;
                case SERVICE_COMMERCIAL:
                    System.out.println("SERVICE_COMMERCIAL");
                    break;
                case SERVICE_JURIDIQUE:
                    System.out.println("SERVICE_JURIDIQUE");
                    break;
                case GESTIONNAIRE:
                    System.out.println("GESTIONNAIRE");
                    break;
                default:
                    break;
            }
        } else {
            System.out.println("Utilisateur inconnue");
        }


    }
}
