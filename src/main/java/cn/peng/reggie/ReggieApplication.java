package cn.peng.reggie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 名称:ReggieApplication
 * 描述:
 *
 * @author:Secret Base
 * @datetime:2022-12-15 20:48:50
 * @version:1.0
 */
@Slf4j
@ServletComponentScan
@SpringBootApplication
public class ReggieApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReggieApplication.class,args);
        log.info("项目启动成功....");
    }
}
