package ItemsModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Implement IItem
 */

public class BaseItem implements IItem {
   
    private ItemType type;
    private int id;
    private String title;
    private String description;
    private double price;
    private String category;

    
     public BaseItem(ItemType type, int id, String title, String description, double price, String category) {
        this.type = type;
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
    }
     
    /*
    Accessor method for type
    */
   public ItemType getType(){
       return type;
   }
   
   /*
   Mutator method for type
   */
   public void setType (ItemType type){
       this.type = type;
   }
   
   /*
   Accessor method for id
   */
  
    public int getid() {
        return id;
    }

    /*
    mutator method for id
    */
    public void setId (int id){
       this.id = id; 
    }

    /*
    Accessor method for title
    */
    public String getTitle() {
        return title;
    }

    /*
    Mutator method for title
    */
    public void setTitle(String title) {
        this.title = title;
    }

    /*
      Accessor method for description 
     */
    public String getDescription() {
        return description;
    }

    /*
     Mutator method for description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /*
     Accessor method for price
     */
    public double getPrice() {
        return price;
    }

    /*
    Mutator method for price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /*
    Accessor method for category
     */
    public String getCategory() {
        return category;
    }

    /*
    Mutator method for category
     */
    public void setCategory(String category) {
        this.category = category;
    }

   
          
  
}
