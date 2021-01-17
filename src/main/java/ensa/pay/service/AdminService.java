package ensa.pay.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ensa.pay.dto.AdminDto;
import ensa.pay.exception.ExceptionCode;
import ensa.pay.exception.MicroServiceException;
import ensa.pay.mapper.AdminMapper;
import ensa.pay.repository.SuperAdminRepository;

@Service
@Transactional
public class AdminService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminService.class);

	@Autowired
	private SuperAdminRepository AdminRepository;

	public AdminDto findById(Long id) throws MicroServiceException {
		try {
			LOGGER.debug("START SERVICE find by id {}", id);
			return Optional.ofNullable(AdminRepository.findById(id)).map(v -> AdminMapper.toDto(v.get())).orElseThrow(
					() -> new MicroServiceException(ExceptionCode.API_RESOURCE_NOT_FOUND, "admin not found"));
		} catch (Throwable t) {
			throw new MicroServiceException(ExceptionCode.API_RESOURCE_NOT_FOUND, "admin not found");
		}
	}

	public List<AdminDto> findAll() throws MicroServiceException {
		LOGGER.debug("START SERVICE find all");
		return Optional.ofNullable(AdminRepository.findAll()).map(AdminMapper::toDtos)
				.orElseThrow(() -> new MicroServiceException(ExceptionCode.API_RESOURCE_NOT_FOUND, "admins not found"));
	}

	public void delete(Long id) throws MicroServiceException {

		if (!AdminRepository.findById(id).isPresent()) {

			throw new MicroServiceException(ExceptionCode.API_RESOURCE_NOT_FOUND, "admin not found");
		}
		LOGGER.debug("START SERVICE delete by id {}", id);
		AdminRepository.deleteById(id);
		LOGGER.debug("START SERVICE delete by id {}", id);

	}

	public void save(AdminDto AdminDto) throws MicroServiceException {
		try {
			LOGGER.debug("START SERVICE save by id {}, name: {}", AdminDto.getId(), AdminDto.getNom());
			AdminRepository.save(AdminMapper.toEntity(AdminDto));
			LOGGER.debug("START SERVICE save by id {}, name: {}", AdminDto.getId(), AdminDto.getNom());
		} catch (Throwable t) {
			throw new MicroServiceException(ExceptionCode.API_DATA_ERRORS, "Resource is null");
		}
	}
}
