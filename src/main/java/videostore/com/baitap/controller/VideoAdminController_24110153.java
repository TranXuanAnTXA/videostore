package videostore.com.baitap.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import videostore.com.baitap.entity.Video;
import videostore.com.baitap.service.VideoService_24110153;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

// BẮT BUỘC PHẢI CÓ @MultipartConfig ĐỂ XỬ LÝ UPLOAD FILE
@MultipartConfig 
@WebServlet({"/admin/video", "/admin/video/create", "/admin/video/edit", "/admin/video/delete"})
public class VideoAdminController_24110153 extends HttpServlet {

    private VideoService_24110153 videoService = new VideoService_24110153();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        
        try {
            if (uri.contains("edit")) {
                // Sửa lỗi: Phải ép kiểu String sang int
                String idStr = req.getParameter("id");
                if (idStr != null) {
                    int id = Integer.parseInt(idStr);
                    Video video = videoService.findById(id); // Gọi bằng int
                    req.setAttribute("video", video);
                }
            } else if (uri.contains("delete")) {
                // Sửa lỗi: Phải ép kiểu String sang int
                String idStr = req.getParameter("id");
                if (idStr != null) {
                    int id = Integer.parseInt(idStr);
                    videoService.delete(id); // Gọi bằng int
                }
                resp.sendRedirect(req.getContextPath() + "/admin/video");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int pageSize = 6; 
        int currentPage = 1; 
        
        String pageStr = req.getParameter("page");
        if (pageStr != null && !pageStr.isEmpty()) {
            try {
                currentPage = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                currentPage = 1;
            }
        }

        long totalVideos = videoService.count();
        int totalPages = (int) Math.ceil((double) totalVideos / pageSize);

        List<Video> videos = videoService.findAll(currentPage, pageSize);
        
        req.setAttribute("videos", videos);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("totalPages", totalPages);
        
        req.getRequestDispatcher("/views/admin/video.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String uri = req.getRequestURI();

        Video video = new Video();

        // ID
        if (uri.contains("edit")) {
            String id = req.getParameter("id");
            if (id != null && !id.isEmpty()) {
                video.setVideoId(Integer.parseInt(id));
            }
        }

        // Thông tin video
        video.setTitle(req.getParameter("title"));

        String views = req.getParameter("views");
        video.setViews((views == null || views.isEmpty()) ? 0 : Integer.parseInt(views));

        video.setActive(req.getParameter("active") != null);

        try {

            // Thư mục upload
            String uploadPath = getServletContext().getRealPath("/uploads");

            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            Part part = req.getPart("posterFile");

            if (part != null && part.getSize() > 0) {

                String fileName = Paths.get(part.getSubmittedFileName())
                                       .getFileName()
                                       .toString();

                if (!fileName.isEmpty()) {

                    String savePath = uploadPath + File.separator + fileName;

                    part.write(savePath);

                    video.setPoster(req.getContextPath() + "/uploads/" + fileName);

                } else {

                    video.setPoster(req.getParameter("oldPoster"));

                }

            } else {

                video.setPoster(req.getParameter("oldPoster"));

            }

            if (uri.contains("create")) {
                videoService.insert(video);
            } else if (uri.contains("edit")) {
                videoService.update(video);
            }

            resp.sendRedirect(req.getContextPath() + "/admin/video");

        } catch (Exception e) {

            e.printStackTrace();

            req.setAttribute("error", e.getMessage());

            req.getRequestDispatcher("/views/admin/video.jsp")
                    .forward(req, resp);
        }
    }
}