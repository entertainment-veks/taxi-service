package dao3.dao.impl;

import dao3.dao.ManufacturerDao;
import dao3.db.Storage;
import dao3.lib.Dao;
import dao3.model.Manufacturer;
import java.util.List;
import java.util.Optional;

@Dao
public class ManufacturerDaoImpl implements ManufacturerDao {
    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        return Storage.addManufacturerWithId(manufacturer);
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        return Storage.manufacturers.stream()
                .filter(m -> m.getId() == id)
                .findFirst();
    }

    @Override
    public List<Manufacturer> getAll() {
        return Storage.manufacturers;
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        for (int i = 0; i < Storage.manufacturers.size(); i++) {
            if (Storage.manufacturers.get(i).getId() == manufacturer.getId()) {
                Storage.manufacturers.set(i, manufacturer);
                return manufacturer;
            }
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Manufacturer> current =
                Storage.manufacturers.stream()
                .filter(m -> m.getId() == id)
                .findFirst();

        return current.map(m -> Storage.manufacturers.remove(m)).orElse(false);
    }
}
