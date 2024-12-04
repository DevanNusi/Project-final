package PertaCalcApp.repositories;

import PertaCalcApp.config.Database;
import PertaCalcApp.entities.Stock;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Component
public class StokRepositoriesImpl implements StokRepositories{

    private final Database database;

    public StokRepositoriesImpl(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<Stock> ambilDataStock() {
        Connection connection = database.getConnection();
        String sqlStatement = "SELECT * FROM stock";
        ArrayList<Stock> listDataStok = new ArrayList<>();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Stock barisData = new Stock();
                String nama = resultSet.getString(1);
                int harga = resultSet.getInt(2);
                int jumlah = resultSet.getInt(3);
                int id = resultSet.getInt(4);


                barisData.setNama(nama);
                barisData.setHarga(harga);
                barisData.setStock(jumlah);
                barisData.setId(id);

                listDataStok.add(barisData);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return listDataStok;
    }

    @Override
    public void hapusData(int index) {
        String sqlStatement = "DELETE FROM stock WHERE id = ?";
        Connection connection = database.getConnection();

        int id = ambilDataStock().get(index).getId();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1,id);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0){
                System.out.println("Delete Successful !");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void editData(Stock dataBaru, int index) {
        String sqlStatement = "UPDATE stock set nama = ?, harga = ?, jumlah = ? WHERE id = ?";
        Connection conn = database.getConnection();

        int id = ambilDataStock().get(index).getId();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement);

            preparedStatement.setString(1,dataBaru.getNama());
            preparedStatement.setInt(2,dataBaru.getHarga());
            preparedStatement.setInt(3,dataBaru.getStock());
            preparedStatement.setInt(4,id);

            int rowsEffected = preparedStatement.executeUpdate();
            if (rowsEffected > 0) {
                System.out.println("Update successful !");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void tambahData(Stock dataBaru) {

        Connection connection = database.getConnection();
        String sqlStatement = "INSERT INTO stock(nama,harga,jumlah) VALUES(?,?,?)";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1,dataBaru.getNama());
            preparedStatement.setInt(2,dataBaru.getHarga());
            preparedStatement.setInt(3,dataBaru.getStock());


            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Insert successful !");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
