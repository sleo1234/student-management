package com.jrp.sma.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jrp.sma.dao.StudentNotFoundException;
import com.jrp.sma.dao.StudentRepository;
import com.jrp.sma.entities.Student;
import com.jrp.sma.services.FileUploadUtil;
import com.jrp.sma.services.StudentService;
import com.jrp.sma.services.UserPdfExporter;
import com.lowagie.text.DocumentException;



@Controller
@RequestMapping("/students")

public class StudentController {

	@Autowired 
	StudentRepository studRepo;
	
	@Autowired
	StudentService studService;

	
	
	@GetMapping
	public String listFirstPage(Model model) throws  StudentNotFoundException {

		return listByPage(1, model, "firstName", "asc",null);
	}
	
	
	
	
	@GetMapping("/page/{pageNum}")
	public String listByPage(@PathVariable("pageNum") int pageNum, Model model, @Param("sortField") String sortField,
			@Param("sortDir") String sortDir, @Param("keyword") String keyword) throws StudentNotFoundException {
  
		
		
		Page<Student> page = studService.listByPage(pageNum, sortField, sortDir, keyword);
		List<Student> listStudents = page.getContent();
//		System.out.println("Sort field: " + sortField);
//		System.out.println("Sort dir: " + sortDir);
		long startCount = (pageNum - 1) * StudentService.STUDENTS_PER_PAGE + 1;
		long endCount = startCount + StudentService.STUDENTS_PER_PAGE + 1;
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		System.out.println("Page number: " + pageNum);
		System.out.println("Total elements: " + page.getTotalElements());
		System.out.println("Total pages: " + page.getTotalPages());

		int totalPages = page.getTotalPages();
        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listStudents", listStudents);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", reverseSortDir);
		model.addAttribute("moduleURL", "/students");
		model.addAttribute("keyword", keyword);
        System.out.println("kewyord: " + keyword);
		return "students/studentsList";
	}
	
	
	
	
	
	
	
	
	
	
	

	
	@RequestMapping("/new")
	public String studentForm (Model model) {
		Student student = new Student();
		
		model.addAttribute("student",student);
		
		return "students/new-Student";
		
	}
	
	
	@PostMapping("/save")
	public String saveStudent (Model model, Student student, @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		
		student.setPhoto(fileName);
		
		Student savedStudent = studRepo.save(student);
		String uploadDir = "/user-photos/"+ savedStudent.getStudentId();
		
		
	
		
		 FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		 System.out.println("*****************");
		System.out.println(uploadDir);
		return "redirect:/students/new";
	}
	
	@GetMapping("/update")
	public String updateStudent (@RequestParam("id") long id, Model model) {
		Student student = studRepo.findByStudentId(id);
		model.addAttribute("student",student);
	
		return "students/new-Student";
	}
	
	@GetMapping("/delete")
	public String deleteStudent (@RequestParam("id") long id, Model model) {
    Student theStud = studRepo.findByStudentId(id);
    studRepo.delete(theStud);
    model.addAttribute("theStud", theStud);
		return "redirect:/students";
	} 
	
	@GetMapping("/pdf-export")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException{
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=students_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<Student> listStudents = studService.listAll();
        
       UserPdfExporter exporter = new UserPdfExporter(listStudents);

        exporter.export(response);
         
}
	
	
	
}
