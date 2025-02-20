package org.ntut.posd2024f.midterm;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DiscountItem implements Item, Iterable<Item> {

    private Item item;
    private double discount;
    
    public DiscountItem(Item item, double discount) throws IllegalArgumentException {
        if (discount < 0 || discount > 1)
        throw new IllegalArgumentException("The discount should be between 0 and 1.");  
         
        
        this.item = item;
        this.discount = discount;
    }

    public Item getItem(){
        return item;
    }

    public double getDiscount(){
        return discount;
    }

    @Override
    public String getTitle(){
        DecimalFormat df = new DecimalFormat("0.##");
        
        String discountItemTitle = "<"+item.getTitle()+">" + " is on sale! " + df.format((discount * 100)) + "% off!";

        return discountItemTitle;
    }

    @Override
    public <T> void accept(ItemVisitor<T> visitor) {
        visitor.visitDiscountItem(this);
    }

    @Override
    public Iterator<Item> iterator() {
        if (item instanceof Bundle) {
            return ((Bundle)item).iterator();
        }
        else if (item instanceof DiscountItem) {
            return ((DiscountItem)item).iterator();
        }
        return new NullIterator() {
            @Override
            public boolean hasNext() {
                return false;
            }
            @Override
            public Item next() {
                throw new NoSuchElementException("No more element.");
            }
        };
     }

}
