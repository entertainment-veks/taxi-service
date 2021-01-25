package taxiservice.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import taxiservice.dao.DriverDao;
import taxiservice.exception.DataProcessingException;
import taxiservice.lib.Dao;
import taxiservice.model.Driver;
import taxiservice.util.ConnectionUtil;

@Dao
public class DriverDaoJdbcImpl implements DriverDao {

    @Override
    public Driver create(Driver driver) {
        String query = "INSERT INTO drivers (name, licence_number, login, password) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getLicenceNumber());
            statement.setString(3, driver.getLogin());
            statement.setString(4, driver.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                driver.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create " + driver, e);
        }
        return driver;
    }

    @Override
    public Optional<Driver> get(Long id) {
        String query = "SELECT * FROM drivers WHERE driver_id = ? AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            Driver result = null;
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = newDriver(resultSet);
            }
            return Optional.ofNullable(result);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get by id " + id, e);
        }
    }

    @Override
    public List<Driver> getAll() {
        List<Driver> result = new ArrayList<>();
        String query = "SELECT * FROM drivers WHERE deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                result.add(newDriver(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all", e);
        }
        return result;
    }

    @Override
    public Driver update(Driver driver) {
        String query = "UPDATE drivers SET name = ?,"
                + " licence_number = ? "
                + "WHERE driver_id = ? AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getLicenceNumber());
            statement.setLong(3, driver.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update " + driver, e);
        }
        return driver;
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE drivers SET deleted = true"
                + " WHERE driver_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete by id " + id, e);
        }
    }

    @Override
    public Optional<Driver> findByLogin(String login) {
        String query = "SELECT * FROM drivers WHERE login = ? AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            Driver result = null;
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = newDriver(resultSet);
            }
            return Optional.ofNullable(result);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get by login " + login, e);
        }
    }

    private Driver newDriver(ResultSet resultSet) {
        try {
            Driver result = new Driver(resultSet.getObject("name", String.class),
                    resultSet.getObject("licence_number", String.class));
            result.setLogin(resultSet.getObject("login", String.class));
            result.setPassword(resultSet.getObject("password", String.class));
            result.setId(resultSet.getObject("driver_id", Long.class));
            return result;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create driver ", e);
        }
    }
}
