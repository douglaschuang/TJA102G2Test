package com.babymate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.babymate.member.model.MemberVO;
import com.babymate.member.service.MemberService;
import com.babymate.staff.model.StaffVO;
import com.babymate.staff.service.StaffService;

import java.util.*;



//@PropertySource("classpath:application.properties") // 於https://start.spring.io建立Spring Boot專案時, application.properties文件預設已經放在我們的src/main/resources 目錄中，它會被自動檢測到
@Controller
public class IndexController_inSpringBoot {
	
	// @Autowired (●自動裝配)(Spring ORM 課程)
	// 目前自動裝配了EmpService --> 供第60使用
	@Autowired
	StaffService staffSvc;
	
	@Autowired
	MemberService memberSvc;
	
    // inject(注入資料) via application.properties
    @Value("${welcome.message}")
    private String message;
	
    private List<String> myList = Arrays.asList("Spring Boot Quickstart 官網 : https://start.spring.io", "IDE 開發工具", "直接使用(匯入)官方的 Maven Spring-Boot-demo Project + pom.xml", "直接使用官方現成的 @SpringBootApplication + SpringBootServletInitializer 組態檔", "依賴注入(DI) HikariDataSource (官方建議的連線池)", "Thymeleaf", "Java WebApp (<font color=red>快速完成 Spring Boot Web MVC</font>)");
    @GetMapping("/")
    public String index(Model model) {
    	model.addAttribute("message", message);
        model.addAttribute("myList", myList);
        return "index"; //view
    }
    
    // http://......../hello?name=peter1
    @GetMapping("/hello")
    public String indexWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {
        model.addAttribute("message", name);
        return "index"; //view
    }
    
  
    //=========== 以下第57~62行是提供給 /src/main/resources/templates/back-end/emp/select_page.html 與 listAllEmp.html 要使用的資料 ===================   
//  @GetMapping("/staff/select_page")
//	public String select_page(Model model) {
//		return "back-end/staff/select_page";
//	}
    
//  @GetMapping("/member/select_page")
//	public String select_pageM(Model model) {
//		return "back-end/member/select_page";
//	}
    
    @GetMapping("/admin")
	public String listAllStaff(Model model) {
		return "back-end/index";
	}
    
    @GetMapping("/staff/listAllStaff")
	public String listAllEmp(Model model) {
		return "back-end/staff/stafflist";
	}
    
    @GetMapping("/member/listAllMember")
	public String listAllMember(Model model) {
		return "back-end/member/memberlist";
	}
    
    @ModelAttribute("staffListData")  // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
	protected List<StaffVO> referenceListData(Model model) {
		
    	List<StaffVO> list = staffSvc.getAll();
		return list;
	}
    
    @ModelAttribute("memberListData")
	protected List<MemberVO> referenceListDataMem(Model model) {
		
    	List<MemberVO> list = memberSvc.getAll();
		return list;
	}
    


}