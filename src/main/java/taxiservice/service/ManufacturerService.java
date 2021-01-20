package taxiservice.service;

import java.util.List;
import taxiservice.model.Manufacturer;

public interface ManufacturerService extends GenericService<Manufacturer, Long> {
    @Override
    Manufacturer create(Manufacturer manufacturer);

    @Override
    Manufacturer get(Long id);

    @Override
    List<Manufacturer> getAll();

    @Override
    Manufacturer update(Manufacturer manufacturer);

    @Override
    boolean delete(Long id);
}
