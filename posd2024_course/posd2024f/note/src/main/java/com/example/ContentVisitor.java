package com.example;

public interface ContentVisitor {
    public void visitText(Text text);
    public void visitPicture(Picture picture);
    public void visitCaptionedContent(CaptionedContent captionedContent);
    public void visitHybridContent(HybridContent hybridContent);
}
