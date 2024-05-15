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
@Table(name = "bill")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "total_price")
	private double total_price;
	
	@Column(name = "method_payment")
	private String method_payment;
	
	@Column(name = "address")
	private String address;
	
	@JsonIgnore
    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private Set<BillDetail> bill_detail;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="id")
	private User user;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="promotional_codeid", referencedColumnName="id")
	private PromotionalCode promotionalCode;
	

}
