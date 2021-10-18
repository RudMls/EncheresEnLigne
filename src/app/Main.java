package app;

import app.dao.*;
import app.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws ParseException {


        Compte compte = new Compte();
        compte.setEmail("test@gmail.com");
        compte.setMdp("www");
        compte.setRole(Role.MEMBRE);

        CompteDao compteDao = new CompteDao();
        Compte c=compteDao.create(compte);

        SimpleDateFormat sdf=new SimpleDateFormat("dd/mm/yyyy");
        String daten="07/11/1997";
        java.util.Date dateNais=sdf.parse(daten);
        Membre membre=new Membre("HE","Peicong",new java.sql.Date(dateNais.getTime()),"ut1","31000","Toulouse","France",c);

        MembreDao membreDao=new MembreDao();
        Membre m=membreDao.create(membre);

        OptionEnchere optionEnchere=new OptionEnchere();
        optionEnchere.setId(1);
        optionEnchere.setLibelle("compteur_de_visites");
        optionEnchere.setPrixCatalogue(0f);
        optionEnchere.setPrixGold(0f);


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

        CategorieDao categorieDao=new CategorieDao();
       article.setCategorie(categorieDao.find(1));
        MembreDao membreDao1=new MembreDao();
        article.setVendeur(membreDao1.find(1));
        OptionEnchereDao optionEnchereDao=new OptionEnchereDao();
        article.setOption(optionEnchereDao.find(1));

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
}
