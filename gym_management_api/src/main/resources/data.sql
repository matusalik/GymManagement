-- TrainingGoal
INSERT INTO training_goal (name, description) VALUES
    ('Utrata wagi', 'Trening skoncentrowany na redukcji tkanki tłuszczowej.'),
    ('Budowa masy', 'Program mający na celu zwiększenie masy mięśniowej.'),
    ('Poprawa kondycji', 'Trening cardio i wytrzymałościowy dla ogólnej sprawności.');

-- Membership
INSERT INTO membership (name, price, duration, description) VALUES
    ('Standard', 99.99, 30, 'Miesięczny dostęp do siłowni bez dodatkowych usług.'),
    ('Premium', 149.99, 30, 'Dostęp do siłowni + zajęcia grupowe.'),
    ('VIP', 249.99, 90, 'Pełen pakiet, w tym konsultacje z trenerem i dieta.');

-- Equipment
INSERT INTO equipment (name, equipment_type, equipment_location, equipment_condition, equipment_availability) VALUES
    ('Bench', 'FREE_WEIGTH', 'FLOOR_A', 'GOOD', 'AVAILABLE');

-- Exercise
INSERT INTO exercise (name, description, exercise_category, equipment_id, instructions) VALUES
    ('Bench Press', 'Moving bar from point A to B', 'STRENGTH', 1, 'Lorem ipsum');
