package com.vogue.admin.index.service;

import com.vogue.admin.index.mapper.IndexMapper;
import com.vogue.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
public class IndexServiceImpl implements IndexService {

    private final IndexMapper indexMapper;

    public IndexServiceImpl(IndexMapper indexMapper) {
        this.indexMapper = indexMapper;
    }

    @Override
    public BaseResponse savePermission(HashMap<String, Object> param) throws Exception {
        int result = 0;
        String mode = (String) param.get("mode");
        HttpStatus status = null;

        try {
            if (mode.equals("S")) result = indexMapper.insertPermission(param);
            else result = indexMapper.updatePermission(param);
        } catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
        }

        return BaseResponse.BaseCodeBuilder()
                .status(status)
                .result(param)
                .build();
    }
}
