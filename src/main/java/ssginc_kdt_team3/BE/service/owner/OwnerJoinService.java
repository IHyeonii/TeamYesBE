package ssginc_kdt_team3.BE.service.owner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ssginc_kdt_team3.BE.DTOs.owner.OwnerJoinDTO;
import ssginc_kdt_team3.BE.domain.Owner;
import ssginc_kdt_team3.BE.enums.UserRole;
import ssginc_kdt_team3.BE.enums.UserStatus;
import ssginc_kdt_team3.BE.repository.owner.JpaDataOwnerRepository;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class OwnerJoinService {

    private final JpaDataOwnerRepository repo;

    public void join(OwnerJoinDTO ownerJoinDTO) throws Exception {

        boolean emailCheck = repo.existsByEmail(ownerJoinDTO.getEmail());

        if (emailCheck){
            throw new Exception("중복된 이메일 입니다.");
        }
        log.info("log info = {}",emailCheck);
        Owner owner = new Owner();

        owner.setEmail(ownerJoinDTO.getEmail());
        owner.setPassword(ownerJoinDTO.getPassword());
        owner.setName(ownerJoinDTO.getName());
        owner.setPhoneNumber(ownerJoinDTO.getPhone());
        owner.setBirthday(ownerJoinDTO.getBirthday());
        owner.setGender(ownerJoinDTO.isGender());
        owner.setAddress(ownerJoinDTO.getAdddress());
        owner.setRole(UserRole.OWNER);
        owner.setStatus(UserStatus.ACTIVE);

        log.info("log info = {}",owner);
        log.info("log info = {}",emailCheck);

        repo.save(owner);

        log.info("log info = {}",owner.getEmail());
        log.info("log info = {}",owner.getName());
        log.info("log info = {}",owner.getBirthday());
        log.info("log info = {}",owner.getRole());
    }

}