package org.ntut.posd2024f.midterm;

public class PriceVisitor implements ItemVisitor{
    
    private Double totalPrice;

    public PriceVisitor (){
        totalPrice = 0.0;
    }
    
    @Override
    public void visitBook(Book book){
        totalPrice += book.getPrice()  ;
    };

    @Override
    public void visitBundle(Bundle bundle){
        for (Item item : bundle) {
            item.accept(this);
        }
    };

    @Override
    public void visitDiscountItem(DiscountItem discountItem){
         // Apply discount and accept visitor on the wrapped item
         PriceVisitor nestedVisitor = new PriceVisitor();
         discountItem.getItem().accept(nestedVisitor);
         double discountedPrice = nestedVisitor.getResult() * (1 - discountItem.getDiscount());
         totalPrice += discountedPrice;

    };

    @Override
    public Double getResult(){
        return totalPrice;
    }
}