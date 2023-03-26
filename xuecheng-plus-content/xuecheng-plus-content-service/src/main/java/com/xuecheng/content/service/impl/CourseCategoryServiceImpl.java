package com.xuecheng.content.service.impl;

import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @description TODO
 */
@Slf4j
@Service
public class CourseCategoryServiceImpl implements CourseCategoryService {

    @Autowired
    CourseCategoryMapper courseCategoryMapper;

    @Override
    public List<CourseCategoryTreeDto> queryTreeNodes(String id) {
        //调用mapper递归查询出分类信息
        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryMapper.selectTreeNodes(id);

        //找到每个节点的子节点，最终封装成List<CourseCategoryTreeDto>
        //先将list转成map，key就是结点的id，value就是CourseCategoryTreeDto对象
        Map<String, CourseCategoryTreeDto> mapTemp = courseCategoryTreeDtos.stream().filter(item -> !id.equals(item.getId())).collect(Collectors.toMap(key -> key.getId(), value -> value, (key1, key2) -> key2));
        //最终返回list
        List<CourseCategoryTreeDto> courseCategoryList = new ArrayList<>();
        //找子节点放在父节点的childrenTreeNodes
        courseCategoryTreeDtos.stream().filter(item -> !id.equals(item.getId())).forEach(item -> {
            if (item.getParentid().equals(id)) {
                courseCategoryList.add(item);
            }
            //找到节点的父节点
            CourseCategoryTreeDto courseCategoryParent = mapTemp.get(item.getParentid());
            if(courseCategoryParent!=null){
                if(courseCategoryParent.getChildrenTreeNodes()==null){
                    courseCategoryParent.setChildrenTreeNodes(new ArrayList<CourseCategoryTreeDto>());
                }
                courseCategoryParent.getChildrenTreeNodes().add(item);
            }



        });

        return courseCategoryList;
    }
}
