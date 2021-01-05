package com.atguigu.bean;

public class Book {
    private Integer id;
    private String bookName;
    private String author;
    private Integer stock;
    private Integer sales;
    private Double price;

    public Book() {
    }

    public Book(Integer id, String bookName, String author, Integer stock, Integer sales, Double price) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.stock = stock;
        this.sales = sales;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", stock=" + stock +
                ", sales=" + sales +
                ", price=" + price +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
