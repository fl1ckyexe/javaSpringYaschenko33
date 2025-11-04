package app.service;

import app.model.Apartment;
import app.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApartmentService {
    private ApartmentRepository repo;


    @Autowired
    public void setRepo(ApartmentRepository repo) {
        this.repo = repo;
    }

    public List<Apartment> all() {
        return repo.findAll();
    }

    public List<Apartment> byOwner(String owner) {
        return repo.findByOwner(owner);
    }

    public Optional<Apartment> findById(Long id) {
        return repo.findById(id);
    }

    public void save(Apartment apartment) {
        repo.save(apartment);
    }

    public void delete(Long id) {
        repo.delete(id);
    }
}
