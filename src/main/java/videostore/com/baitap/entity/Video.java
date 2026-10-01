package videostore.com.baitap.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Videos")
public class Video {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Lệnh quan trọng báo cho Java biết ID tự tăng
    @Column(name = "VideoId")
    private int videoId; // Phải là int hoặc Integer, KHÔNG được dùng String
    
    // Nhớ Generate lại Getter/Setter cho biến int này nhé

    @Column(name = "Title", columnDefinition = "NVARCHAR(255)", nullable = false)
    private String title;

    @Column(name = "Poster", columnDefinition = "NVARCHAR(255)")
    private String poster;

    @Column(name = "Views")
    private Integer views = 0;

    @Column(name = "Description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(name = "Active")
    private Boolean active = true;
    
    @Column(name = "Quantity")
    private Integer quantity; // Số lượng tồn kho thực tế

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;

    @OneToMany(mappedBy = "video")
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "video")
    private List<Share> shares;

    // Getters and Setters
 // Getters and Setters đã sửa thành kiểu int
    public int getVideoId() { return videoId; }
    public void setVideoId(int videoId) { this.videoId = videoId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPoster() { return poster; }
    public void setPoster(String poster) { this.poster = poster; }

    public Integer getViews() { return views; }
    public void setViews(Integer views) { this.views = views; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    
    public Integer getQuantity() { return quantity; }
	public void setQuantity(Integer quantity) { this.quantity = quantity; }
	
	public List<Favorite> getFavorites() { return favorites; }
    public void setFavorites(List<Favorite> favorites) { this.favorites = favorites; }

    public List<Share> getShares() { return shares; }
    public void setShares(List<Share> shares) { this.shares = shares; }
   
}