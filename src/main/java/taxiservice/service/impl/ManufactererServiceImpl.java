package taxiservice.service.impl;

import java.util.List;
import taxiservice.dao.ManufacturerDao;
import taxiservice.lib.Inject;
import taxiservice.lib.Service;
import taxiservice.model.Manufacturer;
import taxiservice.service.ManufacturerService;

@Service
public class ManufactererServiceImpl implements ManufacturerService {
    @Inject
    private ManufacturerDao manufacturerDao;

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        return manufacturerDao.create(manufacturer);
    }

    @Override
    public Manufacturer get(Long id) {
        return manufacturerDao.get(id).get();
    }

    @Override
    public List<Manufacturer> getAll() {
        return manufacturerDao.getAll();
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        return manufacturerDao.update(manufacturer);
    }

    @Override
    public boolean delete(Long id) {
        return manufacturerDao.delete(id);
    }
}
