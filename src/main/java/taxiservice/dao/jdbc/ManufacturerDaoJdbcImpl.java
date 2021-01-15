package taxiservice.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import taxiservice.dao.ManufacturerDao;
import taxiservice.exception.DataProcessingException;
import taxiservice.lib.Dao;
import taxiservice.model.Manufacturer;
import taxiservice.util.ConnectionUtil;

@Dao
public class ManufacturerDaoJdbcImpl implements ManufacturerDao {
    public static final String MANUFACTURERS_TAB_NAME = "manufacturers";

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        String query = "INSERT INTO " + MANUFACTURERS_TAB_NAME + " (name, country) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getCountry());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                manufacturer.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create " + manufacturer, e);
        }
        return manufacturer;
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        Manufacturer result = new Manufacturer(null, null);
        String query = "SELECT * FROM " + MANUFACTURERS_TAB_NAME
                + " WHERE manufacturer_id = ? AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result.setName(resultSet.getObject("name", String.class));
                result.setCountry(resultSet.getObject("country", String.class));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get by id " + id, e);
        }
        return Optional.of(result);
    }

    @Override
    public List<Manufacturer> getAll() {
        List<Manufacturer> result = new ArrayList<>();
        String query = "SELECT * FROM " + MANUFACTURERS_TAB_NAME + " WHERE deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                result.add(newManufacturer(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all", e);
        }
        return result;
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        String query = "UPDATE " + MANUFACTURERS_TAB_NAME
                + " SET name = ?,"
                + "country = ? "
                + "WHERE manufacturer_id = ? AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getCountry());
            statement.setLong(3, manufacturer.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update " + manufacturer, e);
        }
        return manufacturer;
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE " + MANUFACTURERS_TAB_NAME
                + " SET deleted = true"
                + " WHERE manufacturer_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            boolean result = statement.executeUpdate() == 1;
            return result;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete by id " + id, e);
        }
    }

    private Manufacturer newManufacturer(ResultSet resultSet) {
        try {
            return new Manufacturer(resultSet.getObject("name", String.class),
                    resultSet.getObject("country", String.class));
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create manufcaturer", e);
        }
    }
}
