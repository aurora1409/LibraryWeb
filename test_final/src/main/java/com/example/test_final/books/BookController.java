package com.example.test_final.books;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;



@RestController
@CrossOrigin
public class BookController {
	@GetMapping("/books")
	public List<Book> getBooks(Model model) throws IOException {
		Connection connection=null;
		Statement statement= null;
		ResultSet resultSet=null;
		List<Book> products = new ArrayList<Book>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_ltw","root","1404200209091991");
			statement=connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM jdbc_ltw.book");
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				String author = resultSet.getString("author");
				String category = resultSet.getString("category");
				Date releaseDate = resultSet.getDate("releaseDate");
				int numsOfPages = resultSet.getInt("numsOfPages");
				int numsOfSold = resultSet.getInt("numsOfSold");
				String urlImage = resultSet.getString("urlImage");
				String description= resultSet.getString("description");
				int price = resultSet.getInt("price");
				products.add(new Book(id, title, author, category, releaseDate, numsOfPages, numsOfSold, urlImage, description,price));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	@GetMapping("/books/category")
//	@ResponseBody
	public List<Book> getBookByCategory(Model model, @RequestParam String categoryy) throws IOException {
		Connection connection=null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet=null;
		List<Book> students = new ArrayList<Book>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_ltw","root","1404200209091991");
			preparedStatement=connection.prepareStatement("SELECT * FROM book where category = ?");
			preparedStatement.setString(1, categoryy);
			resultSet= preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				String author = resultSet.getString("author");
				String category = resultSet.getString("category");
				Date releaseDate = resultSet.getDate("releaseDate");
				int numsOfPages = resultSet.getInt("numsOfPages");
				int numsOfSold = resultSet.getInt("numsOfSold");
				String urlImage = resultSet.getString("urlImage");
				String description= resultSet.getString("description");
				int price = resultSet.getInt("price");
				students.add(new Book(id, title, author, category, releaseDate, numsOfPages, numsOfSold, urlImage, description,price));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}
	
	@GetMapping("/books/search")
//	@ResponseBody
	public List<Book> getBooksByName(Model model, @RequestParam String str) throws IOException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    List<Book> books = new ArrayList<Book>();
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_ltw", "root", "1404200209091991");
	        preparedStatement = connection.prepareStatement("SELECT * FROM book where LOWER(title) like LOWER(?)"); // sử dụng hàm LOWER để chuyển tất cả các ký tự trong cột "ten" thành chữ thường
	        preparedStatement.setString(1, "%" + str.toLowerCase() + "%"); // chuyển cả từ khóa tìm kiếm về dạng chữ thường

	        resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String title = resultSet.getString("title");
	            String author = resultSet.getString("author");
	            String category = resultSet.getString("category");
	            Date releaseDate = resultSet.getDate("releaseDate");
	            int numsOfPages = resultSet.getInt("numsOfPages");
	            int numsOfSold = resultSet.getInt("numsOfSold");
	            String urlImage = resultSet.getString("urlImage");
	            String description = resultSet.getString("description");
	            int price = resultSet.getInt("price");
	            books.add(new Book(id, title, author, category, releaseDate, numsOfPages, numsOfSold, urlImage, description, price));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return books;
	}
	
	// Book detail
	@GetMapping("/book/{id}")
//	@ResponseBody
	public Book getBook(Model model, @PathVariable String id) throws IOException {
		Connection connection=null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet=null;
		Book book =new Book();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_ltw","root","1404200209091991");
			preparedStatement=connection.prepareStatement("select * from book where id = ?");
			preparedStatement.setInt(1, Integer.valueOf(id));
			resultSet= preparedStatement.executeQuery();
			while(resultSet.next()) {
				book.setId(resultSet.getInt("id"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(resultSet.getString("author"));
				book.setCategory(resultSet.getString("category"));
				book.setReleaseDate(resultSet.getDate("releaseDate"));
				book.setNumsOfPages(resultSet.getInt("numsOfPages"));
				book.setNumsOfSold(resultSet.getInt("numsOfSold"));
				book.setUrlImage(resultSet.getString("urlImage"));
				book.setDescription(resultSet.getString("description"));
				book.setPrice(resultSet.getInt("price"));
//				book.setSold(resultSet.getInt("sold")!=0? true:false);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return book;
	}
	
	// Update book
	@PutMapping("/book/save/{id}")
	// @PathVariable String bookcode để lấy {bookcode} trên url
	public void updateBook(@RequestBody Book book, @PathVariable String id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result= 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_ltw","root","1404200209091991");
			preparedStatement=connection.prepareStatement("update book set title=?, author=?, category=?, releaseDate=?, numsOfPages=?, numsOfSold =?, urlImage=?, description=?, price=?  where id=?");
			preparedStatement.setString(1, book.getTitle());
			preparedStatement.setString(2, book.getAuthor());
			preparedStatement.setString(3, book.getCategory());
			preparedStatement.setDate(4, book.getReleaseDate());
			preparedStatement.setInt(5, book.getNumsOfPages());
			preparedStatement.setInt(6, book.getNumsOfSold());
			preparedStatement.setString(7, book.getUrlImage());
			preparedStatement.setString(8, book.getDescription());
			preparedStatement.setInt(9, book.getPrice());
//			preparedStatement.setInt(4, book.isSold()?1:0);
			preparedStatement.setInt(10, Integer.valueOf(book.getId()));
			result=preparedStatement.executeUpdate();
			connection.close();
			preparedStatement.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@PostMapping("/book/save/{id}")
	public ResponseEntity<String> addBook(@RequestBody Book book, @PathVariable String id) {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    int result = 0;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_ltw", "root", "1404200209091991");

	        // Kiểm tra xem có cuốn sách nào trong database đã có title và author giống với cuốn sách mới không
	        preparedStatement = connection.prepareStatement("SELECT * FROM book WHERE LOWER(title) = LOWER(?) AND LOWER(author) = LOWER(?)");
	        preparedStatement.setString(1, book.getTitle());
	        preparedStatement.setString(2, book.getAuthor());
	        resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	            // Nếu có, trả về lỗi hoặc thông báo tương tự
	            return ResponseEntity.badRequest().body("Sách có cùng tiêu đề và tác giả đã tồn tại");
	        }

	        // Nếu không, thêm cuốn sách mới vào database
	        preparedStatement = connection.prepareStatement("INSERT INTO book (title, author, category, releaseDate, numsOfPages, numsOfSold, urlImage, description, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
	        preparedStatement.setString(1, book.getTitle());
	        preparedStatement.setString(2, book.getAuthor());
	        preparedStatement.setString(3, book.getCategory());
	        preparedStatement.setDate(4, book.getReleaseDate());
	        preparedStatement.setInt(5, book.getNumsOfPages());
	        preparedStatement.setInt(6, book.getNumsOfSold());
	        preparedStatement.setString(7, book.getUrlImage());
	        preparedStatement.setString(8, book.getDescription());
	        preparedStatement.setInt(9, book.getPrice());
	        result = preparedStatement.executeUpdate();

	        connection.close();
	        preparedStatement.close();

	        return ResponseEntity.ok("Book added successfully.");
	    } catch (Exception e) {
	        // Xử lý ngoại lệ và trả về lỗi
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error.");
	    } finally {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	@DeleteMapping("/book/delete/{id}")
	public void DeleteBook (@PathVariable String id) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_ltw","root","1404200209091991");
			ps = connection.prepareStatement("DELETE FROM book WHERE (id = ?)");
			ps.setInt(1, Integer.parseInt(id));
			ps.executeUpdate();
			ps.close();
			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@GetMapping("/book/{idBook}/comments")
//	@ResponseBody
	public List<Comment2> getCommentsOfBook(Model model, @PathVariable String idBook) throws IOException {
		Connection connection=null;
		PreparedStatement preparedStatement =null;
//		PreparedStatement preparedStatement2 =null;
		ResultSet resultSet=null;
//		ResultSet resultSet2=null;
		List<Comment2> comments = new ArrayList<Comment2>();
		User user =new User();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_ltw","root","1404200209091991");
			preparedStatement=connection.prepareStatement("select * from comment join user on comment.idUser = user.id where idBook =?");
//			preparedStatement=connection.prepareStatement("select * from comment where idBook = ?");
//			preparedStatement2=connection.prepareStatement("select * from user where id = ?");
			preparedStatement.setInt(1, Integer.valueOf(idBook));
			resultSet= preparedStatement.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String comment = resultSet.getString("comment");
				int numsOfStarRating = resultSet.getInt("numsOfStarRating");
				int idUser = resultSet.getInt("idUser");
//				preparedStatement2.setInt(1, idUser);
//				resultSet2= preparedStatement2.executeQuery();
//				while(resultSet2.next()) {
					String name = resultSet.getString("name");
					comments.add(new Comment2(id, comment, numsOfStarRating, name, Integer.valueOf(idBook)));
//				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return comments;
	}
	
	// new comment
		@PostMapping("/book/{idBook}/comment/{idUser}/save")
		public void addCommentOfBook(@RequestBody Comment comment, @PathVariable String idBook, @PathVariable String idUser) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			int result= 0;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_ltw","root","1404200209091991");
				preparedStatement=connection.prepareStatement("insert into comment (comment, numsOfStarRating, idUser, idBook) values (?, ?, ?, ?)");
				
				preparedStatement.setString(1, comment.getComment());
				preparedStatement.setInt(2, comment.getNumsOfStarRating());
				preparedStatement.setInt(3, Integer.parseInt(idUser));
				preparedStatement.setInt(4, Integer.parseInt(idBook));
				

				result=preparedStatement.executeUpdate();
				connection.close();
				preparedStatement.close();
				
			
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		// buying book
		@PostMapping("/book/{idBook}/user/{idUser}/buying")
		public void addBuying(@RequestBody BookBuying comment, @PathVariable String idBook, @PathVariable String idUser) {
		    Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    int result = 0;
		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_ltw", "root", "1404200209091991");
		        connection.setAutoCommit(false); // disable auto-commit mode để thực hiện 2 câu lệnh sql cùng nhau

		        // insert a new buying record
		        preparedStatement = connection.prepareStatement("insert into bookbuyingmanagement (numsOfBuying, idUser, idBook) values (?, ?, ?)");
		        preparedStatement.setInt(1, comment.getNumsOfBuying());
		        preparedStatement.setInt(2, Integer.parseInt(idUser));
		        preparedStatement.setInt(3, Integer.parseInt(idBook));
		        result = preparedStatement.executeUpdate();
		        preparedStatement.close();

		        // update the numsOfSold column of the book table
		        preparedStatement = connection.prepareStatement("update book set numsOfSold = numsOfSold + ? where id = ?");
		        preparedStatement.setInt(1, comment.getNumsOfBuying());
		        preparedStatement.setInt(2, Integer.parseInt(idBook));
		        result = preparedStatement.executeUpdate();
		        preparedStatement.close();

		        connection.commit(); // commit the transaction
		        connection.close();
		    } catch (Exception e) {
		        // rollback the transaction if an error occurs
		        if (connection != null) {
		            try {
		                connection.rollback();
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
		        }
		        e.printStackTrace();
		    }
		}
				
	// get list book buying by user
				@GetMapping("/bookbuyings")
				public List<BookBuying2> getBooks(@RequestParam int idUser) throws IOException {
					Connection connection=null;
					PreparedStatement preparedStatement = null;
					ResultSet resultSet=null;
					List<BookBuying2> products = new ArrayList<BookBuying2>();
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_ltw","root","1404200209091991");
						preparedStatement=connection.prepareStatement("SELECT * FROM bookbuyingmanagement join book on bookbuyingmanagement.idBook = book.id where idUser =?");
						
						preparedStatement.setInt(1, idUser);
						resultSet= preparedStatement.executeQuery();
						
						while(resultSet.next()) {
							int id = resultSet.getInt("id");
							int idBook = resultSet.getInt("idBook");
							String title = resultSet.getString("title");
							String author = resultSet.getString("author");
//							String category = resultSet.getString("category");
//							Date releaseDate = resultSet.getDate("releaseDate");
//							int numsOfPages = resultSet.getInt("numsOfPages");
//							int numsOfSold = resultSet.getInt("numsOfSold");
							String urlImage = resultSet.getString("urlImage");
//							String description= resultSet.getString("description");
							int price = resultSet.getInt("price");
							int numsOfBuying = resultSet.getInt("numsOfBuying");
							int totalPrice = price * numsOfBuying;
							String confirm = resultSet.getString("confirm");
							products.add(new BookBuying2(id,idBook ,title, author, urlImage, price, numsOfBuying, totalPrice, confirm));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					return products;
				}
				
				
				@GetMapping("/bookbuyings/confirm")
				public List<BookBuying2> getBooksAdmin() throws IOException {
					Connection connection=null;
					PreparedStatement preparedStatement = null;
					ResultSet resultSet=null;
					List<BookBuying2> products = new ArrayList<BookBuying2>();
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_ltw","root","1404200209091991");
						preparedStatement=connection.prepareStatement("SELECT * FROM bookbuyingmanagement join book on bookbuyingmanagement.idBook = book.id where confirm =?");
						
						preparedStatement.setString(1, "Chưa xác nhận");
						resultSet= preparedStatement.executeQuery();
						
						while(resultSet.next()) {
							int id = resultSet.getInt("id");
							int idBook = resultSet.getInt("idBook");
							String title = resultSet.getString("title");
							String author = resultSet.getString("author");
//							String category = resultSet.getString("category");
//							Date releaseDate = resultSet.getDate("releaseDate");
//							int numsOfPages = resultSet.getInt("numsOfPages");
//							int numsOfSold = resultSet.getInt("numsOfSold");
							String urlImage = resultSet.getString("urlImage");
//							String description= resultSet.getString("description");
							int price = resultSet.getInt("price");
							int numsOfBuying = resultSet.getInt("numsOfBuying");
							int totalPrice = price * numsOfBuying;
							String confirm = resultSet.getString("confirm");
							products.add(new BookBuying2(id,idBook ,title, author, urlImage, price, numsOfBuying, totalPrice, confirm));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					return products;
				}
				
				@PutMapping("/bookbuying/confirm/save/{id}")
				// @PathVariable String bookcode để lấy {bookcode} trên url
				public void updateBookBuying(@RequestParam String confirm, @PathVariable String id) {
					Connection connection = null;
					PreparedStatement preparedStatement = null;
					int result= 0;
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_ltw","root","1404200209091991");
						preparedStatement=connection.prepareStatement("update bookbuyingmanagement set confirm=?  where id=?");
						preparedStatement.setString(1, confirm);
						
						preparedStatement.setInt(2, Integer.valueOf(id));
						result=preparedStatement.executeUpdate();
						connection.close();
						preparedStatement.close();
						
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				
				// delete Buying
				@DeleteMapping("/bookbuying/delete/{id}")
				public void DeleteBuyingBook (@PathVariable String id) {
					Connection connection = null;
					PreparedStatement ps = null;
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_ltw","root","1404200209091991");
						ps = connection.prepareStatement("DELETE FROM bookbuyingmanagement WHERE (id = ?)");
						ps.setInt(1, Integer.parseInt(id));
						ps.executeUpdate();
						ps.close();
						connection.close();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}

	
				@PostMapping("/login")
				public ResponseEntity<String> login(@RequestBody User user) {
					Connection connection = null;
				    PreparedStatement statement = null;
				    ResultSet resultSet = null;
				    String token = null;
				    try {
				        Class.forName("com.mysql.cj.jdbc.Driver");
				        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_ltw","root","1404200209091991");
				        statement = connection.prepareStatement("SELECT * FROM jdbc_ltw.user WHERE username = ?");
				        statement.setString(1, user.getUsername());
				        resultSet = statement.executeQuery();
				        if (resultSet.next()) {
				            // Kiểm tra mật khẩu
				            String password = resultSet.getString("password");
				            if (!password.equals(user.getPassword())) {
				            	// Trả về status code 401 Unauthorized và thông báo lỗi "Mật khẩu không đúng" nếu mật khẩu không đúng
				            	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mật khẩu không đúng");
				            }
				            
				            // Lấy thông tin user từ ResultSet
				            int id = resultSet.getInt("id");
				            String name = resultSet.getString("name");
				            String email = resultSet.getString("email");
				            String username = resultSet.getString("username");
				            boolean isAdmin = resultSet.getBoolean("isAdmin");
				            
				         // Đánh dấu quyền hạn cho user
				            String role = "USER";
				            if (isAdmin) {
				                role = "ADMIN";
				            }
				            
				            // Tạo token với thông tin quyền hạn
				            Algorithm algorithm = Algorithm.HMAC256("secret_key");
				            token = JWT.create()
				                    .withClaim("id", id)
				                    .withClaim("name", name)
				                    .withClaim("email", email)
				                    .withClaim("username", username)
				                    .withClaim("role", role)
				                    .withExpiresAt(new Date(System.currentTimeMillis() + 600000))
				                    .sign(algorithm);
				            
				            // Trả về token trong header và status code 200 OK
				            HttpHeaders headers = new HttpHeaders();
				            headers.add("Authorization", "Bearer " + token);
				            headers.add("Content-Type", "application/json"); // Thêm header Content-Type
				            return ResponseEntity.ok().headers(headers).body(token);
				        } else {
				            // Trả về status code 401 Unauthorized và thông báo lỗi "Tài khoản không tồn tại" nếu thông tin đăng nhập không hợp lệ
				            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tài khoản không tồn tại");
				        }
				    } catch (Exception e) {
				        e.printStackTrace();
				        // Trả về status code 500 Internal Server Error nếu có lỗi xảy ra
				        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
				    } finally {
				        // Đóng kết nối và statement
				        try {
				            if(connection != null) {
				                connection.close();
				            }
				            if (statement != null) {
				                statement.close();
				            }
				        } catch (SQLException e) {
				            e.printStackTrace();
				        }
				    }
				}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user) {
		Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_ltw","root","1404200209091991");
	        statement = connection.prepareStatement("SELECT * FROM jdbc_ltw.user WHERE username = ?");
	        statement.setString(1, user.getUsername());
	        resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            // Nếu username đã tồn tại, trả về status code 409 Conflict
	            return ResponseEntity.status(HttpStatus.CONFLICT).build();
	        }
	        
	        // Nếu username không tồn tại, thêm user vào CSDL
	        statement = connection.prepareStatement("INSERT INTO jdbc_ltw.user (name, email, username, password, isAdmin) VALUES (?, ?, ?, ?, ?)");
	        statement.setString(1, user.getName());
	        statement.setString(2, user.getEmail());
	        statement.setString(3, user.getUsername());
	        statement.setString(4, user.getPassword());
	        statement.setInt(5, 0);
	        statement.executeUpdate();
	        
	        // Trả về status code 201 Created
	        return ResponseEntity.status(HttpStatus.CREATED).build();
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Trả về status code 500 Internal Server Error nếu có lỗi xảy ra
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } finally {
	        // Đóng kết nối và statement
	        try {
	            if(connection != null) {
	                connection.close();
	            }
	            if (statement != null) {
	                statement.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
}
