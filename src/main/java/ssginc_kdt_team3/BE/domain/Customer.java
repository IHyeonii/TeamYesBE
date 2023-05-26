package ssginc_kdt_team3.BE.domain;

import lombok.Getter;
import lombok.Setter;
import ssginc_kdt_team3.BE.enums.CustomerType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Customer extends User{

  @NotNull
  @Column(name = "customeromer_alarm")
  private boolean alarmBoolean;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "grade_id")
  private Grade grade;

  @Column(name = "customer_type")
  @Enumerated(EnumType.STRING)
  private CustomerType type;
}
