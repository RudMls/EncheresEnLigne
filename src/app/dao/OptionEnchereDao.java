package app.dao;

import app.model.Categorie;
import app.model.OptionEnchere;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OptionEnchereDao extends DAO<OptionEnchere>{

    static final String SELECT_ALL = "SELECT * FROM option_enchere";

    public ArrayList<OptionEnchere> findAll(){

        ArrayList<OptionEnchere> optionEncheres = new ArrayList<>();
        OptionEnchere optionEnchere;
        try {
            Statement statement = super.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                optionEnchere = new OptionEnchere();
                optionEnchere.setId(resultSet.getLong("option_enchere_id"));
                optionEnchere.setLibelle(resultSet.getString("option_enchere_libelle"));
                optionEnchere.setPrixCatalogue(resultSet.getFloat("option_enchere_prix_catalogue"));
                optionEnchere.setPrixGold(resultSet.getFloat("option_enchere_prix_gold"));
                optionEncheres.add(optionEnchere);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionEncheres;
    }

    @Override
    public OptionEnchere findById(long id) {
        String query = "SELECT * FROM options WHERE option_id ="+id;
        try{
            PreparedStatement stmt= super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                OptionEnchere optionEnchere = new OptionEnchere();
                optionEnchere.setId(id);
                optionEnchere.setLibelle(rs.getString("option_libelle"));
                optionEnchere.setPrixCatalogue(rs.getFloat("option_prix_catalogue"));
                optionEnchere.setPrixGold(rs.getFloat("option_prix_gold"));
                return optionEnchere;}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OptionEnchere create(OptionEnchere obj) {
        return null;
    }

    @Override
    public OptionEnchere update(OptionEnchere obj) {
        return null;
    }

    @Override
    public void delete(OptionEnchere obj) {

    }
}
