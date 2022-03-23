package com.example.orm;

import com.example.orm.entities.Patient;
import com.example.orm.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class OrmApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {

        SpringApplication.run(OrmApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(new Patient(null,"Assia",new Date(),false,56));
        patientRepository.save(new Patient(null,"Imane",new Date(),false,100));
        patientRepository.save(new Patient(null,"Salma",new Date(),false,210));
        patientRepository.save(new Patient(null,"Hicham",new Date(),false,30));
        patientRepository.save(new Patient(null,"Asmaa",new Date(),false,100));
        patientRepository.save(new Patient(null,"Hamza",new Date(),false,210));

     //  for (int i=0; i<100; i++){
        //   patientRepository.save(
             //      new Patient(null,"Assia",new Date(),Math.random()>0.5?true:false,(int)(Math.random()*100)));

        Page<Patient> patients=patientRepository.findAll(PageRequest.of(1,3));
        System.out.println("Total pages:"+patients.getTotalPages());
        System.out.println("Total elements"+patients.getTotalElements());
        System.out.println("Num Page"+patients.getNumber());
        List<Patient>content=patients.getContent();
        Page<Patient> byMalade = patientRepository.findByMalade(true,PageRequest.of(0,4));
        List<Patient>patientList=patientRepository.chercherpatients("%h%",40);
        content.forEach(p->{
            System.out.println("---------------------");
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println( p.getScore());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());

        });

        System.out.println("***********************");
      /*  Patient patient =patientRepository.findById(1L).orElse(null);
        if (patient!=null){
            patient.setScore(870);
            patientRepository.save(patient);
        }*/

         patientRepository.deleteById(1L);


        //  System.out.println(patient.getScore());
        //  System.out.println(patient.isMalade());
    }
}
