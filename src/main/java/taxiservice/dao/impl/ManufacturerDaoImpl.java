package taxiservice.dao.impl;

import java.util.List;
import java.util.Optional;
import taxiservice.dao.ManufacturerDao;
import taxiservice.db.Storage;
import taxiservice.model.Manufacturer;

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
        throw new RuntimeException("Can't update, element " + manufacturer + " doesn't exist");
    }

    @Override
    public boolean delete(Long id) {
        return Storage.manufacturers.removeIf(e -> e.getId().equals(id));
    }
}
