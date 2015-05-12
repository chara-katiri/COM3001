/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ItemsModel;
import java.util.Map;
/**
 *
 * @author CharaKatiri
 */
public class RoomItem extends BaseItem{

       public RoomItem(int id, String title, String description, double price, String category, Map flags) {
        super(ItemType.ROOMS, id, title, description, price, category);
    }
    
}
