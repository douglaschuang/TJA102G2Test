package com.babymate.staff.controller;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.babymate.staff.model.StaffVO;
import com.babymate.staff.service.StaffService;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@Validated
@RequestMapping("/staff")
public class StaffController {
	
	@Autowired
	StaffService staffSvc;

	/*
	 * This method will be called on select_page.html form submission, handling POST
	 * request It also validates the user input
	 */
	@PostMapping("getOne_For_Display")
	public String getOne_For_Display(
		/***************************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		@NotEmpty(message="員工編號: 請勿空白")
//		@Digits(integer = 4, fraction = 0, message = "員工編號: 請填數字-請勿超過{integer}位數")
//		@Min(value = 7001, message = "員工編號: 不能小於{value}")
//		@Max(value = 7777, message = "員工編號: 不能超過{value}")
		@RequestParam("staffId") String staffId,
		ModelMap model) {
		
		/***************************2.開始查詢資料*********************************************/
//		EmpService empSvc = new EmpService();
		StaffVO staffVO = staffSvc.getOneStaff(Integer.valueOf(staffId));
		
		List<StaffVO> list = staffSvc.getAll();
		model.addAttribute("staffListData", list);     // for select_page.html 第97 109行用
	
		if (staffVO == null) {
			model.addAttribute("errorMessage", "查無資料");
			return "back-end/staff/select_page";
		}
		
		/***************************3.查詢完成,準備轉交(Send the Success view)*****************/
		model.addAttribute("staffVO", staffVO); // for1 --> listOneEmp.html 的第37~44行用
                                            // for2 --> select_page.html的第156用
		return "back-end/staff/listOneStaff";   // 查詢完成後轉交listOneEmp.html
//		return "back-end/emp/select_page";  // 查詢完成後轉交select_page.html由其第158行insert listOneEmp.html內的th:fragment="listOneEmp-div
	}
	
    @GetMapping("/staffadd")
	public String staffAdd(Model model) {
    	StaffVO staff = new StaffVO(); 
    	model.addAttribute("StaffVO", staff);
		return "back-end/staff/staffadd";
	}
	
	@PostMapping("insert")
	public String insert(@Valid StaffVO staffVO, BindingResult result, ModelMap model,
			@RequestParam("pic") MultipartFile[] parts) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
		// 移除圖片欄位的綁定錯誤（若有）
		result = removeFieldError(staffVO, result, "pic");
		
		// 如果有上傳圖片才處理圖片
	    if (!parts[0].isEmpty()) {
	        for (MultipartFile multipartFile : parts) {
	            byte[] buf = multipartFile.getBytes();
	            staffVO.setPic(buf);
	        }
	    }
	    
//		if (result.hasErrors() || parts[0].isEmpty()) {
	    if (result.hasErrors()) {
			model.addAttribute("StaffVO", staffVO);
			return "back-end/staff/staffadd";
		}
		/*************************** 2.開始新增資料 *****************************************/
		// EmpService empSvc = new EmpService();
		staffSvc.addStaff(staffVO);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<StaffVO> list = staffSvc.getAll();
		model.addAttribute("staffListData", list); // for listAllEmp.html 第117 133行用
		model.addAttribute("success", "- (新增成功)");
		return "redirect:/staff/listAllStaff"; // 新增成功後重導至IndexController_inSpringBoot.java的第50行@GetMapping("/emp/listAllEmp")
	}
	
	@PostMapping("staffedit")
	public String getOne_For_Update(@RequestParam("staffId") String staffId, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		// EmpService empSvc = new EmpService();
		StaffVO staffVO = staffSvc.getOneStaff(Integer.valueOf(staffId));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("StaffVO", staffVO);
		return "back-end/staff/staffedit"; // 查詢完成後轉交update_emp_input.html
	}
	/*
	 * This method will be called on staffedit.html form submission, handling POST request It also validates the user input
	 */
	@PostMapping("update")
	public String update(@Valid StaffVO staffVO, BindingResult result, ModelMap model,
			@RequestParam("pic") MultipartFile[] parts) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中pic欄位的FieldError紀錄 --> 見第172行
		result = removeFieldError(staffVO, result, "pic");

		if (parts[0].isEmpty()) { // 使用者未選擇要上傳的新圖片時
			// EmpService empSvc = new EmpService();
			byte[] pic = staffSvc.getOneStaff(staffVO.getStaffId()).getPic();
			staffVO.setPic(pic);
		} else {
			for (MultipartFile multipartFile : parts) {
				byte[] pic = multipartFile.getBytes();
				staffVO.setPic(pic);
			}
		}
		
		// 因為input type=password會帶出時會自動改為null, 所以若未重新輸入, 則維持原密碼
		StaffVO originalStaff = staffSvc.getOneStaff(staffVO.getStaffId());
//		// 若密碼欄位空白，保留原始密碼
	    if (staffVO.getPassword() == null || staffVO.getPassword().isEmpty()) {
	        staffVO.setPassword(originalStaff.getPassword());
	    }
		
		if (result.hasErrors()) {
			model.addAttribute("StaffVO", staffVO);
			return "back-end/staff/staffedit";
		}
		/*************************** 2.開始修改資料 *****************************************/
		// EmpService empSvc = new EmpService();
		staffSvc.updateStaff(staffVO);

		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		staffVO = staffSvc.getOneStaff(Integer.valueOf(staffVO.getStaffId()));
		model.addAttribute("staffVO", staffVO);
//		return "back-end/staff/listAllStaff"; // 修改成功後轉交listAllStaff.html
		return "redirect:/staff/listAllStaff";
	}

	@PostMapping("staffchangepwd")
	public String getOneForChangePwd(@RequestParam("staffId") String staffId, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		// EmpService empSvc = new EmpService();
		StaffVO staffVO = staffSvc.getOneStaff(Integer.valueOf(staffId));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("StaffVO", staffVO);
		return "back-end/staff/staffchangepwd"; // 查詢完成後轉交staffchangepwd.html
	}
	
	/*
	 * This method will be called on listAllStaff.html form submission, handling POST request
	 */
	@PostMapping("delete")
	public String delete(@RequestParam("staffId") String staffId, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始刪除資料 *****************************************/
		// EmpService empSvc = new EmpService();
		staffSvc.deleteStaff(Integer.valueOf(staffId));
		/*************************** 3.刪除完成,準備轉交(Send the Success view) **************/
		List<StaffVO> list = staffSvc.getAll();
		model.addAttribute("staffListData", list); // for listAllEmp.html 第117 133行用
		model.addAttribute("success", "- (刪除成功)");
//		return "back-end/staff/listAllStaff"; // 刪除完成後轉交listAllEmp.html
		return "redirect:/staff/listAllStaff";
	}
	
	// 去除BindingResult中某個欄位的FieldError紀錄
	public BindingResult removeFieldError(StaffVO staffVO, BindingResult result, String removedFieldname) {
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname))
				.collect(Collectors.toList());
		result = new BeanPropertyBindingResult(staffVO, "staffVO");
		for (FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		return result;
	}
    
	@ExceptionHandler(value = { ConstraintViolationException.class })
	//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ModelAndView handleError(HttpServletRequest req,ConstraintViolationException e,Model model) {
	    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
	    StringBuilder strBuilder = new StringBuilder();
	    for (ConstraintViolation<?> violation : violations ) {
	          strBuilder.append(violation.getMessage() + "<br>");
	    }
	    //==== 以下第92~96行是當前面第77行返回 /src/main/resources/templates/back-end/emp/select_page.html用的 ====   
//	    model.addAttribute("empVO", new EmpVO());
//    	EmpService empSvc = new EmpService();
		List<StaffVO> list = staffSvc.getAll();
		model.addAttribute("staffListData", list);     // for select_page.html 第97 109行用
		String message = strBuilder.toString();
	    return new ModelAndView("back-end/staff/stafflist", "errorMessage", "請修正以下錯誤:<br>"+message);
	}
	
}