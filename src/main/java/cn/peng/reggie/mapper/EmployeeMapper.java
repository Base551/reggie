package cn.peng.reggie.mapper;

import cn.peng.reggie.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 名称:EmployeeMapper
 * 描述:
 *
 * @author:Secret Base
 * @datetime:2022-12-16 23:29:04
 * @version:1.0
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
