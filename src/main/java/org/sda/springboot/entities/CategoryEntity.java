package org.sda.springboot.entities;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;

    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.REMOVE)
    private List<ProductEntity> productList;

    @NotEmpty
    private String categoryName;

    public CategoryEntity() { }

    public CategoryEntity(Integer categoryId, List<ProductEntity> productList, String categoryName) {
        this.categoryId = categoryId;
        this.productList = productList;
        this.categoryName = categoryName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<ProductEntity> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductEntity> productList) {
        this.productList = productList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
