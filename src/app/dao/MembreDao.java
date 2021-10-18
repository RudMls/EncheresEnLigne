package app.dao;

import app.model.Compte;
import app.model.Membre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MembreDao extends DAO<Membre>{

    private static final String SELECT_ALL = "SELECT * FROM membre";
    private static final String SELECT_BY_ID = "SELECT * FROM membre WHERE membre_id = ?";
    private static final String SELECT_BY_COMPTE = "SELECT * FROM membre WHERE compte_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM membre WHERE membre_id = ?";
    private static final String UPDATE_BY_ID = "";

    public ArrayList<Membre> findAll() {
        ArrayList<Membre> membres = new ArrayList<>();
        Membre membre;
        try {
            Statement statement = super.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                CompteDao compteDao = new CompteDao();
                membre = new Membre();
                membre.setId(resultSet.getLong("membre_id"));
                membre.setNom(resultSet.getString("membre_nom"));
                membre.setPrenom(resultSet.getString("membre_prenom"));
                membre.setCodePostal(resultSet.getString("membre_code_postal"));
                membre.setAdressePostale(resultSet.getString("membre_adresse_postale"));
                membre.setDateNaissance(resultSet.getDate("membre_date_naissance"));
                membre.setVille(resultSet.getString("membre_ville"));
                membre.setPays(resultSet.getString("membre_pays"));
                membre.setCompte(compteDao.findById(resultSet.getLong("compte_id")));
                membres.add(membre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membres;
    }

    @Override
    public Membre findById(long id) {
        String query = "SELECT * FROM membre WHERE membre_id ="+id;
        try{
            PreparedStatement stmt= super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                CompteDao compteDao=new CompteDao();
                //Membre membre = new Membre(rs.getString("membre_nom"),rs.getString("membre_prenom"),rs.getDate("membre_date_naissance"),rs.getString("membre_email"),rs.getString("membre_adresse_postale"),rs.getString("membre_ville"),rs.getString("membre_pays"),compteDao.find(rs.getLong("compte_id")));
                //membre.setId(id);
                //return membre;
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Membre create(Membre membre) {
        String query = "INSERT INTO Membre (membre_nom, membre_prenom, membre_date_naissance, membre_code_postal, membre_adresse_postale, membre_ville, membre_pays, compte_id) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, membre.getNom());
            preparedStatement.setString(2, membre.getPrenom());
            preparedStatement.setDate(3, membre.getDateNaissance());
            preparedStatement.setString(4, membre.getCodePostal());
            preparedStatement.setString(5, membre.getAdressePostale());
            preparedStatement.setString(6, membre.getVille());
            preparedStatement.setString(7, membre.getPays());
            preparedStatement.setLong(8, membre.getCompte().getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                membre.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membre;
    }

    @Override
    public Membre update(Membre obj) {
        return null;
    }

    @Override
    public void delete(Membre obj) {

    }
}