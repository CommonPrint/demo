package com.demo.mapper.createedit.product;

import com.demo.dto.createedit.product.ShareCreateEditDto;
import com.demo.entity.product.Share;
import com.demo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ShareCreateEditMapper implements Mapper<ShareCreateEditDto, Share> {
    @Override
    public Share map(ShareCreateEditDto object) {
        Share share = new Share();

        copy(object, share);

        return share;
    }

    @Override
    public Share map(ShareCreateEditDto fromObject, Share toObject) {
        copy(fromObject, toObject);

        return toObject;
    }


    // Копируем и вставляем данные
    private void copy(ShareCreateEditDto object, Share Share) {
        Share.setShareValue(object.getShareValue());
        Share.setShareType(object.getShareType());
        Share.setSharePeriod(object.getSharePeriod());
    }
}
