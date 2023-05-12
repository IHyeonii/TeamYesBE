package ssginc_kdt_team3.BE.controller.owner;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ssginc_kdt_team3.BE.DTOs.owner.OwnerJoinDTO;
import ssginc_kdt_team3.BE.DTOs.owner.OwnerLoginDTO;
import ssginc_kdt_team3.BE.service.owner.OwnerJoinService;
import ssginc_kdt_team3.BE.service.owner.OwnerLoginService;


@RequestMapping("/owner")
@RestController
@RequiredArgsConstructor
@Controller

public class OwnerController {

    private final OwnerJoinService ownerJoinService;

    private final OwnerLoginService Service;

    @PostMapping("/join")
    public ResponseEntity<String> joinControl(@RequestBody OwnerJoinDTO ownerJoinDTO) {

        try{
            ownerJoinService.join(ownerJoinDTO);
            return new ResponseEntity<>("회원가입이 완료되었습니다!",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("회원가입 실패",HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/login")
    public ResponseEntity<String> loginCheck(@RequestBody OwnerLoginDTO ownerLogin) {

        try {
            String OwnerEmail = Service.loginCheck(ownerLogin);
            return new ResponseEntity<>(OwnerEmail+"님, 환영합니다!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}