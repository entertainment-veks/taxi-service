package dao3.dao.jdbc;

import dao3.dao.ManufacturerDao;
import dao3.exception.DataProcessingException;
import dao3.lib.Dao;
import dao3.model.Manufacturer;
import dao3.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static dao3.db.Database.MANUFACTURERS_TAB_NAME;

@Dao
public class ManufacturerDaoJDBCImpl implements ManufacturerDao {
    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        String query =
                "INSERT INTO " + MANUFACTURERS_TAB_NAME + " (manufacturer_name, manufacturer_country) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
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
        Manufacturer result = newManufacturer(null, null);
        String query = "SELECT * FROM " + MANUFACTURERS_TAB_NAME + " WHERE manufacturer_id = ? AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result.setName(resultSet.getObject("manufacturer_name", String.class));
                result.setCountry(resultSet.getObject("manufacturer_country", String.class));
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
                result.add(newManufacturer(resultSet.getObject("manufacturer_name", String.class),
                        resultSet.getObject("manufacturer_country", String.class)));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all", e);
        }
        return result;
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        String query = "UPDATE " + MANUFACTURERS_TAB_NAME
                + " SET manufacturer_name = ?,"
                + "manufacturer_country = ? "
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

    private Manufacturer newManufacturer(String name, String contry) {
        return new Manufacturer(name, contry);
    }
}
