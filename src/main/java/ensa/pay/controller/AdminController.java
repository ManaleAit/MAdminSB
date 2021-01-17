package ensa.pay.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import ensa.pay.dto.AdminDto;
import ensa.pay.dto.views.UserView;
import ensa.pay.exception.MicroServiceException;
import ensa.pay.service.AdminService;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Admin")
public class AdminController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	private final AdminService adminService;

	public AdminController(AdminService AdminService) {
		this.adminService = AdminService;
	}

	@PostMapping
	@JsonView(UserView.Basic.class)
	public void add(@RequestBody AdminDto AdminDto) throws MicroServiceException {
		LOGGER.debug("START RESOURCE add admin by name : {}", AdminDto.getNom());
		adminService.save(AdminDto);
		LOGGER.debug("END RESOURCE add admin by id : {}, name: {} is ok", AdminDto.getId(), AdminDto.getNom());
	}

	@PutMapping
	@JsonView(UserView.Basic.class)
	public void update(@RequestBody AdminDto AdminDto) throws MicroServiceException {
		LOGGER.debug("START RESOURCE update admin by name : {}, id: {}", AdminDto.getNom(), AdminDto.getId());
		adminService.save(AdminDto);
		LOGGER.debug("END RESOURCE update admin by id : {}, name: {} is ok", AdminDto.getId(), AdminDto.getNom());
	}

	@GetMapping("/{id}")
	@JsonView(UserView.Basic.class)
	public AdminDto get(@PathVariable Long id) throws MicroServiceException {
		LOGGER.debug("START RESOURCE find admin by id : {}", id);
		AdminDto AdminDto = adminService.findById(id);
		LOGGER.debug("END RESOURCE find admin by id : {}, name :{}", id, AdminDto.getNom());
		return AdminDto;
	}

	@GetMapping
	@JsonView(UserView.Basic.class)
	public List<AdminDto> getAll() throws MicroServiceException {
		LOGGER.debug("START RESOURCE all find cities");
		List<AdminDto> AdminDtos = adminService.findAll();
		LOGGER.debug("END RESOURCE find all cities, size: {}", AdminDtos.size());
		return AdminDtos;
	}

	@DeleteMapping("/{id}")
	@JsonView(UserView.Basic.class)
	public void delete(@PathVariable Long id) throws MicroServiceException {
		LOGGER.debug("START RESOURCE delete admin by id : {}", id);
		adminService.delete(id);
		LOGGER.debug("END RESOURCE delete admin by id : {}, is ok", id);
	}
}
