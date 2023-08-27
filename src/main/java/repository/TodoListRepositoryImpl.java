package repository;

import entity.Todolist;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TodoListRepositoryImpl implements TodoListRepository{

    public Todolist[] data = new Todolist[10];

    private DataSource dataSource;

    public TodoListRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Todolist[] getAll() {
        return data;
    }

    public boolean isFull(){
        // cek apakah model penuh?
        var isFull = true;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null){
                isFull = false;
                break;
            }
        }

        return isFull;
    }

    public void resizeIsFull(){
        // jika model penuh maka resize ke ukuran 2x lipat
        if (isFull()){
            var temp = data;
            data = new Todolist[data.length * 2];

            for (int i = 0; i < temp.length; i++) {
                data[i] = temp[i];
            }
        }
    }

    @Override
    public void add(Todolist todolist) {
        String sql = "INSERT INTO todolist (todo) VALUES (?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, todolist.getTodo());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isExist(Integer number){
        String sql = "SELECT id FROM todolist WHERE id = ?";
        try(Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1, number);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean remove(Integer number) {
        if (isExist(number)) {
            String sql = "DELETE FROM todolist WHERE id = ?";
            try(Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, number);
                preparedStatement.executeUpdate();

                return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            return false;
        }
    }
}
