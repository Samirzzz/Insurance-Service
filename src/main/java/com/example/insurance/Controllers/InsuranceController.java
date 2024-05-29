package com.example.insurance.Controllers;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.insurance.Models.Insurance;
import com.example.insurance.repository.InsuranceRepository;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/insurance")
public class InsuranceController {
private static final String UPLOADED_FOLDER = null;
@Autowired
InsuranceRepository insuranceRepo;

@GetMapping("")
public ResponseEntity<List<Insurance>> getInsurances() {
    List<Insurance> insurances = this.insuranceRepo.findAll();
    return new ResponseEntity<>(insurances, HttpStatus.OK);
}

    @GetMapping("/add")
    public  ModelAndView addcarddetails() {
        ModelAndView mav=new ModelAndView("addinsurance.html");
        Insurance insurance=new Insurance();
        mav.addObject("insurance", insurance);

        return mav;

    }
    @GetMapping("/view")
    public ModelAndView viewCardDetails() {
        ModelAndView mav = new ModelAndView("viewinsurance.html");
        List<Insurance> insurances = insuranceRepo.findAll();
        mav.addObject("insurances", insurances);
        return mav;
    }
    @PostMapping("/add")
    public String addInsurance(@RequestParam("email") String email,
                               @RequestParam("card") MultipartFile file,
                               RedirectAttributes redirectAttributes) throws IOException {
                                String imagesDirectory = "src/main/resources/static/images/";

                                byte[] bytes = file.getBytes();
                                Path path = Paths.get(imagesDirectory + file.getOriginalFilename());
                                Files.write(path, bytes);
                        
            
            Insurance insurance = new Insurance();
            insurance.setEmail(email);
            insurance.setCard("/images/" + file.getOriginalFilename());

            insuranceRepo.save(insurance);

        

        return "added";
    }
 
    
    @GetMapping("/{email}")
    public ResponseEntity<Insurance> getInsuranceByEmail(@PathVariable String email) {
        Insurance insurance = insuranceRepo.findByemail(email);

        if (insurance == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println();
        System.out.println(insurance);
        return new ResponseEntity<>(insurance, HttpStatus.OK);
    }
}




