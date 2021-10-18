package app.dao;

import app.model.Compte;
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
        String query="SELECT * FROM compte WHERE compte_id="+id;
        try{
            PreparedStatement stmt= super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Compte compte = new Compte();
                compte.setId(id);
                compte.setEmail(rs.getString("compte_email"));
                compte.setMdp(rs.getString("compte_mdp"));
                Role role=Role.valueOf(rs.getString("compte_role"));
                compte.setRole(role);
                return compte;}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
    public Compte update(Compte obj) {
        return null;
    }

    @Override
    public void delete(Compte obj) {

    }
}
