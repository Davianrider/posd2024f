package com.example;

public class CaptionedContent implements Content {

    private Content content;
    private Text caption;

    public CaptionedContent(Content content, Text caption) {
        this.content = content;
        this.caption = caption;
    }

    public Content getContent() {
        return content;
    }

    public Text getCaption() {
        return caption;
    }

    @Override
    public void accept(ContentVisitor visitor) {
        visitor.visitCaptionedContent(this);
    }

}
