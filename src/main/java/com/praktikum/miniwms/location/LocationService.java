
package com.praktikum.miniwms.location;

import com.praktikum.miniwms.exceptions.LocationNotFoundByIdException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    // GET: Alle Locations abrufen
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    // POST: Neue Location erstellen
    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    // GET: Eine bestimmte Location anhand ihrer ID abrufen
    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundByIdException(
                        "Location with ID " + id + " does not exist"
                ));
    }

    // PUT: Eine bestehende Location aktualisieren
    public Location updateLocation(Long id, Location updatedLocation) {

        Location existingLocation = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundByIdException(
                        "Location with ID " + id + " does not exist"
                ));

        existingLocation.setName(updatedLocation.getName());
        existingLocation.setDescription(updatedLocation.getDescription());
        existingLocation.setEquipments(updatedLocation.getEquipments());
        existingLocation.setBusinessId(updatedLocation.getBusinessId());

        return locationRepository.save(existingLocation);
    }

    // DELETE: Eine Location löschen
    public void deleteLocation(Long id) {

        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundByIdException(
                        "Location with ID " + id + " does not exist"
                ));

        locationRepository.delete(location);
    }
}
