package com.fastcampus.java.controller.api;

import com.fastcampus.java.controller.CrudController;
import com.fastcampus.java.ifs.CrudInterface;
import com.fastcampus.java.model.entity.Item;
import com.fastcampus.java.model.network.Header;
import com.fastcampus.java.model.network.request.ItemApiRequest;
import com.fastcampus.java.model.network.response.ItemApiResponse;
import com.fastcampus.java.service.ItemApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/item")
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {
    @Autowired
    private ItemApiLogicService itemApiLogicService;

    // 초기 생성자와 같은 역할
    // 해당 메소드가 static 메소드가 실행되는 것과 같이 동작함
    // 현재 컨트롤러가 생성될 때 주입받은 객체를 상속받았기 때문에 현재 baseService에 해당 객체를 지정한다는 의미
    @PostConstruct
    public void init() {
        this.baseService = itemApiLogicService;
    }

    /*@Override
    @PostMapping("") // /api/item
    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
        return itemApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}") // /api/item/{id}
    public Header<ItemApiResponse> read(@PathVariable Long id) {
        return itemApiLogicService.read(id);
    }

    @Override
    @PutMapping("") // /api/item
    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
        return itemApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}") // /api/item/{id}
    public Header delete(@PathVariable Long id) {
        return itemApiLogicService.delete(id);
    }*/
}
