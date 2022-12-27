package cn.peng.reggie.service.impl;

import cn.peng.reggie.entity.Employee;
import cn.peng.reggie.mapper.EmployeeMapper;
import cn.peng.reggie.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 名称:EmployeeServiceImpl
 * 描述:
 *
 * @author:Secret Base
 * @datetime:2022-12-16 23:41:20
 * @version:1.0
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
