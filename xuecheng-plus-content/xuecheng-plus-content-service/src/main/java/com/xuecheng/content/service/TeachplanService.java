package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.BindTeachplanMediaDto;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;

import java.util.List;

/**
 * @version 1.0
 * @description 课程计划管理相关接口
 */
public interface TeachplanService {
 /**
  * 根据课程id查询课程计划
  * @param courseId 课程计划
  * @return
  */
  public List<TeachplanDto> findTeachplanTree(Long courseId);

 /**
  * 新增/修改/保存课程计划
  * @param saveTeachplanDto
  */
 public void saveTeachplan(SaveTeachplanDto saveTeachplanDto);


 /**
  * @description 教学计划绑定媒资
  * @param bindTeachplanMediaDto
  * @return com.xuecheng.content.model.po.TeachplanMedia
  */
 public void associationMedia(BindTeachplanMediaDto bindTeachplanMediaDto);
}
