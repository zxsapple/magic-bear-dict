package com.magic.bear.dict.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.magic.bear.dict.dal.entity.SysDictType;
import com.magic.bear.dict.service.IDictTypeService;
import com.magic.bear.dict.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zxs
 * @date 2022/7/21 16:35
 * @desc
 */
@RestController
@RequestMapping("/magic/bear/dictType")
public class DictTypeController {

    @Autowired
    private IDictTypeService dictTypeService;

    @PostMapping("/pageList")
    public ResultVO<Page<SysDictType>> pageList(SysDictType dictType) {

        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        String pageStr = request.getParameter("page");
        String sizeStr = request.getParameter("limit");

        int pageNum = StringUtils.isEmpty(pageStr) ? 1 : Integer.parseInt(pageStr);
        int pageSize = StringUtils.isEmpty(sizeStr) ? 10 : Integer.parseInt(sizeStr);

        Page<SysDictType> sysDictTypePage = dictTypeService.pageList(dictType, pageNum, pageSize);

        return ResultVO.success(sysDictTypePage);
    }


}
