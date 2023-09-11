package ss7.bt.service;

import ss7.bt.model.Smartphone;

import java.util.List;
import java.util.Optional;

public interface ISmartphoneService {
    List<Smartphone> findAllByProducerContaining(String producer);
    List<Smartphone> findAll();
    Optional<Smartphone> findById(Long id);
    void deleteById(Long id);
    Smartphone save(Smartphone smartPhone);
}
