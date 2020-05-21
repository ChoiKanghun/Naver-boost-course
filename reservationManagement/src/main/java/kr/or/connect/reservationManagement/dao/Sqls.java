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


	public static final String SELECT_ALL_PRODUCTS = 
			SELECT_PRODUCTS_FRAME 
			+ "ORDER BY product.id ASC ";


	public static final String SELECT_LIMIT_PRODUCTS 
	= SELECT_PRODUCTS_FRAME 
	+ "ORDER BY product.id ASC "
	+ "limit :start, :limit ";


	public static final String SELECT_LIMIT_PRODUCTS_BY_CATEGORY_ID 
	= 	SELECT_PRODUCTS_FRAME
		+ "and product.category_id = :categoryId "
		+ "ORDER BY product.id ASC "
		+ "limit :start, :limit ";
					

	public static final String COUNT_ALL_PRODUCT 
	= "SELECT count(product_image.type) " + 
	"FROM product " + 
	"JOIN display_info ON product.id = display_info.product_id " + 
	"JOIN product_image ON display_info.product_id = product_image.product_id " + 
	"WHERE product_image.type='th' ";

	public static final String COUNT_PRODUCT_BY_CATEGORY_ID 
	= COUNT_ALL_PRODUCT + "and product.category_id = :categoryId";
	
	public static final String SELECT_CATEGORIES_INFO_GROUP_BY_CATEGORY_ID 
	= "" + 
			"SELECT category.id AS id, " + 
			"category.name AS name, " + 
			"count(display_info.product_id) AS count " + 
			"FROM category " + 
			"JOIN product " + 
			"ON category.id = product.category_id " + 
			"JOIN display_info " + 
			"ON display_info.product_id = product.id " +
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

	public static final String GET_DETAIL_PAGE_ITEMS_BY_ID
	= "SELECT display_info.id AS display_info_id, " + 
			"product.id AS product_id, product.description AS product_description, " + 
			"display_info.place_name AS place_name, product.content AS product_content, " + 
			"file_info.save_file_name AS product_image_url " + 
			"FROM product " + 
			"JOIN display_info " + 
			"ON product.id = display_info.product_id " + 
			"JOIN product_image " + 
			"ON display_info.product_id = product_image.product_id " + 
			"JOIN file_info " + 
			"ON file_info.id = product_image.file_id " + 
			"WHERE (product_image.type='et' or product_image.type='ma') "
			+ " and display_info.id = :id ";
	
	public static final String SELECT_USER_COMMENTS_BY_ID
	= 
			"SELECT reservation_user_comment.comment AS comment, " + 
			"product.id AS product_id, " + 
			"reservation_user_comment.score AS score, " + 
			"reservation_user_comment.create_date AS create_date, " + 
			"file_info.save_file_name AS product_image_url, " + 
			"reservation_user_comment_image.file_id AS file_id, " + 
			"reservation_info.reservation_email AS reservation_email " + 
			"FROM reservation_user_comment " + 
			"JOIN product  " + 
			"ON product.id = reservation_user_comment.product_id   " + 
			"LEFT JOIN reservation_user_comment_image  " + 
			"ON reservation_user_comment.id = reservation_user_comment_image.reservation_user_comment_id  " + 
			"LEFT JOIN file_info " + 
			"ON file_info.id = reservation_user_comment_image.file_id " +
			"LEFT JOIN reservation_info " +
			"ON reservation_info.id = reservation_user_comment.reservation_info_id " +
			"WHERE product.id = :id ";

	public static final String GET_LIMITED_USER_COMMENTS_BY_ID
	= SELECT_USER_COMMENTS_BY_ID + "LIMIT 0, :limit ";
	
	
	/*project 4*/
	public static final String SELECT_COMMENT_IMAGE_BY_RESERVATION_USER_COMMENT_ID
	= "" + 
			"SELECT " + 
			"file_info.content_type AS content_type, " + 
			"file_info.delete_flag AS delete_flag, " + 
			"file_info.id AS file_id, " + 
			"file_info.file_name AS file_name, " + 
			"reservation_user_comment_image.id AS image_id, " + 
			"reservation_info.id AS reservation_info_id, " + 
			"reservation_user_comment.id AS reservation_user_comment_id, " + 
			"file_info.save_file_name AS save_file_name, " + 
			"SUBSTRING(DATE_FORMAT(reservation_user_comment.create_date, '%Y-%m-%dT%H:%i:%s.%f'), 1, 23)  " + 
			"AS create_date, " + 
			"SUBSTRING(DATE_FORMAT(reservation_user_comment.modify_date, '%Y-%m-%dT%H:%i:%s.%f'), 1, 23)  " + 
			"AS modify_date " + 
			"FROM file_info " + 
			"JOIN reservation_user_comment_image " + 
			"ON file_info.id = reservation_user_comment_image.file_id " + 
			"JOIN reservation_info " + 
			"ON reservation_info.id = reservation_user_comment_image.reservation_info_id " + 
			"JOIN reservation_user_comment " + 
			"ON reservation_user_comment.id = reservation_user_comment_image.reservation_user_comment_id " + 
			"JOIN product " + 
			"ON product.id = reservation_user_comment.product_id " + 
			"JOIN display_info " + 
			"ON product.id = display_info.product_id " + 
			"WHERE reservation_user_comment_id = :reservationUserCommentId ";
	
	public static final String SELECT_COMMENTS_BY_DISPLAY_INFO_ID
	= "SELECT " + 
			"reservation_user_comment.comment AS comment, " + 
			"reservation_user_comment.id AS comment_id, " + 
			"SUBSTRING(DATE_FORMAT(reservation_user_comment.create_date, '%Y-%m-%dT%H:%i:%s.%f'), 1, 23) " + 
			"AS create_date, " + 
			"SUBSTRING(DATE_FORMAT(reservation_user_comment.modify_date, '%Y-%m-%dT%H:%i:%s.%f'), 1, 23) " + 
			"AS modify_date, " + 
			"product.id AS product_id, " + 
			"SUBSTRING(DATE_FORMAT(reservation_info.reservation_date, '%Y-%m-%dT%H:%i:%s.%f'), 1, 23) " + 
			"AS reservation_date, " + 
			"reservation_info.reservation_email AS reservation_email, " + 
			"reservation_info.id AS reservation_info_id, " + 
			"reservation_info.reservation_name AS reservation_name, " + 
			"reservation_info.reservation_tel AS reservation_telephone, " + 
			"reservation_user_comment.score AS score " + 
			"" + 
			"FROM reservation_user_comment " + 
			"JOIN reservation_info " + 
			"ON reservation_info.id = reservation_user_comment.reservation_info_id " + 
			"JOIN product " + 
			"ON product.id = reservation_user_comment.product_id " + 
			"JOIN display_info " + 
			"ON display_info.product_id = product.id " + 
			"" + 
			"WHERE display_info.id = :displayInfoId ";
	
	public static final String SELECT_PRODUCT_IMAGES_BY_DISPLAY_INFO_ID
	= "SELECT " + 
			"file_info.content_type AS content_type, " + 
			"file_info.delete_flag AS delete_flag, " + 
			"file_info.id AS file_info_id, " + 
			"file_info.file_name AS file_name, " + 
			"SUBSTRING(DATE_FORMAT(file_info.create_date, '%Y-%m-%dT%H:%i:%s.%f'), 1, 23) " + 
			"AS create_date, " + 
			"SUBSTRING(DATE_FORMAT(file_info.modify_date, '%Y-%m-%dT%H:%i:%s.%f'), 1, 23) " + 
			"AS modify_date, " + 
			"file_info.save_file_name AS save_file_name, " + 
			"product.id AS product_id, " + 
			"product_image.id AS product_image_id, " + 
			"product_image.type AS type " + 
			"FROM " + 
			"file_info " + 
			"JOIN product_image " + 
			"ON product_image.file_id = file_info.id " + 
			"JOIN product " + 
			"ON product_image.product_id = product.id " + 
			"JOIN display_info " + 
			"ON display_info.product_id = product.id " + 
			"WHERE (type = 'ma' or type = 'et') " + 
			"and display_info.id = :displayInfoId ";
	
	public static final String SELECT_PRODUCT_PRICES_BY_DISPLAY_INFO_ID
	= "SELECT " + 
			"product_price.price AS price, " + 
			"product_price.discount_rate AS discount_rate, " + 
			"product_price.price_type_name AS price_type_name, " + 
			"product.id AS product, " + 
			"product_price.id AS product_price_id, " + 
			"SUBSTRING(DATE_FORMAT(product_price.create_date, '%Y-%m-%dT%H:%i:%s.%f'), 1, 23) " + 
			"AS create_date, " + 
			"SUBSTRING(DATE_FORMAT(product_price.modify_date, '%Y-%m-%dT%H:%i:%s.%f'), 1, 23) " + 
			"AS modify_date " + 
			"FROM " + 
			"product_price " + 
			"JOIN product " + 
			"ON product_price.product_id = product.id " + 
			"JOIN display_info " + 
			"ON display_info.product_id = product.id " + 
			"WHERE display_info.id = :displayInfoId ";
	
	public static final String SELECT_DISPLAY_INFO_BY_DISPLAY_INFO_ID
	= "SELECT " + 
			"display_info.id AS display_info_id, " + 
			"display_info.email AS email, " + 
			"display_info.homepage AS homepage, " + 
			"display_info.opening_hours AS opening_hours, " + 
			"display_info.place_lot AS place_lot, " + 
			"display_info.place_name AS place_name, " + 
			"display_info.place_street AS place_street, " +
			"display_info.tel AS telephone, " +
			"category.id AS category_id, " + 
			"category.name AS category_name, " + 
			"product.id AS product_id, " + 
			"product.content AS product_content, " + 
			"product.description AS product_description, " + 
			"product.event AS product_event, " + 
			"SUBSTRING(DATE_FORMAT(display_info.create_date, '%Y-%m-%dT%H:%i:%s.%f'), 1, 23) " + 
			"AS create_date, " + 
			"SUBSTRING(DATE_FORMAT(display_info.modify_date, '%Y-%m-%dT%H:%i:%s.%f'), 1, 23) " + 
			"AS modify_date " + 
			"FROM " + 
			"display_info " + 
			"JOIN product " + 
			"ON display_info.product_id = product.id " + 
			"JOIN category " + 
			"ON product.category_id = category.id " + 
			"WHERE display_info.id = :displayInfoId";
	
	public static final String SELECT_DISPLAY_INFO_IMAGE_BY_DISPLAY_INFO_ID
	= "SELECT " + 
			"display_info_image.id AS display_info_image_id, " + 
			"file_info.content_type AS content_type, " + 
			"file_info.delete_flag As delete_flag, " + 
			"file_info.id AS file_id, " + 
			"file_info.file_name AS file_name, " + 
			"file_info.save_file_name AS save_file_name, " + 
			"display_info.id AS display_info_id, " + 
			"SUBSTRING(DATE_FORMAT(display_info.create_date, '%Y-%m-%dT%H:%i:%s.%f'), 1, 23) " + 
			"AS create_date, " + 
			"SUBSTRING(DATE_FORMAT(display_info.modify_date, '%Y-%m-%dT%H:%i:%s.%f'), 1, 23) " + 
			"AS modify_date " + 
			"FROM " + 
			"display_info_image " + 
			"JOIN display_info " + 
			"ON display_info_image.display_info_id = display_info.id " + 
			"JOIN file_info " + 
			"ON display_info_image.file_id = file_info.id " + 
			"WHERE display_info.id = :displayInfoId" ;
	
	/*project 5*/
	public static final String SELECT_RESERVATIONS_BY_RESERVATION_EMAIL
	= "SELECT " + 
			"reservation_info.id AS reservation_info_id, " +
			"reservation_info.cancel_flag AS cancel_Yn, " + 
			"reservation_info.product_id AS product_id, " + 
			"reservation_info.display_info_id AS display_info_id, " + 
			"reservation_info.reservation_date AS reservation_date,  " + 
			"reservation_info.reservation_name AS reservation_name, " + 
			"reservation_info.reservation_email AS reservation_email, " + 
			"reservation_info.reservation_tel AS reservation_telephone, " + 
			"SUM(product_price.price) AS total_price, " + 
			"SUBSTRING(DATE_FORMAT(reservation_info.create_date, '%Y-%m-%dT%H:%i:%s.%f'), 1, 23) " + 
			"AS create_date, " + 
			"SUBSTRING(DATE_FORMAT(reservation_info.modify_date, '%Y-%m-%dT%H:%i:%s.%f'), 1, 23) " + 
			"AS modify_date " + 
			"FROM " + 
			"reservation_info " + 
			"JOIN display_info " + 
			"ON display_info.id = reservation_info.display_info_id " + 
			"JOIN product " + 
			"ON product.id = reservation_info.product_id " + 
			"JOIN product_price " + 
			"ON product_price.product_id = reservation_info.product_id " + 
			"WHERE reservation_info.reservation_email = :reservationEmail ";
	
	public static final String GET_DELETE_RESULT
	= "SELECT " + 
			"reservation_info.cancel_flag AS cancel_Yn, " + 
			"reservation_info.id AS reservation_info_id, " + 
			"reservation_info.product_id AS product_id, " + 
			"reservation_info.display_info_id AS display_info_id, " + 
			"reservation_info.reservation_date AS reservation_date,  " + 
			"reservation_info.reservation_name AS reservation_name, " + 
			"reservation_info.reservation_email AS reservation_email, " + 
			"reservation_info.reservation_tel AS reservation_telephone, " + 
			"SUM(product_price.price ) AS total_price, " + 
			"SUBSTRING(DATE_FORMAT(reservation_info.create_date, '%Y-%m-%dT%H:%i:%s.%f'), 1, 23) " + 
			"AS create_date, " + 
			"SUBSTRING(DATE_FORMAT(reservation_info.modify_date, '%Y-%m-%dT%H:%i:%s.%f'), 1, 23) " + 
			"AS modify_date " + 
			"FROM " + 
			"reservation_info " + 
			"JOIN display_info " + 
			"ON display_info.id = reservation_info.display_info_id " + 
			"JOIN product " + 
			"ON product.id = reservation_info.product_id " + 
			"WHERE reservation_info.id = :reservationId ";
	
	public static final String GET_DELETE_RESULT_PRICES
	= "SELECT " + 
			"reservation_info.id AS reservation_info_id, " + 
			"reservation_info_price.id AS reservation_info_price_id, " + 
			"product_price.id AS product_price_id, " + 
			"COUNT(reservation_info_price.reservation_info_id) AS count " + 
			"FROM" + 
			"reservation_info " + 
			"JOIN reservation_info_price " + 
			"ON reservation_info_price.reservation_info_id = reservation_info.id " + 
			"JOIN product_price " + 
			"ON product_price.id = reservation_info_price.product_price_id " + 
			"WHERE reservation_info.id = :reservationId";
}
