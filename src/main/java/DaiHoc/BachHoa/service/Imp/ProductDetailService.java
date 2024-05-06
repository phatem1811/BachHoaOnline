package DaiHoc.BachHoa.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import DaiHoc.BachHoa.entity.ProductDetail;
import DaiHoc.BachHoa.service.IProductDetailService;

@Service
public class ProductDetailService implements IProductDetailService{

	@Autowired
	ProductDetailService repo;


	@Override
	public boolean create(ProductDetail productDetail) {
		try {
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	

	@Override
	public boolean update(ProductDetail productdetail) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public List<ProductDetail> getAll() {
		// TODO Auto-generated method stub
		return null;
	}



}
