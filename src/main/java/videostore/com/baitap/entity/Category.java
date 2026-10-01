package videostore.com.baitap.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @Column(name = "CategoryId", columnDefinition = "NVARCHAR(50)")
    private String categoryId;

    @Column(name = "CategoryName", columnDefinition = "NVARCHAR(100)", nullable = false)
    private String categoryName;

    @Column(name = "Images", columnDefinition = "NVARCHAR(255)")
    private String images;

    @Column(name = "Status")
    private Boolean status = true;

    @OneToMany(mappedBy = "category")
    private List<Video> videos;

    // Getters and Setters
    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }

    public List<Video> getVideos() { return videos; }
    public void setVideos(List<Video> videos) { this.videos = videos; }
}