package app.repository;

import app.model.Apartment;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ApartmentRepository {

    private final Path file = Paths.get("apartments.txt");
    private final Map<Long, Apartment> data = new HashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    public ApartmentRepository() {
        loadFromFile();
    }



    public List<Apartment> findAll() {
        return new ArrayList<>(data.values());
    }

    public List<Apartment> findByOwner(String owner) {
        return data.values().stream()
                .filter(a -> a.getOwnerUsername().equals(owner))
                .toList();
    }

    public Optional<Apartment> findById(Long id) {
        return Optional.ofNullable(data.get(id));
    }

    public void save(Apartment apartment) {
        if (apartment.getId() == null) {
            apartment.setId(idGen.getAndIncrement());
        }
        data.put(apartment.getId(), apartment);
        writeToFile();
    }

    public void delete(Long id) {
        data.remove(id);
        writeToFile();
    }



    private void loadFromFile() {
        try {
            if (!Files.exists(file)) return;
            List<String> lines = Files.readAllLines(file);
            for (String line : lines) {
                String[] p = line.split(";");
                if (p.length < 7) continue;
                Long id = Long.parseLong(p[0]);
                Apartment a = new Apartment(
                        id, p[1], p[2],
                        Double.parseDouble(p[3]), p[4],
                        Integer.parseInt(p[5]), p[6]
                );
                data.put(id, a);
                idGen.updateAndGet(x -> Math.max(x, id + 1));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading apartments", e);
        }
    }

    private void writeToFile() {
        try {
            List<String> lines = data.values().stream()
                    .map(a -> a.getId() + ";" + a.getTitle() + ";" + a.getAddress() + ";" +
                            a.getPrice() + ";" + a.getOwnerUsername() + ";" +
                            a.getRooms() + ";" + a.getLayoutType())
                    .toList();
            Files.write(file, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Error writing apartments", e);
        }
    }
}
