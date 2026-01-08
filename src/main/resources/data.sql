CREATE TABLE IF NOT EXISTS events (
  id INT PRIMARY KEY,
  name VARCHAR(255),
  available_slots INT
);

INSERT INTO events (id, name, available_slots) VALUES
(1, '🎸 Concert 2026', 100),
(2, '💻 Tech Workshop', 50);
