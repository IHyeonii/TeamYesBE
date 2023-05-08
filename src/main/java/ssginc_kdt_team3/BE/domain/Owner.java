package ssginc_kdt_team3.BE.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ssginc_kdt_team3.BE.DTOs.cust.Address;
import ssginc_kdt_team3.BE.enums.UserRole;
import ssginc_kdt_team3.BE.enums.UserStatus;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "owner")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Owner extends User{

}
