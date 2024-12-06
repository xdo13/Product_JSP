package com.company.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.ProductDAO;
import com.company.dto.ProductVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/ProductWrite.do")
public class ProductWriteSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("product/productWrite.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("UTF-8");
		
		ServletContext context = getServletContext();
				
		String path = context.getRealPath("upload");
		String encType = "UTF-8";
		int sizeLimit = 20 * 1024 * 1024;
				
		MultipartRequest multi = new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());
				
		String productname= multi.getParameter("productname");
		int price = Integer.parseInt(multi.getParameter("price"));
		String descripation = multi.getParameter("description");
		String pictureUrl =  multi.getFilesystemName("pictureUrl");
		ProductVO pVo = new ProductVO();
		pVo.setProductName(productname);
		pVo.setPrice(price);
		pVo.setDescription(descripation);
		pVo.setPictureUrl(pictureUrl);
			
		ProductDAO pDao = ProductDAO.getInstance();
		pDao.insertProduct(pVo);
				
		response.sendRedirect("productList.do");
	
	}

}
