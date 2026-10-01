package videostore.com.baitap.service;

import videostore.com.baitap.dao.VideoDAO_24110153;
import videostore.com.baitap.entity.Video;
import java.util.List;
import java.util.Map;
public class VideoService_24110153 {
    private VideoDAO_24110153 dao = new VideoDAO_24110153();

    public List<Video> findAll() {
        return dao.findAll();
    }
    public Video findById(int id) {
        return dao.findById(id);
    }

    public void insert(Video video) {
        dao.insert(video);
    }

    public void update(Video video) {
        dao.update(video);
    }

    public void delete(int id) {
        dao.delete(id);
    }
    public long count() {
        return dao.count();
    }

    public List<Video> findAll(int page, int pageSize) {
        return dao.findAll(page, pageSize);
    }
    public List<Video> findByCategory(String categoryId, int page, int pageSize){

        return dao.findByCategory(categoryId, page, pageSize);

    }

    public long countByCategory(String categoryId){

        return dao.countByCategory(categoryId);

    }
    public Map<Integer, Long> countFavorites() {
        return dao.countFavorites();
    }

    public Map<Integer, Long> countShares() {
        return dao.countShares();
    }
}