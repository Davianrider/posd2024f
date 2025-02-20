package com.example;

import java.util.ArrayList;
import java.util.List;

public class InsertVisitor implements ContentVisitor {
    private Content toBeInserted;
    private List<Content> result;

    public InsertVisitor(Content content) {
        this.toBeInserted = content;
        this.result = new ArrayList<Content>();
    }

    @Override
    public void visitText(Text text) {
        String originalText = text.getText();
        if (toBeInserted instanceof Text) {
            String newText = originalText.substring(0, text.getPosition()) + toBeInserted + originalText.substring(text.getPosition());
            result.add(new Text(newText));
        } else {
            result.add(new Text(originalText.substring(0, text.getPosition())));
            result.add(toBeInserted);
            result.add(new Text(originalText.substring(text.getPosition())));
        }
    }

    public List<Content> getResult() {
        return result;
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
