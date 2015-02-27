package ItemsModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Implement IItem
 */

public class BaseItem implements IItem {
    private int itemsID;
    private String title;
    private String description;
    private double price;
    private String category;

    public BaseItem (int itemsID, String title, String description, double price, String category){
    this.itemsID=itemsID;
    this.title=title;
    this.description=description; 
    this.price=price;
    this.category=category;
}
    /**
     * @return the itemsID
     */
    public int getItemsID() {
        return itemsID;
    }

    /**
     * @param itemsID the itemsID to set
     */
    public void setItemsID(int itemsID) {
        this.itemsID = itemsID;
    }

    /**
     * @return the Title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param Title the Title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the Price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param Price the Price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the Category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param Category the Category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }
    
 
  
  
          
  
}
