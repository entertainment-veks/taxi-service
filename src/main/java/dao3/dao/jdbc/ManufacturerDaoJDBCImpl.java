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
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement stmt = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, manufacturer.getName());
            stmt.setString(2, manufacturer.getCountry());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                manufacturer.setId(rs.getLong(1));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create " + manufacturer, e);
        }
        return manufacturer;
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        Manufacturer result = new Manufacturer(null, null);
        String query = "SELECT * FROM " + MANUFACTURERS_TAB_NAME + " WHERE manufacturer_id = ? AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result.setName(rs.getObject("manufacturer_name", String.class));
                result.setCountry(rs.getObject("manufacturer_country", String.class));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get by id " + id, e);
        }
        return Optional.of(result);
    }

    @Override
    public List<Manufacturer> getAll() {
        List<Manufacturer> result = new ArrayList<>();
        String query = "SELECT * FROM " + MANUFACTURERS_TAB_NAME + " WHERE deleted = false";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                result.add(new Manufacturer(rs.getObject("manufacturer_name", String.class),
                        rs.getObject("manufacturer_country", String.class)));
            }
            rs.close();
            stmt.close();
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
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, manufacturer.getName());
            stmt.setString(2, manufacturer.getCountry());
            stmt.setLong(3, manufacturer.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update " + manufacturer, e);
        }
        return manufacturer;
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE " + MANUFACTURERS_TAB_NAME
                + " SET deleted = 1"
                + " WHERE manufacturer_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, id);
            boolean result = stmt.executeUpdate() == 1;
            stmt.close();
            return result;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete by id " + id, e);
        }
    }
}
