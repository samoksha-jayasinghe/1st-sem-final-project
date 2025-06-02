package lk.ijse.bakerymanagment.dto.tm;

import lombok.Data;

/*@Getter
@Setter
@ToString*/
@Data

public class ItemTM {

        private String itemId;
        private String name;
        private String category;
        private int price;
        private int quantity;
        private String expirDate;

        public ItemTM(String itemId, String name, String category, int price, int quantity, String expirDate) {
        }

       /* public ItemTM(String itemId, String name, String category, double price, int quantity, String expirDate) {
        }*/
}

