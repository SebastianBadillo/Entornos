-- Drop database if exists and recreate
DROP DATABASE IF EXISTS project_db;
CREATE DATABASE project_db;
USE project_db;

-- Drop tables in order of dependencies
DROP TABLE IF EXISTS user_group_members;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS user_groups;
DROP TABLE IF EXISTS users;

-- Users table
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       first_name VARCHAR(50) NOT NULL,
                       last_name VARCHAR(50) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       program VARCHAR(100),
                       description TEXT,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- User groups table
CREATE TABLE user_groups (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             name VARCHAR(100) NOT NULL,
                             leader_id INT,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             FOREIGN KEY (leader_id) REFERENCES users(id)
                                 ON DELETE SET NULL
                                 ON UPDATE CASCADE
);

-- Group membership (many-to-many)
CREATE TABLE user_group_members (
                                    user_id INT,
                                    group_id INT,
                                    PRIMARY KEY (user_id, group_id),
                                    FOREIGN KEY (user_id) REFERENCES users(id)
                                        ON DELETE CASCADE
                                        ON UPDATE CASCADE,
                                    FOREIGN KEY (group_id) REFERENCES user_groups(id)
                                        ON DELETE CASCADE
                                        ON UPDATE CASCADE
);

-- Projects table
CREATE TABLE projects (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          title VARCHAR(255) NOT NULL,
                          description TEXT,
                          repo_link VARCHAR(255),
                          group_id INT,
                          status ENUM('PENDING', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED') DEFAULT 'PENDING',
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          FOREIGN KEY (group_id) REFERENCES user_groups(id)
                              ON DELETE SET NULL
                              ON UPDATE CASCADE
);
