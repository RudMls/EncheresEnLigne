package app;

import app.dao.CompteDao;
import app.dao.MembreDao;
import app.dao.OptionEnchereDao;
import app.model.Compte;
import app.model.Membre;
import app.model.OptionEnchere;
import app.model.Role;

import java.sql.Date;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Inscription
        // Inscription();

        OptionEnchereDao optionEnchereDao = new OptionEnchereDao();
        ArrayList<OptionEnchere> optionEncheres = optionEnchereDao.findAll();

        System.out.println(optionEncheres);

    }

    public static void Inscription(){
        Compte compte = new Compte();
        compte.setEmail("test@gmail.com");
        compte.setMdp("www");
        compte.setRole(Role.MEMBRE);
        CompteDao compteDao = new CompteDao();
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
    }

    public static void Connexion(){

        String email = "r.monlouis@gmail.com";
        String mdp = "pwd";
        CompteDao compteDao = new CompteDao();
        compteDao.getId(email, mdp);

    }
}
