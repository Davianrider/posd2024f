package com.example;

public class FindVisitor implements ContentVisitor {

    private String target;

    public FindVisitor(String target) {
        this.target = target;
    }

    @Override
    public void visitText(Text text) {
        int position = text.getText().indexOf(target);
        if (position != -1) {
            text.setPosition(position);
        }
    }

    @Override
    public void visitPicture(Picture picture) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitPicture'");
    }

    @Override
    public void visitCaptionedContent(CaptionedContent captionedContent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitCaptionedContent'");
    }

    @Override
    public void visitHybridContent(HybridContent hybridContent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visitHybridContent'");
    }
}
