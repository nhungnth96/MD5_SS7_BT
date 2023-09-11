package ss7.bt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ss7.bt.model.Smartphone;

import java.util.List;

@Repository
public interface ISmartphoneRepository extends JpaRepository<Smartphone, Long> {
    List<Smartphone> findAllByProducerContaining(String producer);
}

