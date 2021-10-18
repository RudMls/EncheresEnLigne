package app.dao;

import app.model.Membre;
import app.model.SignalerArticle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MembreDao extends DAO<Membre>{
    @Override
    public Membre find(long id) {
        Membre membre=null;
        String query = "Select * from Membre m Where m.membre_id =?";
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                membre = new Membre();
                membre.setId(rs.getLong(1));
                membre.setNom(rs.getString(2));
                membre.setPrenom(rs.getString(3));
                membre.setEmail(rs.getString(4));
                membre.setDateNaissance(rs.getDate(5));
                membre.setAdressePostale(rs.getString(6));
                membre.setCodePostal(rs.getString(7));
                membre.setVille(rs.getString(8));
                membre.setPays(rs.getString(9));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  membre;
    }

    @Override
    public Membre create(Membre membre) {
        String query = "INSERT INTO Membre (membre_nom, membre_prenom, membre_date_naissance, membre_email,membre_code_postal, membre_adresse_postale, membre_ville, membre_pays, compte_id) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?,?)";
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, membre.getNom());
            preparedStatement.setString(2, membre.getPrenom());
            preparedStatement.setDate(3, membre.getDateNaissance());
            preparedStatement.setString(4, membre.getEmail());
            preparedStatement.setString(5, membre.getCodePostal());
            preparedStatement.setString(6, membre.getAdressePostale());
            preparedStatement.setString(7, membre.getVille());
            preparedStatement.setString(8, membre.getPays());
            preparedStatement.setLong(9, membre.getCompte().getId());
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
    public Membre update(Membre membre) {
        String query = "UPDATE Membre SET membre_nom=?, membre_prenom=?, membre_date_naissance=?, membre_code_postal=?, membre_adresse_postale=?, membre_ville=?, membre_pays=?, compte_id=? WHERE membre_id=?";
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
            preparedStatement.setLong(9,membre.getId());
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
    public void delete(Membre membre) {
        String query = "DELETE FROM Membre WHERE membre_id="+membre.getId();
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getResultSet();
            System.out.println(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}