package com.example.controller;

import java.io.StringWriter;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/welcome")
public class Welcome {
		
	@RequestMapping("/")
	public String hi(Map<String, Object> model) {
		model.put("time", new Date());
		model.put("message", "这是测试的内容。。。");
		model.put("toUserName", "张三");
		model.put("fromUserName", "老许");
		return "welcome"; //自动寻找resources/templates中名字为welcome.ftl/welcome.vm的文件作为模板，拼装后返回
	}
	
	@RequestMapping("/velocity")
	public void velocity(){

		Velocity.init();

        /* lets make a Context and put data into it */

        VelocityContext context = new VelocityContext();

        context.put("name", "Velocity");
        context.put("user", "Jakarta");

        /* lets render a template */

        StringWriter w = new StringWriter();
        /*
        Velocity.mergeTemplate("D:/mars/velocity/target/classes/templates", context, w );
        System.out.println(" template : " + w );
        */
        Template template = Velocity.getTemplate("src/main/resources/templates/velocity.vm");
        
        /* lets make our own string to render */

        String s = "We are using $project $name to render this.";
        w = new StringWriter();
        //Velocity.evaluate( context, w, "mystring", s );
        template.merge(context, w);
        System.out.println(" string : " + w );
	}

}
