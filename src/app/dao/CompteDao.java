package app.dao;

import app.model.Compte;
import app.model.Membre;
import app.model.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompteDao extends DAO<Compte>{

    private final static String DELETE_COMPTE = "DELETE FROM compte WHERE compte_id = ?";

    public Compte getCompte(String email, String mdp) {
        Compte compte = null;
        String query = "SELECT compte_id FROM compte WHERE compte_email = ? AND compte_mdp = ?";
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, mdp);
            ResultSet resultSet = preparedStatement.executeQuery();
            //id = resultSet.getLong("compte_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return compte;
    }

    @Override
    public Compte find(long id) {
        Compte compte=null;
        String query = "Select * from Membre m Where m.membre_id =?";
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                compte = new Compte();
                compte.setId(rs.getLong(1));
                compte.setEmail(rs.getString(2));
                compte.setMdp(rs.getString(3));
                compte.setRole(Role.valueOf(rs.getString(4)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return compte;
    }

    @Override
    public Compte create(Compte compte) {
        String query = "INSERT INTO Compte (compte_email, compte_mdp, compte_role) VALUES(?, ?, ?)";
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, compte.getEmail());
            preparedStatement.setString(2, compte.getMdp());
            preparedStatement.setString(3, compte.getRole().toString());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                compte.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return compte;
    }

    @Override
    public Compte update(Compte compte) {

        String query = "UPDATE Compte SET compte_email=?, compte_mdp=?, compte_role=? WHERE compte_id=?";
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, compte.getEmail());
            preparedStatement.setString(2, compte.getMdp());
            preparedStatement.setString(3, compte.getRole().toString());
            preparedStatement.setLong(4,compte.getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                compte.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return compte;
    }

    @Override
    public void delete(Compte compte) {
        String query = "DELETE FROM Compte WHERE compte_id="+compte.getId();
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
