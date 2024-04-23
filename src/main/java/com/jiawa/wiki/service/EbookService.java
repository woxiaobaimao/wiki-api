package com.jiawa.wiki.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.jiawa.wiki.domain.Ebook;
import com.jiawa.wiki.domain.EbookExample;
import com.jiawa.wiki.mapper.EbookMapper;

import com.jiawa.wiki.req.EbookReq;
import com.jiawa.wiki.resp.EbookResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {

    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    @Resource
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req ) {
        EbookExample ebookExample=new EbookExample();
        EbookExample.Criteria criteria=ebookExample.createCriteria();
        criteria.andNameLike("%"+req.getName()+"%");
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);
        List<EbookResp> respList=new ArrayList<EbookResp>();
        for (Ebook ebook : ebookList) {
            EbookResp ebookResp=new EbookResp();
            BeanUtils.copyProperties(ebook, ebookResp);
            respList.add(ebookResp);
        }
        return  respList;
    }
}
