package videostore.com.baitap.service;

import videostore.com.baitap.dao.CategoryDAO_24110153;
import videostore.com.baitap.entity.Category;
import java.util.List;
import java.util.Map;

public class CategoryService_24110153 {
    private CategoryDAO_24110153 dao = new CategoryDAO_24110153();

    public List<Category> findAll() { return dao.findAll(); }
    public Category findById(String id) { return dao.findById(id); }
    public void insert(Category category) { dao.insert(category); }
    public void update(Category category) { dao.update(category); }
    public void delete(String id) { dao.delete(id); }
    public Map<String, Long> countVideosByCategory(){

        return dao.countVideosByCategory();

    }
    
}