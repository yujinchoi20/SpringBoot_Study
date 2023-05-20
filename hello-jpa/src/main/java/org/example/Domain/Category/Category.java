package org.example.Domain.Category;

import jakarta.persistence.*;
import org.example.Domain.BasicEntity;

import java.util.List;

@Entity
public class Category extends BasicEntity {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Category_Item> category_items;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child;

    public void addChildCategory(Category child){
        this.child.add(child);
        child.setParent(this);
    }

    public void addItem(Category_Item category_item){
        category_items.add(category_item);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category_Item> getCategory_items() {
        return category_items;
    }

    public void setCategory_items(List<Category_Item> category_items) {
        this.category_items = category_items;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getChild() {
        return child;
    }

    public void setChild(List<Category> child) {
        this.child = child;
    }
}
