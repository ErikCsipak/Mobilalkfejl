package com.example.vercukornezegeto.entities.Resource;

import com.google.type.DateTime;

public class Annotation {
    private DateTime time;
    private String authorString;
    private String markdown;

    public Annotation(String markdown) {
        setMarkdown(markdown);
    }

    public DateTime getTime() {
        return time;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }

    public String getAuthorString() {
        return authorString;
    }

    public void setAuthorString(String authorString) {
        this.authorString = authorString;
    }

    public String getMarkdown() {
        return markdown;
    }

    public void setMarkdown(String markdown) {
        if (markdown.matches("\\s*(\\S|\\s)*")){
            this.markdown = markdown;
        } else {
            System.err.println("Invalid markdown: " + markdown + " (must match markdown regex)");
        }
    }
}
