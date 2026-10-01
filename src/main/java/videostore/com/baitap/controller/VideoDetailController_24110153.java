package videostore.com.baitap.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import videostore.com.baitap.entity.Video;
import videostore.com.baitap.service.VideoService_24110153;

import java.io.IOException;

@WebServlet("/video/detail")
public class VideoDetailController_24110153 extends HttpServlet {

    private VideoService_24110153 videoService = new VideoService_24110153();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idStr = req.getParameter("id");

        if (idStr != null && !idStr.isEmpty()) {
            try {

                int id = Integer.parseInt(idStr);

                Video video = videoService.findById(id);

                if (video != null) {

                    video.setViews(video.getViews() + 1);
                    videoService.update(video);

                    req.setAttribute("video", video);
                    req.getRequestDispatcher("/views/detail.jsp")
                       .forward(req, resp);
                    return;
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        resp.sendRedirect(req.getContextPath() + "/home");
    }
}