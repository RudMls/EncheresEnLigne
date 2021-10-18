package app.dao;

import app.model.Compte;
import app.model.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompteDao extends DAO<Compte>{

    private static final String DELETE_BY_ID = "DELETE FROM compte WHERE compte_id = ?";
    private static final String SELECT_ALL = "SELECT * FROM membre";
    private static final String SELECT_BY_ID = "";
    private static final String SELECT_BY_EMAIL = "";
    private static final String SELECT_BY_EMAIL_PWD = "";

    private Compte getResultSet(ResultSet resultSet) throws SQLException {
        Compte compte = null;
        if (resultSet.next()) {
            compte = new Compte();
            compte.setId(resultSet.getLong("compte_id"));
            compte.setEmail(resultSet.getString("compte_email"));
            compte.setMdp(resultSet.getString("compte_mdp"));
            compte.setRole(Role.valueOf(resultSet.getString("compte_role")));
        }
        return compte;
    }

    public boolean find(String email) {
        String query = "SELECT compte_id FROM compte WHERE compte_email = ?";
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Compte find(String email, String mdp) {
        Compte compte = null;
        String query = "SELECT compte_id FROM compte WHERE compte_email = ? AND compte_mdp = ?";
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, mdp);
            ResultSet resultSet = preparedStatement.executeQuery();
            compte = getResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return compte;
    }

    @Override
    public Compte findById(long id) {
        Compte compte = null;
        String query = "SELECT * FROM compte WHERE compte_id = ?";
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            compte = getResultSet(resultSet);
        } catch (SQLException e) {
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
    public Compte update(Compte obj) {
        return null;
    }

    @Override
    public void delete(Compte obj) {

    }
}
