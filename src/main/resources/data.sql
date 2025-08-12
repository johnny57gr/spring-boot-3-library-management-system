-- === Demo data for table book ===
INSERT INTO book (author, isavailable, isbn, title) VALUES
('Robert C. Martin', FALSE, '9780132350884', 'Clean Code'),
('Αντουάν ντε Σαιντ-Εξυπερύ', FALSE, '9789603251234', 'Ο Μικρός Πρίγκιπας'),
('Φραντς Κάφκα', TRUE, '9789604537863', 'Η Δίκη'),
('Άλκη Ζέη', TRUE, '9789600342928', 'Το Καπλάνι της Βιτρίνας'),
('Γιάννης Ρίτσος', TRUE, '9789600422187', 'Η Σονάτα του Σεληνόφωτος'),
('Αρκάς', FALSE, '9786180700660', 'Η Ζωή Μετά');  -- (διόρθωσα σε 13ψηφιο ISBN)

-- === Demo data for table members ===

INSERT INTO members (date_of_birthday, email, first_name, last_name, membership_date, phone) VALUES
('1986-03-23', 'yiannispas86@gmail.com', 'Γιάννης', 'Πασσάς', '2025-07-29', '6984705234'),
('1990-04-20', 'maria@example.com', 'Μαρία', 'Παπαδοπούλου', '2024-09-15', '6981234567'),
('1985-12-11', 'marios.k@example.com', 'Μάριος', 'Κωνσταντίνου', '2024-10-01', '6947890123'),
('1992-08-03', 'eleni.g@example.com', 'Ελένη', 'Γεωργίου', '2024-07-22', '6976543210'),
('1988-05-17', 'demi.kal@example.com', 'Δημήτρης', 'Καλογήρου', '2024-08-10', '6931122334'),
('1981-10-16', 'mpassaqm@hotmail.com', 'Μαριάννα', 'Πασσά', '2025-08-03', '6934995004'),
('2016-10-09', 'melina.triant@gmail.com', 'Μελίνα', 'Τριανταφυλλίδη', '2025-08-08', '6987748452');

-- === Demo data for table loans ===

INSERT INTO loans (actual_return_date, borrow_date, return_date, returned, book_id, member_id) VALUES
('2025-08-03', '2025-08-01', '2025-08-08', TRUE, 5, 2),
(NULL,           '2025-08-03', '2025-08-10', FALSE, 2, 1),
('2025-08-03', '2025-08-03', '2025-08-05', TRUE, 6, 4),
('2025-08-03', '2025-08-03', '2025-08-10', TRUE, 4, 2),
('2025-08-08', '2025-08-03', '2025-08-10', TRUE, 5, 5),
(NULL,           '2025-08-08', '2025-08-15', FALSE, 6, 7); -- παλιά ήταν book_id=8 -> το έφερα στο 6
