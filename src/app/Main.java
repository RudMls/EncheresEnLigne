package app;

import app.dao.CompteDao;
import app.model.Compte;
import app.model.Membre;
import app.model.Role;

public class Main {

    public static void main(String[] args) {


        Compte compte = new Compte();
        compte.setEmail("test@gmail.com");
        compte.setMdp("www");
        compte.setRole(Role.MEMBRE);

        CompteDao compteDao = new CompteDao();
        compteDao.create(compte);




    }
}
