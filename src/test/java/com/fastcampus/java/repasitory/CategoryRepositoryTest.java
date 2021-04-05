package com.fastcampus.java.repasitory;

import com.fastcampus.java.JavaApplicationTests;
import com.fastcampus.java.model.entity.Category;
import com.fastcampus.java.repository.CategoryRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class CategoryRepositoryTest extends JavaApplicationTests {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create() {
        String type = "computer";
        String title = "컴퓨터";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        Category category = new Category();
        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createdAt);
        category.setCreatedBy(createdBy);

        Category newCategory = categoryRepository.save(category);
        Assert.assertNotNull(newCategory);
        Assert.assertEquals(newCategory.getType(), type);
        Assert.assertEquals(newCategory.getTitle(), title);
    }

    @Test
    public void read() {
        // Query Method
        String type = "computer";
        Optional<Category> optionalCategory = categoryRepository.findByType(type);

        // select * from category where type = "computer";
        optionalCategory.ifPresent(c -> {
            // 쿼리 메소드로 가져온 type과 찾으려는 type이 일치하는지 확인
            Assert.assertEquals(c.getType(), type);
            
            System.out.println(c.getId());
            System.out.println(c.getType());
            System.out.println(c.getTitle());
        });
    }
}
