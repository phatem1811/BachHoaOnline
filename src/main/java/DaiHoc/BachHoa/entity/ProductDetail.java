package DaiHoc.BachHoa.entity;

import java.sql.Date;
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
@Table(name = "product_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {
 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@Column(name = "description", length = 500)
    private String description;
	
	@Column(name = "type")
    private String type;

	@Column(name = "weight")
    private float weight;
	
	@Column(name = "preserve")
    private String preserve;
	
	@Column(name = "processing")
    private String processing;
	
	@Column(name = "mfd")
    private Date manufacture ;
	
	@Column(name = "exp")
    private Date expert  ;
	
	@JsonIgnore
	@OneToOne(mappedBy = "productDetail")
	private Product product;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="branch_id", referencedColumnName="id")
	private Branch branch;
	
}
