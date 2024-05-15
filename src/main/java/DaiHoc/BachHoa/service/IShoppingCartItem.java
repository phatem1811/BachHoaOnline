package DaiHoc.BachHoa.service;

import java.util.Collection;

import DaiHoc.BachHoa.entity.LineItem;

public interface IShoppingCartItem {

	double getAmount();

	int getCount();

	Collection<LineItem> getAllLineItems();

	void clear();

	LineItem updateQuantity(Long productId, int quantity);

	void remove(Long id);

	void add(LineItem item);
	
	LineItem findLineItem(Long id);

}
