CREATE TABLE IF NOT EXISTS cars (
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     --car_id INT NOT NULL,
     name VARCHAR(50) NOT NULL,  
     image_name VARCHAR(255),
     description VARCHAR(255) NOT NULL,
     staring INT NOT NULL,
     goaling INT NOT NULL,
     car_model VARCHAR(50) NOT NULL,
     address VARCHAR(255) NOT NULL,
     car_number VARCHAR(50) NOT NULL,
     created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
     updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
 );
 
 CREATE TABLE IF NOT EXISTS reservations (
     id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     car_id INT NOT NULL,
     checkin_date DATE NOT NULL,
     checkout_date DATE NOT NULL,
     staring INT NOT NULL,
     goaling INT NOT NULL,
     created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
     updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     FOREIGN KEY (car_id) REFERENCES cars (id)
 );