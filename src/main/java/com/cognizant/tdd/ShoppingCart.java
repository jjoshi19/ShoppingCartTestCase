package com.cognizant.tdd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    Map<String,Item> items = new HashMap<>();
    public int totalItems(){
        return items.size();
    }

    public void addItem(Item item){
        if(items.containsKey(item.getItemName()))
        {
            Item oldItem  = items.get(item.getItemName());
            oldItem.setCountOfItem(oldItem.getCountOfItem()+ item.getCountOfItem());
            items.put(item.getItemName(),oldItem);
        }
        else items.put(item.getItemName(),item);
    }

    public void removeItem(Item item){
         items.remove(item.getItemName());
    }
    public double getTotal(){
        double total=0.00;
        for(Item item:items.values()){
            total+= item.getValue()* item.getCountOfItem();
        }
        return total;
    }

    public List<Item> getItems(){
        return new ArrayList<>( items.values());

    }

    public void reduceItemQuantity(String name, int quantity)throws IllegalArgumentException{

        if(items.containsKey(name))
        {
            Item oldItem  = items.get(name);
            if(oldItem.getCountOfItem()>=quantity) {
                oldItem.setCountOfItem(oldItem.getCountOfItem() - quantity);
            }else{
                throw new IllegalArgumentException("Reduced quantity must be less then current quantity");
            }
            items.put(name,oldItem);

        }

    }
}