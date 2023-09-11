package ss7.bt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ss7.bt.model.Smartphone;
import ss7.bt.service.ISmartphoneService;

import java.util.Optional;

@RestController
@RequestMapping("/phone")
@CrossOrigin(origins = "*") // kết nối 2 cổng fe (4200) vs be (8080)
public class PhoneController {
    @Autowired
    ISmartphoneService smartPhoneService;

    @GetMapping("/list")
    public ModelAndView getAllSmartphonePage() {
        return new ModelAndView("smartphone","smartphones",smartPhoneService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> createSmartphone(@RequestBody Smartphone smartphone) {
        return new ResponseEntity<>(smartPhoneService.save(smartphone), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> allPhones() {
        return new ResponseEntity<>(smartPhoneService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Smartphone> smartPhone = smartPhoneService.findById(id);
        if (!smartPhone.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(smartPhone.get(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSmartphone(@PathVariable Long id) {
        Optional<Smartphone> smartphoneOptional = smartPhoneService.findById(id);
        if (!smartphoneOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        smartPhoneService.deleteById(id);
        return new ResponseEntity<>(smartphoneOptional.get(), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSmartPhone(@PathVariable Long id, @RequestBody  Smartphone smartphone){
        Optional<Smartphone> smartPhone1 = smartPhoneService.findById(id);
        if (!smartPhone1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        smartPhone1.get().setProducer(smartphone.getProducer());
        smartPhone1.get().setModel(smartphone.getModel());
        smartPhone1.get().setPrice(smartphone.getPrice());

        return new ResponseEntity<>(smartPhoneService.save(smartPhone1.get()), HttpStatus.OK);
    }
}
