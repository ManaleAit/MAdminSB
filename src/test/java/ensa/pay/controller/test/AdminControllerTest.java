package ensa.pay.controller.test;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Vector;

import static org.mockito.Mockito.doThrow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import ensa.pay.dto.AdminDto;
import ensa.pay.exception.ExceptionCode;
import ensa.pay.exception.MicroServiceException;
import ensa.pay.service.AdminService;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
//@WithMockUser
public class AdminControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private AdminService adminService;

	@Test
	public void getNotFoundTest() throws Exception {
		given(adminService.findById(anyLong()))
				.willThrow(new MicroServiceException(ExceptionCode.API_RESOURCE_NOT_FOUND, "admin not found"));

		mockMvc.perform(get("/Admin/{Id}", 2).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.code").value("API_RESOURCE_NOT_FOUND"))
				.andExpect(jsonPath("$.message").value("admin not found"))
				.andExpect(jsonPath("$.ticketId").isNotEmpty()).andExpect(jsonPath("$.status").value(404))
				.andExpect(jsonPath("$.time").isNotEmpty()).andExpect(jsonPath("$.errors").exists())
				.andExpect(jsonPath("$.errors", hasSize(0))).andExpect(jsonPath("$.fieldErrors").exists())
				.andExpect(jsonPath("$.fieldErrors", hasSize(0)));
	}

	@Test
	public void getTest() throws Exception {
		AdminDto adminDto = new AdminDto();
		adminDto.setId(1L);
		adminDto.setNom("manal");
		adminDto.setPrenom("ait dik");
		adminDto.setEmail("manal147@gmail.com");
		adminDto.setAdresse("safi,maroc");
		adminDto.setTelephone("0687412");

		given(adminService.findById(anyLong())).willReturn(adminDto);
		mockMvc.perform(get("/Admin/{Id}", 1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.nom").value("manal"))
				.andExpect(jsonPath("$.prenom").value("ait dik"))
				.andExpect(jsonPath("$.email").value("manal147@gmail.com"))
				.andExpect(jsonPath("$.adresse").value("safi,maroc"))
				.andExpect(jsonPath("$.telephone").value("0687412"));

	}

	@Test
	public void SaveTest() throws Exception {

		AdminDto adminDto = new AdminDto();
		adminDto.setId(1L);
		adminDto.setNom("manal");
		adminDto.setPrenom("ait dik");
		adminDto.setEmail("manal147@gmail.com");
		adminDto.setAdresse("safi,maroc");
		adminDto.setTelephone("0687412");
		doNothing().when(adminService).save(org.mockito.ArgumentMatchers.any());
		mockMvc.perform(MockMvcRequestBuilders.post("/Admin").content(asJsonString(adminDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void SaveNullTest() throws Exception {
		doThrow(new MicroServiceException(ExceptionCode.API_DATA_ERRORS, "Resource is null")).when(adminService)
				.save(null);
		mockMvc.perform(MockMvcRequestBuilders.post("/Admin")).andExpect(status().isBadRequest());
	}

	/*@Test
	public void DeleteTest() throws Exception {

		doNothing().when(adminService).delete(1L);
		mockMvc.perform(get("/Admin/{Id}", 1)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());


	}*/

	@Test
	public void getAllNotFoundTest() throws Exception {
		given(adminService.findAll())
				.willThrow(new MicroServiceException(ExceptionCode.API_RESOURCE_NOT_FOUND, "admin not found"));

		mockMvc.perform(get("/Admin").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.code").value("API_RESOURCE_NOT_FOUND"))
				.andExpect(jsonPath("$.message").value("admin not found"))
				.andExpect(jsonPath("$.ticketId").isNotEmpty()).andExpect(jsonPath("$.status").value(404))
				.andExpect(jsonPath("$.time").isNotEmpty()).andExpect(jsonPath("$.errors").exists())
				.andExpect(jsonPath("$.errors", hasSize(0))).andExpect(jsonPath("$.fieldErrors").exists())
				.andExpect(jsonPath("$.fieldErrors", hasSize(0)));
	}

	@Test
	public void getAllTest() throws Exception {
		List<AdminDto> adminDtos = new Vector<AdminDto>();
		AdminDto adminDto = new AdminDto();
		adminDto.setId(1L);
		adminDto.setNom("manal");
		adminDto.setPrenom("ait dik");
		adminDto.setEmail("manal147@gmail.com");
		adminDto.setAdresse("safi,maroc");
		adminDto.setTelephone("0687412");
		adminDtos.add(adminDto);
		given(adminService.findAll()).willReturn(adminDtos);

		mockMvc.perform(get("/Admin").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].id").value(1)).andExpect(jsonPath("$.[0].nom").value("manal"))
				.andExpect(jsonPath("$.[0].prenom").value("ait dik"))
				.andExpect(jsonPath("$.[0].email").value("manal147@gmail.com"))
				.andExpect(jsonPath("$.[0].adresse").value("safi,maroc"))
				.andExpect(jsonPath("$.[0].telephone").value("0687412"));

	}

	@Test
	public void DeleteNotFoundTest() throws Exception {
		doThrow(new MicroServiceException(ExceptionCode.API_RESOURCE_NOT_FOUND, "admin not found")).when(adminService)
				.delete(anyLong());

		mockMvc.perform(delete("/Admin/{Id}", 2).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("$.code").value("API_RESOURCE_NOT_FOUND"))
				.andExpect(jsonPath("$.message").value("admin not found"))
				.andExpect(jsonPath("$.ticketId").isNotEmpty()).andExpect(jsonPath("$.status").value(404))
				.andExpect(jsonPath("$.time").isNotEmpty()).andExpect(jsonPath("$.errors").exists())
				.andExpect(jsonPath("$.errors", hasSize(0))).andExpect(jsonPath("$.fieldErrors").exists())
				.andExpect(jsonPath("$.fieldErrors", hasSize(0)));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
