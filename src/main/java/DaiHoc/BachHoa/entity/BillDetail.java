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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bill_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "total_lineitem")
	private double totalLineitem;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="bill_id", referencedColumnName="id")
	private Bill bill;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="product_id", referencedColumnName="id")
	private Product product;
	
	
}
