package com.val.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.val.entity.Product;

public interface IProductRepo extends JpaRepository<Product, Integer>{

}
