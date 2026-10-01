package videostore.com.baitap.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "Username", columnDefinition = "NVARCHAR(50)")
    private String username;

    @Column(name = "Password", columnDefinition = "NVARCHAR(50)", nullable = false)
    private String password;

    @Column(name = "Phone", columnDefinition = "NVARCHAR(15)")
    private String phone;

    @Column(name = "Fullname", columnDefinition = "NVARCHAR(100)")
    private String fullname;

    @Column(name = "Email", columnDefinition = "NVARCHAR(100)", nullable = false)
    private String email;

    @Column(name = "Admin")
    private Boolean admin = false;

    @Column(name = "Active")
    private Boolean active = true;

    @Column(name = "Images", columnDefinition = "NVARCHAR(255)")
    private String images;

    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "user")
    private List<Share> shares;

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Boolean getAdmin() { return admin; }
    public void setAdmin(Boolean admin) { this.admin = admin; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }

    public List<Favorite> getFavorites() { return favorites; }
    public void setFavorites(List<Favorite> favorites) { this.favorites = favorites; }

    public List<Share> getShares() { return shares; }
    public void setShares(List<Share> shares) { this.shares = shares; }
}