package DaiHoc.BachHoa.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 500, nullable = false, columnDefinition = "varchar(500) default 'Chưa đặt tên'")
    private String name;

    @Column(name = "image", length = 255)
    private String image;
 


    @Column(name = "price")
    private double price;

    @Column(name = "unit")
    private String unit;

    @JsonIgnore
    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "productdetail_id", referencedColumnName="id")
    private ProductDetail productDetail;
    
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cate_id", referencedColumnName="id")
	private Category category;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "subcate_id", referencedColumnName="id")
	private SubCategory subcategory;
	
	
	@JsonIgnore
	@OneToMany(mappedBy="product", cascade = CascadeType.ALL)
	private Set<BillDetail> billDetail;
	
	@JsonIgnore
	@OneToMany(mappedBy="product", cascade = CascadeType.ALL)
	private Set<Review> reviews;


}
