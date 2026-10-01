package videostore.com.baitap.controller;

import videostore.com.baitap.dao.OrderDAO_24110153;
import videostore.com.baitap.entity.Order;
import videostore.com.baitap.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/orders")
public class AdminOrderServlet_24110153 extends HttpServlet {
	private final OrderDAO_24110153 orderDAO = new OrderDAO_24110153();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Kiểm tra quyền Admin (giả sử User có trường isAdmin() hoặc thuộc tính admin)
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user == null || !user.getAdmin()) { // Tùy thuộc vào getter kiểm tra admin của bạn (vd: getAdmin() hoặc
												// isRole())
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		// Lấy tất cả đơn hàng hoặc lọc theo trạng thái
		String status = request.getParameter("status");
		if (status == null || status.trim().isEmpty()) {
			status = "ALL";
		}

		// Bạn có thể viết thêm hàm findAll hoặc tận dụng hàm có sẵn. Ở đây ta dùng hàm
		// lọc theo username = null hoặc viết riêng hàm lấy tất cả trong DAO.
		// Để nhanh gọn, ta gọi hàm lấy danh sách theo trạng thái nhưng truyền vào
		// username dạng null hoặc tạo hàm findAllOrders trong DAO.
		// Gọi hàm dành riêng cho Admin để lấy toàn bộ đơn hàng trong hệ thống theo
		// trạng thái
		List<Order> orders = orderDAO.findOrdersForAdmin(status);

		request.setAttribute("orders", orders);
		request.setAttribute("currentStatus", status);
		request.getRequestDispatcher("/views/admin/admin-orders-history.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int orderId = Integer.parseInt(request.getParameter("orderId"));
			String status = request.getParameter("status");

			// Admin cập nhật trạng thái
			orderDAO.updateOrderStatus(orderId, status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/admin/orders");
	}
}