package kr.ac.hansung.cse.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.hansung.cse.model.Product;
import kr.ac.hansung.cse.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ProductService productService;

	@RequestMapping
	public String adminPage() {
		return "admin";
	}

	@RequestMapping("/productInventory")
	public String getProducts(Model model) {
		List<Product> products = productService.getProducts();

		model.addAttribute("products", products);

		return "productInventory"; // view's logical name
	}

	@RequestMapping(value = "/productInventory/addProduct", method = RequestMethod.GET)
	public String addProduct(Model model) {

		Product product = new Product();

		product.setCategory("컴퓨터");

		model.addAttribute("product", product);

		return "addProduct";
	}

	// web form data -> object(filled with form data)만들어서 넘겨줌
	@RequestMapping(value = "/productInventory/addProduct", method = RequestMethod.POST)
	public String addProductPost(Product product, BindingResult result, HttpServletRequest request) {

		MultipartFile productImage = product.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + productImage.getOriginalFilename());

		if (productImage.isEmpty() == false) {
			System.out.println("--------- file start -----------");
			System.out.println("name :   " + productImage.getName());
			System.out.println("filename : " + productImage.getOriginalFilename());
			System.out.println("size : " + productImage.getSize());
			System.out.println("savePath : " + savePath);
			System.out.println("--------- file end -----------\n");
		}

		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(savePath.toString()));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		product.setImageFilename(productImage.getOriginalFilename());

		productService.addProduct(product);
		System.out.println("Adding Product cannot be done");

		return "redirect:/admin/productInventory";

	}

	@RequestMapping(value = "/productInventory/deleteProduct/{id}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable int id, HttpServletRequest request) {

		Product product = productService.getProductById(id);

		
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + product.getImageFilename());

		if(Files.exists(savePath)) {
			try {
				Files.delete(savePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		productService.deleteProduct(product);

		return "redirect:/admin/productInventory";

	}

	@RequestMapping(value = "/productInventory/updateProduct/{id}", method = RequestMethod.GET)
	public String updateProduct(@PathVariable int id, Model model) {

		// 데이터베이스에 저장된 내용을 보여줘야 하므로 읽어오는 작업이 필요

		Product product = productService.getProductById(id);

		model.addAttribute("product", product);

		return "updateProduct";

	}

	@RequestMapping(value = "/productInventory/updateProduct", method = RequestMethod.POST)
	public String updateProductPost(Product product, BindingResult result, HttpServletRequest request) {

		MultipartFile productImage = product.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + productImage.getOriginalFilename());

		if (productImage.isEmpty() == false) {
			System.out.println("--------- file start -----------");
			System.out.println("name :   " + productImage.getName());
			System.out.println("filename : " + productImage.getOriginalFilename());
			System.out.println("size : " + productImage.getSize());
			System.out.println("savePath : " + savePath);
			System.out.println("--------- file end -----------\n");
		}

		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(savePath.toString()));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		product.setImageFilename(productImage.getOriginalFilename());

		System.out.println(product); // id 부분이 제대로 연결이 되어있지 않다. 그래서 updateProduct.jsp 수정(id보내야함)

		productService.updateProduct(product);
		System.out.println("Updating Product cannot be done");

		return "redirect:/admin/productInventory";

	}
}
