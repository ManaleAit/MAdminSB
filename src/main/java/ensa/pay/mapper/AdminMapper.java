package ensa.pay.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ensa.pay.domain.Admin;
import ensa.pay.dto.AdminDto;

public class AdminMapper {
	private AdminMapper() {
	}

	public static AdminDto toDto(Admin admin) {
		if (admin == null) {
			return null;
		}
		return AdminDto.builder().id(admin.getId()).nom(admin.getNom()).prenom(admin.getPrenom())
				.email(admin.getEmail()).adresse(admin.getAdresse()).telephone(admin.getTelephone()).build();
	}

	public static List<AdminDto> toDtos(List<Admin> admins) {
		if (admins != null) {
			return admins.stream().filter(Objects::nonNull).map(AdminMapper::toDto).collect(Collectors.toList());
		} else

		{
			return null;
		}
	}

	public static Admin toEntity(AdminDto adminDto) {
		if (adminDto != null) {
			return Admin.builder().id(adminDto.getId()).nom(adminDto.getNom()).prenom(adminDto.getPrenom())
					.email(adminDto.getEmail()).adresse(adminDto.getAdresse()).telephone(adminDto.getTelephone())
					.build();
		} else {
			return null;
		}
	}
}
