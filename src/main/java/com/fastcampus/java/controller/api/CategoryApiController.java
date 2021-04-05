package com.fastcampus.java.controller.api;

import com.fastcampus.java.controller.CrudController;
import com.fastcampus.java.model.entity.Category;
import com.fastcampus.java.model.network.request.CategoryApiRequest;
import com.fastcampus.java.model.network.response.CategoryApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryApiController extends CrudController<CategoryApiRequest, CategoryApiResponse, Category> {
}
