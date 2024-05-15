package DaiHoc.BachHoa.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineItem {
	private Long productId;
	private String name;
	private String image;
	private double price;
	private int quantity=1;

}
