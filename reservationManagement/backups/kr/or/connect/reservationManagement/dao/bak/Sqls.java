/*package kr.or.connect.reservationManagement.dao.bak;
package kr.or.connect.reservationManagement.dao;

public class Sqls {

	public static final String COUNT_PRODUCT_BY_CATEGORY_ID
	= "SELECT count(*) FROM product WHERE category_id = :categoryId";
	
	//get all List of Product table
	public static final String COUNT_ALL_PRODUCT
	= "SELECT count(*) FROM product";
	
	//get all List of Products table + place_name in display_info
	//+ product_image_url from product_image table
	public static final String SELECT_ALL_PRODUCTS
	= "SELECT product.id AS product_id, product.content AS product_content, display_info.id AS display_info_id, place_name, product.description AS product_description, concat(product_image.product_id, '_th_', product_image.id, '.png') AS product_image_url FROM product JOIN display_info ON product.id = display_info.id JOIN product_image ON product.id = product_image.product_id WHERE product_image.type = 'th' ORDER BY product.id ASC";

	//get Limited List of Products table + place_name in display_info
	//+ product_image_url from product_image table
	public static final String SELECT_LIMIT_PRODUCTS
	= "SELECT product.id AS product_id, product.content AS product_content, display_info.id AS display_info_id, place_name, product.description AS product_description, concat(product_image.product_id, '_th_', product_image.id, '.png') AS product_image_url FROM product JOIN display_info ON product.id = display_info.id JOIN product_image ON product.id = product_image.product_id WHERE product_image.type = 'th' ORDER BY product.id ASC limit :start, :limit";

	//get Limited List of Products table + place_name in display_info
	//+ product_image_url from product_image table
	//WHERE category_id = ?
	public static final String SELECT_LIMIT_PRODUCTS_BY_CATEGORY_ID
	= "SELECT product.id AS product_id, product.content AS product_content, display_info.id AS display_info_id, place_name, product.description AS product_description, concat(product_image.product_id, '_th_', product_image.id, '.png') AS product_image_url FROM product JOIN display_info ON product.id = display_info.id JOIN product_image ON product.id = product_image.product_id WHERE category_id = :categoryId and product_image.type = 'th' ORDER BY product.id ASC limit :start, :limit";

	

	public static final String SELECT_TH
	= "SELECT id, product_id, type, file_id FROM product_image WHERE type = :type ORDER BY id ASC";	
	
	//get product_image table records
	public static final String SELECT_BY_PRODUCT_ID 
			= "SELECT id, product_id, type, file_id FROM product_image WHERE product_id = :id and type = :type";
	
	public static final String SELECT_ALL_PRODUCT
		= "SELECT * FROM product ORDER BY id ASC";
	
	public static final String SELECT_ALL_DISPLAY_INFO
		= "SELECT * FROM display_info ORDER BY id ASC"; 
	

}
*/