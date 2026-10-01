package videostore.com.baitap.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import videostore.com.baitap.entity.Category;
import videostore.com.baitap.service.CategoryService_24110153;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig // Hỗ trợ upload ảnh
@WebServlet({"/admin/category", "/admin/category/create", "/admin/category/edit", "/admin/category/delete"})
public class CategoryAdminController_24110153 extends HttpServlet {

    private CategoryService_24110153 categoryService = new CategoryService_24110153();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        
        try {
            if (uri.contains("edit")) {
                String id = req.getParameter("id");
                Category category = categoryService.findById(id);
                req.setAttribute("category", category);
            } else if (uri.contains("delete")) {
                String id = req.getParameter("id");
                categoryService.delete(id);
                resp.sendRedirect(req.getContextPath() + "/admin/category");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Lấy danh sách hiển thị lên bảng
        List<Category> categories = categoryService.findAll();
        req.setAttribute("categories", categories);
        
        req.getRequestDispatcher("/views/admin/category.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        Category category = new Category();
        
        category.setCategoryId(req.getParameter("categoryId"));
        category.setCategoryName(req.getParameter("categoryName"));
        category.setStatus(req.getParameter("status") != null);

        // --- XỬ LÝ UPLOAD HÌNH ẢNH CATEGORY ---
        try {
            String uploadPath = req.getServletContext().getRealPath("/uploads");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();

            Part part = req.getPart("imageFile");
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();

            if (fileName != null && !fileName.isEmpty()) {
                part.write(uploadPath + File.separator + fileName);
                category.setImages(req.getContextPath() + "/uploads/" + fileName);
            } else {
                category.setImages(req.getParameter("oldImage"));
            }

            // Gọi Service lưu
            if (uri.contains("create")) {
                categoryService.insert(category);
            } else if (uri.contains("edit")) {
                categoryService.update(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Lỗi: " + e.getMessage());
        }

        resp.sendRedirect(req.getContextPath() + "/admin/category");
    }
}