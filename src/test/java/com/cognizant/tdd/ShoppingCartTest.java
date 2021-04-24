package com.cognizant.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import javax.swing.plaf.metal.MetalBorders;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {
    ShoppingCart sc;

    @BeforeEach
    public void setUp(){
       sc = new ShoppingCart();
    }
    @Test
    public void testIsEmpty(){
        assertEquals(0,sc.totalItems());
    }
    @Test
    public void testAddItem(){
        sc.addItem(new Item("apple", 10,1));
        assertEquals(10,sc.getTotal());
        sc.addItem(new Item("oranges",10,2));
        assertEquals(30,sc.getTotal());
    }
    @Test
    public void testAddSameItem(){
        sc.addItem(new Item("apple", 1,10));
        assertEquals(10,sc.getTotal());
        sc.addItem(new Item("apple", 1,10));

        assertEquals(20,sc.getTotal());
    }

    @Test
    public void testDisplayItem(){
        Item item1 = new Item("apple", 1,10);
        sc.addItem(item1);
        Item item2 = new Item("oranges",2,10);
        sc.addItem(item2);
        List<Item> expected = new ArrayList<>();
        expected.add(item1);
        expected.add(item2);
        final List<Item> actual = sc.getItems();
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void testItemsOnSale(){
        final Item item1 = new Item("apple", 1,10,true);
        sc.addItem(item1);
        Item item2 = new Item("oranges",2,10,false);
        sc.addItem(item2);
        List<Item> items = sc.getItems();
        Optional<Item> itemHighlighted = items.stream()
                .filter(item -> item.getItemName().equalsIgnoreCase(item1.getItemName()))
                .findAny();
        assertTrue(itemHighlighted.isPresent());
        assertEquals(true, itemHighlighted.get().isOnSale());
    }
    @Test
    public void testRemoveItem(){
        Item item1 =new Item("apple", 1,10);
        sc.addItem(item1);
        assertEquals(10,sc.getTotal());
        sc.removeItem(item1);
        assertEquals(0, sc.getTotal());
        assertEquals(0,sc.totalItems());

    }
    @Test
    public void testReduceItemQuantity(){
        Item item1 =new Item("apple", 3,10);
        sc.addItem(item1);
        sc.reduceItemQuantity("apple",7);
        List<Item> items = sc.getItems();

        assertEquals(3, items.get(0).getCountOfItem());

    }
    @Test
    public void testReduceInvalidItemQuantity(){
        Item item1 =new Item("apple", 3,10);
        sc.addItem(item1);
        assertThrows(IllegalArgumentException.class, () -> {sc.reduceItemQuantity("apple",20);});

    }
}
