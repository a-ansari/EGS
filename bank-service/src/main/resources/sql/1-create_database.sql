CREATE DATABASE egs_bank CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

CREATE USER 'egs_user'@'%' IDENTIFIED BY 'egs_pass';

GRANT ALL PRIVILEGES ON egs_bank.* TO 'egs_user'@'%';
