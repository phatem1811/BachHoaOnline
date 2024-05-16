package DaiHoc.BachHoa.service.Imp;

import java.util.Map;
import java.util.Collection;
import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import DaiHoc.BachHoa.entity.LineItem;
import DaiHoc.BachHoa.service.IShoppingCartItem;

@Service
@SessionScope
public class ShoppingCartItem implements IShoppingCartItem {

	Map<Long, LineItem> maps = new HashMap<>();
	
	@Override
	public void add(LineItem item) {
		LineItem lineItem = maps.get(item.getProductId());
		if(lineItem == null ) {
			maps.put(item.getProductId(), item);
		}
		else {
			lineItem.setQuantity(lineItem.getQuantity() + 1);
		}
	}
	@Override
	public void remove(Long id) {
		maps.remove(id);
	}
	public LineItem findLineItem(Long id) {
		LineItem lineItem = maps.get(id);
		return lineItem;
	}
	@Override
	public LineItem updateQuantity(Long productId, int quantity) {
		LineItem lineItem = maps.get(productId);
		lineItem.setQuantity(quantity);
		return lineItem;
	}
	@Override
	public void clear() {
		maps.clear();
	}
	@Override
	public Collection<LineItem> getAllLineItems() {
		return maps.values();
	}
	@Override
	public int getCount() {
		int count = 0;
		Collection<LineItem> list = this.getAllLineItems();
		for (LineItem lineitem :list) {
			count+=1;
		}
		return count;
	}
	@Override
	public double getAmount() {
		return maps.values().stream().mapToDouble(item -> item.getQuantity()*item.getPrice()).sum();
	}
	public double getTotal() {
		double tong = 0;
		Collection<LineItem> list = this.getAllLineItems();
		for (LineItem lineitem :list) {
			tong += lineitem.getQuantity()*lineitem.getPrice();
		}
		return tong;
	}
	
	
}
