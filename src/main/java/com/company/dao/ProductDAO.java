package com.company.dao;

public class ProductDAO {
	public ProductDAO() {
	}
	private static ProductDAO instance = new ProductDAO();
	
	public static ProductDAO getInstance() {
		return instance;
	}

}
