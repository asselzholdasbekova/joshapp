package kz.assel.joshapp.controllers;

import kz.assel.joshapp.dao.Dao;
import kz.assel.joshapp.dao.DaoFactory;
import kz.assel.joshapp.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {
    Dao productDao = DaoFactory.createDao("product");

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("products", productDao.index());
        return "products/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productDao.show(id));
        return "products/show";
    }

    @GetMapping("/new")
    public String newProduct(@ModelAttribute("product") Product product){
        return "products/new";
    }

    @PostMapping
    public String create(@ModelAttribute("product") Product product){
        productDao.save(product);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("product", productDao.show(id));
        return "products/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("product") Product product, @PathVariable("id") int id){
        productDao.update(id, product);
        return "redirect:/";
    }
}
