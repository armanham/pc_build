package com.bdg.pc_build.wishlist.model.entity;


import com.bdg.pc_build.wishlist.model.dto.WishlistDTO;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@Entity
@Table(name = "wishlist")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @Column(name = "product-name" , nullable = false )
    String productName;

    @Column(name = "description", nullable = false)
    String description;

//    @Column(name = "recommended_price")
//    Double recommendedPrice;

    @Column(name = "created_at")
    @CreationTimestamp
    Timestamp createdAt;

    @Column(name = "username" , nullable = false)
    String username;


    public Wishlist(WishlistDTO wishlistDTO) {
        this.productName = wishlistDTO.getProductName();
        this.description = wishlistDTO.getDescription();
//        this.recommendedPrice = wishlistDTO.getRecommendedPrice();
        this.username = wishlistDTO.getUsername();
    }



}



