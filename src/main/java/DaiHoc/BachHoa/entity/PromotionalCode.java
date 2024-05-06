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
@Table(name="promotional_code")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionalCode {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@Column(name = "code")
	private String code;
	
    private double discount;

    @Column(name = "exp_date")
    private Date expiryDate;
    
    @JsonIgnore
    @OneToMany(mappedBy="promotionalCode", cascade = CascadeType.ALL)
    private Set<Bill> bills;
}
