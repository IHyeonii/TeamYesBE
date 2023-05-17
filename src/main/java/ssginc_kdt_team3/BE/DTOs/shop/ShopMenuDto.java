package ssginc_kdt_team3.BE.DTOs.shop;

import lombok.Data;
import ssginc_kdt_team3.BE.domain.ShopMenu;

@Data
public class ShopMenuDto {

    private long menuId;
    private String menuName;
    private int menuPrice;
    private String menuImg;

    public ShopMenuDto(ShopMenu shopMenu) {
        this.menuId = shopMenu.getId();
        this.menuName = shopMenu.getName();
        this.menuPrice = shopMenu.getPrice();
        this.menuImg = shopMenu.getMenu_img();
    }

    public ShopMenuDto() {
    }
}
