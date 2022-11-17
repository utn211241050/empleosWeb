package com.empleos.empleosWeb.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.empleos.empleosWeb.model.Categoria;
import com.empleos.empleosWeb.service.IntCategoriasService;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {
	
	@Autowired
	private IntCategoriasService serviceCategorias;
	@PostMapping("/guardar")
	public String guardar(Categoria categoria, RedirectAttributes atributo) {
		categoria.setId(serviceCategorias.obtenerTodas().size()+1);
		System.out.println(categoria);
		serviceCategorias.guardar(categoria);
		atributo.addFlashAttribute("msg","Categoria guardada con exito");
		
		return "redirect:/categorias/index";
		
	}
	
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam("id") int idCategoria,RedirectAttributes atributo) {
		serviceCategorias.eliminar(idCategoria);
		atributo.addFlashAttribute("msg","Categoria eliminada");
		return "redirect:/categorias/index";
	}
	@GetMapping("/crear")
	public String crearCategoria() {
		return "Categorias/formCategoria";
	}

	
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Categoria>lista=serviceCategorias.obtenerTodas();
		/*for(Categoria categoria:lista) {
			System.out.print(categoria);
		}*/
		model.addAttribute("Categorias", lista);
		//System.out.print(lista);
		return "Categorias/listCategorias";
	}
	
}
