package cn.peng.reggie.controller;

import cn.peng.reggie.common.R;
import cn.peng.reggie.entity.Employee;
import cn.peng.reggie.service.EmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * 名称:EmployeeController
 * 描述:
 *
 * @author:Secret Base
 * @datetime:2022-12-16 23:48:56
 * @version:1.0
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){

        /*
        * 员工登录
        */

        //1.将页面提交的密码password进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

        // 2.根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        // 3.如果没有查询到则返回登录失败结果
        if(emp == null){
            return R.error("用户名不存在");
        }

        // 4.密码对比，如果不一致则返回登录失败结果
        if(!emp.getPassword().equals(password)){
            return R.error("密码不正确");
        }

        // 5.查看员工状态，如果已为禁用状态，则返回员工已禁用结果
        if(emp.getStatus() == 0){
            return R.error("账号已被禁用");
        }

        // 6.登陆成功，将员工id存入session并返回登陆成功结果
        request.getSession().setAttribute("employee",emp.getId());

        return R.success(emp);

    }

    /*
     * 员工退出
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    /*
    * 新增员工
    */
    @PostMapping
    public R<String> save(HttpServletRequest request,@RequestBody Employee employee){
        log.info("新增员工，员工信息{}",employee.toString());
//        设置初始密码
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes(StandardCharsets.UTF_8)));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
//        获取登录用户id
        Long empID = (Long) request.getSession().getAttribute("employee");
        employee.setCreateUser(empID);
        employee.setUpdateUser(empID);

        employeeService.save(employee);
        return R.success("用户添加成功");
    }

    /*
    * 分页查询
    */
@GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        log.info("page = {},pageSize = {},name = {}",page,pageSize,name);

//      构造分页构造器
        Page pageInfo = new Page(page,pageSize);

//      构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
//      添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
//      添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);

//    执行查询
        employeeService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }
}
