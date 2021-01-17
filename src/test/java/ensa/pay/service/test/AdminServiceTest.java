package ensa.pay.service.test;

import static org.mockito.BDDMockito.given;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.assertj.core.api.Assertions;
import java.util.Optional;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import ensa.pay.domain.Admin;
import ensa.pay.dto.AdminDto;
import ensa.pay.exception.ExceptionCode;
import ensa.pay.exception.MicroServiceException;
import ensa.pay.mapper.AdminMapper;
import ensa.pay.repository.SuperAdminRepository;
import ensa.pay.service.AdminService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	@Autowired
	private AdminService adminService;
	@MockBean
	private SuperAdminRepository adminRepository;

	@Test(expected = MicroServiceException.class)
	public void saveAdminTestCasException() {
		Admin admin = null;
		given(adminRepository.save(admin))
				.willThrow(new MicroServiceException(ExceptionCode.API_DATA_ERRORS, "Resource is null"));
		adminService.save(AdminMapper.toDto(admin));
	}

	@Test
	public void saveAdminTest() {
		Admin admin = new Admin();
		admin.setId(3L);
		admin.setNom("manal");
		admin.setPrenom("ait dik");
		admin.setEmail("manal147@gmail.com");
		admin.setAdresse("safi,maroc");
		admin.setTelephone("0687412");
		given(adminRepository.save(admin)).willReturn(admin);
		adminService.save(AdminMapper.toDto(admin));
	}

	@Test
	public void DeleteAdminTest() {
		Admin admin = new Admin();
		admin.setId(1L);
		admin.setAdresse("safi,maroc");
		admin.setEmail("manal147@gmail.com");
		admin.setNom("manal");
		admin.setPrenom("ait dik");
		admin.setTelephone("0687412");
		Optional<Admin> optional = Optional.of(admin);
		given(adminRepository.findById(1L)).willReturn(optional);
		adminService.delete(1L);
	}

	@Test(expected = MicroServiceException.class)
	public void DeleteAdminExceptionTest() {
		given(adminRepository.findById(anyLong())).willReturn(null);
		adminService.findById(any());

	}

	@Test(expected = MicroServiceException.class)
	public void NotFindAdminTest() {
		Optional<Admin> optional = null;
		given(adminRepository.findById(anyLong())).willReturn(optional);
		adminService.findById(any());

	}

	@Test
	public void FindAdminTest() {
		Admin admin = new Admin();
		admin.setId(1L);
		admin.setNom("manal");
		admin.setPrenom("ait dik");
		admin.setEmail("manal147@gmail.com");
		admin.setAdresse("safi,maroc");
		admin.setTelephone("0687412");

		Optional<Admin> optional = Optional.of(admin);
		given(adminRepository.findById(1L)).willReturn(optional);
		AdminDto adminDto = AdminMapper.toDto(admin);
		assertEquals(adminService.findById(adminDto.getId()).getId(), adminRepository.findById(1L).get().getId());
		Assertions.assertThat(adminRepository.findById(1L).get())
				.isEqualToIgnoringGivenFields(adminService.findById(adminDto.getId()));
	}

}
