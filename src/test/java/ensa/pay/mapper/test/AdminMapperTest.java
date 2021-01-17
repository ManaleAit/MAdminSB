package ensa.pay.mapper.test;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ensa.pay.domain.Admin;
import ensa.pay.dto.AdminDto;
import ensa.pay.mapper.AdminMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminMapperTest {
	 @Test
	    public void toDto() {
		 Admin admin = Admin.builder().build();
		 AdminDto  adminDto = AdminMapper.toDto(admin);
	     assertNotNull(adminDto);
	     Assertions.assertThat(adminDto).isEqualToIgnoringGivenFields(admin);
	    }

	    @Test
	    public void toDtos() {
	    	Admin Admin1 = Admin.builder().id(1L).build();
	    	Admin Admin2 = Admin.builder().id(2L).build();
	    	Admin Admin3 = Admin.builder().id(3L).build();
	        List<AdminDto> adminDtos = AdminMapper.toDtos(asList(Admin1, null, Admin2,Admin3));
	        assertTrue(adminDtos != null && adminDtos.size() == 3);
	    }

	    @Test
	    public void toEntity() {
	    	AdminDto adminDto = AdminDto.builder().build();
	    	Admin admin = AdminMapper.toEntity(adminDto);
	        Assertions.assertThat(admin).isEqualToIgnoringGivenFields(adminDto);
	    }
}
