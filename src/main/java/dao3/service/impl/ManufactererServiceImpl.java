package dao3.service.impl;

import dao3.dao.ManufacturerDao;
import dao3.lib.Inject;
import dao3.lib.Service;
import dao3.model.Manufacturer;
import dao3.service.ManufacturerService;
import java.util.List;

@Service
public class ManufactererServiceImpl implements ManufacturerService {
    @Inject
    ManufacturerDao manufacturerDao;

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        return manufacturerDao.create(manufacturer);
    }

    @Override
    public Manufacturer get(Long id) {
        return manufacturerDao.get(id).orElse(null);
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
