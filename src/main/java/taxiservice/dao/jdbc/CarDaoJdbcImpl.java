package taxiservice.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import taxiservice.dao.CarDao;
import taxiservice.exception.DataProcessingException;
import taxiservice.lib.Dao;
import taxiservice.model.Car;
import taxiservice.model.Driver;
import taxiservice.model.Manufacturer;
import taxiservice.util.ConnectionUtil;

@Dao
public class CarDaoJdbcImpl implements CarDao {

    @Override
    public Car create(Car car) {
        String query = "INSERT INTO cars (model, manufacturer_id) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection
                        .prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, car.getModel());
            statement.setLong(2, car.getManufacturer().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                car.setId(resultSet.getLong(1));
            }

            insertDataIntoCarsDrivers(car, connection);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create " + car, e);
        }
        return car;
    }

    @Override
    public Optional<Car> get(Long id) {
        String query = "SELECT * FROM cars c"
                + " INNER JOIN manufacturers m"
                + " ON c.manufacturer_id = m.manufacturer_id"
                + " WHERE car_id = ? AND c.deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            Car result = null;
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = newCar(resultSet);
            }
            if (result != null) {
                result.setDrivers(getDrivers(result.getId()));
            }
            return Optional.ofNullable(result);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get by id " + id, e);
        }
    }

    @Override
    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM cars c"
                + " INNER JOIN manufacturers m"
                + " ON c.manufacturer_id = m.manufacturer_id"
                + " WHERE c.deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cars.add(newCar(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all", e);
        }
        if (cars != null) {
            for (Car car : cars) {
                car.setDrivers(getDrivers(car.getId()));
            }
        }
        return cars;
    }

    @Override
    public Car update(Car car) {
        String query = "UPDATE cars SET model = ?,"
                + " manufacturer_id = ? "
                + "WHERE car_id = ? AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, car.getModel());
            statement.setLong(2, car.getManufacturer().getId());
            statement.setLong(3, car.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update " + car, e);
        }

        query = "DELETE FROM cars_drivers WHERE car_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, car.getId());
            statement.executeUpdate();

            insertDataIntoCarsDrivers(car, connection);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update " + car, e);
        }
        return car;
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE cars SET deleted = true"
                + " WHERE car_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete by id " + id, e);
        }
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        List<Car> result = new ArrayList<>();
        String query = "SELECT * FROM cars_drivers cd"
                + " JOIN cars c"
                + " ON cd.car_id = c.car_id"
                + " JOIN manufacturers m"
                + " ON c.manufacturer_id = m.manufacturer_id"
                + " JOIN drivers d"
                + " ON cd.diver_id = d.driver_id"
                + " WHERE cd.diver_id = ? AND c.deleted = false AND d.deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, driverId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(newCar(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all by driver", e);
        }
        if (result != null) {
            for (Car car : result) {
                car.setDrivers(getDrivers(car.getId()));
            }
        }
        return result;
    }

    private void insertDataIntoCarsDrivers(Car car, Connection connection) {
        String query = "INSERT INTO cars_drivers (car_id, diver_id) VALUES (?, ?)";
        for (Driver driver : car.getDrivers()) {
            try (PreparedStatement statement = connection
                         .prepareStatement(query)) {
                statement.setLong(1, car.getId());
                statement.setLong(2, driver.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new DataProcessingException("Can't insert into cars_drivers " + car, e);
            }
        }
    }

    private List<Driver> getDrivers(Long carId) {
        List<Driver> result = new ArrayList<>();
        String query = "SELECT * FROM cars_drivers c"
                + " INNER JOIN drivers d"
                + " ON c.diver_id = d.driver_id"
                + " WHERE car_id = ? AND d.deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, carId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(newDriver(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all", e);
        }
        return result;
    }

    private Car newCar(ResultSet resultSet) {
        try {
            Manufacturer resultManufacturer = new Manufacturer(
                    resultSet.getObject("name", String.class),
                    resultSet.getObject("country", String.class));
            resultManufacturer.setId(resultSet.getObject("manufacturer_id", Long.class));

            Car result = new Car(
                    resultSet.getObject("model", String.class),
                    resultManufacturer);
            result.setId(resultSet.getObject("car_id", Long.class));
            return result;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't return new car", e);
        }
    }

    private Driver newDriver(ResultSet resultSet) {
        try {
            Driver result = new Driver(resultSet.getObject("name", String.class),
                    resultSet.getObject("licence_number", String.class));
            result.setId(resultSet.getObject("driver_id", Long.class));
            return result;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create driver ", e);
        }
    }
}
