INSERT IGNORE INTO role (id, name) VALUES (1, 'ROLE_USER');
INSERT IGNORE INTO role (id, name) VALUES (2, 'ROLE_ADMIN');
INSERT IGNORE INTO users (id, username, password, yob, country) VALUES (1, 'admin', '$2a$10$ghURgC2wCVwwDo5w4tqOU.n6UshNG2uwuKuMUatxxz/kgT7vJHUzm', 2000, 'admin');
INSERT IGNORE INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT IGNORE INTO user_roles (user_id, role_id) VALUES (1, 2);
INSERT IGNORE INTO game (id, running, total_rate, level, games) VALUES (1, true, 3.0, 1, 0);
UPDATE IGNORE users SET game_id = 1 WHERE id = 1;