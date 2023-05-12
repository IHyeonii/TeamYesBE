package ssginc_kdt_team3.BE.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "shop_menu")
public class ShopMenu {

    @Id
    @NotNull
    @Column(name = "menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "menu_name", length = 20)
    private String name;

    @NotNull
    @Column(name = "menu_price")
    private int price;

    @NotNull
    @Column(name = "menu_imgurl")
    private String menu_img;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;
}
