package kr.or.connect.reservationManagement.dao;

public class Sqls {
	
	public static final String SELECT_PRODUCTS_FRAME 
	= "SELECT display_info.id AS display_info_id, "
			+ "product.id AS product_id, " + "product.description AS product_description, "
			+ "display_info.place_name AS place_name, " + "product.content AS product_content, "
			+ "file_info.save_file_name AS product_image_url "
			+ "FROM product " 
			+ "JOIN display_info "
			+ "ON product.id = display_info.product_id "
			+ "JOIN product_image "
			+ "ON display_info.product_id = product_image.product_id "
			+ "JOIN file_info "
			+ "ON file_info.id = product_image.file_id "
			+ "WHERE product_image.type='th' ";

	// get all List of Products table + place_name in display_info
	// + product_image_url from product_image table
	public static final String SELECT_ALL_PRODUCTS = 
			SELECT_PRODUCTS_FRAME 
			+ "ORDER BY product.id ASC ";

	// get Limited List of Products table + place_name in display_info
	// + product_image_url from product_image table
	public static final String SELECT_LIMIT_PRODUCTS 
	= SELECT_PRODUCTS_FRAME 
	+ "ORDER BY product.id ASC "
	+ "limit :start, :limit ";

	// get Limited List of Products table + place_name in display_info
	// + product_image_url from product_image table
	// WHERE category_id = ?
	public static final String SELECT_LIMIT_PRODUCTS_BY_CATEGORY_ID 
	= 	SELECT_PRODUCTS_FRAME
		+ "and product.category_id = :categoryId "
		+ "ORDER BY product.id ASC "
		+ "limit :start, :limit ";
					
	// get count of items
	// for totalCount
	public static final String COUNT_ALL_PRODUCT 
	= "SELECT count(product_image.type) " + 
	"FROM product " + 
	"JOIN display_info ON product.id = display_info.product_id " + 
	"JOIN product_image ON display_info.product_id = product_image.product_id " + 
	"WHERE product_image.type='th' ";

	public static final String COUNT_PRODUCT_BY_CATEGORY_ID 
	= COUNT_ALL_PRODUCT + "and product.category_id = :categoryId";
	
	public static final String SELECT_CATEGORIES_BY_CATEGORY_ID 
	= "" + 
			"SELECT category.id AS id, " + 
			"category.name AS name, " + 
			"count(display_info.product_id) AS count " + 
			"FROM category " + 
			"JOIN product" + 
			"ON category.id = product.category_id" + 
			"JOIN display_info " + 
			"ON display_info.product_id = product.id " +
			"WHERE category.id = :categoryId " +
			"GROUP BY category.id ";

	public static final String PROMOTION_INFO 
	= "SELECT promotion.id AS id, "
			+ "promotion.product_id AS product_id, "
			+ "file_info.save_file_name AS product_image_url "
			+ "FROM promotion "
			+ "JOIN product_image "
			+ "ON promotion.product_id = product_image.product_id "
			+ "JOIN file_info "
			+ "ON file_info.id = product_image.file_id "
			+ "WHERE type='th' ";


	
	
	/*
	 ** not in use. in case in need.
	 */
	public static final String SELECT_TH = "SELECT id, product_id, type, file_id FROM product_image WHERE type = :type ORDER BY id ASC";

	public static final String SELECT_BY_PRODUCT_ID = "SELECT id, product_id, type, file_id FROM product_image WHERE product_id = :id and type = :type";

	public static final String SELECT_ALL_PRODUCT = "SELECT * FROM product ORDER BY id ASC";

	public static final String SELECT_ALL_DISPLAY_INFO = "SELECT * FROM display_info ORDER BY id ASC";

	

}
