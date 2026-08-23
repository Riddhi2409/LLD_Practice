package entity;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final static Map<String,Item> itemMap =new HashMap<>();
    private final static Map<String,Integer> stockMap = new HashMap<>() ;

    public void addItem(String code, int quantity, Item item){
        itemMap.put(code,item);
        stockMap.put(code,quantity);
    }

    public Item getItem(String code){
        return itemMap.get(code);
    }

    public void reduceItem(String code){
        int quantity=stockMap.getOrDefault(code,0);
        if(quantity>0){
            stockMap.put(code,quantity-1);
        }
    }

    public boolean isAvailable(String code){
        int quantity=stockMap.getOrDefault(code,0);
        return quantity > 0;
    }

}
