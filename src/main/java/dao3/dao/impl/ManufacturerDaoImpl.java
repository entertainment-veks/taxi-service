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
                .filter(m -> m.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Manufacturer> getAll() {
        return Storage.manufacturers;
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        for (int i = 0; i < Storage.manufacturers.size(); i++) {
            if (manufacturer.getId().equals(Storage.manufacturers.get(i).getId())) {
                Storage.manufacturers.set(i, manufacturer);
                return manufacturer;
            }
        }
        throw new RuntimeException("Can't update, element doesn't exist");
    }

    @Override
    public boolean delete(Long id) {
        return Storage.manufacturers.removeIf(e -> e.getId().equals(id));
    }
}
