package com.demo.mapper.read.product;

import com.demo.dto.read.product.ShareReadDto;
import com.demo.entity.product.Share;
import com.demo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShareReadMapper implements Mapper<Share, ShareReadDto> {

    @Override
    public ShareReadDto map(Share object) {

        return new ShareReadDto(
                object.getId(),
                object.getShareValue(),
                object.getShareType(),
                object.getSharePeriod()
        );
    }

}
