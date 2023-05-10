package ssginc_kdt_team3.BE.controller.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ssginc_kdt_team3.BE.DTOs.reservation.CustomerReservationAddDTO;
import ssginc_kdt_team3.BE.DTOs.reservation.CustomerReservationDetailDTO;
import ssginc_kdt_team3.BE.DTOs.reservation.CustomerReservationListDTO;
import ssginc_kdt_team3.BE.DTOs.reservation.CustomerReservationUpdateDTO;
import ssginc_kdt_team3.BE.service.customer.CustomerReservationService;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/customer/reservation")
public class CustomerReservationController {

    private final CustomerReservationService reservationService;

    @PostMapping("/add")
    public ResponseEntity createReservation(@Validated @RequestBody CustomerReservationAddDTO dto, BindingResult bindingResult) {

        if (dto.getPeople() <= dto.getChild()) {
            log.info("too many baby");
            bindingResult.reject("too many baby");
        }

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        Long aLong = reservationService.makeReservation(dto);

        if (aLong != null) {
            return ResponseEntity.ok().body(aLong + "번 ");
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("listAll/{id}/{page}")
    public ResponseEntity<Page<CustomerReservationListDTO>> showAllCustomerReservation(@PathVariable(name = "id") Long id, @PathVariable(name = "page") int page) {
        Pageable pageable = PageRequest.of(page-1, 2);
        Page<CustomerReservationListDTO> list = reservationService.showMyAllReservation(pageable, id);

        return ResponseEntity.ok(list);
    }

    @GetMapping("listActive/{id}/{page}")
    public ResponseEntity<Page<CustomerReservationListDTO>> showActiveCustomerReservation(@PathVariable(name = "id") Long id, @PathVariable(name = "page") int page) {
        Pageable pageable = PageRequest.of(page-1, 10);
        Page<CustomerReservationListDTO> list = reservationService.showMyActiveReservation(pageable, id);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/update/{id}")
    public ResponseEntity updateReservationRead(@PathVariable(name = "id") Long id) {
        try {
            CustomerReservationUpdateDTO reservationUpdateDTO = reservationService.showUpdateReservation(id);
            return ResponseEntity.ok(reservationUpdateDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/update/{id}")
    public ResponseEntity updateReservation(@Validated @RequestBody CustomerReservationUpdateDTO dto, BindingResult bindingResult, @PathVariable(name = "id") Long id) {

        boolean b = reservationService.updateReservation(id, dto);

        if (dto.getPeople() <= dto.getChild()) {
            log.info("too many baby");
            bindingResult.reject("too many baby");
        }

        if (bindingResult.hasErrors()) {
            bindingResult.reject("too many baby");
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        if (b) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity cancelReservation(@PathVariable(name = "id") Long id) {
        boolean b = reservationService.customerCancel(id);

        if (b) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable(name = "id") Long id) {
        Optional<CustomerReservationDetailDTO> one = reservationService.findOne(id);

        if (one.isPresent()) {
            return ResponseEntity.ok(one.get());
        }
        return ResponseEntity.badRequest().build();
    }
}
