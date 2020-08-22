package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.iiht.evaluation.coronokit.exception.AdminException;
import com.iiht.evaluation.coronokit.model.ProductMaster;
import com.iiht.evaluation.coronokit.service.AdminService;
import com.iiht.evaluation.coronokit.service.AdminServiceImpl;


@WebServlet({"/admin","/login","/insertproduct","/deleteproduct","/editproduct","/list","/logout"})
//@WebServlet("/admin")
public class AdminController extends HttpServlet {
	
	public static final long serialVersionUID = 1L;
	private AdminService adminService;
	
	@Override
	public void init() throws ServletException {
		adminService = new AdminServiceImpl();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getServletPath();
		//String action =  request.getParameter("action");

		String view = "";

		switch (url) {
			case "login" : view = doAdminLogin(request, response);break;
			case "insertproduct":view = doInsertProduct(request, response);break;
			case "deleteproduct":view = doDeleteProduct(request, response);break;
			//case "editproduct":view = doEditproduct(request, response);break;
			case "list":view = doGetAllProducts(request, response);break;	
			case "logout":view = doAdminLogout(request, response);break;	
			default : view = "notfound.jsp"; break;		
			}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(view);
		 dispatch.forward(request, response);	 
		
	}

	private String doAdminLogout(HttpServletRequest request, HttpServletResponse response) {
		String viewName= null;
		viewName = "logout.jsp";
		return viewName;
	}

	private String doGetAllProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String view = ""; 
		try {
			List<ProductMaster> products = adminService.listAllProducts();
			request.setAttribute("products", products);
			view = "listproducts.jsp";
		}catch(AdminException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errorPage.jsp";	
		} 
		return view;
	}

	private String doDeleteProduct(HttpServletRequest request, HttpServletResponse response) {
		int productId = Integer.parseInt(request.getParameter("id"));
		String view ="";
		try {
			adminService.deleteProduct(productId);
			request.setAttribute("msg", "Product Record Deleted!");
			view = "index.jsp";
		} catch (AdminException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		}

		return view;
	}
	
	private String doInsertProduct(HttpServletRequest request, HttpServletResponse response){
		
		 String viewName = null; 
		return viewName;
	} 

	private String doAdminLogin(HttpServletRequest request, HttpServletResponse response) {
		
		String name = request.getParameter("Loginid");
		String pwd = request.getParameter("Password");
		
		String viewName= null;
		
		if(name.equalsIgnoreCase("admin") && pwd.equalsIgnoreCase("admin")) {  
			viewName = "success.jsp";
		} else {
			viewName = "errorPage.jsp";
		}
		return viewName;
	}
	
}