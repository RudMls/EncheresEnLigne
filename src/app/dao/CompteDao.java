package app.dao;

import app.model.Compte;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompteDao extends DAO<Compte>{
    @Override
    public Compte find(long id) {
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
                long id = rs.getLong(1);
                compte.setId(id);
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
