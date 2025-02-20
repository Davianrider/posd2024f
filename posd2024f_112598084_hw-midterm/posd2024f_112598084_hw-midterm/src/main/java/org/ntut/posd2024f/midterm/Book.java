package org.ntut.posd2024f.midterm;

import java.lang.String;

public class Book implements Item{

    private String title;
    private double price;

    public Book(String title, double price) throws IllegalArgumentException{       
        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException("The book should have a title.");
        
        if (price < 0)
            throw new IllegalArgumentException("The price should be greater than or equal to 0.");    
        
        this.title = title;
        this.price = price;
    }

    @Override
    public String getTitle(){
        return title;
    }

    
    public double getPrice(){
        return price;
    }

    @Override
    public <T> void accept(ItemVisitor<T> visitor) {
        visitor.visitBook(this);
    }

    


}
