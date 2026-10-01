package videostore.com.baitap.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import videostore.com.baitap.entity.Category;
import videostore.com.baitap.entity.Video;
import videostore.com.baitap.service.CategoryService_24110153;
import videostore.com.baitap.service.VideoService_24110153;

@WebServlet("/home")
public class HomeController_24110153 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private VideoService_24110153 videoService = new VideoService_24110153();

	private CategoryService_24110153 categoryService = new CategoryService_24110153();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Category> categories = categoryService.findAll();

		Map<String, Long> videoCounts = categoryService.countVideosByCategory();

		req.setAttribute("categories", categories);
		req.setAttribute("videoCounts", videoCounts);

		int pageSize = 3;

		int currentPage = 1;

		String page = req.getParameter("page");

		if (page != null) {

			try {

				currentPage = Integer.parseInt(page);

			} catch (Exception e) {

				currentPage = 1;

			}

		}

		String categoryId = req.getParameter("categoryId");

		List<Video> videos;

		long totalVideos;

		if (categoryId != null && !categoryId.isBlank()) {

			videos = videoService.findByCategory(categoryId, currentPage, pageSize);

			totalVideos = videoService.countByCategory(categoryId);

		} else {

			videos = videoService.findAll(currentPage, pageSize);

			totalVideos = videoService.count();

		}

		int totalPages =

				(int) Math.ceil((double) totalVideos / pageSize);
		req.setAttribute("favoriteCounts", videoService.countFavorites());
		req.setAttribute("shareCounts", videoService.countShares());
		req.setAttribute("videos", videos);

		req.setAttribute("currentPage", currentPage);

		req.setAttribute("totalPages", totalPages);

		req.setAttribute("selectedCategoryId", categoryId);

		req.getRequestDispatcher("/views/home.jsp").forward(req, resp);

	}

}