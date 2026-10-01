package videostore.com.baitap.utils;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class MySiteMeshFilter_24110153 extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        // Chỉ định file layout.jsp sẽ được dùng để bọc toàn bộ các trang web (/*)
        // Đường dẫn này khớp với vị trí file trong ảnh của bạn
        builder.addDecoratorPath("/*", "/decorators/layout.jsp");
        
        // (Tùy chọn) Nếu sau này bạn có trang nào KHÔNG MUỐN hiện Header/Footer (ví dụ api)
        // thì mở comment dòng dưới:
        // builder.addExcludedPath("/api/*");
    }
}