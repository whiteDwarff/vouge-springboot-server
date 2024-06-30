package com.vogue.admin.index;

import com.vogue.admin.index.service.IndexService;
import com.vogue.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RequestMapping("/api/admin/index/*")
@RestController
public class IndexController {

    private final IndexService indexService;

    public IndexController(IndexService indexService) { this.indexService = indexService; }

    @PostMapping("savePermission")
    public BaseResponse savePermission(@RequestBody HashMap<String, Object> param) throws Exception{
        log.info("POST : /api/admin/index/savePermission : " + param.toString());

        return indexService.savePermission(param);
    }
}
