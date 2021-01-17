package ensa.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ensa.pay.repository.SuperAdminRepository;

//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@SpringBootApplication
public class MicroserviceApplication  {
	
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceApplication.class, args);

	}
	
   /* public void run(String... params) {
    
    	Admin a=new Admin(3L,"manal","manal","ffff","hgyg","7777");
    	Admin s=AdminRepository.insert(a);
    	System.out.println("hi id    "+s.getId());
    	
      }*/
}
