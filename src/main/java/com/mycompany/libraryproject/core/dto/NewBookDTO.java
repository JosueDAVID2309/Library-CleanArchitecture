package com.mycompany.libraryproject.core.dto;

/**
 *
 * @author Josue
 */
public class NewBookDTO {
    private String title;
    private int authorId;
    
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the idAuthor
     */
    public int getAuthorId() {
        return authorId;
    }

    /**
     * @param idAuthor the idAuthor to set
     */
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
    
}
