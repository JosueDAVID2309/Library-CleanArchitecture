
package com.mycompany.libraryproject.core.entities;


public class Book {
    
    private int id;
    private String title;
    private int authorId;

    public Book(){
        
    }
    
    public Book(int id, String title, int authorId){
        this.id = id;
        this.title = title;
        this.authorId = authorId;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the titulo
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitle(String titulo) {
        this.title = titulo;
    }

    /**
     * @return the authorId
     */
    public int getAuthorId() {
        return authorId;
    }

    /**
     * @param authorId the authorId to set
     */
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

}
