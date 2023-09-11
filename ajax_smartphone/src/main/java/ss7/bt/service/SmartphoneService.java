package ss7.bt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ss7.bt.model.Smartphone;
import ss7.bt.repository.ISmartphoneRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SmartphoneService implements ISmartphoneService{
    @Autowired
    ISmartphoneRepository smartphoneRepository;
    @Override
    public List<Smartphone> findAllByProducerContaining(String producer) {
        return smartphoneRepository.findAllByProducerContaining(producer);
    }

    @Override
    public List<Smartphone> findAll() {
        return smartphoneRepository.findAll();
    }

    @Override
    public Optional<Smartphone> findById(Long id) {
        return smartphoneRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        smartphoneRepository.deleteById(id);
    }

    @Override
    public Smartphone save(Smartphone smartphone) {
        return smartphoneRepository.save(smartphone);
    }

}
