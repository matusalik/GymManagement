-- TrainingGoal
INSERT INTO training_goal (training_goal_id, name, description) VALUES
  (0, 'Utrata wagi', 'Trening skoncentrowany na redukcji tkanki tłuszczowej.'),
  (1, 'Budowa masy', 'Program mający na celu zwiększenie masy mięśniowej.'),
  (2, 'Poprawa kondycji', 'Trening cardio i wytrzymałościowy dla ogólnej sprawności.');

-- Membership
INSERT INTO membership (membership_id, name, price, duration, description) VALUES
  (0, 'Standard', 99.99, 30, 'Miesięczny dostęp do siłowni bez dodatkowych usług.'),
  (1, 'Premium', 149.99, 30, 'Dostęp do siłowni + zajęcia grupowe.'),
  (2, 'VIP', 249.99, 90, 'Pełen pakiet, w tym konsultacje z trenerem i dieta.');
