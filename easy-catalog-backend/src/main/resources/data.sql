-- Insert categories
INSERT INTO category (name) VALUES 
('Electronics'),
('Furniture'),
('Clothing'),
('Toys'),
('Books');

-- Insert products
INSERT INTO product (name, description, price, category_id, available) VALUES 
('Smartphone', 'A high-end smartphone with 6GB RAM and 128GB storage.', 699.99, (SELECT id FROM category WHERE name = 'Electronics'), true),
('Laptop', 'A powerful laptop with 16GB RAM, 512GB SSD, and Intel i7 processor.', 1299.99, (SELECT id FROM category WHERE name = 'Electronics'), true),
('Sofa', 'A comfortable 3-seat sofa made of leather and fabric.', 499.99, (SELECT id FROM category WHERE name = 'Furniture'), true),
('T-Shirt', 'A soft cotton T-shirt in various colors and sizes.', 19.99, (SELECT id FROM category WHERE name = 'Clothing'), true),
('Action Figure', 'An action figure from a popular superhero movie, ideal for collectors.', 24.99, (SELECT id FROM category WHERE name = 'Toys'), true);
