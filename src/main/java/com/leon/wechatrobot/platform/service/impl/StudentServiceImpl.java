package com.leon.wechatrobot.platform.service.impl;

import com.leon.wechatrobot.platform.bean.Student;
import com.leon.wechatrobot.platform.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 04/04/2018.
 *
 * @author Xiaolei-Peng
 */

@Service
public class StudentServiceImpl implements StudentService{

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Student record) {
        return 0;
    }

    @Override
    public int insertSelective(Student record) {
        return 0;
    }

    @Override
    public Student selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public List<Student> listAllStudent(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Student record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Student record) {
        return 0;
    }
}
